package edu.bus.bte324.canehub.infoclasses;

public class MenuInfo {
    private String itemName;
    private double price;
    private String description;
    private int menuID;
    private int restaurantID;
    private int quantity;

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public MenuInfo(String itemName, double price, String description){
        this.itemName = itemName;
        this.price = price;
        this.description = description;
    }

    public MenuInfo(String itemName, double price, String description, int menuID){
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.menuID = menuID;
    }

    public MenuInfo(String itemName, double price, String description, int menuID, int restaurantID){
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.menuID = menuID;
        this.restaurantID = restaurantID;
    }

    public MenuInfo(String itemName, double price, String description, int menuID, int restaurantID, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.menuID = menuID;
        this.restaurantID = restaurantID;
        this.quantity = 0;
    }

    @Override
    public String toString() {
        return "MenuInfo [itemName=" + itemName + ", price=" + price + ", description=" + description + ", menuID="
                + menuID + ", restaurantID=" + restaurantID + ", quantity=" + quantity + "]";
    }

    
    

    

   
    

    
    
    
    
    
}
