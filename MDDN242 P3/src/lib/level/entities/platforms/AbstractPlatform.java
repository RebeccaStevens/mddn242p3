package lib.level.entities.platforms;

import lib.level.Entity;
import lib.level.Level;

public abstract class AbstractPlatform extends Entity {

	public AbstractPlatform(Level level, float x, float y, float width, float height) {
		super(level, x, y, width, height);
	}
	
	public AbstractPlatform(Level level, float x, float y, float z, float width, float height, float depth) {
		super(level, x, y, z, width, height, depth);
	}

}
