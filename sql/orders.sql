CREATE DATABASE  IF NOT EXISTS `fooddelivery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE fooddelivery;

DROP TABLE IF EXISTS orders;

create table orders(
id int(11) NOT NULL auto_increment ,
total_price decimal(10,2),
order_date datetime,
PRIMARY KEY (id)
);

