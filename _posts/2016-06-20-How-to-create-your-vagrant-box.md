---
comments: true
date: 2016-06-20 
layout: post
title: "How to create your vagrant box"
img: /static/images/vagrant.png
---

[Vagrant](https://www.vagrantup.com) is a great tool from HashiCorp to test ideas without polluting your computer with software you aren't going to need afterward. This is  also an easy way to test simple cluster setups like an Elasticsearch cluster config. In order to build a cluster, you create a Vagrantfile like this one:
<br><br><br><br><br><br><br><br><br>

<script src="https://gist.github.com/toff63/d11f525632bdcd0c26d88a92d6f3b236/acfa91b68f3b5c2e3c96d2045a6bb608690a5b6c.js"></script>

It defines 5 virtual machines with static IPs. As there are no provisionning defined we will have to connect to each box separatly and repeat the same commands like `sudo apt-get update && sudo apt-get upgrade`. If my `ubuntu/trusty64` box is pretty old, it is going to take some time and I will download 5 times the same packages. In order to circumvent the issue, we can do it once, create a box and use this box instead of `ubuntu/trusty64`. Here is an example of Vagrantfile

<script src="https://gist.github.com/toff63/821d6b11046288e58e4d110e3ac2742d.js"></script>

We run `vagrant up` and let it provision a box for us updating the OS, installing java, elasticsearch and Kibana. Once done I can run the command:

```
vagrant package --output ubuntu-es.box
```

This will create a file ubuntu-es.box that we can now add to our local list of known boxes:

```
vagrant box add --name ubuntu-es ubuntu-es.box
```

We can now update our initial Vagrantfile for the elasticsearch boxes:

<script src="https://gist.github.com/toff63/d11f525632bdcd0c26d88a92d6f3b236.js"></script>

Have fun creating your own boxes!

