package com.ghost.game;

public class Word {
	
	private String word;
	private int weigh;

	public Word(String word, int weigh) {
		this.word = word;
		this.weigh = weigh;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int getWeigh() {
		return weigh;
	}
	
	public void setWeigh(int weigh) {
		this.weigh = weigh;
	}
	
	@Override
	public String toString() {
		return this.word;
	}
}
