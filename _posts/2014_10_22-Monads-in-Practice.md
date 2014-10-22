---
comments: true
date: 2014-10-22
layout: post
title: Monads in Practice
---

I gave a lecture Saturday 18 of October 2014 at [The developer conference](http://thedevelopersconference.com.br/) about Monads and how to create your own. 

<iframe src="//www.slideshare.net/slideshow/embed_code/40441891" width="425" height="355" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" style="border:1px solid #CCC; border-width:1px; margin-bottom:5px; max-width: 100%;" allowfullscreen> </iframe> <div style="margin-bottom:5px"> <strong> <a href="//www.slideshare.net/toff63/monads-in-practice" title="Monads in practice" target="_blank">Monads in practice</a> </strong> from <strong><a href="//www.slideshare.net/toff63" target="_blank">Christophe Marchal</a></strong> </div>

#Disclaimer

I have spent most of my programer life writing code in an Ojbect Oriented way. Even though I have started to study Functional programming a couple of years ago, I still consider myself as a beginner. My talk about Monads might not be very precise and rigourus but I think it gives a good balance with the post which are too rigourus and not enough practical for programmer with an OOP background

# Monad in a Nutshell

This is basically a design technique to encapsulate complexity and provide a better API. The easiest way of understanding that is to realize that you probably yet use structures that looks like a Monad. The most common example is jquery.

Jquery basically hide for the programmer the horrible DOM API. However, it let's you manipulate the DOM through a better API without giving you access to the DOM itself. Instead it always return you an instance of jquery. 

Another example is the Java 8 stream API. It let's you sepcify what you want to do with your collection without giving you access to the collection itself. It also always return a stream for further manipulations.

# When should I use Monads

There are several cases where a Monad fits the problem you are trying to solve. The one that is kind of obvious to me today, is the case where you have a process that will lead you to 2 different states like: Authorized or not Authorized, Failure or Success, Null or Something ....

Each time you have a kind of branching that lead to a limited number of states, I like to start constructed something that can become a Monad. It could become because I am lazy programmer: I will only add map and flatmap if I need to compose out of the structure.


# How to build your Monad

First, create a trait that will represent the concept you want to abstract: Authorization for example. Then create a constructor, typically in an apply funcion of a companion object in scala. Then create one class per output state extending the trait: Authorized and Unauthorized in the case of an authorization.

You have just created the backbone of your monadic strcuture. Then, you just have to start using it and add the needed method to the trait in order compose with other funcion when it makes sense or aggregate with other (case of Validation: you usually have a list of Validation to perform that aren't related).

# Conclusion

As any other design technic, it doesn't solve all the problems: in my presentation I end using the Load pattern to manage the database connection as it makes more sense than creating a monad structure. To be a real monad, your structure needs to have a set of functions. It works perfectly for theory and mathematic, but in my daily job, I prefer creating the things on-demand.
