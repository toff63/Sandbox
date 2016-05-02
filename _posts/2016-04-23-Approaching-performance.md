---
comments: true
date: 2016-04-23 
layout: post
title: "Approaching performance"
img: http://www.fotografteknikleri.com/wp-content/uploads/2012/11/2411.2012-Suha-Derbent-2-420x215.jpg
---


Performance is a critical part of a system as it impacts the user experience. It is often implicit in the requirement. However client, product owner and tester expect the system to be responsive. The problem though never actually appears in the requirement and when a performance issue strikes, people have very little time to tackle it.

## Understanding the problem

This is the very first step. You need to talk with people complaining about performance to understand their expectations and since when it is happenning. You should ask questions like:

* When did it start happening?
* What are the symptoms?
* What recently changed?

and any other question that can help you understand and reproduce the issue.

## Collecting facts

### Workload characterization method

This methods force you to ask the right questions which can help you to understand what is happenning.

1. Who is causing the workload? PID, UID, IP, ...
2. Why is the load called? Code path, stack trace, ...
3. What is the load? IOPS, tps, r/w, ...
4. How is the load changing over time?

### The Utilization Saturation and Error Method: [USE Method](http://www.brendangregg.com/usemethod.html)

For Every Resource, check _utilization_, _saturation_ and _errors_

If you work with linux you can try the following commands which are described in Netflix post: [Linux Performance Analysis in 60,000 Milliseconds ](http://techblog.netflix.com/2015/11/linux-performance-analysis-in-60s.html)

<script src="https://gist.github.com/toff63/32f9d322c03425146daa.js"></script>

Every part of the Linux kernel has a tool to collect its performance.

![Linux Obervability Tools](http://www.brendangregg.com/Perf/linux_observability_tools.png)

## CPU Analysis

In order to understand what your CPU is doing you can find the process using a tool like top. However, to find out what your process is consuming so much CPU, you need some other tools. If you are working on linux, you can use the Flame Graph which will show you what your CPU is executing and for how much time

<script src="https://gist.github.com/toff63/9487f030802d85b6e535.js"></script>

Running perf view on top of a Play simple application:

![flame grap](/static/images/perf3.svg)

Another tool which work on Linux and Windows is [PerfView](https://channel9.msdn.com/Series/PerfView-Tutorial) which hide OS calls and focus on the part of your code being executed.

## My CPU is low and the system is slow

You may want to do an Off-CPU analysis to check if your threads are waiting for IO, waiting to acquire some lock or waiting for work.

![Off-CPU analysis](http://image.slidesharecdn.com/velocity2015linuxperftools-150527215912-lva1-app6891/95/velocity-2015-linux-perf-tools-19-638.jpg?cb=1439009710)

## Learn more

I strongly recommend to check [Brendan D. Gregg blog about performance](http://www.brendangregg.com/index.html) to have a dive into performance analysis techniques.
