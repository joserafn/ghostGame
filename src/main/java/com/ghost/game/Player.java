package com.ghost.game;

public class Player {
	
	public enum PlayerType {
	    HUMAN,
	    MACHINE
	}
	
	PlayerType playerType;
	Boolean active = false;
	Integer order;
	Integer score;

	public Player(PlayerType playerType, Integer order, Boolean active) {
		this.playerType = playerType;
		this.score = 0;
		this.order = order;
		this.active = active;
	}
	
	public Integer getScore() {
		return this.score;
	}
	
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public PlayerType getPlayerType() {
		return this.playerType;
	}
	
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	
	public void increaseScore() {
		this.score += 1;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
