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

    //Returns ActionHandler instance because there is only one.
    public static ActionHandler getInstance() {
        return actionHandler;
    }

    //Constructors
    public ActionHandler(ActionEvent e, InventoryView view, InventoryModel model, Item menuItem) {
        event = e;
        ActionHandler.view = view;
        ActionHandler.model = model;
        menuActions(e);
        ActionHandler.menuItem = menuItem;
    }

    public ActionHandler(){
        view = InventoryController.getInstance().getView();
        model = InventoryModel.getInstance();
    }

    //Event handlers for main menu and edit item menu.
    public void menuActions (ActionEvent e) {
        String name = "name";
        String count = "count";
        String description = "description";
        String location  = "location";

        //Add item.
        if (e.getSource() == view.getAddButton()) {
            try {
                InventoryController.getMenuItem().setItemName(inputDialogBox(
                        "Enter the item name", name));
                InventoryController.getMenuItem().setItemCount(inputDialogBox(
                        "Enter the item count", count));
                InventoryController.getMenuItem().setItemDescription(inputDialogBox(
                        "Enter the item description", description));
                InventoryController.getMenuItem().setItemLocation(inputDialogBox(
                        "Enter the item location", location));
            } catch (Exception empty) {
                System.out.println("No input received");
            }

            if (!isNull(InventoryController.getMenuItem()) && !isEmpty(InventoryController.getMenuItem())) {
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
            try {
                InventoryController.getMenuItem().setItemName(inputDialogBox(
                        "Enter the item name to delete", name));
            } catch (Exception empty) {
                System.out.println("No input received");
            }

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
            try {
                InventoryController.getMenuItem().setItemName(inputDialogBox("Enter the item to change", name));
                oldName = InventoryController.getMenuItem().getItemName();
            } catch (Exception empty) {
                System.out.println("No input received");
            }

            //Verify item exists and set up edit item popup location.
            try {
                if (ItemHandler.exists(InventoryController.getMenuItem())) {
                    ItemHandler.getItemData(InventoryController.getMenuItem());
                    Component source = (Component) e.getSource();
                    view.getEditMenu().show(source,0,0);
                }
            } catch (Exception ex) {
                //Return to main menu if cancel is pressed.
                if (Integer.parseInt(InventoryController.getMenuItem().getItemName()) == JOptionPane.CANCEL_OPTION) {
                    getFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(getFrame(), "Item does not exist");
                }
            }
        }

        //Search for item by name.
        if (e.getSource() == view.getSearchButton()) {
            try {
                InventoryController.getMenuItem().setItemName(inputDialogBox(
                        "Enter the item to search for", name));
                if (ItemHandler.exists(InventoryController.getMenuItem())) {
                    ItemHandler.getItemData(InventoryController.getMenuItem());
                    JOptionPane.showMessageDialog(getFrame(), "Name: " +
                            InventoryController.getMenuItem().getItemName() + "\nCount: " +
                            InventoryController.getMenuItem().getItemCount() + "\nDescription: " +
                            InventoryController.getMenuItem().getItemDescription() + "\nLocation: " +
                            InventoryController.getMenuItem().getItemLocation());
                } else {
                    JOptionPane.showMessageDialog(getFrame(), "Item does not exist");
                }
            } catch (Exception empty) {
                System.out.println("No input received");
            }
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
