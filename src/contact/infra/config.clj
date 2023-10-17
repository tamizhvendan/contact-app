(ns contact.infra.config
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [malli.core :as m]
            [malli.error :as me]))

(def Schema 
  [:map 
   [:app/profile [:enum :dev :prod]]
   [:webserver/port int?]])

(defn read-config [profile]
  (let [config (aero/read-config (io/resource "config.edn") {:profile profile})]
    (if (m/validate Schema config)
      config
      (let [err (me/humanize (m/explain Schema config))]
        (throw (ex-info (str "invalid configuration: " err) err))))))

(comment 
  (read-config :dev)
  )