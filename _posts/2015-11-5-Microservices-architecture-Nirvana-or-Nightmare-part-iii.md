---
comments: true
date: 2015-11-03 
layout: post
title: "No microservices without ownership"
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

## No microservices without ownership

Having multiple teams working independently is great, but the traditional way of developing software still has some weaknesses that microservices architecture will exacerbate. 

![Hand off](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-21-638.jpg?cb=1443223618)


In most traditional organization, a business analyst gather needs from a client. Based on those needs the business analyst will build a set of requirement attending the client needs. Those requirement will be delivered to developers as a set of user stories or even, sometimes, as a document. The developer will barely have access to the business analyst and almost never to the client. Developpers will develop a solution, deliver it to an environment where QA will test and gives its approval to go in production. Starting production, the responsability is in the operation guys. The main problem is that usually every stage of this process has been handled by a different part of the organization. This isn't one team attending the client need but rather different independant parts. 

The main question here is: Whom belong this feature?
* the client who asked for it and sometimes paid for it?
* the business analyst who specified it?
* the developpers who expressed it into a language computers understand?
* the QA who gave its approval?
* the operation who put and maintain it live in production?

When a problem happen during this process or after this process, usually, every people involved point the finger to another part of the organization, whinning the fault not theirs. 

In traditional environment where there is only a couple of monolith in production, this situation can be tolerable. However when the number of services raise, the number of moving part and failure tend to raise. Fixing problems as fast as possible becomes critical. If nothing change, every department of the company will escalate complains about other departments, not assuming their part of responsability.

![Management 3.0](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-26-638.jpg?cb=1443223618)

The truth is, everyone is responsible. However the lower you are in the downstream, the more you feel you are just inheriting other people problems as you don't have much control on the demand. The recommanded solution is part of a bigger movement named "Management 3.0" that goes far beyond the scope of this post. However. it promotes empowering teams. As the idea of microservices architecture is to have independent teams, you want them to have full control of what they deliver and be empowered. In other words, you though about it, you code it and run it!

