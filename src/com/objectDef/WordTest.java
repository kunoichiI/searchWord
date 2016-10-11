package com.objectDef;

import static org.junit.Assert.*;

import org.junit.*;

public class WordTest {
	private Word item;
	private String word = "slice";
	private int occurence;
	private int calledTimes;
	
	@Before
	public void setUp() throws Exception {
		item = new Word(word);
		item.setOccurence(10);
		item.setCalledTimes(5);
	}
	
	@Test
	public void testToString() {
		assertEquals("Test output string", "[" + "word:slice,  occurence: " + 10 + ",  " + "calledTimes: " + 5 + "]", item.toString());
	}
	
	@Test
	public void testWord() {
		assertEquals(item.getOccurence(), 10);
		assertEquals(item.getCalledTimes(), 5);
	}

}
