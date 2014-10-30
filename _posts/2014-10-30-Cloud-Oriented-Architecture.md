---
comments: true
date: 2014-10-30
layout: post
title: Building a Cloud oriented architecture
---

The first thing that you need to realize when going to the cloud is that everything is set for automation. The main *automation* that you won't find in a datacenter is the server creation. What you ultimately want, is to pay as low as possible in term of server. Hence you should optimize your architecture to run on the cheapest serve and use autoscaling functionalities, based on server creation automation, to scale. If your application needs high availability, you should also make sure your architecture can smoothly run on various datacenters and region.

## Paradigm shift

### The datacenter paradigm
Before the cloud, companies were buying servers in datacenters. A server was seen as an investment, and not something that you can sell back easily. Consequently, as any investment, it was bought to host various applications and with sufficiently good hardware be a good servers for several years. It implies very big servers with serveral dozens of cores and RAM. In those conditions, a good architecture needed to make the best of the cores with *monolithic* applications. You would naturally avoid distribution as it makes things harder and you would have a hard time to get your company buy a lot of servers. The natural cost of this situation is that every application the server hosts is competing for the server resources. Hence if you have several CPU bounded applications running on the same server, you can very quickly run into some performance issues. Another issue is in case of hardware failure or server manutention: it will affect all applications running on the server.

### The cloud paradigm
Cloud provider don't let you buy server, they only rant them to you. Consequently, servers aren't investments on the long term anymore. You don't need to worry about what you will do with the server if you have nothing to run on it. Now, the company wants applications to run on the cheapest server possible. In order to handle higher loads they just want to spawn new instances and kill them when the application load is back to normal. This is the *elastic* property of the  [Reactive Manifesto](http://reactivemanifesto.org). The challenge is now different. Architecture that run on several server are just more complicated by nature. Even though failure were possible in the monolithic model, they happen a lot more in the distributed model. They also are a lot more complicated to handle as you can have part of the system running while the rest has just crashed. You now have to work with latencies issues which very quickly lead to consistency issues. However, there are a couple of things that can simplify the situation.

## Getting elastic

The key here is to have an application that is *stateless*, in other words, your application nodes share nothing. Shared state is your worst ennemy in this situation. This must be your mindset and the mindset of your developper. If you are building a web application for example, you need to work as if session doesn't exist. Frameworks like [Play!](http://playframework.org) are based on this idea. You might wonder how I manage entitlement in this situation. Well, I set a cookie into the user browser with a generated id. This id is kept in Redis cluster. This is the only shared information, and I use a database to share it between my nodes.

## Limiting ressources use

This must be one of your goal: make the best use of your server and remove any processing that could be handled in a cheaper way. An example is the delivery of static content by a web server. Usually, web servers generate web pages and deliver static content like javascripts, css and images. This delivery is costing in term of ressources (cpu, memory, bandwidth). It is best to use a [CDN](http://en.wikipedia.org/wiki/Content_delivery_network) service for that like CloudFront in Amazon. Doing, so will make you pay a cloud service that is usually very cheap and remove some burdun on your server which will make you save some money.

## High Availability 

Getting elastic will definitly help but it won't be enough if part of your cloud provider is down or under maintenance. To cope with this scenario, you need to have your application deployed in *serveral datacenters* and ultimately in several region. As your application node don't share any state, it should be fine. However, you probably persist your application data in some database. You need to care about your *data syncronization*.. Each database will have its solution. In my case, I chose to use Couchdb for several other reasons. However, it has a very good synchronization process that keep all master nodes in sync. The drawback is that it doesn't handle merge automatically. The solution is to have in each datacenter, one master for writing and several read only nodes. All read only node are synchronizing with the master node of their datacenter. Each master node are synchronizing between themselves. By default the load balancer is configured to always use one datacenter, but if it becomes unavailable, it will start using the fallback datacenter. Doing so, at any point in time, the system in writing in only one master node and all couchdb nodes are in sync.

In Amazon, you also have the possibility to use the RDS service with multizone service, however, this is a quite expensive solution
