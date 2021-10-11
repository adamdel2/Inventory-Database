package InventoryController;

import InventoryModel.*;
import InventoryView.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//User inputs, checks, and calls.
public class InventoryController extends InventoryView implements ActionListener {
    private static InventoryModel model;
    private static InventoryView view = new InventoryView();
    private Item item = new Item();
    //Used to perform item manipulations in both InventoryController and ActionHandler.
    private static Item menuItem = new Item();

    //Make only one instance of InventoryController
    private static InventoryController controller;
    static {
        try {
            controller = new InventoryController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Returns InventoryController instance because there is only one.
    public static InventoryController getInstance() {
        return controller;
    }

    //Constructors
    public InventoryController() {
        menu(view);
    }

    public InventoryController(InventoryView view, InventoryModel model) {
        InventoryController.view = view;
        InventoryController.model = model;
        menu(view);
    }

    //Button presses for InventoryView frame.
    public void actionPerformed(ActionEvent e) {
        menuItem.setEquals(ActionHandler.getActionItems());
        //Check if edit item menu button listeners have been created.
        editButtons(e);
        ActionHandler.getInstance().menuActions(e);
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

    public boolean isEmpty(Item item) {
        if (this.getItemName().isEmpty() || this.getItemCount().isEmpty() || this.getItemDescription().isEmpty()
                || this.getItemLocation().isEmpty()) {
            return true;
        }

        return false;
    }

    //Create action listeners for edit item menu if there are none.
    private void editButtons(ActionEvent e) {
        if (view.getEditName().getActionListeners().length == 0) {
            view.getEditName().addActionListener(this);
        }

        if (view.getEditCount().getActionListeners().length == 0) {
            view.getEditCount().addActionListener(this);
        }

        if (view.getEditDescription().getActionListeners().length == 0) {
            view.getEditDescription().addActionListener(this);
        }

        if (view.getEditLocation().getActionListeners().length == 0) {
            view.getEditLocation().addActionListener(this);
        }
    }

    //Changes item in database after "Edit item" button press.
    public void editItemData(String oldName, Item menuItem) {
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
    public static Item getMenuItem() {
        return menuItem;
    }

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