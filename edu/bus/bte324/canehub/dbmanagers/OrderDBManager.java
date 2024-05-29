package edu.bus.bte324.canehub.dbmanagers;

import edu.bus.bte324.canehub.appdata.OrderData;
import edu.bus.bte324.canehub.entities.Order;
//import edu.bus.bte324.canehub.entities.RestaurantMenu;
import edu.bus.bte324.canehub.infoclasses.MenuInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class OrderDBManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "CaneEats";
    private static final String SCHEMA_USER = ""; //TODO Use your username
    private static final String SCHEMA_PASSWORD = ""; //TODO Use Your Password

    private static final String Driver_URL = "com.mysql.cj.jdbc.Driver";

    public int insertCompleteOrder(int customerID, int restaurantID, double totalPrice, HashSet<MenuInfo> orderItems) throws Exception {
        
        Class.forName(Driver_URL);

        Connection connection = null;
        int orderID = 0;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement( "INSERT INTO Orders VALUES (0, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS); 
            
            ps.setInt(1, customerID);
            ps.setInt(2, restaurantID);
            ps.setDouble(3, totalPrice);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                orderID = Integer.parseInt(rs.getString(1));
            }

            PreparedStatement psItem = connection.prepareStatement("INSERT INTO Order_Items VALUES (?,?,?,?)");
            for (MenuInfo item : orderItems) {
                psItem.setInt(1, orderID);
                psItem.setInt(2, item.getMenuID());
                psItem.setInt(3,item.getQuantity());
                psItem.setDouble(4, item.getPrice());
                psItem.addBatch();
            }
            psItem.executeBatch();
            

            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            return orderID;
            
        }
    }

    public void getOrdersFromDBbyCustomerID(int customerID) throws Exception {
        Class.forName(Driver_URL);

        Connection connection = null;
        

        //HashSet<Customer> customersSet = new HashSet<Customer>();

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT OD.ORDERID, CS.CUSTOMERNAME, GROUP_CONCAT(M.ITEMNAME SEPARATOR ', ') AS ITEMNAMES, OD.TOTALPRICE\n" + //
                                "FROM ORDERS OD\n" + //
                                "JOIN CUSTOMER CS ON OD.CUSTOMERID = CS.CUSTOMERID\n" + //
                                "JOIN ORDER_ITEMS ODI ON OD.ORDERID = ODI.ORDERID\n" + //
                                "JOIN MENU M ON M.MENUID = ODI.MENUID\n" + //
                                "WHERE CS.CUSTOMERID= ?\n" + //
                                "GROUP BY OD.ORDERID, CS.CUSTOMERNAME\n" + //
                                "ORDER BY OD.ORDERID");

            ps.setInt(1,customerID);
            


            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Integer orderID = Integer.parseInt(resultSet.getString("ORDERID"));
                String customerName = resultSet.getString("CUSTOMERNAME");
                String itemNames = resultSet.getString("ITEMNAMES");
                Double totalPrice = Double.parseDouble(resultSet.getString("TOTALPRICE"));

                
                System.out.println("Order ID = " + orderID + ", Customer Name: " + customerName + ", Name of Items: " + itemNames + ". Total Price: "+ totalPrice);
                
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
            
        }
    }

    public boolean checkIfOrderExists(int customerID) throws Exception {
        Class.forName(Driver_URL);
        Connection connection = null;
        boolean exists = false;

        try {
            connection = DriverManager.getConnection(DB_URL + SCHEMA, SCHEMA_USER, SCHEMA_PASSWORD);
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM ORDERS WHERE CUSTOMERID = ?");

            
            ps.setInt(1, customerID);
            
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
    
