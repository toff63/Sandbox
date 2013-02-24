(ns immutant.init
  (:use [messaging-hello-world.core]
        [messaging-hello-world.rpc])
  (:require [immutant.messaging :as msg]
            [immutant.web :as web]))

;; This file will be loaded when the application is deployed to Immutant, and
;; can be used to start services your app needs. Examples:


(msg/start  "queue.work")
(msg/listen "queue.work" #(hello %) :concurrency 5)


(msg/start  "topic.news")
(msg/listen "topic.news" #(hello %) :concurrency 5)
(msg/listen "topic.news" #(hello (str "2 " %)) :concurrency 5)

(msg/start   "queue.hello")
(msg/respond "queue.hello" #(hello-service %))


;; Web endpoints need a context-path and ring handler function. The context
;; path given here is a sub-path to the global context-path for the app
;; if any.

(web/start "/queue" my-ring-handler-queue)
(web/start "/topic" my-ring-handler-topic)
(web/start "/rpc" hello-rpc)

; (web/start "/foo" a-different-ring-handler)

;; To start a Noir app:
; (server/load-views (util/app-relative "src/messaging_hello_world/views"))
; (web/start "/" (server/gen-handler {:mode :dev :ns 'messaging-hello-world}))


;; Messaging allows for starting (and stopping) destinations (queues & topics)
;; and listening for messages on a destination.


