package InventoryModel;

import java.sql.Connection;
import java.sql.DriverManager;

//Connection handling
public class InventoryModel {
    //MySQL connection variable.
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

    //Returns InventoryModel instance because there is only one.
    public static InventoryModel getInstance() {
        return model;
    }

    //Creates MySQL connection.
    public Connection setConnection() {
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

    //Return the connection if established, or create connection if none.
    public Connection getConnection() {
        if (conn  == null) {
            setConnection();
        }

        return conn;
    }
}
