package com.ghost.game;

import java.util.List;

public interface IDictionary {
	
	public Boolean lookUpWord(String word);
	public List<Word> lookUpMatchingWords(String substring);
	public Integer getDictionarySize();

}
