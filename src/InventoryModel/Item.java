package InventoryModel;

public class Item extends ItemHandler{
    private static String item_name;
    private static String item_count;
    private static String item_description;
    private static String item_location;

    //Constructors
    public Item() {
        item_name = "";
        item_count = "0";
        item_description = "";
        item_location = "";
    }

    public Item(String item_name, String item_count, String item_description, String item_location) {
        Item.item_name = item_name;
        Item.item_count = item_count;
        Item.item_description = item_description;
        Item.item_location = item_location;
    }

    //Setters and getters.
    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        Item.item_name = item_name;
    }

    public String getItemCount() {
        return item_count;
    }

    public void setItemCount(String item_count) {
        Item.item_count = item_count;
    }

    public String getItemDescription() {
        return item_description;
    }

    public void setItemDescription(String item_description) {
        Item.item_description = item_description;
    }

    public String getItemLocation() {
        return item_location;
    }

    public void setItemLocation(String item_location) {
        Item.item_location = item_location;
    }

    //Copy item data.
    public void setEquals(Item itemToCopy) {
        if (itemToCopy != null) {
            this.setItemName(itemToCopy.getItemName());
            this.setItemCount(itemToCopy.getItemCount());
            this.setItemDescription(itemToCopy.getItemDescription());
            this.setItemLocation(itemToCopy.getItemLocation());
        }
    }
}
