(ns contact.core
  (:require [contact.infra.system :as system])
  (:gen-class))

(.addShutdownHook (Runtime/getRuntime)
                  (Thread. (fn []
                             (system/stop))))

(defn -main [& _]
  (if-let [profile (System/getenv "PROFILE")]
    (system/start (keyword profile))
    (throw (ex-info "Environment Variable PROFILE not found" {}))))