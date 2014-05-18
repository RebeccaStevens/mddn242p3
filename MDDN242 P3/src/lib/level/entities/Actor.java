package lib.level.entities;

import lib.level.Entity;
import lib.level.Level;

public abstract class Actor extends Entity {

	public Actor(Level level, float x, float y, float width, float height) {
		super(level, x, y, width, height);
	}
	
	public Actor(Level level, float x, float y, float z, float width, float height, float depth) {
		super(level, x, y, z, width, height, depth);
	}

}
