(ns clog.core
  (:use ring.adapter.jetty
        ring.middleware.resource
        ring.util.response
        net.cgrand.moustache))

(defn index
  [req]
  (response "Welcome, to Clog - A Blog engine written in Clojure"))


;; Routes definition
(def routes
  (app 
    [""] index))

;; start function for starting jetty
(defn start [port]
  (run-jetty #'routes {:port (or port 8080) :join? false}))
