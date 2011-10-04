(ns leiningen.hooks.envclasspath
  (:require [clojure.string :as s]
            [clojure.java.io :as f]
            [robert.hooke]
            [leiningen.classpath]))

(println "what you say?")

(defn- get-jars [dir]
  (if (.endsWith dir "*")
    (for [jar (.listFiles (f/file (.substring dir 0 (- (count dir) 1))))
          :when (.endsWith (.getName jar) ".jar")]
      jar)
    [dir]))

(defn expand-dirs
  [dirs]
  (mapcat get-jars dirs))

(def extra-cp-dirs (expand-dirs
                    (filter #(> (count (s/trim %)) 0)
                            (s/split (System/getenv "CLASSPATH") #":"))))

(robert.hooke/add-hook #'leiningen.classpath/get-classpath
                       (fn [get-classpath project]
                         (println "adding classpath")
                         (concat (get-classpath project) extra-cp-dirs)))
