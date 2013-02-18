(ns immutant.init
  (:require [ring.middleware.session :as ring-session]
            [immutant.messaging :as messaging]
            [immutant.web :as web]
            [immutant.util :as util]
            [noir.server :as server]
            [immutant.web.session :as immutant-session])
  )

;; This file will be loaded when the application is deployed to Immutant, and
;; can be used to start services your app needs. Examples:


;; Web endpoints need a context-path and ring handler function. The context
;; path given here is a sub-path to the global context-path for the app
;; if any.

; (web/start "/" my-ring-handler)
; (web/start "/foo" a-different-ring-handler)

;; To start a Noir app:
 (server/load-views (util/app-relative "src/my_noir_webapp/views"))
 (web/start "/" (ring-session/wrap-session 
                  (server/gen-handler {:mode :dev :ns 'my-noir-webapp})
                  {:store (immutant-session/servlet-store)}))


;; Messaging allows for starting (and stopping) destinations (queues & topics)
;; and listening for messages on a destination.

; (messaging/start "/queue/a-queue")
; (messaging/listen "/queue/a-queue" #(println "received: " %))

