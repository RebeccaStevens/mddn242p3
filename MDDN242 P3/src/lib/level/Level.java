package lib.level;

import java.util.ArrayList;
import java.util.List;

import lib.LibraryManager;
import lib.Time;
import lib.level.cameras.CameraStatic;
import processing.core.PGraphics;

public abstract class Level {

	protected List<Entity> entities;
	protected Camera camera;
	
	protected boolean drawBoundingBoxes;
	
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
	}
	
	void addEntity(Entity ent){
		entities.add(ent);
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
