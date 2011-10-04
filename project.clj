(defproject lein-envclasspath "0.1.2"
  :description "add the contents of CLASSPATH to lein's classpath"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [robert/hooke "1.1.2"]]
  :eval-in-leiningen true
  :hooks [leiningen.hooks.envclasspath])
