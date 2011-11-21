(ns team.quality
  (:require [borneo.core :as neo]))

(defn- node-qualities
  "Get quality node"
  []
  (neo/walk (neo/root) :qualities))

(defn- add-quality! [name]
  (io!)
  (neo/with-tx
    (neo/create-child! (node-qualities) :quality {:name name})))

(defn add-qualities! [& names]
  (io!)
  (let [qualities (if (coll? (first names)) (first names) names)]
   (neo/with-tx
      (dorun (map add-quality! qualities)))))

(defn get-quality [name]
  "Return user node"
  (io!)
  (neo/with-tx
    (first (neo/traverse (node-qualities) {:name name} :quality))))

(defn get-all-qualities
  "Return all qualities"
  []
  (io!)
  (neo/with-tx
    (doall (map neo/props 
                (neo/traverse (node-qualities) :quality)))))

(defn get-team-member-having-quality [qualityName]
  (doall (map #(neo/prop % :name)
              (neo/traverse (get-quality qualityName) :1 nil :hasQuality))))
