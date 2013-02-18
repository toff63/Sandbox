(ns my-noir-webapp.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "my-noir-webapp"]
               (include-css "/css/reset.css")]
              [:body
               [:div#wrapper
                content]]))

(defpartial site-layout [& content]
  (html5
    [:head 
     [:title "my site"]]
    [:body
     [:div#wrapper
      content]]))
