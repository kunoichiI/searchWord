package com.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBoperation.DBConnManager;
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
        if (word.equals("")) {
                word = "Word cannot be empty";
        } 
        
        // Connect to MySQL database "searchWord"
        Word wordItem = new Word(word);
        Connection conn = null;
        Word tmp =  DBUtils.getWordbyName(conn, wordItem);
        System.out.print(tmp.toString());
		wordItem.setOccurence(tmp.getOccurence());
		wordItem.setCalledTimes(tmp.getCalledTimes());
		
        System.out.print(wordItem.toString());
        response.setContentType("text/plain");
        if (wordItem != null) {
        		
        		response.getWriter().write(wordItem.toString());
        }
        		//else {
//        		res = searchInFile(word);
//        		
//        		System.out.print(res);
//        		DBUtils.insertWordItem(conn, word, res, calledTimes);
//        		response.getWriter().write(word + String.valueOf(res) + 1);
//        }    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public int searchInFile(String word) throws IOException {
		int count = 0;
		String srcDir = "/Users/Stephanie/Documents/workspace/searchWord/txtfiles/";
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
			System.out.print("the total is: " + count);
		}
		return count;
	}

}



