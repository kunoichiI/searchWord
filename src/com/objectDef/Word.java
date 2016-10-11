package com.objectDef;

public class Word {
	private String word;
	private int occurence;
	private int calledTimes;
	
	public Word(String str) {
		this.word = str;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public void setOccurence(int i) {
		this.occurence = i;
	}
	
	public void setCalledTimes(int j) {
		this.calledTimes = j;
	}
	
	public int getOccurence() {
		return this.occurence;
	}
	
	public int getCalledTimes() {
		return this.calledTimes;
	}
	
	@Override
	public String toString() {
		return "[" + "word:" + this.getWord() + ",  " + "occurence: " + this.getOccurence() + ",  " + "calledTimes: " + this.getCalledTimes() + "]";
	}
}
