(defproject clojure_code "1.0.0-SNAPSHOT"
            :description "Clojure playground"
            :dev-dependencies [[ring-serve "0.1.1"]]
            :plugins [[cider/cider-nrepl "0.1.0-SNAPSHOT"]]
            :dependencies [[org.clojure/clojure "1.5.1"]
                           [hiccup "1.0.5"]
                           [org.clojure/math.numeric-tower "0.0.4"]
                           [org.clojure/tools.nrepl "0.2.3"]]
            :repl-options {:nrepl-middleware
                 [cider.nrepl.middleware.complete/wrap-complete
                  cider.nrepl.middleware.info/wrap-info
                  cider.nrepl.middleware.inspect/wrap-inspect]})
