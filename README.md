# Inventory-Database
Inventory database

Project was set up using MVC pattern and utilizes MySQL for database management.\
The purpose of the project is to practice a design pattern, SQL, testing, and interfaces.

* SQL info\
mysql inventory: contains table setup for MySQL.\
MySQL user/pass is in InventoryModel (not secure). Default host information.\
MySQL info: HostName- 127.0.0.1 Port- 3306\
Copy mysql inventory.sql text into MySQL to set up a table with the correct naming for the project.

Target: Maven information.

* Class info\
InventoryController: handles any change calls to InventoryModel and InventoryView.\
InventoryModel: handles changes to database.\
InventoryModel-Item: items that are to be manipulated in the database.\
InventoryModel-ItemHandler: handles Item changes that need to be pushed to the database.\
InventoryView: interface that is manipulated by InventoryController.\
InventoryManager-Main: initiates program.
