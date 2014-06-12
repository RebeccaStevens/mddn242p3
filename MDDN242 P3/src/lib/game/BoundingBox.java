package lib.game;

import processing.core.PVector;

abstract class BoundingBox {

	protected Entity entity;

	BoundingBox(Entity ent){
		this.entity = ent;
	}
	
	public abstract boolean contains(PVector point);
	
	boolean contains(BoundingBox other) {
		return contains(other, entity.getLocation());
	}
	
	boolean contains(BoundingBox other, PVector location) {
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
	
	boolean intersects(BoundingBox other) {
		return intersects(other, entity.getLocation());
	}
	
	boolean intersects(BoundingBox other, PVector location) {
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
	
	public final void intersectsAny(){
		
	}

	public abstract float getWidth();

	public abstract float getHeight();

	public abstract float getDepth();

	public abstract float getCenterX();
	
	public abstract float getCenterY();
	
	public abstract float getCenterZ();

	public abstract float getMinX();
	
	public abstract float getMinY();
	
	public abstract float getMinZ();

	public abstract float getMaxX();
	
	public abstract float getMaxY();
	
	public abstract float getMaxZ();

	final Entity getEntity() {
		return entity;
	}
}
