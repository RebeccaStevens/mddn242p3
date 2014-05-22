package lib.level.entities;

import lib.level.Entity;
import lib.level.Level;
import processing.core.PVector;

public abstract class Ground extends Entity {
	
	/**
	 * Create a 2D Ground
	 * @param level The level the entity will exist in
	 * @param x The x location
	 * @param y The y location
	 * @param width The width
	 * @param height The height
	 */
	public Ground(Level level, float x, float y, float width, float height){
		super(level, x, y, width, height);
	}
	
	/**
	 * Create a 2D Ground
	 * @param level The level the entity will exist in
	 * @param location The location of this entity
	 * @param width The width
	 * @param height The height
	 */
	public Ground(Level level, PVector location, float width, float height){
		super(level, location, width, height);
	}
	
	/**
	 * Create a 3D Ground
	 * @param level The level the entity will exist in
	 * @param location The location of this entity
	 * @param width The width
	 * @param height The height
	 * @param depth The depth
	 */
	public Ground(Level level, PVector location, float width, float height, float depth){
		super(level, location, width, height, depth);
	}
	
	/**
	 * Create a 3D Ground
	 * @param level The level the entity will exist in
	 * @param x The x location
	 * @param y The y location
	 * @param z The z location
	 * @param width The width
	 * @param height The height
	 * @param depth The depth
	 */
	public Ground(Level level, float x, float y, float z, float width, float height, float depth){
		super(level, x, y, z, width, height, depth);
	}

	public abstract float getGroundFriction();
}
