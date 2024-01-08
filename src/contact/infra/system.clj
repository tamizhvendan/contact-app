(ns contact.infra.system
  (:require [contact.infra.config :as config]
            [contact.infra.web-server :as web-server]
            [mount.core :as mount]))

(mount/defstate config
  :start (config/read-config (:profile (mount/args))))

(defn- system []
  #:system {:config config})

(mount/defstate web-server
  :start (web-server/start (system))
  :stop (web-server/stop web-server))

(defn start [profile]
  (mount/start-with-args {:profile profile}))

(defn stop []
  (mount/stop))

(comment
  (def system (start :dev))
  (stop))