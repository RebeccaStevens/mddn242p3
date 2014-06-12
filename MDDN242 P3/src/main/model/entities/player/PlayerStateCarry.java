package main.model.entities.player;


class PlayerStateCarry extends PlayerState {

	PlayerStateCarry(Player player) {
		super(player);
	}
	
	void updateState(){
		super.updateState();
	}
	
	@Override
	void update(double delta) {
		
	}
	
	@Override
	public String toString() {
		return "State: Carry";
	}
}