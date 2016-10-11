package com.DBoperation;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return DBConnManager.getMySQLConnection();
	}
	
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		}catch (Exception e) {
			System.out.print("Database error in closing");
		}
	}
	
	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		}catch(Exception e) {
			System.out.print("Database rollback error");
		}
	}
}
