package main.model.entities.player;

import processing.core.PVector;

public class PlayerStateWalk extends PlayerState {

	protected PVector walk;
	
	PlayerStateWalk(Player player) {
		super(player);
		this.walk = new PVector();
	}
	
	void updateState(){
		super.updateState();
		if(player.isOnGround() && (player.key_jump.isPressed() || (player.hasController && player.ctrl_jump.isPressed()))){
			player.setState(player.jumpState);
		}
	}

	@Override
	void start() {
		player.limitVelocityHorizontal(player.walkSpeed);
	}
	
	@Override
	void update(double delta) {
		getWalkForce();
		player.applyForce(walk, 0);
	}

	PVector getWalkForce() {
		if(player.hasController){
			PVector input = player.ctrl_analogStick.getData();
			walk.set(input.x, 0, input.y);
		}
		else{
			walk.set(
					((player.key_moveRight.isPressed() ? 1 : -1) - (player.key_moveLeft.isPressed() ? 1 : -1)),
					0,
					((player.key_moveDown.isPressed() ? 1 : -1) - (player.key_moveUp.isPressed() ? 1 : -1)));
			walk.normalize();
		}
		walk.mult(walk.magSq()); // cube the vector
		walk.mult(player.walkForce);
		return walk;
	}
	
	@Override
	PVector move(double delta) {
		PVector loc = player.defaultMove(delta);
		if(PVector.dist(loc, player.otherPlayer.getLocation()) < 1000){
			return loc;
		}
		return player.getLocation();
	}
	
	@Override
	public String toString() {
		return "State: Walk";
	}
}