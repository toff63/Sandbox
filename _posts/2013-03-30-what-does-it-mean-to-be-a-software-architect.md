---
comments: true
date: 2013-03-30 
layout: blog
title: What does it mean to be a software architect
---

#What does it mean to be a software architect?

Written 30-03-2013

This is the first question I had to answer starting this quest: becoming a software architect. I started reading the book [Software Architect Bootcamp](http://www.goodreads.com/book/show/2827735-software-architect-bootcam "Software Architect Bootcamp") to be able to answer this question. What follows is my own understanding and I would recommand to read books and articles about it as there is no clear definition.

##Creates software architectures

This is the most obvious answer :) However, in order to create architectures you need to understand what is an architecture. A very quick answer, as I will have to dedicate a full post about it. It is a peace of software that attend business needs managing complexity and enabling software evolution. In other words, your architecture should meet the non functional requirements and developpers shouldn't have to focus too much on very complex technical challenges. The architecture should meet your company business needs, which means that if you fail in creating the proper architecture, the company will most probably fail. It also means that your architecture should be good enough to have its components changed as technology evolve. 

##Software requirements

As the architecture will have to meet the business goals of your company, the architect must understand the business requirements and challenges they imply. In order to know requirements, the architect will have to ask the right questions. The one could have big influence on your architecture. For example, the read/write proportions can completly change the architecture. Asynchronous requirements will have to be detected. This is the kind of requirement that will never be said like that by the business. In order to have the best vision of the requirement, architects use technics that force them to see the problem from various point of view. The 4+1 Architecture is an example. You cannot discover that you will have to provide some crazy feature that won't fit into your current architecutre while the software is being developped. 

##Technical leader

The architecture should help developpers to focus on business needs and not complex issues like distribution of the code inside a cluster. The architect should always be with developpers as the architecture has been made for them. The architect is there to help developpers in their work. In order to do so, he will review developper code and *teach* technics that will improve code quality. This is an important role of the architect. He will spend a lot of time teaching and mentoring developpers so they can produce the best product in term of feature and in term of quality. The architect is responsible for the overall product quality. He cannot do the whole product alone, that's why he need to be constantly among developpers pushing them toward technical excellence. 
