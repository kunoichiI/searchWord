package com.DBoperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			PreparedStatement stmt = null;
			String sql = "INSERT INTO word(word,occurence,calledTimes) VALUES(?,?,?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			stmt.setInt(2, occurence);
			stmt.setInt(3, calledTimes);
			stmt.executeUpdate();
			
			stmt.close();
		}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Word updateAndGet(Connection conn, Word wordItem) throws ClassNotFoundException, SQLException {
		if ((conn = DBConnManager.getMySQLConnection()) != null) {
			PreparedStatement update = null;
			PreparedStatement get = null;
			
			String sql1 = "Update word set calledTimes = calledTimes + 1 where word = ?";
			String sql2 = "Select * from word where word = ?";
			
			// Using JDBC Transaction(Update calledTimes before getting its row(for word that has been searched before, and being called currently
			try {
				conn.setAutoCommit(false);
				
				update = conn.prepareStatement(sql1);
				get = conn.prepareStatement(sql2);
				
				// Set parameters
				update.setString(1, wordItem.getWord());
				update.executeUpdate();
				get.setString(1, wordItem.getWord());
				get.executeQuery();
				wordItem.setCalledTimes(wordItem.getCalledTimes()+ 1);
				conn.commit();
				
			}catch (SQLException e) {
				conn.rollback();
			}finally {
				if (update != null) {
					update.close();
				}
				if (get != null) {
					get.close();
				}
				conn.setAutoCommit(true);
			}
		}
		return wordItem;
	}
}
