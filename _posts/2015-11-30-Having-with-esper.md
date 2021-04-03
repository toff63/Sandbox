---
comments: true
date: 2015-11-30 
layout: post
title: "Having fun with Esper"
img: /static/images/esper-alert.png
---

## What is Esper

[Eserper](http://www.espertech.com/esper/) is a library that helps you work with [Complex Event Processing](https://en.wikipedia.org/wiki/Complex_event_processing)(CEP). The basic idea is anlyzing a constant flux of event and discover patterns. This is different from [Stream Processing](https://en.wikipedia.org/wiki/Stream_processing) which will aggregate, filter and transform a flux of event. You usually use CEP when you want to take action when a certain pattern appear in the events.

## Querying your stream

Esper provides the Esper Query Language which is very close to SQL. This is nice as it diminishes the learning curve, however, it hides the fact you have to think about your problem differently. When you query a database using SQL, the database simulate data isn't changing during the SQL execution. When using Esper, data being queried will constantly change. That's why you need to think about your problem differently.

## How to think about your problem

You need to think of your stream of data as a river with various kind of fishes. What you want is find if certain pattern appear among those fishes. In order to do that, you will need want to isolate the type of fishes you are interested up to the point where it is easy to express the pattern you are looking for as a simple SQL.

## Example

Imagine you have a stream of data coming from temperature sensors spread accross your home. When the temperature get below 22 degrees in both the hall and the bedroom, you want to display a warning. When the temperature in those places get below 18 you want to display an alert.

### Let's isolate the places we are interested in

In order to do that we will start creating named windows only containing temperature of hall and bedroom.

<script src="https://gist.github.com/toff63/1cf24a89fc1b46f424cb.js"></script>

### Let's deduce the status of each places

<script src="https://gist.github.com/toff63/bbbf2bb20253a4689dfb.js"></script>

### Get the home temperature status

We are almost there. For each room we have the status: ok, warning, alert. Now let's use an SQL to deduce the status of the home:

<script src="https://gist.github.com/toff63/b49cc76f7d235c2e6f96.js"></script>

You can copy and past the full script below into [Esper EPL Online](http://esper-epl-tryout.appspot.com/epltryout/mainform.html) and have fun with it :)

Full script:

<script src="https://gist.github.com/toff63/250bc58f498f53e169a8.js"></script>


Have fun!


