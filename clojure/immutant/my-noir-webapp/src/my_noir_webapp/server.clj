(ns my-noir-webapp.server
  (:require [noir.server :as server]))

(server/load-views-ns 'my-noir-webapp.views)

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'my-noir-webapp})))

