package lib.level.cameras;

import lib.level.Camera;
import lib.level.Level;
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

	public CameraStatic(Level level, float x, float y, float rotation) {
		this(level, new PVector(x, y), new PVector(rotation, 0));
	}

	public CameraStatic(Level level, float x, float y, float z, float pan, float tilt, float roll) {
		this(level, new PVector(x, y, z), new PVector(pan, tilt, roll));
	}

	@Override
	public void update(float delta) {
	}
}