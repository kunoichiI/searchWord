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
	
	public static boolean exist(Connection conn, String word) {
		Boolean exist = false;
		try {
			if ((conn = DBConnManager.getMySQLConnection()) != null) {
				String sql = "Select * from word where word = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, word);
				ResultSet rs = ps.executeQuery();
				if (rs != null) {
					exist = true;
				}else {
					exist = false;
				}
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
	
	public static void insertWordItem(Connection conn, String word, int occurence, int calledTimes) {
		try {
			if ((conn = DBConnManager.getMySQLConnection()) != null) {
				// Check if word already exists in database or not
				if (exist(conn, word)) {
					return;
				} else {
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
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
