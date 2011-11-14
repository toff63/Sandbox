(defproject hello-www "1.0.0-SNAPSHOT"
  :description "A compojure 'Hello World' application"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [compojure "0.6.4"]]
  :dev-dependencies [[lein-ring "0.4.5"]]
  
  ;; specify ring handler
  :ring {:handler hello-www.core/app})


