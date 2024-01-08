(ns contact.infra.web-server
  (:require [aleph.http :as http]))

(defn handler [_]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello!"})

(defn start [{:system/keys [config]}]
  (http/start-server handler {:port (:web-server/port config)
                              :shutdown-timeout 0}))

(defn stop [^java.io.Closeable web-server]
  (.close web-server))

(comment
  (def server (start #:system {:config {:web-server/port 6060}}))
  (stop server))