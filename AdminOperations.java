package project_bankApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminOperations {

    // Method to view all users
    public static void viewAllUsers() throws Exception {
        String query = "SELECT user_id, username, balance FROM users";
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            connection = SetConnection.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            System.out.println("User ID | Username | Balance");
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                double balance = rs.getDouble("balance");
                System.out.printf("%-8d | %-10s | %.2f\n", userId, username, balance);
            }
            
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }

    // Method to view all transactions
    public static void viewAllTransactions() throws Exception {
        String query = "SELECT transaction_id, user_id, transaction_type, amount FROM transactions";
        
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            connection = SetConnection.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            System.out.println("Transaction ID | User ID | Transaction Type | Amount");
            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                int userId = rs.getInt("user_id");
                String transactionType = rs.getString("transaction_type");
                double amount = rs.getDouble("amount");
                System.out.printf("%-15d | %-7d | %-16s | %.2f\n", transactionId, userId, transactionType, amount);
            }
            
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        }
    }
    
    // Additional admin-specific methods can go here
}

