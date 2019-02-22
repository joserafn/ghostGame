package com.ghost.game;

public interface IPlayerSet {

	void resetPlayers() throws GhostException;
	Integer getTotalPlayers();
    void changePlayerTurn();
	Player getActivePlayer();
	Player getPlayer(Integer order);
	void initializePlayerTurns();
}
