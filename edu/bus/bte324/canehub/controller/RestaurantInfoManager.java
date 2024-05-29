package edu.bus.bte324.canehub.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.bus.bte324.canehub.entities.Restaurant;
import edu.bus.bte324.canehub.entities.RestaurantMenu;
import edu.bus.bte324.canehub.utilities.MenuInputUtility;
import edu.bus.bte324.canehub.utilities.AddressInputUtility;
import edu.bus.bte324.canehub.appdata.MenuData;
import edu.bus.bte324.canehub.appdata.RestaurantsData;
import edu.bus.bte324.canehub.dbmanagers.RestaurantDBManager;
import edu.bus.bte324.canehub.dbmanagers.MenuDBManager;

public class RestaurantInfoManager {
    private Restaurant r;
    private RestaurantDBManager rdm = new RestaurantDBManager();
    private RestaurantsData rd = new RestaurantsData();
    private MenuData md = new MenuData();
    private MenuDBManager mdm = new MenuDBManager();

    public void manage() {
        System.out.println("Restaurant Management Page");
        System.out.println("1. Add Restaurant");
        System.out.println("2. Edit Restaurant Info");
        System.out.println("3. Delete Restaturant");
        System.out.println("4. Add Item to Restaurant Menu");
        System.out.println("5. Edit Restaurant Menu Item");
        System.out.println("6: Delete Restaurant Menu Item");
        System.out.println("7. Show All Restaurants");
        System.out.println("8: Go Back To Main Page");
        

        Scanner restScanner = new Scanner(System.in);
        int choice = restScanner.nextInt();

        switch(choice)
        {
            case 1:
                System.out.println("Creating a new Restaurant");
                Scanner restInfoScanner = new Scanner(System.in);
                System.out.print("Input Restaurant Name :");
                String restName = restInfoScanner.nextLine();
                
                try{
                    System.out.println("Does This Restaurant Have a Cuisine? Enter y(yes) or n(no)");
                    String cuisineChoice = restInfoScanner.nextLine();
                    if(cuisineChoice.equals("y")){
                        System.out.print("Input Restaurant Cuisine :");
                        String restCuisine = restInfoScanner.nextLine();
                        System.out.print("Input Restaurant Phone :");
                        String restPhone = restInfoScanner.nextLine();
                        System.out.println("Input Restaurant Address");
                        r = new Restaurant(restName,restCuisine,restPhone);
                        r.setRestaurantAddress(AddressInputUtility.getAddressInfoFromConsole());
                        System.out.println("New Restaurant Created Successfully : "+r);
                        rd.addRestaurant(r);
                        break;
                    }

                    else if (cuisineChoice.equals("n")){
                        System.out.print("Input Restaurant Phone :");
                        String restPhone = restInfoScanner.nextLine();
                        System.out.println("Input Restaurant Address");
                        r = new Restaurant(restName,restPhone);
                        r.setRestaurantAddress(AddressInputUtility.getAddressInfoFromConsole());
                        System.out.println("New Restaurant Created Successfully : "+r);
                        rd.addRestaurant(r);
                        break;

                    }
                    else {
                        //System.out.println("That is not an option");
                        throw new Exception("Not an Option Try Again...");
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid Input!");
                    break;
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    break;

                }

                

            case 2:
                System.out.println("Input Restaurant Name to Edit: ");
                Scanner restEditScanner = new Scanner(System.in);
                String restaurantToEdit = restEditScanner.nextLine();
                System.out.println("Input Restaurant Phone to Edit: ");
                String restaurantPhoneEdit = restEditScanner.nextLine();

                try{
                    if (rdm.checkIfRestaurantExists(restaurantToEdit, restaurantPhoneEdit)){
                        Restaurant rEdit = new Restaurant(restaurantToEdit,restaurantPhoneEdit);
                        rEdit.setRestaurantAddress(AddressInputUtility.getAddressInfoFromConsole());

                        //cEdit.setCustomerPayment(PaymentInputUtility.getPaymentInfoFromConsole());
                
                        System.out.println("Editing Restaurant...");
                        rd.updateRestaurant(rEdit, rEdit);
                        System.out.println("Restaurant Edited Successfully!");
                    }
                    else {
                        throw new Exception("Restaurant Does Not Exist");
                        
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    break;
                }

                break;

            case 3:
                System.out.println("Input Restaurant Name to Delete: ");
                Scanner restDeleteScanner = new Scanner(System.in);
                String restaurantToDelete = restDeleteScanner.nextLine();
                System.out.println("Input Phone Accompanied by The Restaurant to Delete: ");
                String restaurantPhoneToDelete = restDeleteScanner.nextLine();
                    
                Restaurant rDelete = new Restaurant(restaurantToDelete,restaurantPhoneToDelete);
                    
                try{
                    if (rdm.checkIfRestaurantExists(restaurantToDelete,restaurantPhoneToDelete)){
                        System.out.println("Deleting Restaurant...");
                        rd.removeRestaurant(rDelete);
                        System.out.println("Restaurant Deleted!");
                    }
                    else{
                        throw new Exception("Restaurant Does Not Exist");
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());

                }
                break;
            
            case 4:
                
                Scanner menuScanner = new Scanner(System.in);
                System.out.println("Add Item to Restaurant Menu...\nInput RestaurantID");
                int restaurantID = menuScanner.nextInt();
                menuScanner.nextLine();
                System.out.println("Enter Item Name");
                String ItemName = menuScanner.nextLine();
                //rm.setMenuItem(MenuInputUtility.getMenuInfoFromConsole());
                try{
                    
                    if (mdm.checkIfMenuItemExists(restaurantID,ItemName)){
                        throw new Exception("Item Already Exists");
                        
                    }
                    else {
                        RestaurantMenu rm = new RestaurantMenu(restaurantID);
                        System.out.println("Re-Enter Item");
                        rm.setMenuItem(MenuInputUtility.getMenuInfoFromConsole());
                        md.addRestaurantItem(rm);
                        System.out.println("Item Succesfully Added");
                        
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;

            case 5:
                Scanner menuEditScanner = new Scanner(System.in);
                System.out.println("Edit Restaurant Menu Item...\nInput RestaurantID");
                int restaurantIDEdit = menuEditScanner.nextInt();
                menuEditScanner.nextLine();
                System.out.println("Enter Item Name");
                String editItemName = menuEditScanner.nextLine();
                
                
                //rmEdit.setMenuItem(MenuInputUtility.getMenuInfoFromConsole());
                try{
                    if (mdm.checkIfMenuItemExists(restaurantIDEdit,editItemName)){
                        RestaurantMenu rmEdit = new RestaurantMenu(restaurantIDEdit);
                        System.out.println("Re-Enter Item...");
                        rmEdit.setMenuItem(MenuInputUtility.getMenuInfoFromConsole());
                        md.updateRestaurantItem(rmEdit, rmEdit);
                        System.out.println("Menu Item Edited Successfully");
                    }
                    else{
                        throw new Exception("Menu Item Does Not Exist");
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());

                }
                break;
                

            case 6:
                Scanner menuDeleteScanner = new Scanner(System.in);
                System.out.println("Delete Restaurant Menu Item...\nInput RestaurantID");
                int restaurantIDDelete = menuDeleteScanner.nextInt();
                menuDeleteScanner.nextLine();
                System.out.println("Enter Item Name");
                String deleteItemName = menuDeleteScanner.nextLine();
                RestaurantMenu rmDelete = new RestaurantMenu(restaurantIDDelete);
                //rmEdit.setMenuItem(MenuInputUtility.getMenuInfoFromConsole());
                try{
                    if (mdm.checkIfMenuItemExists(restaurantIDDelete,deleteItemName)){
                        System.out.println("Deleting Item...");
                        md.removeRestaurantItem(rmDelete, deleteItemName);
                        System.out.println("Menu Item Deleted Successfully");
                    }
                    else{
                        throw new Exception("Menu Item Does Not Exist");
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());

                }
                break;
                

            case 7:
                rd.displayAll();
                break;

            case 8:
                break;


            default: break;
        }
    }
}
