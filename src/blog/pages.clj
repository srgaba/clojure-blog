(ns blog.pages
  (:require [hiccup.page :refer [html5]]))

(defn base-page [& body]
  (html5 
   [:head [:title "Clj Blog Document Title"]]
   [:body [:h1 "Clj Blog"]
    [:a {:href "/"} [:h1 "CljBlog"]]
    body]))

(defn index [articles] 
  (base-page 
   (for [a articles]
           [:a {:href (str "/articles/" (:_id a))} [:h1 (:title a)]] )))

(defn detail [a]
  (base-page
   [:small (:created-at a)]
   [:h1 (:title a)]
   [:p (:body a)]))

;; (defn index [articles]
;;   (->> articles
;;        (map #(str "<h2>" (:title %) "</h2>"))
;;        (apply str "<h1>CljBlog</h1>")))