package InventoryModel;
import InventoryManager.*;
import InventoryModel.*;
import InventoryView.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.lang.*;

//Alterations to items
public class ItemHandler {
    //Constructors
    public ItemHandler(Item item) {
    }

    public ItemHandler(){
    }

    public static void addItem(Item item) throws Exception {
        //Retrieve current MySQL connection.
        InventoryModel dbConn = new InventoryModel();
        Connection conn = dbConn.getConnection();
        //Statement to be passed to MySQL.
        String sql = "INSERT INTO inventory_items (item_name, item_id, item_count, item_description, item_location) "
                + " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, item.getItemName());
        pst.setString(2, null);
        pst.setString(3, item.getItemCount());
        pst.setString(4, item.getItemDescription());
        pst.setString(5, item.getItemLocation());
        //Pushes changes to current table.
        pst.executeUpdate();
    }

    public static void removeItem(Item item) throws Exception {
        //Retrieve current MySQL connection.
        InventoryModel dbConn = new InventoryModel();
        Connection conn = dbConn.getConnection();

        //Statement to be passed to MySQL.
        String sql = "DELETE FROM inventory_items WHERE item_name = '";
        sql = sql.concat(item.getItemName() + "'"
                + "ORDER BY item_id LIMIT 1");
        PreparedStatement pst = conn.prepareStatement(sql);
        //Pushes changes to current table.
        pst.executeUpdate();
    }

    //Updates all item fields in current table.
    public static void editItem(Item item) throws Exception {
        //Retrieve current MySQL connection.
        InventoryModel dbConn = new InventoryModel();
        Connection conn = dbConn.getConnection();

        //Statement to be passed to MySQL.
        String sql = "UPDATE inventory_items SET item_name = \"";
        sql = sql.concat(item.getItemName() + "\", item_count = \"" + item.getItemCount() + "\", " +
                "item_description = \"" + item.getItemDescription() + "\", " +
                "item_location = \"" + item.getItemLocation() + "\" " +
                "WHERE inventory_items.item_name = " + "\"" + item.getItemName() + "\"");
        PreparedStatement pst = conn.prepareStatement(sql);
        //Pushes changes to current table.
        pst.executeUpdate();
    }
}
