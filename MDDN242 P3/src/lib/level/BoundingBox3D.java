package lib.level;

import processing.core.PVector;

public class BoundingBox3D extends BoundingBox {
	
	private float x, y, z, width, height, depth;

	public BoundingBox3D(Entity ent, float width, float height, float depth){
		this(ent, -width/2, -depth/2, -height/2, width, height, depth);
	}

	public BoundingBox3D(Entity ent, float x, float y, float z, float width, float height, float depth){
		super(ent);
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.depth = depth;
		this.height = height;
	}

	@Override
	public boolean contains(PVector point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public float getDepth() {
		return depth;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getZ() {
		return z;
	}

}
