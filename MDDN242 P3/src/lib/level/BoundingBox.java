package lib.level;

import processing.core.PVector;

abstract class BoundingBox {

	protected Entity entity;

	BoundingBox(Entity ent){
		this.entity = ent;
	}
	
	public abstract boolean contains(PVector point);

	public abstract float getWidth();

	public abstract float getHeight();

	public abstract float getDepth();

	public abstract float getX();
	
	public abstract float getY();
	
	public abstract float getZ();
}
