package main.model.entities.player;

class PlayerStateJump extends PlayerState {
	
	PlayerStateJump(Player player) {
		super(player);
	}

	void updateState(){
		player.setState(player.fallState);
	}
	
	@Override
	void update(double delta) {
		player.applyForce(0, player.jumpForce, 0, 0);
	}
	
	@Override
	public String toString() {
		return "State: Jump";
	}
}
