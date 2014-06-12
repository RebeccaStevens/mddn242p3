package main.model.entities.player;

import processing.core.PVector;

abstract class PlayerState {
	
	protected Player player;

	PlayerState(Player player){
		this.player = player;
	}
	
	boolean canActivate() {
		return true;
	}

	void start() {
		
	}
	
	void end() {
		
	}
	
	void updateState(){
		if(player.isOnGround()){
			if(player.key_duck.isPressed() || (player.hasController && player.ctrl_duck.isPressed())){
				player.setState(player.duckState);
			}
			else{
				player.setState(player.walkState);
			}
		}
		else{
			player.setState(player.fallState);
		}
	}

	void update(double delta) {
		
	}
	
	boolean canMove(PVector newLocation) {
		return true;
	}
}
