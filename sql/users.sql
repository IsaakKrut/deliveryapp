CREATE DATABASE  IF NOT EXISTS `fooddelivery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE fooddelivery;


DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(30) DEFAULT NULL,
  last_name varchar(30) DEFAULT NULL,
  email varchar(30) NOT NULL,
  password varchar(20) NOT NULL,
  birth_date datetime,
  PRIMARY KEY (id)
);