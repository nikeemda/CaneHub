package edu.bus.bte324.canehub.appdata;

import java.util.HashSet;

import edu.bus.bte324.canehub.dbmanagers.CustomerDBManager;
import edu.bus.bte324.canehub.entities.Customer;

public class CustomersData {
    private HashSet<Customer> customerSet = new HashSet<Customer>();
    private CustomerDBManager cdm = new CustomerDBManager();


    public boolean addCustomer(Customer c)
    {
        boolean status = customerSet.add(c);
        try {
            cdm.insertSingleCustomerIntoDB(c);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return status;

    }

    public boolean removeCustomer(Customer c) 
    {

        try{
            if (cdm.checkIfCustomerExists(c.getCustomerName(), c.getPhoneNumber())){
                cdm.deleteCustomerByName(c.getCustomerName(),c.getPhoneNumber());
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
        return customerSet.remove(c);
        
    }

    public boolean updateCustomer(Customer oldValue, Customer newValue) 
    {   
        

        customerSet.remove(oldValue);
        boolean status = customerSet.add(newValue);
        try {
                cdm.deleteCustomerByName(oldValue.getCustomerName(),oldValue.getPhoneNumber());
                cdm.insertSingleCustomerIntoDB(newValue);
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
        System.out.println("Printing all the customers here:");

        try {
            for(Customer c:  cdm.getAllCustomersFromDB())
            {
                //System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
