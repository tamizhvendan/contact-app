(ns contact.infra.system
  (:require [contact.infra.config :as config]
            [contact.infra.web-server :as web-server]))

(defn start [profile]
  (let [config (config/read-config profile)
        system #:system {:config config}
        web-server (web-server/start system)]
    (assoc system :system/web-server web-server)))

(defn stop [{:system/keys [web-server]}]
  (web-server/stop web-server))

(comment 
  (def system (start :dev))
  (stop system)
  )