package lib.level.entities;

import lib.level.Entity;
import lib.level.Level;

public abstract class Actor extends Entity {

	public Actor(Level level, float x, float y, float width, float height, float mass) {
		super(level, x, y, width, height);
		init(mass);
	}
	
	public Actor(Level level, float x, float y, float z, float width, float height, float depth, float mass) {
		super(level, x, y, z, width, height, depth);
		init(mass);
	}
	
	private void init(float mass){
		setGravityEffected(true);
		setMass(mass);
		setCollisionGroup(1);
	}

}