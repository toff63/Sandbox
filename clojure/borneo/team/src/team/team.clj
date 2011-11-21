(ns team.team
  (:require [borneo.core :as neo]
            [team.quality :as quality]))

(defn- node-members
  "Get members node"
  []
  (neo/walk (neo/root) :members))

(defn- show-rel [r]
  (io!)
  (str (:name (neo/props(neo/start-node r)))
       " " (neo/rel-type r)
       " " (:name (neo/props (neo/end-node r)))
       (if (not (empty? (neo/props r))) (str  " " (neo/props r)))))

(defn- get-team-member [name]
  "Return user node"
  (io!)
    (first (neo/traverse (node-members) {:name name} :member)))

(defn get-all-rel [name]
  (io!)
    (doall (map show-rel (neo/rels (get-team-member name)))))

(defn get-all-team-members
  "Return all team members"
  []
  (io!)
    (doall (map neo/props 
                (neo/traverse (node-members) :member))))

(defn- add-team-member! [name]
  (io!)
  (neo/with-tx
    (neo/create-child! (node-members) :member {:name name})))

(defn- add-team-mate-rel! [m1 m2]
  (io!)
  (neo/with-tx
    (neo/create-rel! m1 :teammate m2)))

(defn- create-quality-relationship [from rel-name to props]
  (neo/set-props!
      (neo/create-rel! from rel-name to) props))

(defn add-quality! [memberName qualityName level]
  (io!)
  (neo/with-tx
    (let [member (get-team-member memberName)
          quality (quality/get-quality qualityName)
          props {:level level}]
      (create-quality-relationship member :hasQuality quality props)
      (create-quality-relationship quality :isQualityOf member props))))

(defn- qualities-of [memberName]
  (io!)
     (neo/rels (get-team-member memberName) [:hasQuality]))

(defn show-qualities-of [memberName]
  (io!)
    (doall (map show-rel (qualities-of memberName))))


(defn add-team! [& names]
  (io!)
  (let [team (if (coll? (first names)) (first names) names)]
    (neo/with-tx 
      (let[teamNodes (map add-team-member! team)]
        (dorun (for [m1 teamNodes m2 teamNodes] (if (not (= m1 m2))(add-team-mate-rel! m1 m2))))))))
