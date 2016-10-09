package com.objectDef;

public class Word {
	private String word;
	private int occurence;
	private int calledTimes;
	
	public Word() {
		
	}
	
	public void setWord(String s) {
		this.word = s;
	}
	
	public void setOccurence(int i) {
		this.occurence = i;
	}
	
	public void setCalledTimes(int j) {
		this.calledTimes = j;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getOccurence() {
		return this.occurence;
	}
	
	public int getCalledTimes() {
		return this.calledTimes;
	}
}
