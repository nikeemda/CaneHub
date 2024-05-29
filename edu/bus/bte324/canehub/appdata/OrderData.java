package edu.bus.bte324.canehub.appdata;

//import edu.bus.bte324.canehub.entities.Order;
import edu.bus.bte324.canehub.infoclasses.MenuInfo;
import edu.bus.bte324.canehub.dbmanagers.OrderDBManager;
import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class OrderData {

    public HashMap<Integer, MenuInfo> currentOrder = new HashMap<>();
    OrderDBManager odm = new OrderDBManager();
    CustomerDBManager cdm = new CustomerDBManager();

    public void addItemToOrder(MenuInfo item, int quantity){
        //boolean status = currentOrder.add(item);
        if (currentOrder.containsKey(item.getMenuID())){
            MenuInfo itemExists = currentOrder.get(item.getMenuID());
            itemExists.setQuantity(itemExists.getQuantity() + 1);
        }
        else{
            item.setQuantity(quantity);
            currentOrder.put(item.getMenuID(),item);
        }

        
    }

    
    public double completePurchase(int customerID) throws Exception {
        if (currentOrder.isEmpty()) {
            throw new Exception("The order is empty.");
        }
        double total=0.0;
        for (Map.Entry<Integer, MenuInfo> entry : currentOrder.entrySet()) {
            MenuInfo info = entry.getValue();
            total = info.getPrice() * info.getQuantity();
        }
            
        
        // Assume all items are from the same restaurant
        int restaurantID = currentOrder.values().stream().iterator().next().getRestaurantID();
        // You need a method to retrieve the current customer's ID

        odm.insertCompleteOrder(customerID, restaurantID, total, new HashSet<>(currentOrder.values()));
        currentOrder.clear();
        return total;
    }

    public void itemsInCart(){
        if(currentOrder.isEmpty()){
            System.out.println("Your Cart Is Empty..");
        }
        for (Map.Entry<Integer, MenuInfo> entry : currentOrder.entrySet()) {
            MenuInfo info = entry.getValue();
            double total = info.getPrice() * info.getQuantity();
            System.out.println("Your Current Total is: " + total);
        }
        /* 
        for(MenuInfo item: currentOrder.values()){
            System.out.println(item + ", Quantity: " + item.getQuantity());

        }*/
    }

    public void resetOrder(){
        currentOrder.clear();
    }
    

    
    
}
