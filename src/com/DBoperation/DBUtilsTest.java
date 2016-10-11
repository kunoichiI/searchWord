package com.DBoperation;

import static org.junit.Assert.*;
import java.sql.Connection;
import org.junit.*;
import com.objectDef.Word;
import junit.framework.TestCase;

public class DBUtilsTest extends TestCase {
	private int lady_call = 0, smith_call = 0;
	Connection myConn;
	protected void setUp() throws Exception {
		super.setUp();
		myConn = DBConnManager.getMySQLConnection();
		myConn.setAutoCommit(false);
	}
	
	@Test 
	public void testDAO() throws Exception {
		Word lady = new Word("lady");
		Word smith = new Word("smith");
		
		smith_call = DBUtils.getWordbyName(myConn, smith).getCalledTimes();
		lady_call = DBUtils.getWordbyName(myConn, lady).getCalledTimes();
		assertEquals(1087, DBUtils.getWordbyName(myConn, lady).getOccurence());
		assertEquals(smith_call, DBUtils.getWordbyName(myConn, smith).getCalledTimes());
		
		DBUtils.insertWordItem(myConn, "john", 692, 1);
		DBUtils.insertWordItem(myConn, "out", 6347, 1);
		
		// Test insertAndGet
		
		assertEquals("Call lady again", ++lady_call, DBUtils.updateAndGet(myConn, lady).getCalledTimes());
		assertEquals("Call smith again", ++smith_call, DBUtils.updateAndGet(myConn, smith).getCalledTimes());	
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		myConn.commit();
		myConn.close();
	}
	
	
}

