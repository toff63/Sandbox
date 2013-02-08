(ns hello-world.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (str "Hello, World from " x))

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
