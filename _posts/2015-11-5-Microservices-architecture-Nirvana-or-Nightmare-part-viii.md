---
comments: true
date: 2015-11-03 
layout: post
title: "Existing microservices stacks"
img: http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-1-638.jpg?cb=1443223618
---

_This is part of the transcript of a talk I gave at The Developer Conference in Porto Alegre. You can find the [deck online](http://www.slideshare.net/toff63/microservices-architecture-nirvana-or-nightmare)_


I broke the transcript in several part that can be read independantly:

* [Part 1: SOA Services in 5 minutes](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-i.html)
* [Part 2: Scaling your business](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-ii.html)
* [Part 3: No microservices without ownership](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-iii.html)
* [Part 4: Data architecture for microservices](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-iv.html)
* [Part 5: Operability in  microservices world](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-v.html)
* [Part 6: Anti-fragile system](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-vi.html)
* [Part 7: Agile coaching for successful microservices system](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-vii.html)
* [Part 8: Existing microservices stacks](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-viii.html)

<br>

## Existing microservices stacks

A micro service looks like the following. It has all the component to be operable and manageable.

![Full picture](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-73-638.jpg?cb=1443223618)

This is a lot to create and automation will just free time to work on more important things. I don't know any easy simple stack. Complexity is inherent to what we want to achieve: an agile business that can scale in developer and features.

I am aware of three stacks: 

* twitter stack based on finagle
* Netflix stack (spring cloud is using it intensively)
* Akka

I am currently using Netflix stack and had some experience with Akka. None of those stacks are easy and well documented. They both require a lot of study and work. If you start using one of them, work as a hacker, reading the component codes and tests to figure out what it does and how to use it.

