package lib.level.cameras;

import lib.level.Camera;
import lib.level.Entity;
import lib.level.Level;

public class CameraFollow extends Camera {

	private Entity target;

	public CameraFollow(Level level){
		this(level, null);
	}
	
	public CameraFollow(Level level, Entity entity){
		super(level);
		follow(entity);
	}
	
	public void follow(Entity entity){
		target = entity;
	}

	@Override
	public void update(float delta) {
		if(target == null) return;
		location.set(target.getLocation());
	}
}