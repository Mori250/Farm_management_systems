package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class production_entity {

    private String production_name;
    private double quantity;
    private double unit_price;
    private double total_price;
    private int production_id;
    private String production_date;

    // Constructors

    public production_entity() {
        // Default constructor
    }

    public production_entity(String production_name, double quantity, double unit_price, String production_date) {
        this.production_name = production_name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.total_price = quantity * unit_price;
        this.production_date = production_date;
    }

    // Setters

    public void setProduction_name(String production_name) {
        this.production_name = production_name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        this.total_price = this.unit_price * this.quantity;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
        this.total_price = this.unit_price * this.quantity;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setProduction_id(int production_id) {
        this.production_id = production_id;
    }

    public void setProduction_date(String production_date) {
        this.production_date = production_date;
    }

    // Getters

    public String getProduction_name() {
        return production_name;
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

    public int getProduction_id() {
        return production_id;
    }

    public String getProduction_date() {
        return production_date;
    }

    // Database operations

    public void insertData() {
        String url = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO production (product_name, quantity, unit_price, total_price, production_date) VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setString(1, this.production_name);
            stm.setDouble(2, this.quantity);
            stm.setDouble(3, this.unit_price);
            stm.setDouble(4, this.total_price);
            stm.setString(5, this.production_date);

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

    public void delete(int production_id) {
        String url = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";

        String sql = "DELETE FROM production WHERE production_id = ?";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setInt(1, production_id);

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

    public void update(int production_id) {
        String url = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";

        String sql = "UPDATE production SET production_name = ?, quantity = ?, unit_price = ?, total_price = ?, production_date = ? WHERE production_id = ?";

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setString(1, this.production_name);
            stm.setDouble(2, this.quantity);
            stm.setDouble(3, this.unit_price);
            stm.setDouble(4, this.total_price);
            stm.setString(5, this.production_date);
            stm.setInt(6, production_id);

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
