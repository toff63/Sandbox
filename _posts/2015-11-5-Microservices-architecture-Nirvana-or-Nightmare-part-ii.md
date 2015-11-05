---
comments: true
date: 2015-11-03 
layout: post
title: "Scaling your business"
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

## Scaling your business

[David Heinemeier Hansson](http://david.heinemeierhansson.com/) a.k.a DHH make it clear that he doesn't like Microservices Architecture in his openning [talk](https://www.youtube.com/watch?v=KJVTM7mE1Cc) at Rails Conf. He thinks this is an over complex architecture compared to the problem it pretends to solve. However, it is important to understand the environment in which DHH works. He is founder & CTO at Basecamp (former 37signals). This company is different from most companies I know. It is a very successful company as its product Basecamp has been used by more 15 million users. Beside the fact that the company is more than 16 years old, it is still a team of [45 programmers, designers, supporters, and sysops](http://david.heinemeierhansson.com/#basecamp). This company isn't bigger in term of employees because they [want to stay small](https://37signals.com/05.html). This is quiete different from most of the companies I am working for. 37signals values are also very different from what I am used to: ASAP is poison, Meetings are toxic, Planning is guessing detailed in [REWORK](http://37signals.com/rework/). In the context of a small company that aims to stay small, I agree that Microservices Architecture is over complex and doesn't make much sense. 

However, those values aren't shared by most of the companies I know. Most companies have growing has a core value. Growing in term of revenue, customers, employees, features, ...

## Microservices Architecture is primarily to scale people

When you decide to scale in term of people, on a management point of view, you will probably start creating teams with one responsible per team. This is common sense, you divide the group into smaller groups to ease the management of the whole. What you would expect is the more employees you have, more feature you deliver, more customer you manage to attend. Problems happen if you don't also split the code base teams are working on. In this case changes made by one team will affect teams. That's why they need to schedule meetings to agree on how each part of the code base will interact. This coupling at the software level create a huge time overhead managing changes into the code base. The bigger the code base, the worst it gets. At some point, adding people just slows the release process.

### In one of my previous project.

![Previous project teams](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-16-638.jpg?cb=1443223618)


We were using a SOA architecture, and teams were created. They weren't working on the same code base. There was:
* one team for the UI
* one team for the services
* one team for the data layer

Even though teams aren't working on the same code base, they have inter-dependencies: UI cannot release a feature until services have release their feature. As most features depend on data stored in the data layer, services weren't able to release until data layer team release. It forces teams to synchronize feature, create stubs to start developping before the other team is done developing its part. At the end of the day, your release frequency is bounded by the slowest team. As we still have the need to synchronize every team, we still have meeting overheads which slows down the development pipeline.

### Scaling teams

![Ideal project teams](http://image.slidesharecdn.com/microservicesarchitecture-nirvanaornightmare-150925232452-lva1-app6891/95/microservices-architecture-nirvana-or-nightmare-18-638.jpg?cb=1443223618)

That's why microservices architecture advocate for:
* each service service has its own ui and its own database
* each ui, service, database belongs to one team and one team only

With this seperation in place you guarantee every team will have independant release cycles. With small release cycle, you are giving power to business to experiment things. They don't need to survey the market to discover what feature it needs. They can put a new feature or enhance an existing one and observe the results right away. They can even deliver feature with different flavours to observe which one is better using client feedback. This feedback can come directly from interviewing with clients as by observing how clients are using the product. Short release cycle is key to power your business and give it the agility the market require.

