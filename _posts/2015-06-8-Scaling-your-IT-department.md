---
comments: true
date: 2015-06-08 
layout: post
title: "Scaling your IT Department"
img: /static/images/scaling-it.png
---

Scaling Agile is a recurrent subject in the literature. The problem is that people just consider scaling at the organization level forgetting about the *Conway's Law*: 

>"organizations which design systems ... are constrained to produce designs which are copies of the communication structures of these organizations"

## At the beginning ...

When the project starts, we usually have a *small team* doing their best to *produce a software* that will make money. As they seek agility, it is pretty common having them following a mix of scrum and Extreme Programming. When the *product* starts *growing*, the *team* needs to *grow* in order to deliver *more features* at a higher *speed*. As the project started with *one team* mainly focusing on delivering, we usually have a *minimal design* with a *small monolith*. A small monolith is *harmless* and is actually a *good solution*. It has the advantage of being *very simple* and provide a high *productivity* for small team. That's why, for me, Martin Fowler's post [Monolith First](http://martinfowler.com/bliki/MonolithFirst.html) makes perfectly sense. Starting with distributed services will add a unnecessary complexity that will slow down delivery for no good reason. If you have one, team, the natural Conway's law consequency is that you will have a monolith.

## Creating more teams

The natural evolution is to define *more teams*. Great, but how do you split the team *responsabilities*? How do they *interact* and share experience, knowledge and lesson learned? How do they *sincronize* for *releases*? The creation of new teams isn't as simple as it looks. In order to have things working smoothly you will need to accept the Conway's Law: *split your small monolith* so each *team* can work *independantly*. The scary part for most companies is that it involves some investments in *architecture*. However, if the first team had some architecture skills, they will have created a monolith with an architecture that *can evolve* into a *distributed architecture*. This is the main topic of the [talk](http://www.slideshare.net/toff63/aws-play-couch-db-scaling-soa-in-the-cloud) I gave at [TDC Floripa](http://www.thedevelopersconference.com.br/tdc/2015/florianopolis/trilha-smart-apis) where I expose how to create a SOA architecture in the cloud with minimum impact on developers productivity.

## All boils down to isolation level

At the end of the day, what will make your IT scale is the level of *isolation* you put into each part of the system. This system is composed of your business, your sale, your IT department, your product and your running environment. Each part of the system impact on the other. Creating *new teams* is just a way to provide enough *isolation* to a group of people so they can *perform better*. What I explained previously is that this level of isolation needs to reflect in the software. That's why *APIs* are now so *common and necessary* to *scale* the company as the software. The *DevOps* movement is growing and *telling* that you should *destroy department* and have *multidisciplinary teams*. It looks scary as it completly change the organization of your company. It basically means that your *teams* are now *responsible* for a part of your company's *business* and are composed of developer, qa, business, sale and ops. The problem is that you still want your developers, BAs, QAs to *share knowledge* and *experience*, even though they are in isolated separated team. [Community of Practice](http://en.wikipedia.org/wiki/Community_of_practice) is a way of managing this. 

## Summary

In other word, creating new teams without adapting your architecture to provide those team the necessary isolation to deploy and evolve separatly will make your IT slower. The same is true when doing the contrary: having a distributed architecture for one team only will create unnecessary complexity that will slow down your IT. The worst of those two extreme is having a distributed architecture and multiple teams that don't refect the organization of your software: every team works in every service. You have the complexity and sincronization problem at the same time which is a good receipe for a very slow and toublesome IT.
