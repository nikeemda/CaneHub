package edu.bus.bte324.canehub.entities;

import edu.bus.bte324.canehub.infoclasses.AddressInfo;
import edu.bus.bte324.canehub.infoclasses.PaymentInfo;

public class Customer {
    private String customerName;
    private AddressInfo customerAddress;
    private String phoneNumber;
    private PaymentInfo customerPayment;
    private int customerID;
    
    

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Customer(String customerName, String phoneNumber) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

    public Customer(int customerID, String customerName, String phoneNumber){
        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        
    }

    public int getCustomerID(){
        return customerID;
    }

    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public AddressInfo getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(AddressInfo customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PaymentInfo getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(PaymentInfo customerPayment) {
        this.customerPayment = customerPayment;
    }

   @Override
   public String toString() {
      return "Customer{" +
              " customerID='" + customerID + '\'' +
              ", customerName='" + customerName + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", customerAddress=" + customerAddress + '\''+
              ", customerPayment=" + customerPayment +
              '}';
   }
   
   
   public String toStringForFile(){
       return customerID + ", "+ customerName + "," + phoneNumber + " , "+  customerAddress.toStringForFile() + " , "+customerPayment.toStringForFile();
   }
}


