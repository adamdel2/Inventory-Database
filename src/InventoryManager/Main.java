package InventoryManager;
import InventoryModel.*;
import InventoryView.*;
import InventoryController.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //Sets up database to be used with MySQL.
        InventoryModel db = new InventoryModel();
        db.setConnection();

        //Set up interface and theme.
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        //Creating the view and controller to be used.
        InventoryView view = new InventoryView("Inventory");
        InventoryController controller = new InventoryController(view, db);
        controller.menu(view);
    }
}
