---
comments: true
date: 2015-11-03 
layout: post
title: "Microservices architecture: Nirvana or Nightmare?"
img: http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-1-638.jpg?cb=1443223618
---

_This part of the transcript of a talk I gave at The Developer Conference in Porto Alegre. You can find the [deck online](http://www.slideshare.net/toff63/microservices-architecture-nirvana-or-nightmare)_

I broke the transcript in several part that can be read independantly:

* [Part 1: SOA Services in 5 minutes]({{ page.url }})
* [Part 2: Scaling your business](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-ii.html)
* [Part 3: No microservices without ownership](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-iii.html)
* [Part 4: Data architecture for microservices](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-iv.html)
* [Part 5: Operability in  microservices world](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-v.html)
* [Part 6: Anti-fragile system](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-vi.html)
* [Part 7: Agile coaching for successful microservices system](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-vii.html)
* [Part 8: Existing microservices stacks](/2015/11/03/Microservices-architecture-Nirvana-or-Nightmare-part-viii.html)

<br>

## SOA Services in 5 minutes

Even thoug SOA has existed for years, there are still some misconceptions about what a SOA service looks like. 

### Just another flavor of SOA

Microservices architecture is just a flavor of SOA. A Service Oriented Architecutre is an architecture that respect the principles exposed in the SOA Manifesto. It means that it is an organizational architecture, and not something you do for a project. By being organizational, it becomes strategic for the company as it open new way of selling business value, organizing teams and growing. One point that Microservices exacerbate is the __flexibility__. By having very small services, you start having services that can just act as composable functions which is very powerful. It also gives you a lot of flexibility in how you want to release and charge your product as every part of it is independant and can be charged seperatly. 

### SOA isn't Web Service

This is a misconception I still find in some of my clients. They usually are very disappointed with SOA. They complain that when they change something in a  "service" they usually have to update and re-deploy other services. It means that they are creating web services and not SOA services. SOA services promote loose coupling, which means that every SOA service guarantee they won't break their consumer. If they need to implement a feature that will break consumers, they create a new version and give a certain amount of time to consumers to migrate to the new version. This loose coupling is critical, without it, creating services mainly add complexity to the system with very few benefits.

## Contract Versionning

![Contract Versionning slide](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-7-638.jpg?cb=1443223618)

A SOA service will tipically have several versions of it with consumer using each of their version. It is important that a governance state the maximum number of version a service needs to support, otherwise it can quickly a maintenance nightmare. In order to guarantee that your new feature isn't breaking your existing consumers, you need to have contract tests. The contract is just a set of pojo with a trait. If you see that your new feature breaks an exisiting contract test, it most likely mean that you need to create a new version of the contract.

## SOA Service design

![Service anatomy slide](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-8-638.jpg?cb=1443223618)

This is the design I usually recommand when creating a service. The contract contains the trait, the supported transports (HTTP annotations for example), the input/output definition, the constraints on those input/output to avoid malformed input. This contract __cannot__ depend on the service implementation. You want to let other services import this contract if they want to without getting the service implementation in their classpath. The implememtation depends on the contract. In theory you should be able to have several service implementations for one contract. The anti corruption layer guarantee that no business object appear in the contract. You don't want that a change in a table mapping automatically change the contract. You need to isolate each part of the service to promote decoupling and avoid unexpected side effects. Your service should always implement the latest version of the contract. The Backward Compatiblity layer will convert the contract of previous supported versions into the latest version. Below that you will have your domain object with its domain logic and gateway to call other services and datasources. However, you don't want those datasources polluting your business model, that why you also have converters here.


I am done with the SOA service technical consideration. Now let's jump into microservices architecture. However, before that, it is important to remember that it isn't the only architecture out there, and not necessarly the best for your company.
