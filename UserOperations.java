package project_bankApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperations {

    // Method to check the balance of a user by username
    public static double checkBalance(String username) throws Exception {
        String query = "SELECT balance FROM users WHERE username = ?";
        double balance = 0.0;
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            connection = SetConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                balance = rs.getDouble("balance");
            } else {
                throw new SQLException("User not found.");
            }
            
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
        
        return balance;
    }
    
    // Additional user-specific methods can go here
}

