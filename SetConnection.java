package project_bankApplication;

import java.sql.* ;

public class SetConnection {
	public static final String URL="jdbc:mysql://localhost:3306/projectBankApplication" ;
	public static final String USER="root" ;
	public static final String PASSWORD="Revelio@1112" ;
	
	public static Connection getConnection() throws SQLException{
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}
		catch (SQLException e){
			System.out.println("DataBase Connection Failed" + e.getMessage()) ;
			throw e ;
		}
	}
}
