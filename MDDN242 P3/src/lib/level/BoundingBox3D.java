package lib.level;

import processing.core.PVector;

public class BoundingBox3D extends BoundingBox {
	
	private float width, height, depth;
	private float halfWidth, halfHeight, halfDepth;
	private PVector location;

	public BoundingBox3D(Entity ent, float width, float height, float depth){
		this(ent, 0, 0, 0, width, height, depth);
	}

	public BoundingBox3D(Entity ent, float x, float y, float z, float width, float height, float depth){
		super(ent);
		location = new PVector(x, y, z);
		this.halfWidth  = (this.width  = width)  / 2;
		this.halfHeight = (this.height = height) / 2;
		this.halfDepth  = (this.depth  = depth)  / 2;
	}

	@Override
	public boolean contains(PVector point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(BoundingBox2D other, PVector location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(BoundingBox3D other, PVector location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(BoundingBox2D other, PVector location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(BoundingBox3D other, PVector location) {
		PVector te = PVector.add(location, this.location);
		PVector oe = PVector.add(other.entity.getLocation(), other.location);
		
		return	((te.x - halfWidth  < oe.x + other.width /2 && te.x + halfWidth  > oe.x - other.width /2) || (te.x + halfWidth  > oe.x - other.width /2 && te.x + halfWidth  < oe.x + other.width /2 ))
			&&	((te.y - halfHeight < oe.y + other.height/2 && te.y + halfHeight > oe.y - other.height/2) || (te.y + halfHeight > oe.y - other.height/2 && te.y + halfHeight < oe.y + other.height/2 ))
			&&	((te.z - halfDepth  < oe.z + other.depth /2 && te.z + halfDepth  > oe.z - other.depth /2) || (te.z + halfDepth  > oe.z - other.depth /2 && te.z + halfDepth  < oe.z + other.depth /2 ));
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
		return location.x;
	}

	@Override
	public float getY() {
		return location.y;
	}

	@Override
	public float getZ() {
		return location.z;
	}

}
