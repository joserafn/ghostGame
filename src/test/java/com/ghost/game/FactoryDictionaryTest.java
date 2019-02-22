package com.ghost.game;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class FactoryDictionaryTest {
  
	private final String DICTIONARY_FILE = "./src/main/resources/dictionaries/scrabbleDictionary.txt";
    
	@Test
	public void returnsValidTxtDictionary() throws FileNotFoundException {
		
		FactoryDictionary dictFactory = new FactoryDictionary(); 
		IDictionary dictionary = dictFactory.getDictionary("TXT", DICTIONARY_FILE);
		
		assertNotNull(dictionary);
		assertNotSame(dictionary.getDictionarySize(), 0);
	}
	
	@Test(expected=FileNotFoundException.class)
	public void exceptionRaisesWithBadDictionaryLocation() throws FileNotFoundException {
		
		FactoryDictionary dictFactory = new FactoryDictionary(); 
		dictFactory.getDictionary("TXT", "badDictionary");
	}
	
	
}