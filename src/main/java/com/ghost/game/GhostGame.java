package com.ghost.game;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ghost.game.Player.PlayerType;

public class GhostGame {
	
	private static final Logger logger = LogManager.getLogger(GhostGame.class);
	
	private final String DICTIONARY_FILE = "./src/main/resources/dictionaries/ghostGameDict.txt";
	
	// Minimun word length to start the game.
	static final int MIN_WORD_LENGTH = 3;
	static final int PLAYERS_NUMBER = 2;
	
	private String wordFragment;
	private List<Word> matches;
	private IDictionary dictionary;	
	private Integer round = 0; 
	private PlayerSet players;
	private String message;
 
    public GhostGame() throws GhostException {
    	
    	players = new PlayerSet(PLAYERS_NUMBER);	
    	this.dictionary = loadTxtDictionary();
    	this.message = "";
    	this.wordFragment = "";
    }
    
    private IDictionary loadTxtDictionary() {
		IDictionary dictionary = null;
		try {
			FactoryDictionary dictFactory = new FactoryDictionary();
			dictionary = dictFactory.getDictionary("TXT", DICTIONARY_FILE);
			
		} catch (FileNotFoundException e) {
			logger.error("Error creating the dictionary: " + e.getMessage());
		}
		return dictionary;
	}
    
	public void newGhostGame() throws GhostException {
		this.wordFragment = "";
		this.message = "";
		this.players.resetPlayers();
	}
	
	public void newGhostGameRound() throws GhostException {
		this.wordFragment = "";
		this.message = "";
		this.round += 1;
		players.initializePlayerTurns();
	}
    
    // Add a new letter to the current fragment
	public Integer addLetter(char letter) {
		
		Integer result;
		
		// Add the letter to the fragment and check validity
		this.wordFragment = new StringBuilder(wordFragment).append(letter).toString().toUpperCase();
		
		result = checkFragment();
		
		// If the fragment is valid, change the player turn (Machine)
		if (result == 0) {
			
			players.changePlayerTurn();
		
			if (players.getActivePlayer().getPlayerType() == PlayerType.MACHINE ) {
				// After adding a valid letter, the next player is a machine, the machine will try to add the next letter.
				this.wordFragment = new StringBuilder(wordFragment).append(getLetter(wordFragment)).toString();
				result = checkFragment();
			}
		} 
		
		if (result != 0) {
			// The player lose
			players.getActivePlayer().increaseScore();
		} else {
			players.changePlayerTurn();
		}
		
		return result;
	}
	
	private void rankWords(String wordFragment) {
		
		// Lower ranking value to losing words (word.length = wordFragment.length + 1)
		matches.forEach(s -> {
			if (s.getWord().length() == wordFragment.length()+1)
				s.setWeigh(-1);
		});
		
		// As the computer will be always the second player, we will rank higher words with odd number of letters
		matches.forEach(s -> {
			if (s.getWeigh() != 1 ) // Words already ranked
				if ((s.getWord().length() & 1) == 0) 
					s.setWeigh(1);
		});
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	private List<Word> getMaxRankedWords() {
		
		Optional<Word> maxWordRank = matches
							      .stream()
							      .max(Comparator.comparing(Word::getWeigh));

		Integer maxRank = maxWordRank.get().getWeigh();
		
		return matches.stream()
					.filter(s -> s.getWeigh() == maxRank)
					.collect(Collectors.toList());
	}

	private char getNexLetter(String wordFragment) {
		
		// Given the existing word list of possibles full words, we are
		// going to assign a weigh to each word.
		// This weigh will be calculated to maximize the winning possibilities.
		rankWords(wordFragment);
		 
		// Get randomly between the most optimal words
		List<Word> possibleWords = getMaxRankedWords();
		
		Integer pos = getRandomNumberInRange(1, possibleWords.size());
		return possibleWords.get(pos-1).getWord().charAt(wordFragment.length());
	}
	
	// Method to find a letter to add to the current fragment
	// without creating a valid word.
	private char getLetter(String wordFragment) {
		// Find words in the dictionary matching the current fragment
		matches = dictionary.lookUpMatchingWords(wordFragment);
		return getNexLetter(wordFragment);
	}
	
	// Returns:
	// 0 if the fragment is valid 
	// 1 if the fragment does not belong to any existing word.
	// 2 if the fragment already is an existing word.
	public Integer checkFragment() {
		
		// If the current fragment is no part of any word, the player lose.
		if (dictionary.lookUpMatchingWords(wordFragment).isEmpty()) {
			message = "The fragment \""+wordFragment+"\" is not part of any valid word.";
			return 1;
		}
		
		// If the fragment (3 or more letters) already is a valid word, the player lose.
		if (wordFragment.length()>=MIN_WORD_LENGTH) {
			if (dictionary.lookUpWord(wordFragment)) {
				message = "The word \""+wordFragment+"\" exists in the dictionary.";
				return 2;
			}
		}
		return 0;
	}
	
	//
	
	public String getWordFragment() {
    	return wordFragment;
    }
	
	public Integer getScorePlayerHuman() {
		// Player with order 1 is the human
		return players.getPlayer(1).getScore();
	}
	
	public Integer getScorePlayerMachine() {
		// Player with order 2 is the machine
		return players.getPlayer(2).getScore();
	}
	
	public Integer getRound() {
		return this.round;
	}
	
	public void nextRound() {
		this.round += 1;
	}
	
	public String getMessage() {
		return this.message;
	}
}
