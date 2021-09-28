CREATE DATABASE inventory;
USE inventory;
CREATE TABLE inventory_items (
  item_name VARCHAR(100) NOT NULL,
  item_id INT NOT NULL AUTO_INCREMENT,
  item_count INT,
  item_description VARCHAR(1000),
  item_location VARCHAR(100),
  PRIMARY KEY (item_id)
);

SELECT * FROM inventory_items;