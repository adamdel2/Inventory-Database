package InventoryController;

import InventoryModel.InventoryModel;
import InventoryModel.Item;
import InventoryModel.ItemHandler;
import InventoryView.InventoryView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ActionListener handler for menu buttons.
public class ActionHandler extends InventoryController implements ActionListener {
    ActionEvent event;
    private static InventoryView view;
    private static InventoryModel model;
    //Original item name.
    private static String oldName;
    private static Item menuItem;

    //Make only one instance of InventoryController
    private static ActionHandler actionHandler;
    static {
        try {
            actionHandler = new ActionHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ActionHandler(){
        view = InventoryController.getInstance().getView();
        model = InventoryModel.getInstance();
    }

    public static ActionHandler getInstance() {
        return actionHandler;
    }

    //Constructor
    public ActionHandler(ActionEvent e, InventoryView view, InventoryModel model, Item menuItem) {
        event = e;
        ActionHandler.view = view;
        ActionHandler.model = model;
        menuActions(e);
        ActionHandler.menuItem = menuItem;
    }

    //Event handlers for main menu and edit item menu.
    public void menuActions (ActionEvent e) {
        //Add item.
        if (e.getSource() == view.getAddButton()) {
            InventoryController.getMenuItem().setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the item name"));
            InventoryController.getMenuItem().setItemCount(JOptionPane.showInputDialog(view.getFrame(), "Enter the item count"));
            InventoryController.getMenuItem().setItemDescription(JOptionPane.showInputDialog(view.getFrame(), "Enter the item description"));
            InventoryController.getMenuItem().setItemLocation(JOptionPane.showInputDialog(view.getFrame(), "Enter the item location"));

            if (!isNull(InventoryController.getMenuItem())) {
                try {
                    ItemHandler.addItem(InventoryController.getMenuItem());
                    tableSetup();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        //Remove item.
        if (e.getSource() == view.getRemoveButton()) {
            InventoryController.getMenuItem().setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the item name to delete"));
            if (!isNull(InventoryController.getMenuItem())) {
                try {
                    ItemHandler.removeItem(InventoryController.getMenuItem());
                    tableSetup();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        //Edit item.
        if (e.getSource() == view.getEditButton()) {
            InventoryController.getMenuItem().setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the item to change"));
            oldName = InventoryController.getMenuItem().getItemName();

            //Verify item exists and set up edit item popup location.
            try {
                if (ItemHandler.exists(InventoryController.getMenuItem())) {
                    ItemHandler.getItemData(InventoryController.getMenuItem());
                    Component source = (Component) e.getSource();
                    view.getEditMenu().show(source,0,0);
                } else System.out.println("Item does not exist");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //Search for item.**********************************************************************************************
        if (e.getSource() == view.getSearchButton()) {
            InventoryController.getMenuItem().setItemName(JOptionPane.showInputDialog(view.getFrame(),
                    "Enter the item name to search for"));
        }

        //Exit program.
        if (e.getSource() == view.getExitButton()) {
            System.exit(0);
        }

        //Edit item popup buttons.
        if (e.getSource() == view.getEditName()) {
            InventoryController.getMenuItem().setItemName(JOptionPane.showInputDialog(view.getFrame(),
                    "Enter the new item name"));
            editItemData(oldName, InventoryController.getMenuItem());
        }

        if (e.getSource() == view.getEditCount()) {
            InventoryController.getMenuItem().setItemCount(JOptionPane.showInputDialog(view.getFrame(),
                    "Enter the new item count"));
            editItemData(oldName, InventoryController.getMenuItem());
        }

        if (e.getSource() == view.getEditDescription()) {
            InventoryController.getMenuItem().setItemDescription(JOptionPane.showInputDialog(view.getFrame(),
                    "Enter the new item description"));
            editItemData(oldName, InventoryController.getMenuItem());
        }

        if (e.getSource() == view.getEditLocation()) {
            InventoryController.getMenuItem().setItemLocation(JOptionPane.showInputDialog(view.getFrame(),
                    "Enter the new item location"));
            editItemData(oldName, InventoryController.getMenuItem());
        }
    }

    public static Item getActionItems() {
        return menuItem;
    }
}
