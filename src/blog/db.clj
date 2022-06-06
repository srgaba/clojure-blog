(ns blog.db
  (:require [monger.core :as mg] [monger.collection :as mc])
  (:import
   [org.bson.types ObjectId]))

(def db-connection-uri (or (System/getenv "CLJBLOG_MONGO_URI") 
                           "mongodb://localhost:27017/clj-blog"))

(def db (-> db-connection-uri
            mg/connect-via-uri
            :db))

(def articles-coll "articles")

(defn create-article [title body]
  (mc/insert db articles-coll 
             {:title title
              :body body
              :created-at (new java.util.Date)}))

(defn list-articles []
  (mc/find-maps db articles-coll))

(defn get-article-by-id [art-id]
  (mc/find-map-by-id db articles-coll (ObjectId. art-id)))

