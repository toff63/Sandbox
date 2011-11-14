(ns hello-www.core
  (:use compojure.core)
  (:use clojure.set)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [clojure.java.io :as io]))

(defstruct user :name)
(def users (ref ()))

(defn store-user [name]
  (dosync (alter users conj (struct user name))))

(defn display-users [base-uri]
  (if (empty? @users) "No registered users."
    (str  "Registered users: "
         (print-str (map #(str "<a href=\"" base-uri "/user/" (:name %) "\">" (:name %)  "</a>") @users)))))

(defn base-uri [r]
  (str (name (:scheme r)) "://" (:server-name r) ":" (:server-port r) ))

(defroutes main-routes
  ;; extract user agent from headers
  (GET "/" [:as r]
       (response/render
         (str "<h1>Hello World Wide Web!</h1>\n<h2>User agent</h2>"
            (get (:headers r) "user-agent")
            "<h2>Full request</h2>" r) r ))

  ;; display list of registered users
  (GET "/users" [:as r]
       (response/render (display-users (base-uri r)) r ))

  ;; display content of a file
  (GET "/foobar" [:as r] (response/render (io/resource "resources/foobar") r))

  ;; redirect to an external link
  (GET "/github" _ {:status 302 :headers {"Location" "http://github.com/toff63"}})

  ;; passing parameter inside the url (clout)
  (GET "/user/:name" [name] (str  "<h1>Hello " name "!</h1>"))

  ;; Method POST with some constraints in received name
  (POST ["/user/:name" , :name #"[A-Za-z]+"] [name :as r]
        (do
          (store-user name) 
          (display-users (base-uri r))))

  ;; defaults routes
  (route/resources "/")
  (route/not-found "Page not found"))

;; handler decoration
(def app
  (handler/site main-routes))
