package project_bankApplication;

import java.sql.* ;

public class UserRegistration {
	public static boolean registerUser(String username,String password,String email,String phone) throws Exception{
		
		Connection connection = null;
	    PreparedStatement ps = null;
		try {
			connection = SetConnection.getConnection();
	        String query = "INSERT INTO users (username, password_hash, email, phone) VALUES (?, ?, ?, ?)";
	        ps = connection.prepareStatement(query);
	        String hashedPassword = PasswordUtils.hashPassword(password);
	        
	        ps.setString(1, username);
	        ps.setString(2, hashedPassword); // Store hashed password
	        ps.setString(3, email);
	        ps.setString(4, phone);

	        int rowsInserted = ps.executeUpdate();
	        return rowsInserted > 0;
		}catch (SQLException e) {
	        System.out.println("Error registering user: " + e.getMessage());
	        throw e;
	    } finally {
	        // Close resources manually
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                System.out.println("Error closing PreparedStatement: " + e.getMessage());
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                System.out.println("Error closing Connection: " + e.getMessage());
	            }
	        }
	    }
	}
}
