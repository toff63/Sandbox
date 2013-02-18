(ns messaging-hello-world.core
  (:require [immutant.messaging :as msg]
            ))

(defn send-hello-world []
  (msg/publish "queue.work" "hello world"))
