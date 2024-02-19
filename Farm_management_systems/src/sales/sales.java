package sales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class sales {

    private String product_name;
    private double quantity;
    private double unit_price;
    private double total_price;
    private int sales_id;
    private String sales_date;

    // Constructors

    public sales() {
        // Default constructor
    }

    public sales(String product_name, double quantity, double unit_price, String sales_date) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.total_price = quantity * unit_price; // Calculating total price
        this.sales_date = sales_date;
    }

    // Setters

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        // Recalculate total price whenever quantity changes
        this.total_price = this.unit_price * this.quantity;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
        // Recalculate total price whenever unit price changes
        this.total_price = this.unit_price * this.quantity;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setSales_id(int sales_id) {
        this.sales_id = sales_id;
    }

    public void setSales_date(String sales_date) {
        this.sales_date = sales_date;
    }

    // Getters

    public String getProduct_name() {
        return product_name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public int getSales_id() {
        return sales_id;
    }

    public String getSales_date() {
        return sales_date;
    }

    // Database operations

    public void insertData() {
        String url = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO sales (product_name, quantity, unit_price, total_price, sales_date) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setString(1, this.product_name);
            stm.setDouble(2, this.quantity);
            stm.setDouble(3, this.unit_price);
            stm.setDouble(4, this.total_price);
            stm.setString(5, this.sales_date);

            int rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data inserted successfully!", "After insert", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Data inserted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert data!", "After insert", JOptionPane.ERROR_MESSAGE);
                System.out.println("Failed to insert data.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int sales_id) {
        String url = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";

        String sql = "DELETE FROM sales WHERE sales_id = ?";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setInt(1, sales_id);

            int rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data deleted successfully!", "After delete", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Data deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete data!", "After delete", JOptionPane.ERROR_MESSAGE);
                System.out.println("Failed to delete data. No matching record found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int sales_id) {
        String url = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";

        String sql = "UPDATE sales SET product_name = ?, quantity = ?, unit_price = ?, total_price = ?, sales_date = ? WHERE sales_id = ?";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setString(1, this.product_name);
            stm.setDouble(2, this.quantity);
            stm.setDouble(3, this.unit_price);
            stm.setDouble(4, this.total_price);
            stm.setString(5, this.sales_date);
            stm.setInt(6, sales_id);

            int rowsAffected = stm.executeUpdate();

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
