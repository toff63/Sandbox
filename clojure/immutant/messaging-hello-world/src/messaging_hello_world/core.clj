(ns messaging-hello-world.core
  (:require [immutant.messaging :as msg])
  (:use [clojure.tools.logging :only (info)]))


(defn hello [name]
  (info "Hello " name))

(defn my-ring-handler-queue [request]
  (msg/publish "queue.work" "world")
  {:status 200})

(defn my-ring-handler-topic [request]
  (msg/publish "topic.news" "world")
  {:status 200})


