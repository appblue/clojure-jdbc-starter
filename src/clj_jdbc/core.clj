(ns clj-jdbc.core)
(require '[next.jdbc :as jdbc])

;;; THIS WILL WORK WITH H2
;;; (def db-h2 {:dbtype "h2" :dbname "example"})

(def db-pgsql 
  {
   :dbtype "postgresql" 
   :user "kkielak" 
   :password "" 
   :host "127.0.0.1" 
   :dbname "postgres"})

(def ds (jdbc/get-datasource db-pgsql))
(def conn (jdbc/get-connection ds))

;; EXAMPLE USAGE
;; -------------
;; (let [my-datasource (jdbc/get-datasource {:dbtype "..." :dbname "..." ...})]
;;     (with-open [connection (jdbc/get-connection my-datasource)]
;;       (jdbc/execute! connection [...])
;;       (reduce my-fn init-value (jdbc/plan connection [...]))
;;       (jdbc/execute! connection [...])))
;; 
;; NOTIFICATIONS IN POSTGRESQL:
;; ---------------------------
;; (jdbc/execute! conn ["listen foo"])
;; (jdbc/execute! conn ["notify foo 'tst'"])
;;
;; (def notifications (.getNotifications conn))
;; (map #(vector (.getName %) (.getParameter %)) notifications)
;;  => (["foo" "tst"])
;; (.getName (first notifications))
;;  => "foo"

;;;
;;; THIS WILL WORK WITH H2
;;;
;; (jdbc/execute! ds ["
;; drop table if exists address;
;; create table address (
;;   id int auto_increment primary key,
;;   name varchar(32),
;;   email varchar(255)
;; )"])

;;;
;;; THIS IS POSTGRESQL VERSION OF THE QUERY
;;;
(jdbc/execute! conn ["
-- postgresql version
drop table if exists address;
create table address (
  id int,
  name varchar(32),
  email varchar(255)
)"])

(jdbc/execute! conn ["
insert into address(name,email)
  values('Sean Corfield','sean@corfield.org')"])

(def result-set 
  (jdbc/execute! 
   conn 
   ["select a1.name, a2.name from address a1,address a2"]))
