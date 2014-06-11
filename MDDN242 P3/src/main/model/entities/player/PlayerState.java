package main.model.entities.player;

import processing.core.PVector;

abstract class PlayerState {
	
	protected Player player;

	PlayerState(Player player){
		this.player = player;
	}
	
	void updateState(){
		if(player.isOnGround()){
			player.setState(player.walkState);
		}
		else{
			player.setState(player.fallState);
		}
	}

	void update(double delta) {
		
	}
	
	PVector move(double delta) {
		return player.defaultMove(delta);
	}

	void start() {
		
	}
	
	void end() {
		
	}
}
