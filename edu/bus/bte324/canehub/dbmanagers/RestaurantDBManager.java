package edu.bus.bte324.canehub.dbmanagers;

//import edu.bus.bte324.canehub.entities.Customer;
import edu.bus.bte324.canehub.entities.Restaurant;
import edu.bus.bte324.canehub.filemanagers.CustomerFileManager;
import edu.bus.bte324.canehub.infoclasses.AddressInfo;
//import edu.bus.bte324.canehub.infoclasses.PaymentInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class RestaurantDBManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "CaneEats";
    private static final String SCHEMA_USER = ""; //TODO Use your username
    private static final String SCHEMA_PASSWORD = ""; //TODO Use Your Password

    private static final String Driver_URL = "com.mysql.cj.jdbc.Driver";

    public HashSet<Restaurant> getAllRestaurantsFromDB() throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<Restaurant> restaurantsSet = new HashSet<Restaurant>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESTAURANT");

       

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Integer rID = Integer.parseInt(resultSet.getString("RestaurantID"));
                String rName = resultSet.getString("RestaurantName");
                String rPhone = resultSet.getString("RestaurantPhoneNumber");
                String rCuisine = resultSet.getString("RestaurantCuisine");
                String RestaurantAddress_FirstLine = resultSet.getString(5);
                String RestaurantAddress_SecondLine = resultSet.getString(6);
                String RestaurantAddress_State = resultSet.getString(7);
                String RestaurantAddress_City = resultSet.getString(8);
                String RestaurantAddress_Zip = resultSet.getString(9);
                
                if(rCuisine == null){
                    Restaurant r = new Restaurant(rID, rName, rPhone);
                    r.setRestaurantAddress(new AddressInfo(RestaurantAddress_FirstLine, RestaurantAddress_SecondLine, RestaurantAddress_State, RestaurantAddress_City, RestaurantAddress_Zip)); // The address object here is an unnamed object
                    restaurantsSet.add(r);
                    System.out.println(r);
                }
                else{
                    Restaurant r = new Restaurant(rID,rName,rCuisine, rPhone);
                    r.setRestaurantAddress(new AddressInfo(RestaurantAddress_FirstLine, RestaurantAddress_SecondLine, RestaurantAddress_State, RestaurantAddress_City, RestaurantAddress_Zip)); // The address object here is an unnamed object
                    restaurantsSet.add(r);
                    System.out.println(r);
                }
                  // New named customer object
                
                //r.setRestaurantPayment(new PaymentInfo(Customer_Payment_CardNumber, Customer_Payment_CardName, "VISA", Customer_Payment_CardCVV, Customer_Payment_CardExpiry));

                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return restaurantsSet;
        }
    }

    public int insertSingleRestaurantIntoDB(Restaurant r) throws Exception
    {
        HashSet<Restaurant> rset = new HashSet<Restaurant>();
        rset.add(r);
        return insertRestaurantsIntoDB(rset); //Wrapper
    }


    public int insertRestaurantsIntoDB(HashSet<Restaurant> restaurantSet) throws Exception
         {
            Class.forName(Driver_URL);

            Connection connection = null;

            int count = 0;

            try {
                connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
                PreparedStatement ps = connection.prepareStatement("INSERT INTO RESTAURANT VALUES (0,?,?,?,?,?,?,?,?)");

                for(Restaurant r : restaurantSet)
                {
                    ps.setString(1,r.getRestaurantName());
                    ps.setString(2,r.getRestaurantNumber());
                    ps.setString(3,r.getRestaurantCuisine());
                    ps.setString(4,r.getRestaurantAddress().getLine1Address());
                    ps.setString(5,r.getRestaurantAddress().getLine2Address());
                    ps.setString(6,r.getRestaurantAddress().getState());
                    ps.setString(7,r.getRestaurantAddress().getCity());
                    ps.setString(8,r.getRestaurantAddress().getZipCode());
                    

                    count += ps.executeUpdate();

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
                return count;
            }
    }

    public int deleteRestaurantByName(String restaurantName, String restaurantPhone) throws Exception {
        Class.forName(Driver_URL);
        Connection connection = null;
        int count = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM RESTAURANT WHERE RESTAURANTNAME = ? AND RESTAURANTPHONENUMNBER =?");

            ps.setString(1, restaurantName);
            ps.setString(2, restaurantPhone);
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

    public boolean checkIfRestaurantExists(String restaurantName, String restaurantPhone) throws Exception {
        Class.forName(Driver_URL);
        Connection connection = null;
        boolean exists = false;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM RESTAURANT WHERE RESTAURANTNAME = ? AND RESTAURANTPHONENUMBER=?");

            
            ps.setString(1, restaurantName);
            ps.setString(2, restaurantPhone);
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

    public boolean checkIfRestaurantExistsByID(int restaurantID) throws Exception {
        Class.forName(Driver_URL);
        Connection connection = null;
        boolean exists = false;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM RESTAURANT WHERE RESTAURANTID = ?");

            
            ps.setInt(1, restaurantID);
            
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

}
