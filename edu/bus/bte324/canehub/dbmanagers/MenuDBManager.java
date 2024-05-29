package edu.bus.bte324.canehub.dbmanagers;

import edu.bus.bte324.canehub.entities.*;
import edu.bus.bte324.canehub.infoclasses.MenuInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class MenuDBManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "CaneEats";
    private static final String SCHEMA_USER = ""; //TODO Use your username
    private static final String SCHEMA_PASSWORD = ""; //TODO Use Your Password

    private static final String Driver_URL = "com.mysql.cj.jdbc.Driver";

    public HashSet<RestaurantMenu>  getAllRestaurantItemsFromDB(int restaurantID)throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        HashSet<RestaurantMenu> restaurantMenuSet = new HashSet<RestaurantMenu>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MENU WHERE RESTAURANTID=? ");
            ps.setInt(1,restaurantID);
       

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int menuID = Integer.parseInt(resultSet.getString("menuID"));
                restaurantID = Integer.parseInt(resultSet.getString("RestaurantID"));
                String itemName = resultSet.getString("ItemName");
                String description = resultSet.getString("description");
                double price = Double.parseDouble(resultSet.getString("Price"));

                RestaurantMenu rm = new RestaurantMenu(restaurantID,menuID);
                rm.setMenuItem(new MenuInfo(itemName, price, description,menuID,restaurantID));
                restaurantMenuSet.add(rm);
                System.out.println(rm);
            }
                
            }

         catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return restaurantMenuSet;
        }


    }

    public int insertSingleMenuItemIntoDB(RestaurantMenu rm) throws Exception
    {
        HashSet<RestaurantMenu> rmenu = new HashSet<RestaurantMenu>();
        rmenu.add(rm);
        return insertRestaurantMenuItemIntoDB(rmenu); //Wrapper
    }


    public int insertRestaurantMenuItemIntoDB(HashSet<RestaurantMenu> restaurantMenuSet) throws Exception
         {
            Class.forName(Driver_URL);

            Connection connection = null;

            int count = 0;

            try {
                connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
                PreparedStatement ps = connection.prepareStatement("INSERT INTO MENU VALUES (0,?,?,?,?)");

                for(RestaurantMenu rm : restaurantMenuSet)
                {
                    ps.setInt(1,rm.getRestaurantID());
                    ps.setString(2,rm.getMenuItem().getItemName());
                    ps.setString(3,rm.getMenuItem().getDescription());
                    ps.setDouble(4,rm.getMenuItem().getPrice());

                    count += ps.executeUpdate();

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
                return count;
            }
    }

    public int deleteRestaurantItemByName(int restaurantID, String itemName) throws Exception {
        Class.forName(Driver_URL);
        Connection connection = null;
        int count = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM MENU WHERE RESTAURANTID = ? AND ITEMNAME =?");

            ps.setInt(1, restaurantID);
            ps.setString(2, itemName);
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

    public boolean checkIfMenuItemExists(int restaurantID, String itemName) throws Exception {
        Class.forName(Driver_URL);
        Connection connection = null;
        boolean exists = false;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM MENU WHERE RESTAURANTID = ? AND ITEMNAME=?");

            
            ps.setInt(1, restaurantID);
            ps.setString(2, itemName);
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

    public RestaurantMenu  getARestaurantItemFromDB(int restaurantID, int menuID)throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;

        RestaurantMenu rm = null;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MENU WHERE RESTAURANTID=? AND MENUID =? ");
            ps.setInt(1,restaurantID);
            ps.setInt(2,menuID);
       

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                menuID = Integer.parseInt(resultSet.getString("menuID"));
                restaurantID = Integer.parseInt(resultSet.getString("RestaurantID"));
                String itemName = resultSet.getString("ItemName");
                String description = resultSet.getString("description");
                double price = Double.parseDouble(resultSet.getString("Price"));

                rm = new RestaurantMenu(restaurantID,menuID);
                rm.setMenuItem(new MenuInfo(itemName, price, description,menuID,restaurantID));
                
                System.out.println(rm);
            }
                
            }

         catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return rm;
        }


    }

    public static void main(String[] args) throws Exception{

        MenuDBManager mdm = new MenuDBManager();

        mdm.getARestaurantItemFromDB(3, 5);

        //cdm.insertCustomersIntoDB(customerSet);


    }

}
