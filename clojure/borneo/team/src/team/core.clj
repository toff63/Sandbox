(ns team.core
  (:require [borneo.core :as neo]
            [team.team :as team]
            [team.quality :as quality]))

(def dbName "XPTeam")

(defn get-all-rel [name]
  (io!)
  (neo/with-db! dbName (team/get-all-rel name)))

(defn get-all-team-members
  "Return all team members"
  []
  (io!)
  (neo/with-db! dbName (team/get-all-team-members)))

(defn get-all-qualities
  "Return all qualities"
  []
  (io!)
  (neo/with-db! dbName (quality/get-all-qualities)))

(defn add-team! [& names]
  (io!)
  (neo/with-db! dbName (team/add-team! names)))

(defn add-quality-to!
  ([teamMemberName qualityName] (add-quality-to! teamMemberName qualityName "bad"))
  ([teamMemberName qualityName level]
  (io!)
  (neo/with-db! dbName
                (team/add-quality! teamMemberName qualityName level))))

(defn get-team-member-having-quality [qualityName]
  (io!)
  (neo/with-db! dbName (quality/get-team-member-having-quality qualityName)))

(defn add-qualities! [& names]
  (io!)
  (neo/with-db! dbName (quality/add-qualities! names)))

(defn show-qualities-of [name]
  (io!)
  (neo/with-db! dbName (team/show-qualities-of name)))

(defn- create-if-not-exists!
  "Create child only if there is no such relationship from parent node."
  ([rel-type]
   (create-if-not-exists! (neo/root) rel-type nil))
  ([rel-type props]
   (create-if-not-exists! (neo/root) rel-type props))
  ([parent rel-type props]
   (when-not (neo/rel? parent rel-type)
     (neo/create-child! rel-type props))))

(defn- create-db-schemas! []
  (io!)
  (neo/with-tx
    (doseq [k '(:members :qualities)]
      (create-if-not-exists! k))))

(defn init! []
  (io!)
  (neo/with-db! dbName
    (neo/purge!)
    (create-db-schemas!)))
