package edu.bus.bte324.canehub.controller;

import edu.bus.bte324.canehub.appdata.CustomersData;
import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.utilities.AddressInputUtility;
import edu.bus.bte324.canehub.utilities.PaymentInputUtility;
import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;

import java.util.Scanner;

public class CustomerInfoManager {

    private CustomersData cd = new CustomersData();
    private CustomerDBManager cdm = new CustomerDBManager();

    public void manage() {
        System.out.println("Customer Management Page");
        System.out.println("1. Add Customer");
        System.out.println("2. Edit Customer Info");
        System.out.println("3. Delete Customer");
        System.out.println("4. Print All Customers");
        System.out.println("5: Go Back To Main Page");

        Scanner custScanner = new Scanner(System.in);
        int choice = custScanner.nextInt();
 
        switch(choice)
        {
            case 1:
                System.out.println("Creating a new Customer");
                Scanner custInfoScanner = new Scanner(System.in);
                System.out.print("Input Customer Name :");
                String custName = custInfoScanner.nextLine();
                System.out.print("Input Customer Phone :");
                String custPhone = custInfoScanner.nextLine();

                Customer c = new Customer(custName,custPhone);

               // AddressInputUtility au = new AddressInputUtility()
                c.setCustomerAddress(AddressInputUtility.getAddressInfoFromConsole());

                c.setCustomerPayment(PaymentInputUtility.getPaymentInfoFromConsole());
                System.out.println("New Customer Created Successfully : "+c.toStringForFile());
                cd.addCustomer(c);
                break;
            
            case 2:
                System.out.println("Input Customer Name to Edit: ");
                Scanner custEditScanner = new Scanner(System.in);
                String customerToEdit = custEditScanner.nextLine();
                System.out.println("Input Customer Phone to Edit: ");
                String customerPhoneEdit = custEditScanner.nextLine();

                try{
                    if (cdm.checkIfCustomerExists(customerToEdit, customerPhoneEdit)){
                        Customer cEdit = new Customer(customerToEdit,customerPhoneEdit);
                        cEdit.setCustomerAddress(AddressInputUtility.getAddressInfoFromConsole());

                        cEdit.setCustomerPayment(PaymentInputUtility.getPaymentInfoFromConsole());
                
                        System.out.println("Editing Customer...");
                        cd.updateCustomer(cEdit, cEdit);
                        System.out.println("Customer Edited Successfully!");
                    }
                    else {
                        throw new Exception("Customer Does Not Exist");
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                
                
                break;

            case 3:
                System.out.println("Input Customer Name to Delete: ");
                Scanner custDeleteScanner = new Scanner(System.in);
                String customerToDelete = custDeleteScanner.nextLine();
                System.out.println("Input Phone Accompanied by The Customer to Delete: ");
                String customerPhoneToDelete = custDeleteScanner.nextLine();
                
                Customer cDelete = new Customer(customerToDelete,customerPhoneToDelete);

                try{
                    if (cdm.checkIfCustomerExists(customerToDelete,customerPhoneToDelete)){
                        System.out.println("Deleting Customer...");
                        cd.removeCustomer(cDelete);
                        System.out.println("Customer Deleted!");
                    }
                    else{
                        throw new Exception("Customer Does Not Exist");
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());

                }
                
            case 4: 
                cd.displayAll();
                break;
            case 5:
                break;

            default: break;
        }
    }
}
