package InventoryModel;

public class Item extends ItemHandler{
    private static String itemName;
    private static String itemCount;
    private static String itemDescription;
    private static String itemLocation;

    //Constructors
    public Item() {
        itemName = "";
        itemCount = "0";
        itemDescription = "";
        itemLocation = "";
    }

    public Item(String itemName, String itemCount, String itemDescription, String itemLocation) {
        Item.itemName = itemName;
        Item.itemCount = itemCount;
        Item.itemDescription = itemDescription;
        Item.itemLocation = itemLocation;
    }

    //Setters and getters.
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        Item.itemName = itemName;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        Item.itemCount = itemCount;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        Item.itemDescription = itemDescription;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        Item.itemLocation = itemLocation;
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
