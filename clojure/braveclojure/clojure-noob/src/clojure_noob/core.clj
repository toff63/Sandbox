(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn match-body-part [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  [asym-body-parts]
  (loop [remaining-body-parts asym-body-parts result asym-body-parts]
    (if (empty? remaining-body-parts)
      result
      (if (clojure.string/starts-with? (:name (first remaining-body-parts)) "left-")
        (recur (rest remaining-body-parts) (conj result (match-body-part (first remaining-body-parts))))
        (recur (rest remaining-body-parts) result)))))

(defn my-reduce [acc current]
  (if (clojure.string/starts-with? (:name current) "left-")
    (conj acc (match-body-part current))
    acc))

(defn better-symmetrize-body-parts
  [asym-body-parts]
  (reduce my-reduce asym-body-parts asym-body-parts))

(def alien-body-parts [{:name "head" :size 3}
                       {:name "left-eye" :size 1}
                       {:name "left-ear" :size 1}
                       {:name "mouth" :size 1}
                       {:name "nose" :size 1}
                       {:name "neck" :size 2}
                       {:name "left-shoulder" :size 3}
                       {:name "left-upper-arm" :size 3}
                       {:name "chest" :size 10}
                       {:name "back" :size 10}
                       {:name "left-forearm" :size 3}
                       {:name "abdomen" :size 6}
                       {:name "left-kidney" :size 1}
                       {:name "left-hand" :size 2}
                       {:name "left-knee" :size 2}
                       {:name "left-thigh" :size 4}
                       {:name "left-lower-leg" :size 3}
                       {:name "left-achilles" :size 1}
                       {:name "left-foot" :size 2}])

(defn match-radial-body-part
  "Alien version with 5 parts"
  ([part]
   [{:name (clojure.string/replace (:name part) #"^left-" "1-") :size (:size part)}
    {:name (clojure.string/replace (:name part) #"^left-" "2-") :size (:size part)}
    {:name (clojure.string/replace (:name part) #"^left-" "3-") :size (:size part)}
    {:name (clojure.string/replace (:name part) #"^left-" "4-") :size (:size part)}
    {:name (clojure.string/replace (:name part) #"^left-" "5-") :size (:size part)}])
  ([part n]
   (map (fn [i] {:name (clojure.string/replace (:name part) #"^left-" (str i "-")) :size (:size part)}) (range n))))

(defn reduce-radial-body-parts
  ([acc current]
   (if (clojure.string/starts-with? (:name current) "left-")
     (into acc (match-radial-body-part current))
     (into acc [current])))
  ([n acc current]
   (if (clojure.string/starts-with? (:name current) "left-")
     (into acc (match-radial-body-part current n))
     (into acc [current]))))

(defn radial-symmetrize-body-parts
  ([asym-body-parts]
   (reduce reduce-radial-body-parts [] asym-body-parts))
  ([asym-body-parts n]
   (reduce (partial reduce-radial-body-parts n) [] asym-body-parts)))

(radial-symmetrize-body-parts alien-body-parts)
(radial-symmetrize-body-parts alien-body-parts 2)

