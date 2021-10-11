package InventoryModel;

import java.sql.*;
import java.sql.Connection;
import java.lang.*;

//Alterations to items
public class ItemHandler {
    //Retrieve current MySQL connection.
    static InventoryModel dbConn = new InventoryModel();
    static Connection conn = dbConn.getConnection();
    //String to be passed to MySQL.
    static String sql;
    //Statement actually passed to MySQL.
    static PreparedStatement pst;

    //Constructors
    public ItemHandler(Item item) {
    }

    public ItemHandler(){
    }

    //Add item to database with given parameters.
    public static void addItem(Item item) throws Exception {
        sql = "INSERT INTO inventory_items (item_name, item_id, item_count, item_description, item_location) "
                + " VALUES (?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(sql);
        pst.setString(1, item.getItemName());
        pst.setString(2, null);
        pst.setString(3, item.getItemCount());
        pst.setString(4, item.getItemDescription());
        pst.setString(5, item.getItemLocation());

        //Push changes to current table.
        pst.executeUpdate();
    }

    //Remove item in database using the item name.
    public static void removeItem(Item item) throws Exception {
        sql = "DELETE FROM inventory_items WHERE item_name = ?" +
                "ORDER BY item_id LIMIT 1";
        pst = conn.prepareStatement(sql);
        pst.setString(1, item.getItemName());

        //Push changes to current table.
        pst.executeUpdate();
    }

    //Updates all item fields in current table.
    public static void editItem(Item item, String oldName) throws Exception {
        sql = "UPDATE inventory_items SET item_name = ?, " +
                "item_count = ?, item_description = ?, item_location = ?" +
                "WHERE item_name = ?";

        //Fill prepared statement data.
        pst = conn.prepareStatement(sql);
        pst.setString(1, item.getItemName());
        pst.setString(2, item.getItemCount());
        pst.setString(3, item.getItemDescription());
        pst.setString(4, item.getItemLocation());
        pst.setString(5, oldName);

        //Push changes to current table.
        pst.executeUpdate();
    }

    //Check if item exists in database.
    public static boolean exists(Item item) throws Exception {
        boolean exists = false;

        sql = "SELECT * FROM inventory_items WHERE item_name = ?";
        pst = conn.prepareStatement(sql);
        //Add item name to "sql" string.
        pst.setString(1, item.getItemName());

        ResultSet results = null;
        results = pst.executeQuery();

        //Check if MySQL returned results.
        if (results.next()) {
            exists = true;
        }

        return exists;
    }

    //Find item in database.
    public static Item getItemData(Item item) throws Exception {
        sql = "SELECT * FROM inventory_items WHERE item_name = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, item.getItemName());
        ResultSet itemData = pst.executeQuery();

        if(itemData.next()) {
            item.setItemCount(itemData.getString("item_count"));
            item.setItemDescription(itemData.getString("item_description"));
            item.setItemLocation(itemData.getString("item_location"));
        }

        return item;
    }
}
