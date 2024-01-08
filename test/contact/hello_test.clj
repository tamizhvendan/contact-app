(ns contact.hello-test
  (:require [clojure.test :refer [deftest is]]))

(deftest hello-test
  (is (= "hello world" (str "hello " "world"))))