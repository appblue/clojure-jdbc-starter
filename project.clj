(defproject clj-jdbc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [seancorfield/next.jdbc "1.0.424"]
                 [com.h2database/h2 "1.4.199"]
                 [org.postgresql/postgresql "42.2.12"]]
  :repl-options {:init-ns clj-jdbc.core})
