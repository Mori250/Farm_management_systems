package purchase;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;

import javax.swing.JOptionPane;

public class purchase {

    protected static final String Double = null;
	private String product_name;
    private double quantity;
    private double unit_price;
    private double total_price;
    private int purchase_id;
    private String purchase_date;

    // Constructors

    public purchase() {
        // Default constructor
    }

    public purchase(String name, String district, String sector) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.purchase_id = purchase_id;
    }

    // Setters

    public void setName(String product_name) {
        this.product_name = product_name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
    public void setTotal_price(double total_price) {
        this.total_price = unit_price * quantity;
    }
    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }
    
    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    // Getters

    public String getName() {
        return product_name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }
    public int getPurchase_id() {
        return purchase_id;
    }
    public void getPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }
    public void makeconnection() {
    	// JDBC URL, username, and password of MySQL server
        String host = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";
    }
    public void insertData() {
        String host = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO purchase (product_name,quantity,unit_price,total_price,purchase_date) VALUES (?, ?, ?,?,?)";

        try (
        		
            Connection con = DriverManager.getConnection(host, user, password);

            // Create a prepared statement
        		
            PreparedStatement stm= con.prepareStatement(sql);
        ) {
            // Set the values for the prepared statement
        	
           stm.setString(1, this.product_name);
           String quantityStr = String.valueOf(quantity);
           String unitPriceStr = String.valueOf(unit_price);
           String totalPriceStr = String.valueOf(unit_price * quantity);

           // Assuming stm is your PreparedStatement
           stm.setString(2, quantityStr);
           stm.setString(3, unitPriceStr);
           stm.setString(4, totalPriceStr);
           stm.setString(5, purchase_date);
            // Execute the query
            int rowsAffected = stm.executeUpdate();

            // Check the result
            if (rowsAffected > 0) {
            	System.out.println("Data inserted successfully!");
                JOptionPane.showMessageDialog(null, "Data inserted successfully!","After insert",JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to insert data.");
                JOptionPane.showMessageDialog(null, "Failed to insert data.!","After insert",JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }	
    }	

public void delete(int prison_id) {
	// JDBC URL, username, and password of MySQL server
    String url = "jdbc:mysql://localhost/farmer_management_system";
    String user = "root";
    String password = "";

    // SQL query to delete data
    String sql = "DELETE FROM purchase WHERE purchase_id = ?";

    try (
        // Establish the con
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a prepared statement
        PreparedStatement stm = con.prepareStatement(sql);
    ) {
        // Set the value for the WHERE clause
        stm.setInt(1, prison_id); // Assuming there is a column named 'id' for the WHERE clause

        // Execute the delete
        int rowsAffected = stm.executeUpdate();

        // Check the result
        if (rowsAffected > 0) {
        	JOptionPane.showMessageDialog(null, "Data delete successfully!","After insert",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Data deleted successfully!");
        } else {
        	JOptionPane.showMessageDialog(null, "Failed to delete data.!","After insert",JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to delete data. No matching record found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void update(int purchase_id) {
    // JDBC URL, username, and password of MySQL server
    String url = "jdbc:mysql://localhost/farmer_management_system";
    String user = "root";
    String password = "";

    // SQL query to update data
    String sql = "UPDATE purchase SET product_name = ?, quantity = ?, unit_price = ?, total_price = ?, purchase_date = ? WHERE purchase_id = ?";

    try (
        // Establish the connection
        Connection con = DriverManager.getConnection(url, user, password);

        // Create a prepared statement
        PreparedStatement stm = con.prepareStatement(sql);
    ) {
        // Set the new values for the update
        stm.setString(1, this.product_name);
        stm.setDouble(2, this.quantity);
        stm.setDouble(3, this.unit_price);
        stm.setDouble(4, this.total_price);
        stm.setString(5, this.purchase_date);
        stm.setInt(6, purchase_id);

        // Execute the update
        int rowsAffected = stm.executeUpdate();

        // Check the result
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Data updated successfully!", "After update", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Data updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update data!", "After update", JOptionPane.ERROR_MESSAGE);
            System.out.println("Failed to update data. No matching record found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
