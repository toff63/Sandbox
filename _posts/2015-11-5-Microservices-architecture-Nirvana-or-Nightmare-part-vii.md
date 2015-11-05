---
comments: true
date: 2015-11-03 
layout: post
title: "Agile coaching for successful microservices system"
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

##Agile coaching for successful microservices system

Thinking that all those changes and complexity can be hidden from your developers is a foolish idea.

![XP](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-67-638.jpg?cb=1443223618)

XP provides you what you need to develop very good software in term of business with a high technical quality. However, in a such complex system, it isn't enough! At the same time, going into micro service architecutre without having the values that comes with XP is highly risky and I wouldn't recommend it! Each micro-service needs to have its own architecture that supports its need. That's why you will need agile teams with high technical skills, because beyond the system architecture, every part of the business will have specific characteristics that the service architecture will need to attend. In this architecture, teams get more responsibility. With responsability comes more guarantees every team need to provide. The best way to guarantee service will work smoothly is to provide:

* Unit tests
* functional tests
* Contract tests
* Backward compatibility tests (never break and existing consumer)
* Forward compatibility tests (make sure your old code version works with a newer database version)
* Stress tests
* Chaos tests

![Practice](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-70-638.jpg?cb=1443223618)

As the team is now responsible for keeping the code running, they will need to practice incident handling. A good way to train that is by doing incident drills during the working hours. Observe how the team behave, find improvments and implement them. Doing this kind of thing again and again will train the team and incident handling will be done smoothly at any hour of the day. At the same time, developer will try to write code that leaves very few holes for incident. What you want, is arriving in the morning at works and find out a problem occured at 2am, and the system got back to work without any human intervention. The learning curve is big and challenging. That's why you want to implement a culture of learning, everybody is studying something and teaching, sharing. In order to master something you need to teach it. In order to spread a good practice that is good for the company, you need to share it.

