package edu.bus.bte324.canehub.appdata;


import edu.bus.bte324.canehub.entities.Restaurant;
import edu.bus.bte324.canehub.dbmanagers.RestaurantDBManager;

import java.util.HashSet;


public class RestaurantsData {
    private RestaurantDBManager rdm = new RestaurantDBManager();

    private HashSet<Restaurant> RestaurantSet = new HashSet<Restaurant>();

    public boolean addRestaurant(Restaurant r)
    {
        boolean status = RestaurantSet.add(r);
        try {
            rdm.insertSingleRestaurantIntoDB(r);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return status;
        //eturn RestaurantSet.add(c);
    }

    public boolean removeRestaurant(Restaurant r)
    {
        try{
            if (rdm.checkIfRestaurantExists(r.getRestaurantName(), r.getRestaurantNumber())){
                rdm.deleteRestaurantByName(r.getRestaurantName(), r.getRestaurantNumber());
            }

            else{
                throw new Exception("Error... Customer Does not Exist!");
                
            }
        }

        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        
        return RestaurantSet.remove(r);
    }

    public boolean updateRestaurant(Restaurant oldValue, Restaurant newValue)
    {
        RestaurantSet.remove(oldValue);
        boolean status = RestaurantSet.add(newValue);
        try {
                rdm.deleteRestaurantByName(oldValue.getRestaurantName(), oldValue.getRestaurantNumber());
                rdm.insertSingleRestaurantIntoDB(newValue);
            }
            
        catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return status;
    }

    

    public void displayAll()
    {
        System.out.println("Printing all the restaurants here:");
        try{
            for(@SuppressWarnings("unused") Restaurant r: rdm.getAllRestaurantsFromDB()){
            //System.out.println(c);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

} 

