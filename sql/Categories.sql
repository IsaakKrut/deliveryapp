CREATE DATABASE  IF NOT EXISTS `fooddelivery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE fooddelivery;

DROP TABLE IF EXISTS category;

CREATE TABLE category (
  id int(11) NOT NULL,
  name varchar(50) DEFAULT NULL,
  description varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);



insert into category values(1, "Burger Meals", "Burgers with a choice of drink and a side item");
insert into category values(2, "Chicken & Fish", "Items with chicken or fish");
insert into category values(3, "Happy Meals", "");
insert into category values(4, "Individual Items", "");
insert into category values(5, "Hot Drinks", "");
insert into category values(6, "Cold Drinks", "");
insert into category values(7, "Bakery", "");
insert into category values(8, "Sweets & Treats", "");
insert into category values(9, "Snacks & Sides", "");
insert into category values(10, "Condiments", "");
insert into category values(11, "Wraps & Salads", "");