package InventoryManager;
import InventoryModel.*;
import InventoryView.*;
import InventoryController.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        InventoryView view = new InventoryView("Inventory");
        InventoryController controller = new InventoryController(view, db);
        controller.menu(view);

        Item screwdriver = new Item("Screwdriver", "2", "Yellow phillips/flathead screwdriver", "Bottom left desk drawer");
        //ItemHandler.addItem(screwdriver);
        //screwdriver.removeItem(screwdriver);
        //inventoryHandler.editItem(screwdriver);
        //view.printAll();

        //controller.addItem(screwdriver);
    }
}
