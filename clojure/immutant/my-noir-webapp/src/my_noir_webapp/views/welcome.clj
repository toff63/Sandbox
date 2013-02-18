(ns my-noir-webapp.views.welcome
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]])
  (:require [my-noir-webapp.views.common :as common]
            [noir.session :as session]))

(defpage "/welcome" []
           (common/layout
             [:p "Welcome to my-noir-webapp"]
             )) 


(defn previous-server []
  (session/get :previous-server ""))

(defn update-previous-server [node]
  (session/put! :previous-server node))

(defn node-name []
  (System/getProperty "jboss.node.name"))

(defpage "/my-page" []
  (let [node (previous-server)]
    (update-previous-server (node-name))
    (common/site-layout
      [:h1 "This is my first page!"]
      [:p "Hope you like it"]
      [:p (str "the previous node called was " node)])))
