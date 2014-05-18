package lib.level;

import lib.Time;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class Entity {
	
	private PVector location;
	private PVector velocity;
	private PVector rotation;
	private PVector scale;
	
	private PVector maxLocation, minLocation;
	private PVector maxVelocity, minVelocity;
	private PVector maxRotation, minRotation;
	
	private BoundingBox boundingBox;
	
	private Level level;
	
	public Entity(Level level, float x, float y, float width, float height){
		this(level, x, y, 0, width, 1, height);
	}
	
	public Entity(Level level, PVector location, float width, float height){
		this(level, location.x, location.y, 0, width, 1, height);
	}
	
	public Entity(Level level, PVector location, float width, float height, float depth){
		this(level, location.x, location.y, location.z, width, height, depth);
	}
	
	public Entity(Level level, float x, float y, float z, float width, float height, float depth){
		setLevel(level);
		this.location = new PVector(x, y, z);
		this.velocity = new PVector();
		this.rotation = new PVector();
		if(level.is3D()){
			this.boundingBox = new BoundingBox3D(this, width, height, depth);
		}
		else{
			this.boundingBox = new BoundingBox2D(this, width, height);
		}
		this.scale = new PVector(1, 1, 1);
		
		maxLocation = null;	minLocation = null;
		maxVelocity = null;	minVelocity = null;
		maxRotation = null;	minRotation = null;
	}

	/**
	 * Update the entity.
	 * This method calls the method: update(float delta)
	 * @param delta The amount of game time that has passed since the last frame
	 */
	final void _update(float delta){
		update(delta);
		move(delta);
		applyLimits();
	}
	
	/**
	 * Update the entity.
	 * @param delta The amount of game time that has passed since the last frame
	 */
	public abstract void update(float delta);
	
	/**
	 * Move the entity.
	 */
	private void move(float delta){
		// TODO add collision detection
		location.add(PVector.mult(velocity, delta));
	}
	
	/**
	 * Draw the entity.
	 * This method calls the method: draw(delta)
	 * @param g The graphics object to draw to
	 */
	final void draw(PGraphics g){
		g.pushMatrix();
		if(level.is3D()){
			g.rotateX(rotation.x);
			g.rotateY(rotation.y);
			g.rotateZ(rotation.z);
			g.translate(location.x, location.y, location.z);
			g.scale(scale.x, scale.y, scale.z);
		}
		else{
			g.rotate(rotation.x);
			g.translate(location.x, location.y);
			g.scale(scale.x, scale.y);
		}
		g.pushStyle();
		draw(g, Time.getTimeStep());
		g.popStyle();
		if(level.isDrawBoundingBoxes()){
			drawBoundingBox(g);
		}
		g.popMatrix();
	}
	
	/**
	 * Draw the entity.
	 * @param delta The amount of game time that has passed since the last frame
	 */
	public abstract void draw(PGraphics g, float delta);

	/**
	 * Draw a bounding box around the entity.
	 * @param g The graphics object to draw to
	 */
	private void drawBoundingBox(PGraphics g) {
		if(level.is3D()){
			drawBoundingBox2D(g);
		}
		else{
			//TODO
		}
	}
	
	/**
	 * Draw a 2D bounding box around the entity.
	 * @param g The graphics object to draw to
	 */
	private void drawBoundingBox2D(PGraphics g) {
		BoundingBox2D bb = (BoundingBox2D) boundingBox;
		float bbx = bb.getX();
		float bby = bb.getY();
		float bbw = bb.getWidth();
		float bbh = bb.getHeight();
		
		g.pushStyle();
		g.stroke(0xFFFF0000);
		g.strokeWeight(bbw > 10 && bbh > 10 ? 3F : 1F);
		g.noFill();
		g.rectMode(PApplet.CORNER);
		g.rect(bbx, bby, bbw, bbh);
		g.popStyle();
	}
	
	private void setLevel(Level level){
		this.level = level;
		level.addEntity(this);
	}
	
	public final Level getLevel(){
		return level;
	}

	/**
	 * Accelerate the entity.
	 * No friction will be applied to the acceleration.
	 * @param ax X acceleration
	 * @param ay Y acceleration
	 * @return The distance traveled be the entity
	 */
	public PVector accelerate(float ax, float ay){
		return accelerate(new PVector(ax, ay));
	}
	
	/**
	 * Accelerate the entity.
	 * @param ax X acceleration
	 * @param ay Y acceleration
	 * @param friction The amount of friction to apply (should be between 0 and 1 though can be greater)
	 * @return The distance traveled be the entity
	 */
	public PVector accelerate(float ax, float ay, float friction){
		return accelerate(velocity, new PVector(ax, ay), friction);
	}
	
	/**
	 * Accelerate the entity.
	 * No friction will be applied to the acceleration.
	 * @param a
	 * @return The distance traveled be the entity
	 */
	public PVector accelerate(PVector a){
		return accelerate(a, 0);
	}
	
	/**
	 * Accelerate the entity.
	 * @param a
	 * @param friction The amount of friction to apply (should be between 0 and 1 though can be greater)
	 * @return The distance traveled be the entity
	 */
	public PVector accelerate(PVector a, float friction){
		return accelerate(velocity, a, friction);
	}
	
	/**
	 * Accelerate the given velocity by the given acceleration, applying the give amount of friction.
	 * @param veloc The velocity to accelerate
	 * @param accel The amount if acceleration
	 * @param friction The amount of friction to apply (should be between 0 and 1 though can be greater)
	 * @return The distance traveled
	 */
	public static PVector accelerate(PVector velocity, PVector acceleration, float friction){
		PVector delta;
		float time_step = Time.getTimeStep();

		  if (friction == 0) {
		    delta = PVector.add(PVector.mult(velocity, time_step), PVector.mult(acceleration, 0.5F * time_step * time_step));
		    velocity.add(PVector.mult(acceleration, time_step));
		  } 
		  else {
		    delta = PVector.add(PVector.mult(acceleration, time_step / friction), PVector.mult(PVector.sub(velocity, PVector.div(acceleration, friction)), (float) ((1 - Math.exp(-friction * time_step)) / friction)));
		    velocity.set(PVector.add(PVector.div(acceleration, friction), PVector.mult(PVector.sub(velocity, PVector.div(acceleration, friction)), (float) Math.exp(-friction * time_step))));
		  }
		  
		  return delta;
	}
	
	public float getX(){
		return location.x;
	}
	
	public float getY(){
		return location.y;
	}
	
	public float getZ(){
		return location.z;
	}

	public float getWidth() {
		return boundingBox.getWidth();
	}
	
	public float getDepth(){
		return boundingBox.getDepth();
	}

	public float getHeight() {
		return boundingBox.getHeight();
	}
	
	public PVector getLocation(){
		return location.get();
	}
	
	public float getVelocityX(){
		return velocity.x;
	}
	
	public float getVelocityY(){
		return velocity.y;
	}
	
	public float getVelocityZ(){
		return velocity.z;
	}
	
	public PVector getVelocity(){
		return velocity.get();
	}
	
	public float getRotationPan(){
		return rotation.x;
	}
	
	public float getRotationTilt(){
		return rotation.y;
	}
	
	public float getRotationRoll(){
		return rotation.z;
	}
	
	public float getRotation2D(){
		return rotation.x;
	}
	
	public PVector getRotation3D(){
		return rotation.get();
	}
	
	public float getScaleX(){
		return scale.x;
	}
	
	public float getScaleY(){
		return scale.y;
	}
	
	public float getScaleZ(){
		return scale.z;
	}
	
	public PVector getScale(){
		return scale.get();
	}
	
	/**
	 * Set the x location of the entity.
	 * @param x
	 */
	public void setLocationX(float x){
		location.x = x;
	}
	
	/**
	 * Set the y location of the entity.
	 * @param y
	 */
	public void setLocationY(float y){
		location.y = y;
	}
	
	/**
	 * Set the z location of the entity.
	 * (For 3D games only)
	 * @param z
	 */
	public void setLocationZ(float z){
		location.z = z;
	}
	
	/**
	 * Set the location of the entity.
	 * (For 2D games only)
	 * @param x
	 * @param y
	 */
	public void setLocation(float x, float y){
		location.set(x, y, 0);
	}
	
	/**
	 * Set the location of the entity.
	 * (For 3D games only)
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setLocation(float x, float y, float z){
		location.set(x, y, z);
	}

	/**
	 * Set the location of the entity.
	 * @param loc
	 */
	public void setLocation(PVector loc){
		location.set(loc);
	}
	
	/**
	 * Set the x speed of the entity.
	 * @param vx
	 */
	public void setVelocityX(float vx){
		velocity.x = vx;
	}
	
	/**
	 * Set the y speed of the entity.
	 * @param vy
	 */
	public void setVelocityY(float vy){
		velocity.y = vy;
	}
	
	/**
	 * Set the z speed of the entity.
	 * (For 3D games only)
	 * @param vz
	 */
	public void setVelocityZ(float vz){
		velocity.z = vz;
	}
	
	/**
	 * Set the speed of the entity.
	 * (For 2D games only)
	 * @param vx
	 * @param vy
	 */
	public void setVelocity(float vx, float vy){
		velocity.set(vx, vy, 0);
	}
	
	/**
	 * Set the speed of the entity.
	 * (For 3D games only)
	 * @param vx
	 * @param vy
	 * @param vz
	 */
	public void setVelocity(float vx, float vy, float vz){
		velocity.set(vx, vy, vz);
	}
	
	/**
	 * Set the speed of the entity.
	 * @param v
	 */
	public void setVelocity(PVector v){
		velocity.set(v);
	}
	
	/**
	 * Set the pan rotation of the entity.
	 * This is the rotation in the xy plain.
	 * (For 3D games only)
	 * @param pan
	 */
	public void setRotationPan(float pan){
		rotation.x = pan;
	}
	
	/**
	 * Set the tilt rotation of the entity.
	 * This is the rotation in the xz plain.
	 * (For 3D games only)
	 * @param tilt
	 */
	public void setRotationTilt(float tilt){
		rotation.y = tilt;
	}
	
	/**
	 * Set the roll rotation of the entity.
	 * This is the rotation in the yz plain.
	 * (For 3D games only)
	 * @param roll
	 */
	public void setRotationRoll(float roll){
		rotation.z = roll;
	}
	
	/**
	 * Set the rotation of the entity.
	 * (For 2D games only)
	 * @param pan
	 */
	public void setRotation(float rot){
		rotation.x = rot;
	}
	
	/**
	 * Set the rotation of the entity.
	 * (For 3D games only)
	 * @param pan
	 * @param tilt
	 * @param roll
	 */
	public void setRotation(float pan, float tilt, float roll){
		rotation.set(pan, tilt, roll);
	}
	
	/**
	 * Set the rotation of the entity.
	 * (For 3D games only)
	 * @param rot
	 */
	public void setRotation(PVector rot){
		rotation.set(rot);
	}
	
	/**
	 * Set the scale of the entity uniformly to the given value
	 * @param s The new scale of the entity
	 */
	public void setScale(float s){
		scale.set(s, s, s);
	}
	
	/**
	 * Set the scale of the entity.
	 * @param x The x scale of the entity
	 * @param y The y scale of the entity
	 */
	public void setScale(float x, float y){
		scale.set(x, y, scale.z);
	}
	
	/**
	 * Set the scale of the entity.
	 * @param x The x scale of the entity
	 * @param y The y scale of the entity
	 * @param z The z scale of the entity
	 */
	public void setScale(float x, float y, float z){
		scale.set(x, y, z);
	}
	
	/**
	 * Limit the location this entity can be in.
	 * This entity will not be at location in any dimension less than the give values.
	 * @param minX The minimum x location this entity can be
	 * @param minY The minimum y location this entity can be
	 */
	public void limitLocationMin(float minX, float minY){
		limitLocationMin(new PVector(minX, minY));
	}
	
	/**
	 * Limit the location this entity can be in.
	 * This entity will not be at location in any dimension less than the give values.
	 * @param minX The minimum x location this entity can be
	 * @param minY The minimum y location this entity can be
	 * @param minZ The minimum z location this entity can be
	 */
	public void limitLocationMin(float minX, float minY, float minZ){
		limitLocationMin(new PVector(minX, minY, minZ));
	}
	
	/**
	 * Limit the location this entity can be in.
	 * This entity will not be at location in any dimension less than the give values.
	 * @param min The minimum location this entity can be
	 */
	public void limitLocationMin(PVector min){
		if(min == null){
			minLocation = null;
			return;
		}
		if(minLocation == null){
			minLocation = min.get();
		}
		else{
			minLocation.set(min);
		}
	}
	
	/**
	 * Limit the location this entity can be in.
	 * This entity will not be at location in any dimension greater than the give values.
	 * @param maxX The minimum x location this entity can be
	 * @param maxY The minimum y location this entity can be
	 */
	public void limitLocationMax(float maxX, float maxY){
		limitLocationMax(new PVector(maxX, maxY));
	}
	
	/**
	 * Limit the location this entity can be in.
	 * This entity will not be at location in any dimension greater than the give values.
	 * @param maxX The minimum x location this entity can be
	 * @param maxY The minimum y location this entity can be
	 * @param maxZ The minimum z location this entity can be
	 */
	public void limitLocationMax(float maxX, float maxY, float maxZ){
		limitLocationMax(new PVector(maxX, maxY, maxZ));
	}
	
	/**
	 * Limit the location this entity can be in.
	 * This entity will not be at location in any dimension greater than the give values.
	 * @param max The minimum location this entity can be
	 */
	public void limitLocationMax(PVector max){
		if(max == null){
			maxLocation = null;
			return;
		}
		if(maxLocation == null){
			maxLocation = max.get();
		}
		else{
			maxLocation.set(max);
		}
	}
	
	/**
	 * Limit the velocity of this entity.
	 * This entity will not go slower than the given velocity.
	 * @param minX The minimum x velocity of this entity
	 * @param minY The minimum y velocity of this entity
	 */
	public void limitVelocityMin(float minX, float minY){
		limitVelocityMin(new PVector(minX, minY));
	}
	
	/**
	 * Limit the velocity of this entity.
	 * This entity will not go slower than the given velocity.
	 * @param minX The minimum x velocity of this entity
	 * @param minY The minimum y velocity of this entity
	 * @param minZ The minimum z velocity of this entity
	 */
	public void limitVelocitynMin(float minX, float minY, float minZ){
		limitVelocityMin(new PVector(minX, minY, minZ));
	}
	
	/**
	 * Limit the velocity of this entity.
	 * This entity will not go slower than the given velocity.
	 * @param min The minimum velocity of this entity
	 */
	public void limitVelocityMin(PVector min){
		if(min == null){
			minVelocity = null;
			return;
		}
		if(minVelocity == null){
			minVelocity = min.get();
		}
		else{
			minVelocity.set(min);
		}
	}
	
	/**
	 * Limit the velocity of this entity.
	 * This entity will not go faster than the given velocity.
	 * @param maxX The minimum x velocity of this entity
	 * @param maxY The minimum y velocity of this entity
	 */
	public void limitVelocityMax(float maxX, float maxY){
		limitVelocityMax(new PVector(maxX, maxY));
	}
	
	/**
	 * Limit the velocity of this entity.
	 * This entity will not go faster than the given velocity.
	 * @param maxX The maximum x velocity of this entity
	 * @param maxY The maximum y velocity of this entity
	 * @param maxZ The maximum z velocity of this entity
	 */
	public void limitVelocityMax(float maxX, float maxY, float maxZ){
		limitVelocityMax(new PVector(maxX, maxY, maxZ));
	}
	
	/**
	 * Limit the velocity of this entity.
	 * This entity will not go faster than the given velocity.
	 * @param max The maximum velocity of this entity
	 */
	public void limitVelocityMax(PVector max){
		if(max == null){
			maxVelocity = null;
			return;
		}
		if(maxVelocity == null){
			maxVelocity = max.get();
		}
		else{
			maxVelocity.set(max);
		}
	}
	
	/**
	 * Limit the rotation of this entity.
	 * This entity will not have a rotation less than the give value.
	 * @param ang The minimum angle that this entity can be at
	 */
	public void limitRotationMin(float ang){
		limitRotationMin(new PVector(ang, 0, 0));
	}
	
	/**
	 * Limit the rotation of this entity.
	 * This entity will not have a rotation less than any of the give values.
	 * @param minPan The minimum pan angle that this entity can be at
	 * @param minTilt The minimum tilt angle that this entity can be at
	 * @param minRoll The minimum roll angle that this entity can be at
	 */
	public void limitRotationMin(float minPan, float minTilt, float minRoll){
		limitRotationMin(new PVector(minPan, minTilt, minRoll));
	}
	
	/**
	 * Limit the rotation of this entity.
	 * This entity will not have a rotation less than any of the give values.
	 * @param min The minimum angles that this entity can be at
	 */
	public void limitRotationMin(PVector min){
		if(min == null){
			minRotation = null;
			return;
		}
		if(minRotation == null){
			minRotation = min.get();
		}
		else{
			minRotation.set(min);
		}
	}
	
	/**
	 * Limit the rotation of this entity.
	 * This entity will not have a rotation greater than the give value.
	 * @param ang The maximum angle that this entity can be at
	 */
	public void limitRotationMax(float ang){
		limitRotationMax(new PVector(ang, 0, 0));
	}
	
	/**
	 * Limit the rotation of this entity.
	 * This entity will not have a rotation greater than any of the give values.
	 * @param maxPan The maximum pan angle that this entity can be at
	 * @param maxTilt The maximum tilt angle that this entity can be at
	 * @param maxRoll The maximum roll angle that this entity can be at
	 */
	public void limitRotationMax(float maxPan, float maxTilt, float maxRoll){
		limitRotationMax(new PVector(maxPan, maxTilt, maxRoll));
	}
	
	/**
	 * Limit the rotation of this entity.
	 * This entity will not have a rotation greater than any of the give values.
	 * @param max The maximum angles that this entity can be at
	 */
	public void limitRotationMax(PVector max){
		if(max == null){
			maxRotation = null;
			return;
		}
		if(maxRotation == null){
			maxRotation = max.get();
		}
		else{
			maxRotation.set(max);
		}
	}
	
	/**
	 * Remove the minimum limits applied to the location of this entity.
	 */
	public void removeLimitLocationMin(){
		limitLocationMin(null);
	}

	/**
	 * Remove the maximum limits applied to the location of this entity.
	 */
	public void removeLimitLocationMax(){
		limitLocationMax(null);
	}

	/**
	 * Remove the limits applied to the location of this entity.
	 */
	public void removeLimitLocation(){
		removeLimitLocationMin();
		removeLimitLocationMax();
	}

	/**
	 * Remove the minimum limits applied to this entity's velocity.
	 */
	public void removeLimitVelocityMin(){
		limitVelocityMin(null);
	}

	/**
	 * Remove the maximum limits applied to this entity's velocity.
	 */
	public void removeLimitVelocityMax(){
		limitVelocityMax(null);
	}

	/**
	 * Remove the limits applied to this entity's velocity.
	 */
	public void removeLimitVelocity(){
		removeLimitVelocityMin();
		removeLimitVelocityMax();
	}

	/**
	 * Remove the minimum limits applied to the rotation of this entity.
	 */
	public void removeLimitRotationMin(){
		limitRotationMin(null);
	}

	/**
	 * Remove the maximum limits applied to the rotation of this entity.
	 */
	public void removeLimitRotationMax(){
		limitRotationMax(null);
	}
	
	/**
	 * Remove the limits applied to the rotation of this entity.
	 */
	public void removeLimitRotation(){
		removeLimitRotationMin();
		removeLimitRotationMax();
	}
	
	/**
	 * Apply the limits to this entities attributes.
	 */
	private void applyLimits(){
		if(minLocation != null){
			location.x = Math.max(location.x, minLocation.x);
			location.y = Math.max(location.y, minLocation.y);
			location.z = Math.max(location.z, minLocation.z);
		}
		if(maxLocation != null){
			location.x = Math.min(location.x, maxLocation.x);
			location.y = Math.min(location.y, maxLocation.y);
			location.z = Math.min(location.z, maxLocation.z);
		}
		if(minVelocity != null){
			velocity.x = Math.max(velocity.x, minVelocity.x);
			velocity.y = Math.max(velocity.y, minVelocity.y);
			velocity.z = Math.max(velocity.z, minVelocity.z);
		}
		if(maxVelocity != null){
			velocity.x = Math.min(velocity.x, maxVelocity.x);
			velocity.y = Math.min(velocity.y, maxVelocity.y);
			velocity.z = Math.min(velocity.z, maxVelocity.z);
		}
		if(minRotation != null){
			rotation.x = Math.max(rotation.x, minRotation.x);
			rotation.y = Math.max(rotation.y, minRotation.y);
			rotation.z = Math.max(rotation.z, minRotation.z);
		}
		if(maxRotation != null){
			rotation.x = Math.min(rotation.x, maxRotation.x);
			rotation.y = Math.min(rotation.y, maxRotation.y);
			rotation.z = Math.min(rotation.z, maxRotation.z);
		}
	}
}