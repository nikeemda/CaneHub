package edu.bus.bte324.canehub.controller;
import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.Restaurant;
import edu.bus.bte324.canehub.entities.RestaurantMenu;

import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import edu.bus.bte324.canehub.appdata.RestaurantsData;
import edu.bus.bte324.canehub.dbmanagers.RestaurantDBManager;
import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import edu.bus.bte324.canehub.dbmanagers.MenuDBManager;
import edu.bus.bte324.canehub.dbmanagers.OrderDBManager;
import edu.bus.bte324.canehub.appdata.OrderData;
import edu.bus.bte324.canehub.infoclasses.MenuInfo;


public class OrderInfoManager {
    RestaurantsData rd = new RestaurantsData();
    MenuDBManager mdm = new MenuDBManager();
    RestaurantDBManager rdm = new RestaurantDBManager();
    CustomerDBManager cdm = new CustomerDBManager();
    OrderDBManager odm = new OrderDBManager();
    OrderData od = new OrderData();
    MenuInfo item = null;
    RestaurantMenu rm = null;


    public void manage() {
        System.out.println("Order Management Page");
        System.out.println("1. View All Restaurants");
        System.out.println("2. Select A Restaurant");
        System.out.println("3. View Current Order");
        System.out.println("4. Complete Purchase");
        System.out.println("5. View Your Orders");
        System.out.println("6. Go Back To Main Page");

        Scanner scnr = new Scanner(System.in);
        int choice = scnr.nextInt();
        

        switch(choice){
            case 1:
                System.out.println("Showing All Restaurants...");
                rd.displayAll();
                break;

            case 2:
                try{
                    
                    System.out.println("Input Restaurant ID to View Menu");
                    Scanner viewMenuScanner = new Scanner(System.in);
                    int viewMenuRestID = viewMenuScanner.nextInt();
                    viewMenuScanner.nextLine();

                    if(rdm.checkIfRestaurantExistsByID(viewMenuRestID)){
                        mdm.getAllRestaurantItemsFromDB(viewMenuRestID);
                        
                        System.out.println("Input The Menu ID of Item to Purchase...If You Want To Go Back to Main Page Input 999");
                        int itemBuying = viewMenuScanner.nextInt();
                        System.out.println("Enter The Quantity of That Item");
                        int itemQuantity = viewMenuScanner.nextInt();
                        if (itemBuying == 999){
                             viewMenuScanner.nextLine();
                            break;
                        }
                        else {
                            viewMenuScanner.nextLine();
                            rm = mdm.getARestaurantItemFromDB(viewMenuRestID, itemBuying);
                            od.addItemToOrder(rm.getMenuItem(),itemQuantity);
                            System.out.println("Item Added To Order");
                        }
                        
                    }
                    else{
                        throw new Exception("That Restaurant Does Not Exist!");
                    }
                }

                catch (Exception e){
                    System.out.println(e.getMessage());
                }



                break;

            case 3:
                System.out.println("Current Items in Cart...");
                od.itemsInCart();
                

                break;            

            case 4:
            try{
                System.out.println("Complete Your Purchase...\nEnter Customer Name and PhoneNumber to Check Out");
                Scanner purchaseScanner = new Scanner(System.in);
                System.out.println("Input Customer Full Name:");
                String custName = purchaseScanner.nextLine();
                System.out.println("Input Customer Phone:");
                String custPhone = purchaseScanner.nextLine();
                if (cdm.checkIfCustomerExists(custName, custPhone)){
                    int customerID = cdm.getCustomerFromDBbyName(custName, custPhone);
                    double finalPrice = od.completePurchase(customerID);
                    System.out.println("Order Submitted Your Total Price is: " + finalPrice);
                }
                else{
                    throw new Exception("That Customer Does Not Exist");
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

                break;
            
            case 5:
                try{
                    System.out.println("Enter Customer Name and PhoneNumber to View Your Orders");
                    Scanner getOrdersScanner = new Scanner(System.in);
                    System.out.println("Input Customer Full Name:");
                    String custName = getOrdersScanner.nextLine();
                    System.out.println("Input Customer Phone:");
                    String custPhone = getOrdersScanner.nextLine();
                    if (cdm.checkIfCustomerExists(custName, custPhone)){
                        try{
                            
                            int customerID = cdm.getCustomerFromDBbyName(custName, custPhone);
                            if (odm.checkIfOrderExists(customerID)){
                                System.out.println("Your Previous Orders...");
                                odm.getOrdersFromDBbyCustomerID(customerID);
                            }
                            else{
                                throw new Exception("Customer Has No Orders");
                            }
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }

                    }
                    else{
                        throw new Exception("That Customer Does Not Exist");
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }



                break;
            case 6:
                break;

        }

    }
    
}
