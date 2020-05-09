(ns clj-jdbc.core)
(require '[next.jdbc :as jdbc])

;;; (def db-h2 {:dbtype "h2" :dbname "example"})
(def db-pgsql {:dbtype "postgresql" :user "kkielak" :password "" :host "127.0.0.1" :dbname "postgres"})
(def ds (jdbc/get-datasource db-pgsql))

;;;
;;; THIS VERSION WILL WORK WITH H2
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
(jdbc/execute! ds ["
-- postgresql version
drop table if exists address;
create table address (
  id int,
  name varchar(32),
  email varchar(255)
)"])

(jdbc/execute! ds ["
insert into address(name,email)
  values('Sean Corfield','sean@corfield.org')"])

(def result-set (jdbc/execute! ds ["select a1.name, a2.name from address a1,address a2"]))
