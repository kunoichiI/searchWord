package com.DBoperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.objectDef.Word;

public class DBUtils {
	private Connection connection;
	
	public DBUtils() throws ClassNotFoundException, SQLException {
		connection = DBConnManager.getMySQLConnection();
	}
	
	public Word getWord(String str) {
		Word wordItem = new Word(str);
		String query = "select occurence, calledTimes from searchWord where word =" + str;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				wordItem.setOccurence(rs.getInt("occurence"));
				wordItem.setCalledTimes(rs.getInt("calledTimes"));
			}
			System.out.println(wordItem.toString());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return wordItem;
	}
}
