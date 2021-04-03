---
comments: true
date: 2015-11-30 
layout: post
title: "Developer responsability in bad IT management"
img: /static/images/Developer-responsability-in-bad-IT-management.jpg
---

## What do I call bad IT management

You know your IT management is bad when business is unhappy, customer aren't very happy and your IT department isn't happy neither (you can measure it by your IT turn over). This is quite easy to identify. If you start looking at how things work, you will usually find the following patterns: business comes with _challenging_ deadlines, product owner giving advices on how to implement the feature, and IT working late almost all year long. Developer feels they have no freedom and have no power to change anything. PO aren't really owning the product, they usualy just are proxy of business department and are doing their best to make business and developer happy. Business aren't satisfied with their IT performance and start guessing features so they day it finally get released, maybe they attend their new customer needs.

## A complex problem where everybody has its responsability

This is hard to get IT management right, and I personnaly don't have any magic receipe. Just some principles that can help finding the right balance between business needs and IT stability. You will notice I am not mentionning happiness, as it should come as a result. When everybody feels it is doing its job well and isn't failing due to someone else decision, people tend to feel pride :)

## Developer role

As explained in a [previous post](http://francesbagual.net/2015/11/30/Thoughts-about-chaos-engineering.html) IT tend to be in a vicious circle where unplanned work grows and planned work never get released as planned. Part of unplanned work comes from unexpected bug discovered in production. The problem starts by how developers comunicate with POs and management. They will inform management they are creating tests. The truth is, very few people understand how much money you are saving by doing tests:

* developer don't like writing test because bad design make it complex and the fun is seen in the feature, not the test
* managers see this as a place where time is being wasted and something they could drop to achieve deadlines
* POs don't see much value as they will manually test the feature to validate the user story.

Everybody has an opinion on test. Mine is, more level of test you have, less manual test you will need and less bug you will find in production. This is part of the user story implementation work. While all level of tests aren't implemented, the user story isn't done, no need to describe what you are doing. Your garagist won't give you all the details of all check he has done before telling you can pick up the car. It is part of his job to make sure your car work before you pick it up. This is the same with test.

## Someone will take the technical lead

If there isn't a technical leader in the team, someone will act as one. Typically the one who as most interest in influencing the team. Some times you will find the PO giving hints on how you could implement the user story. This is common as most PO are former developer and knows enough to give hints. However, they will tend to advise cutting corner to deliver as soon as possible. At the end of the day, developers will be responsible for maintaining the mess. Make sure every class has only one responsability. Study software design and make things as simples as possible.

## Wrapping up

By accepting to cut too many corners and not forcing basic engineering practices coming from [XP](https://en.wikipedia.org/wiki/Extreme_programming) developers are complice of issues they face. The hard truth is it takes courage and effort to implement a user story properly:

* making sure it integrates into the current software design
* adapt the current design if it doesn't find its place in something existing
* make sure every class only does one thing. Better having a class doing to few things than too many.
* implementing at least 3 levels of tests: unit tests for complex logic, in memory functional tests and functional tests on deployed software
* implement test that tries to break the code
* doing pair design review
* doing pair code review
* understand and refine requirements with PO. Never assume anything, ask, ask and ask. Never implement something you don't understand or look useless! Less is more. Requirement are someone's decision and nothing else.
* improve the current design to better support business

This is hard and the pressure is high for developer to just accept requirements they don't understand, avoid software design, do the minimum set of test, dismiss reviews and just execute what has been planned. Nevertheless, they cannot expect managers and POs to tell them how they should do the work.

