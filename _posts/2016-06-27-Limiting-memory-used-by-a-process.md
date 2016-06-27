---
comments: true
date: 2016-06-27
layout: post
title: "Limiting memory used by a process"
img: http://img.scoop.it/Et2pODbvNM3T-pHBjyhoVDl72eJkfbmt4t8yenImKBVvK0kTmF0xjctABnaLJIm9
---

In modern distributed architecture, baking images (Docker, AMI, Google Compute Engine image, ...) is common place. Each image should run only one main process and some processes for observability like sensu and collectd. However, when memory starts becoming a point of contention, you don't want to loose the main process. Hence you want to guarantee this main process will have enough memory space to run and other processes will use a limited set of ressources. In order to do that, you can configure Linux to limit the amount of memory used by groups of processes.

### Limiting memory using ulimit

Linux kernel let you configure limitation per user using the command [ulimit](https://docs.oracle.com/cd/E19683-01/816-0210/6m6nb7mo3/index.html). This is the old way of doing it and has several limitations that are explained in this blog post: [Limiting time and memory consumption of a program in Linux](http://coldattic.info/shvedsky/pro/blogs/a-foo-walks-into-a-bar/posts/40). However, the limitation it provides may be suficient for your scenario. You can check the limits per user and per process as followed:

<script src="https://gist.github.com/toff63/ba66e9dba56b04676362d85ff35c5f56.js"></script>

In order to limit the amount of memory, you want to change the value shown as `max memory size` in `ulimit -a` and `Max address space` in the process limits. By default it is unlimited. To change this you need to either run the command ulimit, which will take effect until your reboot, or edit the file [/etc/security/limits.conf](http://linux.die.net/man/5/limits.conf). In this file, you can add a lines like the following to set the limit of memory used by user telemetry to `100*1024=102400 kb` (100M)

<script src="https://gist.github.com/toff63/95b9ec2266c79659686e18b82b6b8ac4.js"></script>

If telemetry tries to use more than 100M of memory, the process trying to allocate the memory will be killed by the kernel.

### Limiting memory using cgroup

[Cgroup](https://www.kernel.org/doc/Documentation/cgroup-v1/cgroups.txt) is a kernel feature which is not really new (introduced in the kernel by Google in 2006) but is being more and more used. It lets you restrain the amount of ressources processes can use. One of the ressource you can limit is the physical memory a group can access. Cgroups let you limit other kind of ressources like the bandwidth used to access disk but this goes beyond the scope of this post. CentOS 7 uses Systemd which already organizes processes into control groups. You can see the different groups by running

```
systemd-cgls
```

<script src="https://gist.github.com/toff63/e2d0f734784886dc3cb316bed58b87f0.js"></script>

As you can see, cgroups are already at the heart of CentOS 7 ressource management. Let's limit collectd memory to 100M. First let's install collectd

```
sudo yum install epel-release
sudo yum install collectd
sudo service collectd start
```

Listing the cgroups again (`systemd-cgls`) will show a new cgroup created for collectd under the group: `system.slice`

```
└─system.slice
  ├─collectd.service
    │ └─30008 /usr/sbin/collectd -C /etc/collectd.conf -f
```

Let's now limit the memory it can uses to 100M.

```
sudo systemctl set-property collectd MemoryLimit=100M
systemctl show -p MemoryLimit collectd
# MemoryLimit=104857600
```

We can see now the collectd service limited to the use of 100M. Let see what happen if a service goes above its limit. To do so, let's change the limit to 1 kilbytes.

```
sudo systemctl set-property collectd MemoryLimit=1K
systemctl show -p MemoryLimit collectd
# MemoryLimit=1024
```

Collectd stopped working and we can check the details in `/var/log/messages`: 

<script src="https://gist.github.com/toff63/26197b26e8a78e152b7e995694642110.js"></script>

As you can see, kernel invoked oom-killer on top of the collectd cgroup. As collectd was the only process running in this cgroup it killed it: `Memory cgroup out of memory: Kill process 30227 (collectd) score 1 or sacrifice child`. In this example collectd isn't managing to restart as the memory limit is lower than what it needs to starts. However in a real world example systemd will automatically re-spawn the oom-killed service which will start from scratch using less memory than when it crashed.
