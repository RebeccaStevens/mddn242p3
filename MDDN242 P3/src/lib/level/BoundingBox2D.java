package lib.level;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import processing.core.PVector;

public class BoundingBox2D extends BoundingBox {

	private Rectangle2D box;
	
	public BoundingBox2D(Entity ent, float width, float height){
		this(ent, -width/2, -height/2, width, height);
	}

	public BoundingBox2D(Entity ent, float x, float y, float width, float height){
		super(ent);
		this.box = new Rectangle2D.Float(x, y, width, height);
	}

	@Override
	public boolean contains(PVector point) {
		return box.contains(new Point2D.Float(point.x, point.y));
	}

	public boolean contains(BoundingBox2D other) {
		return box.contains(other.box);
	}
	
	public boolean intersects(BoundingBox2D other) {
		return box.intersects(other.box);
	}

	@Override
	public float getX() {
		return (float) box.getX();
	}

	@Override
	public float getY() {
		return (float) box.getY();
	}

	@Override
	public float getZ(){
		return 0;
	}

	@Override
	public float getWidth() {
		return (float) box.getWidth();
	}

	@Override
	public float getHeight() {
		return (float) box.getHeight();
	}

	@Override
	public float getDepth() {
		return 0;
	}
}
