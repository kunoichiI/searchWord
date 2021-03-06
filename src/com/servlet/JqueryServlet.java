package com.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.DBoperation.DBUtils;
import com.objectDef.Word;

/**
 * Servlet implementation class JqueryServlet
 */
@WebServlet("/JqueryServlet")
public class JqueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String word = request.getParameter("word");
		int res = 0;
		int calledTimes = 1;
        if (word.equals("")) { // if no word being inputted..
                word = "Word cannot be empty";
                response.setContentType("text/plain");
                response.getWriter().write(word);
        }else {
        
        		// Connect to MySQL database "searchWord"
        		Word wordItem = new Word(word);
        		Connection conn = null;
        		Word tmp = null;
        
        		// Query database for words which have been searched before, if input word has never been searched, start searching internal resouce text files
        		try {
        			tmp = DBUtils.getWordbyName(conn, wordItem);
        			if (tmp != null) {
        				tmp = DBUtils.updateAndGet(conn, wordItem);
        				System.out.println(tmp.toString());
        				wordItem.setOccurence(tmp.getOccurence());
        				wordItem.setCalledTimes(tmp.getCalledTimes());
        			}
        		} catch (ClassNotFoundException | SQLException e) {
        			e.printStackTrace();
        		}
        
        		response.setContentType("text/plain");
        
        		if (tmp != null) {
        		
        			response.getWriter().write(wordItem.toString());
        		}else {
        			res = searchInFile(word);
        		
        			res = searchInFile(word);
            		
        			System.out.print(res);
        			DBUtils.insertWordItem(conn, word, res, calledTimes);
        			//response.getWriter().write("[word:" + word + ",  " + " occurence: " + String.valueOf(res) + ",  " + " calledTimes: "+ 1 + "]");
        			response.getWriter().write("[word:" + word + ",  " + " occurence: " + String.valueOf(res) + ",  " + " calledTimes: "+ 1 + "]");
        		}  
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public int searchInFile(String word) throws IOException {
		int count = 0;
		String srcDir = "txtfiles";
		File folder = new File(srcDir);
		
		File[] listOfFiles = folder.listFiles();
		
		//System.out.print(listOfFiles.length);
		if (listOfFiles.length > 0) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					BufferedReader a = new BufferedReader(new FileReader(srcDir + listOfFiles[i].getName()));
					String thisLine = null;
					while((thisLine = a.readLine()) != null) {
						String words = thisLine;
						String[] listOfWords = words.split(" ");
						for (String str : listOfWords) {
							str = str.toLowerCase();
							if (str.equals(word.toLowerCase())) {
								count++;
							}
						}
					}
					a.close();
					
				}
			}
		}
		return count;
	}

}



