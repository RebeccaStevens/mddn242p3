package lib.level.cameras;

import processing.core.PVector;
import lib.level.Camera;
import lib.level.Entity;
import lib.level.Level;

public class CameraFollowTwo extends Camera {

	private Entity target1, target2;
	private PVector offsetPos, offsetAng;

	public CameraFollowTwo(Level level){
		this(level, null, null, 0, 0, 0);
	}
	
	public CameraFollowTwo(Level level, float x, float y, float z){
		this(level, null, null, x, y, z);
	}
	
	public CameraFollowTwo(Level level, Entity entity1, Entity entity2, float x, float y, float z){
		this(level, entity1, entity2, x, y, z, 0, 0, 0);
	}
	
	public CameraFollowTwo(Level level, Entity entity1, Entity entity2, float x, float y, float z, float tilt, float pan, float roll){
		super(level);
		follow(entity1, entity2);
		offsetPos = new PVector(x, y, z);
		offsetAng = new PVector(pan, tilt, roll);
	}
	
	public void follow(Entity entity1, Entity entity2){
		target1 = entity1;
		target2 = entity2;
	}

	@Override
	public void update(float delta) {
		if(target1 == null || target2 == null) return;
		System.out.println(1);
		PVector t1p = target1.getLocation();
		PVector t2p = target2.getLocation();
		PVector centroid = new PVector(
				(t1p.x + t2p.x) / 2,
				(t1p.y + t2p.y) / 2,
				(t1p.z + t2p.z) / 2
		);
		location.set(centroid);
		location.add(offsetPos);
		rotation.set(offsetAng);
	}
}