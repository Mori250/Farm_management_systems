package user;

import java.sql.*;
import javax.swing.JOptionPane;

import Home.home;
import login.user_login;
import production.productions;

public class user {

    private String username;
    private String name;
    private String password;

    // Constructors

    public user() {
        // Default constructor
    }

    public user(String username, String password, String name) {
    	this.name = name;
        this.username = username;
        this.password = password;
    }

    // Setters
    
    public void setName( String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    // Getters
    
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

        String sql = "INSERT INTO user (name, username, password) VALUES (?, ?, ?)";

        try (
        		
            Connection con = DriverManager.getConnection(host, user, password);

            // Create a prepared statement
        		
            PreparedStatement stm= con.prepareStatement(sql);
        ) {
            // Set the values for the prepared statement
        	
           stm.setString(1, this.name);
           stm.setString(2, this.username);
            stm.setString(3, this.password);

            // Execute the query
            int rowsAffected = stm.executeUpdate();

            // Check the result
            if (rowsAffected > 0) {
            	user_login ad = new user_login();
				ad.main(new String[0]);
//            	JOptionPane.showMessageDialog(null, "Loged in successfully!","After login",JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                System.out.println("Failed to insert data.");
                JOptionPane.showMessageDialog(null, "Failed to insert data.!","After insert",JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }	
    }
    
    public void login() {
    	makeconnection();
    	String host = "jdbc:mysql://localhost/farmer_management_system";
        String user = "root";
        String password = "";
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        
    	try(	
    			Connection con = DriverManager.getConnection(host, user, password);
            		
                PreparedStatement stm= con.prepareStatement(sql);
    			
    			) {
    		
    		stm.setString(1, this.username);
    		stm.setString(2, this.password);
    		ResultSet rs = stm.executeQuery();
    		if(rs.next()) {
//    			System.out.println("Loged in successfully!");
//                JOptionPane.showMessageDialog(null, "Loged in successfully!","After login",JOptionPane.INFORMATION_MESSAGE);
                home ad = new home();
					ad.main(new String[0]);
    		} else {
    			JOptionPane.showMessageDialog(null, "Incorect User name and password!","After login",JOptionPane.INFORMATION_MESSAGE);
    		}
    		con.close();
    		
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

	
}
