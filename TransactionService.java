package project_bankApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionService {

    public static void deposit(String username, double amount) throws Exception {
        String updateQuery = "UPDATE users SET balance = balance + ? WHERE username = ?";
        String insertQuery = "INSERT INTO transactions (user_id, transaction_type, amount) VALUES ((SELECT user_id FROM users WHERE username = ?), 'deposit', ?)";

        Connection connection = null;
        PreparedStatement updateBalance = null;
        PreparedStatement logTransaction = null;

        try {
            connection = SetConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            updateBalance = connection.prepareStatement(updateQuery);
            updateBalance.setDouble(1, amount);
            updateBalance.setString(2, username);
            updateBalance.executeUpdate();

            logTransaction = connection.prepareStatement(insertQuery);
            logTransaction.setString(1, username);
            logTransaction.setDouble(2, amount);
            logTransaction.executeUpdate();

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Rollback in case of error
            }
            throw e;
        } finally {
            if (logTransaction != null) logTransaction.close();
            if (updateBalance != null) updateBalance.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    public static void withdraw(String username, double amount) throws Exception {
        String updateQuery = "UPDATE users SET balance = balance - ? WHERE username = ? AND balance >= ?";
        String insertQuery = "INSERT INTO transactions (user_id, transaction_type, amount) VALUES ((SELECT user_id FROM users WHERE username = ?), 'withdrawal', ?)";

        Connection connection = null;
        PreparedStatement updateBalance = null;
        PreparedStatement logTransaction = null;

        try {
            connection = SetConnection.getConnection();
            connection.setAutoCommit(false); // Start transaction

            updateBalance = connection.prepareStatement(updateQuery);
            updateBalance.setDouble(1, amount);
            updateBalance.setString(2, username);
            updateBalance.setDouble(3, amount); // Check for sufficient balance

            int rowsAffected = updateBalance.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Insufficient funds or user not found.");
            }

            logTransaction = connection.prepareStatement(insertQuery);
            logTransaction.setString(1, username);
            logTransaction.setDouble(2, amount);
            logTransaction.executeUpdate();

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Rollback in case of error
            }
            throw e;
        } finally {
            if (logTransaction != null) logTransaction.close();
            if (updateBalance != null) updateBalance.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
}






