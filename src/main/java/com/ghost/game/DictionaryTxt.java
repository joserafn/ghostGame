package com.ghost.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DictionaryTxt implements IDictionary {

	// Minimun word length contained by the dictionary
	static final int MIN_WORD_LENGTH = 3;

	// This the most simple approach. The right approach would be using a Directed Acyclic Words Graph
	List<Word> wordList = new ArrayList<Word>();

	public DictionaryTxt(String dictionaryFile) throws FileNotFoundException {

		Scanner s = new Scanner(new File(dictionaryFile));

		while (s.hasNext()){

			// Preventive line cleaning
			String nextWord = s.next().toString().trim().toUpperCase();

			if (nextWord.length() >= MIN_WORD_LENGTH ) {
				
				Word w = new Word(nextWord, 0); // Weigh 0 by default. 
				wordList.add(w); 
			}

		}
		s.close();

	}

	// Returns the dictionary total number of words
	@Override
	public Integer getDictionarySize() {
		return this.wordList.size();
	}
	
	// Search for a complete word into the dictionary returning true if found and false if not.
	@Override
	public Boolean lookUpWord(String w) {
		
		Optional<Word> match = wordList.stream()
				              .filter(p -> p.getWord().equalsIgnoreCase(w))
				              .findFirst();
		
		return match.isPresent();
	}
	
	// Search into the dictionary for words starting by substring
	@Override
	public List<Word> lookUpMatchingWords(String substring) {

		return wordList.stream()
					.filter(s -> s.getWord().startsWith(substring.toUpperCase()))
					.collect(Collectors.toList());
	}

}
