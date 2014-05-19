package lib.level;

import java.util.ArrayList;
import java.util.List;

import lib.LibraryManager;
import lib.Time;
import lib.level.cameras.CameraStatic;
import processing.core.PGraphics;
import processing.core.PVector;

public abstract class Level {

	protected List<Entity> entities;
	protected Camera camera;
	
	protected boolean drawBoundingBoxes;
	
	private PVector gravity;
	private float airFriction;
	
	public Level(){
		this(null);
	}
	
	public Level(Camera camera){
		entities = new ArrayList<Entity>();
		if(camera == null){
			this.camera = new CameraStatic(this);
		}
		else{
			this.camera = camera;
		}
		gravity = new PVector();
		airFriction = 0;
	}
	
	void addEntity(Entity ent){
		entities.add(ent);
	}
	
	PVector getGravity() {
		return gravity;
	}

	float getAirFriction() {
		return airFriction;
	}

	public void setGravity(float gravityDown){
		setGravity(new PVector(0, gravityDown, 0));
	}
	
	public void setGravity(PVector gravity){
		this.gravity.set(gravity);
	}
	
	public void setAirFriction(float airFriction) {
		this.airFriction = airFriction;
	}
	
	public void setCamera(Camera camera){
		this.camera.setLevel(null);
		this.camera = camera;
		this.camera.setLevel(this);
	}
	
	public void update(){
		float timeStep = Time.getTimeStep();
		camera.update(timeStep);
		for(Entity e : entities){
			e._update(timeStep);
		}
	}
	
	public void draw(){
		PGraphics g = getGraphics();
		g.pushMatrix();
		camera.apply(g);
		for(Entity e : entities){
			e.draw(g);
		}
		g.popMatrix();
	}
	
	public final void makeActive(){
		LibraryManager.getMe().setActiveLevel(this);
	}
	
	public final void drawBoundingBoxes(boolean b){
		drawBoundingBoxes = b;
	}
	
	public final boolean isDrawBoundingBoxes(){
		return drawBoundingBoxes;
	}

	public final PGraphics getGraphics() {
		return LibraryManager.getMe().getSketch().g;
	}
	
	public abstract boolean is3D();
}
