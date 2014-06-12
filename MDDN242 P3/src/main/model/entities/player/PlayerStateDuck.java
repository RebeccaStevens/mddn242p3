package main.model.entities.player;

import lib.game.BoundingBox3D;

class PlayerStateDuck extends PlayerState {

	private BoundingBox3D originalBoundingBox, newBoundingBox;
	
	PlayerStateDuck(Player player) {
		super(player);
	}
	
	@Override
	boolean canActivate(){
		originalBoundingBox = player.getBoundingBox3D();
		newBoundingBox = new BoundingBox3D(player, originalBoundingBox.getWidth()*1.2F, originalBoundingBox.getHeight()*0.5F, originalBoundingBox.getDepth()*1.2F);
		return !player.getLevel().collidesWithSomething(newBoundingBox);
	}
	
	@Override
	void start() {
		player.setBoundingBox3D(newBoundingBox);
		//player.addLocation(0, originalBoundingBox.getHeight() / 4, 0);
	}
	
	@Override
	void end() {
		player.setBoundingBox3D(originalBoundingBox);
		player.addLocation(0, -originalBoundingBox.getHeight() / 4, 0);
	}

	@Override
	public String toString() {
		return "State: Duck";
	}
}
