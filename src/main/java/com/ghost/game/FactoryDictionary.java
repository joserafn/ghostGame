package com.ghost.game;

import java.io.FileNotFoundException;

public class FactoryDictionary {
	
	// Dictionary factory.
	// Allows dictionary creation from different data sources.
	public IDictionary getDictionary(String dictType, String dictionaryFile) throws FileNotFoundException {
		switch (dictType) {
			case "TXT": return new DictionaryTxt(dictionaryFile);
			case "URL": return new DictionaryURL();
			default: return null;
		}
	}

}
