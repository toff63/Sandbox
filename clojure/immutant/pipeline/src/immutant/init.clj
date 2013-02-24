(ns immutant.init
  (:use pipeline.core)
  
  (:require [immutant.messaging :as messaging]
            [immutant.pipeline :as pl]
            [immutant.jobs :as jobs])
  )

(defonce cuervo-trip 
  (pl/pipeline "cuervo trip"
               check-in-poa
               (pl/step facebook-comment :concurrency 5)
               check-in-campinas
               (pl/step facebook-comment :concurrency 5)
               cocina
               :concurrency 1))

(jobs/schedule "my-job-name" "* */1 * * * ?"
               #(deref (cuervo-trip "POA") 10000 :timeout!)
               :singleton true)
;; This file will be loaded when the application is deployed to Immutant, and
;; can be used to start services your app needs. Examples:


;; Web endpoints need a context-path and ring handler function. The context
;; path given here is a sub-path to the global context-path for the app
;; if any.

; (web/start "/" my-ring-handler)
; (web/start "/foo" a-different-ring-handler)

;; To start a Noir app:
; (server/load-views (util/app-relative "src/pipeline/views"))
; (web/start "/" (server/gen-handler {:mode :dev :ns 'pipeline}))


;; Messaging allows for starting (and stopping) destinations (queues & topics)
;; and listening for messages on a destination.

; (messaging/start "/queue/a-queue")
; (messaging/listen "/queue/a-queue" #(println "received: " %))

