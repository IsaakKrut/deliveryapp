CREATE DATABASE  IF NOT EXISTS `fooddelivery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE fooddelivery;


DROP TABLE IF EXISTS order_items;

create table order_items(
id int(11) NOT NULL auto_increment,
item_id int(11) NOT NULL,
quantity int (3),
order_id int(11),
PRIMARY KEY (id),
foreign key (item_id) references item(id),
foreign key (order_id) references orders(id)
);
alter table order_items
drop primary key;

alter table order_items
add primary key(order_id, item_id) doublePK;