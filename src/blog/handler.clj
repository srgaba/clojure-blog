(ns blog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [blog.db :as db]
            [blog.pages :as p]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (p/index (db/list-articles)))
  (GET "/articles/:art-id" [art-id] (p/detail (db/get-article-by-id art-id)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
