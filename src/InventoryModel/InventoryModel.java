package InventoryModel;
import InventoryController.InventoryController;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

//Connection handling
public class InventoryModel {
    private static Connection conn = null;
    //MySQL required information
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/inventory";
    private final String username = "root";
    private final String password = "SUPERstrong1";

    //Make only one instance of InventoryModel
    private static InventoryModel model;
    static {
        try {
            model = new InventoryModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InventoryModel getInstance() {
        return model;
    }

    //Creates MySQL connection.
    public Connection setConnection() {
        //Statement to be passed to MySQL.
        String sql = "INSERT INTO inventory_items (item_name, item_id, item_count, item_description, item_location) "
                + " VALUES (?, ?, ?, ?, ?)";
        String item = "testItem";
        String id = "0";

        //Connects to MySQL.
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
        } catch (Exception x) {
            System.out.println("Error, cannot connect to MySQL.\n" + x);
        }

        return conn;
    }

        public Connection getConnection() {
            if (conn  == null) {
                setConnection();
            }

            return conn;
        }
}
