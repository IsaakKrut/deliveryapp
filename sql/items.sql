CREATE DATABASE  IF NOT EXISTS `fooddelivery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE fooddelivery;


DROP TABLE IF EXISTS item;

CREATE TABLE item (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  category_id int(11) DEFAULT NULL,
  calories varchar(15) DEFAULT NULL,
  price decimal(10,2) DEFAULT NULL,
  description varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) references category(id)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into item values(1, "Big Mac Extra Value Meal", 1,  "680-1110 Cals", 9.89, "");
insert into item values(2, "Double Big Mac Extra Value Meal", 1,  "790-1120 Cals", 11.39, "");
insert into item values(3, "Quarter Pounder with Cheese Extra Value Meal", 1,  "630-1060 Cals", 9.89, "");
insert into item values(4, "Quarter Pounder without Cheese Extra Value Meal", 1,  "530-960 Cals", 9.89, "");
insert into item values(5, "Double Quarter Pounder BLT Extra Value Meal", 1,  "990-1430 Cals", 12.39, "");
insert into item values(6, "Double Cheeseburger Extra Value Meal", 1,  "520-950 Cals", 8.25, "");
insert into item values(7, "McPicks McDouble Meal", 1,  "480-910 Cals", 5.00, "");

insert into item values(8, "BLT with Crispy Chicken Extra Value Meal", 2,  "880-1310 Cals", 11.39, "");
insert into item values(9, "BLT with Grilled Chicken Extra Value Meal", 2,  "740-1170 Cals", 11.39, "");
insert into item values(10, "Chicken & Backon Signature McWrap with Grilled Chicken Extra Value Meal", 2,  "600-1030 Cals", 10.59, "");
insert into item values(11, "Caesar Signature McWrap with Grilled Chicken Extra Value Meal", 2,  "570-1000 Cals", 10.59, "");
insert into item values(12, "McChicken Extra Value Meal", 2,  "580-1010 Cals", 9.89, "");
insert into item values(13, "10 McNuggets Extra Value Meal", 2,  "630-1060 Cals", 11.49, "");
insert into item values(14, "Filet-O-Fish Extra Value Meal", 2,  "500-930 Cals", 9.89, "");

insert into item values(15, "Happy Meal Hamburger with Mini Fries", 3,  "390-500 Cals", 3.99, "");
insert into item values(16, "Happy Meal Hamburger with Small Fries", 3,  "510-620 Cals", 3.99, "");
insert into item values(17, "Happy Meal Cheeseburger with Mini Fries", 3,  "440-550 Cals", 4.59, "");
insert into item values(18, "Happy Meal Crispy Chicken Snack Wrap with Apple Slices", 3,  "360-470 Cals", 4.59, "");
insert into item values(19, "Happy Meal Crispy Chicken Snack Wrap with Apple Slices", 3,  "290-400 Cals", 4.59, "");
insert into item values(20, "Happy Meal 4 McNuggets with Small Fries", 3,  "480-610 Cals", 4.79, "");
insert into item values(21, "Happy Meal Hotcakes with Mini Fries", 3,  "500-610 Cals", 4.23, "");

insert into item values(22, "Mighty Angus Original", 4,  "790 Cals", 7.79, "");
insert into item values(23, "Bacon and Cheddar Angus", 4,  "770 Cals", 7.79, "");
insert into item values(24, "Big Mac", 4,  "540 Cals", 5.99, "");
insert into item values(25, "Double Big Mac", 4,  "710 Cals", 7.49, "");
insert into item values(26, "Quarter Pounder with Cheese", 4,  "520 Cals", 5.99, "");
insert into item values(27, "Quarter Pounder BLT", 4,  "600 Cals", 6.99, "");

insert into item values(28, "Americano", 5,  "3 Cals", 2.09, "");
insert into item values(29, "Premium Roast Brewer Coffe", 5,  "3 Cals", 1.54, "");
insert into item values(30, "Premium Roast Decaf Coffee", 5,  "0 Cals", 1.54, "");
insert into item values(31, "Orange Pekoe Tea", 5,  "0 Cals", 1.54, "");
insert into item values(32, "Pepperming Tea", 5,  "0 Cals", 1.54, "");
insert into item values(33, "Premium Roast Coffee Carafe (Serves 12)", 5,  "720-760 Cals", 15.99, "");

insert into item values(34, "Coca-Cola", 6,  "100 Cals", 1.49, "");
insert into item values(35, "Barq's Root Beer", 6,  "110 Cals", 1.49, "");
insert into item values(36, "Sprite", 6,  "100 Cals", 1.49, "");
insert into item values(37, "Diet Coke", 6,  "1 Cals", 1.49, "");
insert into item values(38, "NESTEA Iced Tea", 6,  "80 Cals", 1.49, "");
insert into item values(39, "Fruitopia Strawberry", 6,  "90 Cals", 1.49, "");

insert into item values(40, "Cranberry Orange Muffin", 7,  "360 Cals", 1.59, "");
insert into item values(41, "Banana Chocolate Chunk Muffin", 7,  "430 Cals", 1.59, "");
insert into item values(42, "Blueberry Muffin", 7,  "430 Cals", 1.59, "");
insert into item values(43, "Cranberry Orange Muffin Pairing", 7,  "360 Cals", 1.99, "");
insert into item values(44, "Banana Chocolate Chunk Muffin Pairing", 7,  "430 Cals", 1.99, "");
insert into item values(45, "Blueberry Muffin Pairing", 7,  "430 Cals", 1.99, "");

insert into item values(46, "Creme Egg McFlurry", 8,  "420 Cals", 2.99, "");
insert into item values(47, "Mini Reese's Pieces McFlurry", 8,  "420 Cals", 2.99, "");
insert into item values(48, "Oreo Mcflurry", 8,  "330 Cals", 2.99, "");
insert into item values(49, "Fudge Sundae", 8,  "340 Cals", 2.89, "");
insert into item values(50, "Caramel Sundae", 8,  "340 Cals", 2.89, "");
insert into item values(51, "Plain Sundae", 8,  "210 Cals", 2.89, "");

insert into item values(52, "French Fries", 9,  "240 Cals", 1.69, "");
insert into item values(53, "Poutine", 9,  "880 Cals", 4.99, "");
insert into item values(54, "Ranch Chicken Snack Wrap with Grilled Chicken", 9,  "250 Cals", 2.69, "");
insert into item values(55, "Side Caesar Salad", 9,  "270-360 Cals", 3.29, "");

insert into item values(56, "Nugget Sauce", 10,  "35 Cals", 0.20, "");
insert into item values(57, "Salad Dressing", 10,  "210 Cals", 0.30, "");
insert into item values(58, "Big Mac Sauce", 10,  "170 Cals", 0.30, "");
insert into item values(59, "Ketchup Packets", 10,  "10 Cals", 0.00, "");


insert into item values(60, "Caesar Entree Salad with Crispy Chicken", 11,  "640-730 Cals", 8.29, "");
insert into item values(62, "Caesar Entree Salad", 11,  "390-480 Cals", 6.29, "");
insert into item values(63, "Greek Entree Salad with Crispy Chicken", 11,  "560-650 Cals", 8.29, "");
insert into item values(64, "Greek Entree Salad", 11,  "310-400 Cals", 6.29, "");
insert into item values(65, "Chicken & Bacon Signature McWrap with Grilled Chicken", 11,  "460 Cals", 6.69, "");

