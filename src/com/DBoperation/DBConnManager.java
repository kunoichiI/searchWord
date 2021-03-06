package com.DBoperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnManager {

	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		
		String hostName = "localhost";
		String dbName = "searchWord";
		String userName = "root";
		String password = "yuriko";
		
		return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try {
			// Declare the class Driver for MySQL DB
			Class.forName("com.mysql.jdbc.Driver");
		
			// URL Connection for MySQL
			// Example: jdbc:mysql://localhost:3306/searchWord
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
			if (conn != null) {
				System.out.println("Connect to MySQL database!");
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
