package edu.bus.bte324.canehub.entities;

public class Order {

    double price;
    int orderID;
    int customerID;
    int restaurantID;
    int menuID;
    

    public Order(double price, int orderID) {
        this.price = price;
        this.orderID = orderID;
    }

    public Order(double price, int orderID, int customerID, int restaurantID, int menuID) {
        this.price = price;
        this.orderID = orderID;
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        this.menuID = menuID;
    }

    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public int getRestaurantID() {
        return restaurantID;
    }
    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }
    public int getMenuID() {
        return menuID;
    }
    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    @Override
    public String toString() {
        return "Order [price=" + price + ", orderID=" + orderID + ", customerID=" + customerID + ", restaurantID="
                + restaurantID + ", menuID=" + menuID + "]";
    }

    
    
}
