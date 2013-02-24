(ns messaging-hello-world.rpc
  (:require [immutant.messaging :as msg]))

(defn hello-rpc [request]
  (let [result (msg/request "queue.hello" "")]
    (deref result 1000 nil)))

(defn hello-service [request]
  {:status 200
   :headers {"Content-Type" "text/json"}
   :body "{\"Hello world\"}"})
