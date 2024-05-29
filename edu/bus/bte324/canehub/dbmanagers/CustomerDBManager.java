package edu.bus.bte324.canehub.dbmanagers;

import edu.bus.bte324.canehub.entities.Customer;
//import edu.bus.bte324.canehub.filemanagers.CustomerFileManager;
import edu.bus.bte324.canehub.infoclasses.AddressInfo;
import edu.bus.bte324.canehub.infoclasses.PaymentInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class CustomerDBManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "CaneEats";
    private static final String SCHEMA_USER = ""; //TODO Use your username
    private static final String SCHEMA_PASSWORD = ""; //TODO Use Your Password

    private static final String Driver_URL = "com.mysql.cj.jdbc.Driver";

    public HashSet<Customer> getAllCustomersFromDB() throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<Customer> customersSet = new HashSet<Customer>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CUSTOMER");

       

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Integer cID = Integer.parseInt(resultSet.getString("CustomerID"));
                String cName = resultSet.getString("CustomerName");
                String cPhone = resultSet.getString("CustomerPhone");
                String CustomerAddress_FirstLine = resultSet.getString(4);
                String CustomerAddress_SecondLine = resultSet.getString(5);
                String CustomerAddress_State = resultSet.getString(6);
                String CustomerAddress_City = resultSet.getString(7);
                String CustomerAddress_Zip = resultSet.getString(8);
                String Customer_Payment_CardNumber = resultSet.getString(9);
                String Customer_Payment_CardName = resultSet.getString(10);
                String Customer_Payment_CardExpiry = resultSet.getString(11);
                Integer Customer_Payment_CardCVV = Integer.parseInt(resultSet.getString(12).trim());

                Customer c = new Customer(cID,cName, cPhone);  // New named customer object
                c.setCustomerAddress(new AddressInfo(CustomerAddress_FirstLine, CustomerAddress_SecondLine, CustomerAddress_State, CustomerAddress_City, CustomerAddress_Zip)); // The address object here is an unnamed object
                c.setCustomerPayment(new PaymentInfo(Customer_Payment_CardNumber, Customer_Payment_CardName, "VISA", Customer_Payment_CardCVV, Customer_Payment_CardExpiry));

                customersSet.add(c); //Adding from DB to the application HashSet
                
                System.out.println(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return customersSet;
        }
    }

    public int getCustomerFromDBbyName(String name, String phoneNumber) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        int customerID = 0;

        //HashSet<Customer> customersSet = new HashSet<Customer>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE CUSTOMERNAME= ? AND CUSTOMERPHONE =?");

            ps.setString(1,name);
            ps.setString(2,phoneNumber);


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Integer cID = Integer.parseInt(resultSet.getString("CUSTOMERID"));
                
                customerID  = cID;
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return customerID;
        }
    }

    public int insertSingleCustomerIntoDB(Customer c) throws Exception
    {
        HashSet<Customer> cset = new HashSet<Customer>();
        cset.add(c);
        return insertCustomersIntoDB(cset); //Wrapper
    }


    public int insertCustomersIntoDB(HashSet<Customer> customerSet) throws Exception
         {
            Class.forName(Driver_URL);

            Connection connection = null;

            int count = 0;

            try {
                connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
                PreparedStatement ps = connection.prepareStatement("INSERT INTO CUSTOMER VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?)");

                for(Customer c : customerSet)
                {
                    ps.setString(1,c.getCustomerName());
                    ps.setString(2,c.getPhoneNumber());
                    ps.setString(3,c.getCustomerAddress().getLine1Address());
                    ps.setString(4,c.getCustomerAddress().getLine2Address());
                    ps.setString(5,c.getCustomerAddress().getState());
                    ps.setString(6,c.getCustomerAddress().getCity());
                    ps.setString(7,c.getCustomerAddress().getZipCode());
                    ps.setString(8,c.getCustomerPayment().getCardNumber());
                    ps.setString(9,c.getCustomerPayment().getCardName());
                    ps.setString(10,c.getCustomerPayment().getExpDate());
                    ps.setInt(11,c.getCustomerPayment().getCvvCode());
                    ps.setString(12,c.getCustomerPayment().getCardType());

                    count += ps.executeUpdate();

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
                return count;
            }
    }

    public int deleteCustomerByName(String customerName, String customerPhone) throws Exception {
        Class.forName(Driver_URL);
        Connection connection = null;
        int count = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMERNAME = ? AND CUSTOMERPHONE = ?");

            ps.setString(1, customerName);
            ps.setString(2,customerPhone);
            count = ps.executeUpdate();  // Execute the update and get the number of affected rows
        }

       catch (SQLException e) {
        e.printStackTrace();
       }
        finally {
            connection.close();
            return count;
        }
    }

    public boolean checkIfCustomerExists(String customerName, String custPhone) throws Exception {
        Class.forName(Driver_URL);
        Connection connection = null;
        boolean exists = false;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM CUSTOMER WHERE CUSTOMERNAME = ? AND CUSTOMERPHONE =?");

            
            ps.setString(1, customerName);
            ps.setString(2, custPhone);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                exists = resultSet.getInt(1) > 0;  // Execute the update and get the number of affected rows
            }

        }

       catch (SQLException e) {
        e.printStackTrace();
       }
        finally {
            connection.close();
            return exists;
        }
    }



    public static void main(String[] args) throws Exception{

        CustomerDBManager cdm = new CustomerDBManager();

        //cdm.getCustomersFromDBbyName("en");

        //cdm.insertCustomersIntoDB(customerSet);


    }

}
