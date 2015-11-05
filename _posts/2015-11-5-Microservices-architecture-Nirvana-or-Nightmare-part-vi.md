---
comments: true
date: 2015-11-03 
layout: post
title: "Anti-fragile system"
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

## Anti-fragile system

Your system will have good days and bad days. The more you have moving parts, the more you have issues. At some scale, you will always have a problem happenning. It is part of the system and need to be accepted and taken into account from the start. That's why it is important to make the difference between error and failures. 

![Error is not failure](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-58-638.jpg?cb=1443223618)

Developers are used to handle errors: validation errors, exceptions, bad requests, timeout ... However, they will now need to handle failures: server that stops responding, instances going away, network connection having unexpected behaviour, cluster being splitted etc. The bigger your system is, the most expensive failure will be if not handled properly. Datacenter being down will happen, and let's face it, it is better to be prepared. At the end of the day, you want to have the least impact on customer as possible. You might have to degrade the quality of your service temporarly, this is fine. However not letting consumer doing basic things due to some failure might not be acceptable. This needs to be discussed and agreed with business.

![Failure protection](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-63-638.jpg?cb=1443223618)

What fallback strategy should be used. This is really common sense in sport that are very risky like parachuting.

![Fallback](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-64-638.jpg?cb=1443223618)


Designing for anti-fragility isn't enough. Everything might looks good on the paper, but it doesn't work until tested. That's why it is very important to implement chaos testing. This is how you will find most of the scenario you dindn't think about or find some wrong configuration in your system. It is better to handle an incident during working hours than at 3am :) The simian army is a good start.

Thinking that all those changes and complexity can be hidden from your developers is a foolish idea.

