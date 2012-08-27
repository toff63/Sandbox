(defproject clog "0.1.0-SNAPSHOT"
  :description "A blog engine written in clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.3"
                  :exclusion [org.clojure/clojure
                              clj.stacktrace]]
                 [net.cgrand/moustache "1.1.0"]
                 [lobos "1.0.0-SNAPSHOT"]
                 [korma "0.3.0-beta9"]
                 [enlive "1.0.0"]
                 [postgresql "9.1-901.jdbc4"]
                 [clj-yaml "0.3.1"]])
