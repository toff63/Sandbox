(ns hello-world.core
  (:use (sandbar stateful-session)))

(defn foo
  "I don't do a whole lot."
  [x]
  (str "Hello, World from node " (System/getProperty "jboss.node.name")))

(defn ring-handler [request]
  "Main ring handler"
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (foo "immutant")
   }
)

(defn echo-ring-handler [request]
  "Main ring handler"
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str request)
   }
)

(defn get-counter []
  (session-get :counter 0))

(defn session-ring-handler [request]
  (let [counter (+ 1 (get-counter))]
    (do (session-put! :counter counter))
        {:status 200
              :headers {"Content-Type" "text/html"}
              :body (str "Counter Value" counter )
              :session {:counter counter}})
)
