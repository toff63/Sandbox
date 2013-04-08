---
comments: true
date: 2013-03-30 
layout: post
title: What is software architecture
---

There are many definition on the Internet, and no real consencus on this matter. At the end of the day, it is a peace of software. However it has some criteria that make it different from the rest of the software. It needs to abstract complexity, attends non functional business needs and evolve with technologies without impacting the development team. If it fails in attending one of those criteria, the project will most probably fail.

##Non funcional requirements

Non functional requirements are usually not well defined. They are usually expected but not said. You are expecting to have searches performed in less than a second. You are expecting things to be fast enough, you don't need to "wait". The application should be responsive. All those requriements seem natural. However, not always easy to achieve: twitter looks like simple web pages. Nevertheless, to deliver its service to million of users, it has a quiete complex architecture. As an architect, you need to identified those non functional requirement at the very beginning of the project. Based on the context (quantity of data, time, number of requests, read/write operation frequency ...) you will need to figure out what is the best type of architecutre to implement. You cannot fail on this! Migrating architecutre from one type to another is almost impossible once the project started. However, you can have more than one type of architecture in your project to attend different needs. 

##Dealing with complexity

It doesn't mean that development won't have any technical challenge. However they should be focusing on business feature as much as possible. Archtitecture should be dealing with distribution issues. It should be transparent to development team that their peace of code is running in a cluster or on their desktop. Dealing with serialization issues, network failure, distribution and other technical complexity linked to non functional requirements should be transparent to the development team. Failing on this is taking a huge risk as the development team might not be technically prepared to handle this kind of complexity. It usually take years for an architect to get the adequate techincal skill to properly deal with this. The risk is to have an application that isn't working due to technical issues while business feature are implemented, or having a software crippled of bugs linked to this complexity. 

##Architecture evolution

Architecutre will be developped before development team start working on the project. Once the developent team start using the architecture, evolution cannot break the software, neither consume too much time for developper team to migrate from one architecture version to another. However, architecture isn't frozen and needs to evolve to attend future needs and be up-to-date with new technologies otherwise the whole application might not keep up with concurrent products. Failing on this means having to re-build the application based on a branding new architecture. This is an extremly expensive solution that can break the company. 


##Conclusion
Architecture success is critical for the success of the project. That's why software architect always need to be discussing with developpers to validate that complexity is well handled, as well as discussing with infrastructure people to run some stress tests and be sure that non functional requirements are attended.
