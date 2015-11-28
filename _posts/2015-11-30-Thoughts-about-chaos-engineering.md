---
comments: true
date: 2015-11-30 
layout: post
title: "Thoughts about chaos engineering"
img: http://cdn.ebaumsworld.com/mediaFiles/picture/2281958/82817883.png
---

## A Naming issue

The first time I heard of chaos engineering, it was through [Netflix blog](http://techblog.netflix.com/2014/09/introducing-chaos-engineering.html) one year ago. The idea and branding is awesome for geek like me. I love messing around with technologies, however it is frightening for managers and CTOs. Let's be honnest, most IT are yet chaotic enough without willing to add choas to this. Hence, why your IT should even think about investing into chaos engineering? Netflix started talking about it and now Linkedin open source a [failure inducer framework](https://github.com/linkedin/simoorg) and shopify talks about its [own monkeys](http://highscalability.com/blog/2015/11/2/how-shopify-scales-to-handle-flash-sales-from-kanye-west-and.html). Managers tend to say this isn't for them because they aren't Netflix or any other unicorn. IMHO this is the response of someone who has very little time and who fears chaos.

## IT vicious circle

As very well described in [The Phoenix Project](http://itrevolution.com/books/phoenix-project-devops-book/), there are 3 types of work in IT:

* Planned work (demands that comes from business and support the company business goals)
* Maintenance (everything aiming to keep things working smoothly)
* Unplanned work (anything that force you to stop what you are doing to attend it: bugs, outages, production issues, resiliency issues)

Most companies aren't monitoring how much they are spending per type of work. This is sad because otherwise they will notice a vicious circle most companies are in. Due to market constant changes, business pressures to get new features and product as fast as possible. As a consequence, IT cut corners to attend them with the famous: we will clean that mess after the storm. The problem is business always has something urgent to get through IT pipeline, so the storm is always followed by another storm. Cutting corner make the system less and less stable. You can observe this watching expenses in unplanned work growing. As unplanned work take place of planned work, business pressure grows and no time is dedicated to maintenance. This is like a truck that never stop to change its oil. At some point the engine will stop. In case of IT, it doesn't stop, it only keep going slower and slower. The cold blood decision to take is to invest in maintenance to get unplanned work as low as possible in order to have predictability on the planned work. Obviously, the time spent on maintenance won't be on business needs.

## Keeping chaos under control

IT is complex and can look chaotic from external eyes. However, it is actually a complex system needing to be tested constantly to stay predictable. When I hear a CEO explaining me he as availability issues, I automatically understand resiliancy mechanisms in place aren't enough. Truth is, resiliancy mechanism are almost never tested. Most of the time, the resiliancy mechanism in place is to call the support team. In order to limit unplanned work, you need to identify and periodically test your resiliency mechanisms. The idea is simple, you can start by every month, simulating during the day an incident, observe how the resiliency mechanism respond and look for improvement. If the incident reaches your support team, you will call it, incident drill. The hard truth is you learn better how to solve an issue at 14h in the afternoon with your colleagues awaken than alone at 3 in the morning. You can identify which operation are being done and create scripts to perform them. This way you limit potential mistakes done on real incident at night. 

## Every IT should at least have incident drills

More and more architects think about their system resiliency. However they almost never test it. Chaos engineering is being re-branded as Resiliency engineering which in my view is a more manager friendly name. The sooner you find the holes in your resiliency mechanisms the better you are prepared to takle them and keep your unplanned work as low as possible. 
