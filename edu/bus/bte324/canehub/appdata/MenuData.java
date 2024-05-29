package edu.bus.bte324.canehub.appdata;
import edu.bus.bte324.canehub.dbmanagers.MenuDBManager;
import edu.bus.bte324.canehub.entities.RestaurantMenu;
import java.util.HashSet;

public class MenuData {

    public HashSet<RestaurantMenu> restaurantMenuSet = new HashSet<RestaurantMenu>();
    public MenuDBManager mdm = new MenuDBManager();

    public boolean addRestaurantItem(RestaurantMenu rm){
        boolean status = restaurantMenuSet.add(rm);
        try{
            mdm.insertSingleMenuItemIntoDB(rm);

        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return status;

    }

    public boolean removeRestaurantItem(RestaurantMenu rm, String itemName){
        restaurantMenuSet.add(rm);
        boolean status = restaurantMenuSet.remove(rm);
        try{
            if (mdm.checkIfMenuItemExists(rm.getRestaurantID(), itemName)){
                mdm.deleteRestaurantItemByName(rm.getRestaurantID(), itemName);
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
        return status;
    }

    public boolean updateRestaurantItem(RestaurantMenu oldItem, RestaurantMenu newItem){
        restaurantMenuSet.remove(oldItem);
        boolean status = restaurantMenuSet.add(newItem);
        try {
            mdm.deleteRestaurantItemByName(oldItem.getRestaurantID(), oldItem.getMenuItem().getItemName());
            mdm.insertSingleMenuItemIntoDB(newItem);
        }
        
        catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return status;
    }

    
}
