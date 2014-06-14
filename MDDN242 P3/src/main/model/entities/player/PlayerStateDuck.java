package main.model.entities.player;

import processing.core.PVector;
import lib.game.BoundingBox3D;

class PlayerStateDuck extends PlayerState {

	private BoundingBox3D originalBoundingBox, newBoundingBox;
	private PVector offset;
	
	private static final PVector sizeMultiplier = new PVector(1.2F, 0.5F, 1.2F);
	
	PlayerStateDuck(Player player) {
		super(player);
		offset = new PVector();
	}
	
	@Override
	boolean canActivate(){
		BoundingBox3D cbb = player.getBoundingBox3D();
		BoundingBox3D nbb = new BoundingBox3D(player, cbb.getWidth()*sizeMultiplier.x, cbb.getHeight()*sizeMultiplier.y, cbb.getDepth()*sizeMultiplier.z);
		return !nbb.collidesWithSomething();
	}
	
	@Override
	void start() {
		originalBoundingBox = player.getBoundingBox3D();
		newBoundingBox = new BoundingBox3D(player, originalBoundingBox.getWidth()*sizeMultiplier.x, originalBoundingBox.getHeight()*sizeMultiplier.y, originalBoundingBox.getDepth()*sizeMultiplier.z);
		offset.set(0, originalBoundingBox.getHeight()/4, 0);
		
		player.setBoundingBox3D(newBoundingBox);
		player.addLocation(offset);
	}
	
	@Override
	void end() {
		player.setBoundingBox3D(originalBoundingBox);
		player.addLocation(PVector.mult(offset, -1));
	}

	@Override
	public String toString() {
		return "State: Duck";
	}
}
