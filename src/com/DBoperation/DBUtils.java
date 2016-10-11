package com.DBoperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.objectDef.Word;

public class DBUtils {
	public static Word getWordbyName(Connection conn, Word wordItem) throws SQLException, ClassNotFoundException{
			if ((conn = DBConnManager.getMySQLConnection()) != null) {
				//System.out.print("Connect to DB!");
				String sql = "Select * from word where word = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, wordItem.getWord());
				ResultSet rs = stmt.executeQuery();
				Boolean flag = rs.next();
				if (!flag) {
					wordItem = null;
				}else {
					wordItem.setOccurence(rs.getInt("occurence"));
					//System.out.print(rs.getInt("occurence"));
					wordItem.setCalledTimes(rs.getInt("calledTimes"));
					//System.out.print(rs.getInt("calledTimes"));
				
				rs.close();
				stmt.close();
				stmt = null;
			
				conn.close();
				conn = null;
				}
			}
		return wordItem;
	}
	
	public static void insertWordItem(Connection conn, String word, int occurence, int calledTimes) {
		try {
			if ((conn = DBConnManager.getMySQLConnection()) != null) {
				//System.out.print("Connect to DB!");
				String sql = "insert into word(word, occurence, calledTimes) values(?,?,?)";
				PreparedStatement stmt = conn.prepareStatement(sql);
				// Paprameters start with 1
				stmt.setString(1, word);
				stmt.setInt(2, occurence);
				stmt.setInt(3, calledTimes);
				stmt.executeUpdate();
			
				stmt.close();
				stmt = null;
			
				conn.close();
				conn = null;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}																																																																																																																																																																																																																																																																																																																																																																																																																																																															
	}
}
