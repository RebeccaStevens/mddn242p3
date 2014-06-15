package main.model.entities.player;

import processing.core.PVector;

class PlayerStateWalk extends PlayerState {

	public static final float MAX_DIST_APART = 1500;
	
	PVector move;
	
	PlayerStateWalk(Player player) {
		super(player);
		this.move = new PVector();
	}
	
	void updateState(){
		super.updateState();
		if(PVector.dist(player.getLocation(), player.otherPlayer.getLocation()) <= MAX_DIST_APART){
			if(player.isOnGround() && (player.key_jump.isPressed() || (player.hasController && player.ctrl_jump.isPressed()))){
				player.setState(player.jumpState);
			}
		}
	}

	@Override
	void update(double delta) {
		getWalkForce();
		player.applyForce(move, 0);
		player.limitVelocityHorizontal(player.moveSpeed);
	}

	PVector getWalkForce() {
		if(player.hasController){
			PVector input = player.ctrl_analogStick.getData();
			move.set(input.x, 0, input.y);
			move.mult(move.magSq()); // cube the vector
			player.moveSpeed = player.runSpeed;
		}
		else{
			move.set(
					((player.key_moveRight.isPressed() ? 1 : -1) - (player.key_moveLeft.isPressed() ? 1 : -1)),
					0,
					((player.key_moveDown.isPressed() ? 1 : -1) - (player.key_moveUp.isPressed() ? 1 : -1)));
			move.normalize();
			player.moveSpeed = player.key_moveRun.isPressed() ? player.runSpeed : player.walkSpeed;
		}
		
		if(player.getClass()==Player1.class && player.otherPlayer.getGroundEntity() == player){
			player.moveSpeed = player.moveSpeed * 0.4F;
		}
		else if(player.getClass()==Player2.class && player.otherPlayer.getGroundEntity() == player){
			player.moveSpeed = player.moveSpeed * 0.05F;
		}
		
		move.mult(player.moveForce);
		return move;
	}
	
	@Override
	boolean canMove(PVector newLocation) {
		PVector otherPlayerLocation = player.otherPlayer.getLocation();
		float newDistToOtherPlayer = PVector.dist(newLocation, otherPlayerLocation);
		
		if(newDistToOtherPlayer < MAX_DIST_APART) return true;
		
		PVector currentLocation = player.getLocation();
		if(newDistToOtherPlayer < PVector.dist(currentLocation, otherPlayerLocation)) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "State: Walk";
	}
}