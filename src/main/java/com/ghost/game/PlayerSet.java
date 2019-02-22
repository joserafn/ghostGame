package com.ghost.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerSet implements IPlayerSet{

	List<Player> players = new ArrayList<Player>();
	Integer totalPlayers;
	
	public PlayerSet(Integer playersNumber) throws GhostException {
		
		createPlayers(playersNumber);
		this.totalPlayers = playersNumber;
	}
	
	private Player getPlayerByOrder(Integer order) {
		Optional<Player> output = players.stream()
				.filter(p -> p.getOrder() == order)
				.findFirst();
		
		return output.get();
	}
	
	private void createPlayers(Integer playersNumber) throws GhostException {
		
		if (playersNumber < 2) throw new GhostException("Invalid number of players."); 
		
		for (int i = 0; i < playersNumber; i++) {
		    players.add(new Player(Player.PlayerType.HUMAN, i+1, false));
		}
		
		// The first player will be the one who starts
		getPlayerByOrder(1).setActive(true);
		// The last player always will be the machine
		getPlayerByOrder(playersNumber).setPlayerType(Player.PlayerType.MACHINE);
	}
	
	private Integer getActivePlayerOrder() {
		boolean found = false;
		int i = 1;
		// Always should exists an active player
		while (!found && i<this.totalPlayers) {
			if (getPlayerByOrder(i).getActive()) found = true;
			else i++;
		}
		return i;
	}
	
	@Override
	public Integer getTotalPlayers() {
		return this.totalPlayers;
	}
	
	// Changes the active player. The next active player will be the next in the list applying Round-Robin
	@Override
	public void changePlayerTurn() {
		
		Integer nextPlayerActive = (getActivePlayerOrder()%getTotalPlayers());
		
		// Set all the players inactive except the selected
		players.stream()
			.forEach(p->p.setActive(false));
			
		players.get(nextPlayerActive).setActive(true);
	}
	
	@Override
	public Player getActivePlayer() {
		return players.stream()
				  .filter(p -> p.getActive())
				  .findAny()
				  .orElse(null);	
	}
	
	@Override
	public Player getPlayer(Integer order){
		if (order < 1 || order > this.totalPlayers) return null;
		
		return this.players.get(order-1); // The list index starts by 0
	}
	
	// Recreates a new set of players removing the old one.
	@Override
	public void resetPlayers() throws GhostException {
		this.players.clear();
		createPlayers(this.totalPlayers);
	}

	@Override
	public void initializePlayerTurns() {
		// Set all the players inactive except the first one
		players.stream()
			.forEach(p->p.setActive(false));
		
		players.get(0).setActive(true);
	}

}
