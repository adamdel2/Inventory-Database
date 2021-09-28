package InventoryController;
import InventoryManager.*;
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
    public InventoryController() throws Exception {
    }

    public InventoryController(InventoryView view, InventoryModel model) throws Exception {
        this.view = view;
        this.model = model;
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
                    view.tableSetup();
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
                    view.tableSetup();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == view.getEditButton()) {
            menuItem.setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the item to change"));
            showEditMenu(e);
        }

        if (e.getSource() == view.getDisplayButton()) {
        }

        if (e.getSource() == view.getDisplayAllButton()) {
        }

        if (e.getSource() == view.getExitButton()) {
            System.exit(0);
        }
    }

    public void menu(InventoryView view) {
        this.view = getView();
    }

    public boolean isNull(Item item) {
        if (this.getItemName() == null || this.getItemCount() == null || this.getItemDescription() == null
        || this.getItemLocation() == null) {
            return true;
        }

        return false;
    }

    //******************************************************STOPPED. text box doesn't appear.
    private void showEditMenu(ActionEvent e) {
        Component source = (Component) e.getSource();
        view.getEditMenu().show(source,0,0);

        if (e.getSource() == view.getEditName()) {
            menuItem.setItemName(JOptionPane.showInputDialog(view.getFrame(), "Enter the new item name"));
        }
    }

    //Setters and getters.
    public InventoryModel getModel() {
        return model;
    }

    public void setModel(InventoryModel model) {
        this.model = model;
    }

    public InventoryView getView() {
        return view;
    }

    public void setView(InventoryView view) {
        this.view = view;
    }

    public String getItemName() {
        return item.getItemName();
    }

    public void setItemName(String itemName) {
        this.setItemName(itemName);
    }

    public String getItemCount() {
        return item.getItemCount();
    }

    public void setItemCount(String itemCount) {
        this.setItemCount(itemCount);
    }

    public String getItemDescription() {
        return item.getItemDescription();
    }

    public void setItemDescription(String itemDescription) {
        this.setItemDescription(itemDescription);
    }

    public String getItemLocation() {
        return item.getItemLocation();
    }

    public void setItemLocation(String itemLocation) {
        this.setItemLocation(itemLocation);
    }
}
