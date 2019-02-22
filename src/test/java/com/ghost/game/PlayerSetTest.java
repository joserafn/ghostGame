package com.ghost.game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ghost.game.Player.PlayerType;

public class PlayerSetTest {

	PlayerSet ps;
	
	@Before
    public void init() throws GhostException {
		ps = new PlayerSet(2);       
    }
	
	@Test(expected=GhostException.class)
	public void createAPlayerSetWithLessThanTwoPlayers() throws GhostException {
		new PlayerSet(1);
	}
	
	@Test
	public void getTotalPlayersTest() {
		assertTrue(ps.getTotalPlayers() == 2);
	}
	
	@Test
	public void theCurrentPlayerTurnIsChanged() {
		Player p = ps.getActivePlayer();
		ps.changePlayerTurn();
		assertTrue(!p.getActive());
	}
	
	@Test
	public void theFirstPlayerIsTheActiveAfterInitializing() {
		ps.getPlayer(1).setActive(true); 
		ps.changePlayerTurn(); 
		// At this point, the player 1 is not the active one
		
		ps.initializePlayerTurns();
		
		assertTrue(ps.getPlayer(1).getActive());
	}
	
	@Test
	public void theActivePlayerIsReturned() {
		Player p = ps.getActivePlayer();
		assertTrue(p.getActive());
	}
	
	@Test
	public void returnsAnExistingPlayer() {
		Player p = ps.getPlayer(1);
		
		assertNotNull(p);
	}
	
	@Test
	public void tryToRetrieveAnNonExistingPlayer() {
		Player p = ps.getPlayer(3);
		
		assertNull(p);
	}
	
	@Test
	public void returnsTheFirstPlayerNoMachineType() {
		Player p = ps.getPlayer(1);
		
		assertNotNull(p);
		assertTrue(p.getOrder() == 1);
		assertTrue(p.getPlayerType().equals(PlayerType.HUMAN));
		
	}
	
	@Test
	public void returnsTheLastPlayerMachineType() {
		Player p = ps.getPlayer(ps.getTotalPlayers());
		
		assertNotNull(p);
		assertTrue(p.getOrder() == ps.getTotalPlayers());
		assertTrue(p.getPlayerType().equals(PlayerType.MACHINE));
		
	}
	
	@Test
	public void thePlayersAreReseted() throws GhostException {
		
		ps.getPlayer(1).setScore(3);
		ps.getPlayer(2).increaseScore();;
		ps.getPlayer(2).setActive(true);
		
		ps.resetPlayers();
		
		// The first player is the active again
		assertTrue(ps.getActivePlayer().getOrder() == 1);
		
		// The scores of both players are set to 0
		assertTrue(ps.getPlayer(1).getScore() == 0);
		assertTrue(ps.getPlayer(2).getScore() == 0);
		
	}

}
