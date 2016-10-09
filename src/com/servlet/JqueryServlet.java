package com.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if (word.equals("")) {
                word = "Word cannot be empty";
        } else {
                word = "This is : " + word;
        			//System.out.print(word);
        			
        			res = searchInFile(word);
        }
        response.setContentType("text/plain");
        response.getWriter().write(word);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}
	
	public int searchInFile(String word) throws IOException {
		int count = 0;
		String srcDir = "/Users/Stephanie/Documents/workspace/searchWord/txtfiles/";
		File folder = new File(srcDir);
		
		File[] listOfFiles = folder.listFiles();
		
		System.out.print(listOfFiles.length);
		if (listOfFiles.length > 0) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					BufferedReader a = new BufferedReader(new FileReader(srcDir + listOfFiles[i].getName()));
					String thisLine = null;
					while((thisLine = a.readLine()) != null) {
						String words = thisLine;
						if (words.equals(word)) {
							count++;
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



