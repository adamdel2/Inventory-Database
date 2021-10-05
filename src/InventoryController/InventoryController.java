package InventoryController;

import InventoryModel.*;
import InventoryView.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//User inputs, checks, and calls.
public class InventoryController extends InventoryView implements ActionListener {
    private static InventoryModel model;
    private static InventoryView view = new InventoryView();
    private Item item = new Item();
    Item menuItem = new Item();

    //Make only one instance of InventoryController
    private static InventoryController controller;
    static {
        try {
            controller = new InventoryController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InventoryController getInstance() {
        return controller;
    }

    //Constructors
    public InventoryController() {
    }

    public InventoryController(InventoryView view, InventoryModel model) {
        InventoryController.view = view;
        InventoryController.model = model;
        menu(view);
    }

    //Button presses for InventoryView frame.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getAddButton()) {
            menuItem.setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the item name"));
            menuItem.setItemCount(JOptionPane.showInputDialog(view.getFrame(), "Enter the item count"));
            menuItem.setItemDescription(JOptionPane.showInputDialog(view.getFrame(), "Enter the item description"));
            menuItem.setItemLocation(JOptionPane.showInputDialog(view.getFrame(), "Enter the item location"));

            if (!isNull(menuItem)) {
                try {
                    ItemHandler.addItem(menuItem);
                    tableSetup();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == view.getRemoveButton()) {
            menuItem.setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the item name to delete"));
            if (!isNull(menuItem)) {
                try {
                    ItemHandler.removeItem(menuItem);
                    tableSetup();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == view.getEditButton()) {
            menuItem.setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the item to change"));
            //Original item name. Needed to find item in database.
            String oldName = menuItem.getItemName();


            //Verify item exists and retrieve data for item based on name.
            try {
                if (ItemHandler.exists(menuItem)) {
                    ItemHandler.getItemData(menuItem);
                    showEditMenu(e, oldName);
                } else System.out.println("Item does not exist");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == view.getSearchButton()) {
        }

        if (e.getSource() == view.getExitButton()) {
            System.exit(0);
        }
    }

    //Set view for Controller.
    public void menu(InventoryView view) {
        InventoryController.view = getView();
    }

    //Check if item has null for any value.
    public boolean isNull(Item item) {
        if (this.getItemName() == null || this.getItemCount() == null || this.getItemDescription() == null
                || this.getItemLocation() == null) {
            return true;
        }

        return false;
    }

    //Two joptionpanes populate when restarting edit********************************************************************
    //Edit popup menu listeners.
    private void showEditMenu(ActionEvent e, String oldName) {
        Component source = (Component) e.getSource();
        view.getEditMenu().show(source,0,0);

        view.getEditName().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent editEvent) {
                menuItem.setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the new item name"));
                editItemData(oldName);
            }
        });

        view.getEditCount().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent editEvent) {
                menuItem.setItemCount(JOptionPane.showInputDialog(view.getFrame(), "Enter the new item count"));
                editItemData(oldName);
            }
        });

        view.getEditDescription().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent editEvent) {
                menuItem.setItemDescription(JOptionPane.showInputDialog(view.getFrame(),
                        "Enter the new item description"));
                editItemData(oldName);
            }
        });

        view.getEditLocation().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent editEvent) {
                menuItem.setItemLocation(JOptionPane.showInputDialog(view.getFrame(),
                        "Enter the new item location"));
                editItemData(oldName);
            }
        });


    }

    //Changes item in database after "Edit item" button press.
    public void editItemData(String oldName) {
        if (!isNull(menuItem)) {
            try {
                ItemHandler.editItem(menuItem, oldName);
                tableSetup();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //Setters and getters.
    public InventoryView getView() {
        return view;
    }

    public String getItemName() {
        return item.getItemName();
    }

    public String getItemCount() {
        return item.getItemCount();
    }

    public String getItemDescription() {
        return item.getItemDescription();
    }

    public String getItemLocation() {
        return item.getItemLocation();
    }
}