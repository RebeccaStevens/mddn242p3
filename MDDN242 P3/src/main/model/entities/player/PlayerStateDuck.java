package main.model.entities.player;

class PlayerStateDuck extends PlayerState {

	PlayerStateDuck(Player player) {
		super(player);
	}

	@Override
	public String toString() {
		return "State: Duck";
	}
}
