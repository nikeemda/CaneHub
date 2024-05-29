package edu.bus.bte324.canehub.entities;
import edu.bus.bte324.canehub.infoclasses.MenuInfo;
//import edu.bus.bte324.canehub.entities.*;

public class RestaurantMenu {

    //private String phoneNumber;
    //private String restaurantName;
    //Restaurant r = new Restaurant();
    private MenuInfo menuItem;
    
    private int restaurantID;
    private int menuID;
    
    public RestaurantMenu(int restaurantID){
        this.restaurantID = restaurantID;
    }

    public RestaurantMenu(int restaurantID, int menuID){
        this.restaurantID = restaurantID;
        this.menuID = menuID;
    }

    public MenuInfo getMenuItem(){
        return menuItem;
    }

    public void setMenuItem(MenuInfo menuItem) {
        this.menuItem = menuItem;
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
        return "RestaurantMenu [menuItem=" + menuItem + ", restaurantID=" + restaurantID + ", menuID=" + menuID + "]";
    }

    

    
    
    

    

    
    
}
