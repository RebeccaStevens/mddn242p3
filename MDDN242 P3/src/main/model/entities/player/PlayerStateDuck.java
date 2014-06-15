package main.model.entities.player;

import gamelib.game.BoundingBox3D;
import processing.core.PVector;

class PlayerStateDuck extends PlayerStateWalk {

	private BoundingBox3D originalBoundingBox, newBoundingBox;
	private PVector offset;
	
	private static final PVector sizeMultiplier = new PVector(1.2F, 0.5F, 1.2F);
	
	PlayerStateDuck(Player player) {
		super(player);
		offset = new PVector();
	}
	
	@Override
	boolean canActivate(){
		BoundingBox3D bb = player.getBoundingBox3D();
		BoundingBox3D nbb = new BoundingBox3D(player, bb.getWidth()*sizeMultiplier.x, bb.getHeight()*sizeMultiplier.y, bb.getDepth()*sizeMultiplier.z);
		
		player.setBoundingBox3D(nbb);
		player.addLocation(offset);
		
		boolean b = !player.getBoundingBox3D().collidesWithSomething();
		
		player.setBoundingBox3D(bb);
		player.addLocation(PVector.mult(offset, -1));
		return b;
	}
	BoundingBox3D temp;
	@Override
	boolean canDeactivate() {
		BoundingBox3D bb = player.getBoundingBox3D();
		
		player.setBoundingBox3D(originalBoundingBox);
		player.addLocation(PVector.mult(offset, -1));
		
		boolean b = !player.getBoundingBox3D().collidesWithSomething();
		
		player.setBoundingBox3D(bb);
		player.addLocation(offset);
		return b;
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
	void update(double delta) {
		PVector move = player.walkState.getWalkForce();
		move.mult((float) (500 * delta));
		player.addVelocity(move);
	}

	@Override
	public String toString() {
		return "State: Duck";
	}
}
