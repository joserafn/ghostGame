package com.ghost.game;


import static org.junit.Assert.*;

import org.junit.Test;

public class GhostGameTest {
	
	
	
	@Test
	public void aValidNewGhostGameIsCreatedFromConstructor() throws GhostException {
		GhostGame newGG = new GhostGame();
		
		assertNotNull(newGG);
		assertTrue(newGG.getScorePlayerHuman() == 0);
	}

	@Test
	public void aValidNewGhostGameIsCreated() throws GhostException {
		GhostGame gg = new GhostGame();
		gg.newGhostGame();
		
		assertNotNull(gg);
		assertTrue(gg.getScorePlayerHuman() == 0);
		assertTrue(gg.getMessage().equals(""));
		assertTrue(gg.getWordFragment().equals(""));
	}

	@Test
	public void aNewGhostGameRoundIsCreated() throws GhostException {
		
		GhostGame gg = new GhostGame();
		gg.addLetter('A');
		Integer currentRound = gg.getRound();
		gg.newGhostGameRound();
		
		assertNotNull(gg);
		assertTrue(gg.getScorePlayerHuman() == 0);
		assertTrue(gg.getMessage().equals(""));
		assertTrue(gg.getRound() == currentRound+1);
	}

	@Test
	public void testGetWordFragment() throws GhostException {
		GhostGame gg = new GhostGame();
		gg.addLetter('A');
		assertNotNull(gg);
		assertTrue(gg.getWordFragment().startsWith("A"));
	}

	@Test
	public void testGetScorePlayerHuman() throws GhostException {
		GhostGame gg = new GhostGame();
		assertNotNull(gg);
		assertTrue(gg.getScorePlayerHuman() == 0);
	}
	
	@Test
	public void addInvalidWordLetter() throws GhostException {
		GhostGame gg = new GhostGame();
		
		gg.addLetter('1');
		
		assertNotNull(gg);
		assertTrue(gg.getScorePlayerHuman() == 1);
	}

}
