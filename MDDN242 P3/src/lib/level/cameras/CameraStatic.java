package lib.level.cameras;

import lib.level.Camera;
import lib.level.Level;
import processing.core.PConstants;
import processing.core.PVector;

public class CameraStatic extends Camera {
	
	public CameraStatic(Level level){
		this(level, new PVector(), new PVector());
	}
	
	public CameraStatic(Level level, PVector location, PVector rotation){
		super(level);
		this.location = location.get();
		this.rotation = rotation.get();
	}

	@Override
	public void update(float delta) {
		location.x = 500;
		location.y = 300;
		location.z = -1000;
		rotation.y += PConstants.TAU * 0.1 * delta;
	}
}