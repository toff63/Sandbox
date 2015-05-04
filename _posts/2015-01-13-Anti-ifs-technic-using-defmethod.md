---
comments: true
date: 2015-01-13 
layout: post
title: "Anti-ifs Technic: Using clojure Mutimethod"
img: /static/images/anti-if.png
---
## The problem

The problem with *ifs* is that they *multiply themselves* very quickly and soon the logic become very *hard to understand*. The problem it tries to solve is very simple: depending on some conditions I have to change the execution flow. It is actually the a *routing problem*, so your solution should *communicate* the directions and options code can follow. The *if* primitive is very very low level and *doesn't communicate* well the code intention. That's why it is better to avoid them. I am starting a list of blog posts about technics that will help to express better the routing without using if. This is my way of supporting the [Anti-IF Campaign](http://antiifcampaign.com)


## Clojure Multimethod

A couple of weeks ago, I organized a dojo with the time I was working with to refactor part of our application code with clojure. This was a scala code using pattern matching to decide which calcule it should perfor based on the type of tax. The pattern matching was pretty big and wasn't communicating a lot. During this dojo we discovered clojure [multimethods](http://clojure.org/multimethods). The idea is very simple: you *seperate* the function that will decide which *route* the code will follow from the function that actually perform the *computation*. Our routing function looked like:

{% highlight clojure %}
(fn [product] (product :type))
{% endhighlight %}

Hence we declared a multimethod:

{% highlight clojure %}
(defmulti calculate (fn [product] (product :type)))
{% endhighlight %}

which is equivalent to 

{% highlight clojure %}
(defmulti calculate :type)
{% endhighlight %}

as Val Waeselynck mentionned in the comment.

Then we wrote a function for all cases we wanted to handle: if type is 0 we need to return 0.0:

{% highlight clojure %}
(defmethod calculate 0 [product] 0.0)
{% endhighlight %}

If type is 12 we need to return 1.1:


{% highlight clojure %}
(defmethod calculate 12 [product] 1.1)
{% endhighlight %}

Other cases should throw an exception:

{% highlight clojure %}
(defmethod calculate :default [product]
  (throw (IllegalArgumentException.
            (str "Type " ((product :type) " is unknown"))))
{% endhighlight %}


This code is very easy to *understand*, *concise* and *expressive*. There is very minimal boilerplate code and force the programmer to separate well the routing logic, which can be very simple, from the computations.



<a href="http://www.antiifcampaign.com">
<img height="60" width="120"
src="http://antiifcampaign.com/assets/banner_ive-joined.gif"
alt="I have joined Anti-IF Campaign"></a>
