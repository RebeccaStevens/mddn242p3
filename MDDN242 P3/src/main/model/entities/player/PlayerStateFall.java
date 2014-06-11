package main.model.entities.player;

import processing.core.PVector;

class PlayerStateFall extends PlayerState {

	PlayerStateFall(Player player) {
		super(player);
	}
	
	@Override
	void start() {
		
	}

	@Override
	void update(double delta) {
		PVector move = player.walkState.getWalkForce();
		move.limit((float) (300 * delta));
		player.addVelocity(move);
	}
	
	@Override
	public String toString() {
		return "State: Fall";
	}
}
