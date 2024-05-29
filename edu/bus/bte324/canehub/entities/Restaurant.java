package edu.bus.bte324.canehub.entities;

import edu.bus.bte324.canehub.infoclasses.AddressInfo;

public class Restaurant
{
    private String restaurantName;
    private AddressInfo restaurantAddress;
    private String cuisine;
    private String phoneNumber;
    private int restaurantID;

    //TODO Generate getters, setters, toString method, constructors.
    
    public Restaurant(){

    }
    
    public Restaurant(String restaurantName,String phoneNumber){
        this.restaurantName = restaurantName;
        this.phoneNumber = phoneNumber;

    }

    public Restaurant(String restaurantName, String cuisine, String phoneNumber){
        this.restaurantName = restaurantName;
        this.cuisine = cuisine;
        this.phoneNumber = phoneNumber;
    }

    public Restaurant(int restaurantID, String restaurantName, String cuisine,String phoneNumber){
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.cuisine  = cuisine;
        this.phoneNumber = phoneNumber;

    }

    public Restaurant(int restaurantID, String restaurantName, String phoneNumber){
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.phoneNumber = phoneNumber;
    }

    public int getRestaurantID(){
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID){
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public AddressInfo getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(AddressInfo restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantNumber() {
        return phoneNumber;
    }

    public void setRestaurantNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRestaurantCuisine(){
        return cuisine;
    }

    public void setRestaurantCuisine(String cuisine){
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantID= " + restaurantID + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", cuisine=" + cuisine + '\''+
                ", restaurantAddress=" + restaurantAddress + '\''+
                ", phoneNumber='" + phoneNumber +
                '}';
    }
}