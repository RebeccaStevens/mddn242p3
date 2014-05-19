package lib.level;

import processing.core.PVector;

abstract class BoundingBox {

	protected Entity entity;

	BoundingBox(Entity ent){
		this.entity = ent;
	}
	
	public abstract boolean contains(PVector point);
	
	public boolean contains(BoundingBox other) {
		return contains(other, entity.getLocation());
	}
	
	public boolean contains(BoundingBox other, PVector location) {
		if(other instanceof BoundingBox2D){
			return contains((BoundingBox2D)other, location);
		}
		else if(other instanceof BoundingBox3D){
			return contains((BoundingBox3D)other, location);
		}
		return false;
	}
	
	public abstract boolean contains(BoundingBox2D other, PVector location);

	public abstract boolean contains(BoundingBox3D other, PVector location);
	
	public boolean intersects(BoundingBox other) {
		return intersects(other, entity.getLocation());
	}
	
	public boolean intersects(BoundingBox other, PVector location) {
		if(other instanceof BoundingBox2D){
			return intersects((BoundingBox2D)other, location);
		}
		else if(other instanceof BoundingBox3D){
			return intersects((BoundingBox3D)other, location);
		}
		return false;
	}

	public abstract boolean intersects(BoundingBox2D other, PVector location);

	public abstract boolean intersects(BoundingBox3D other, PVector location);

	public abstract float getWidth();

	public abstract float getHeight();

	public abstract float getDepth();

	public abstract float getX();
	
	public abstract float getY();
	
	public abstract float getZ();
}
