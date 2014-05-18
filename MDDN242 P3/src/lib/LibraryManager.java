package lib;

import lib.level.Level;
import processing.core.PApplet;

public class LibraryManager {
	
	private static LibraryManager me;
	
	private PApplet sketch;
	private Time time;
	private Level activeLevel;

	private Background background;

	public LibraryManager(PApplet sketch){
		if(me != null){
			throw new RuntimeException("Error: there can only be one LibraryManager.");
		}
		
		this.sketch = sketch;
		me = this;
		time = new Time(sketch);
		background = new Background(0xFF2277FF);
		
		sketch.g.rectMode(PApplet.CENTER);
		sketch.g.ellipseMode(PApplet.CENTER);
		sketch.g.imageMode(PApplet.CENTER);
		sketch.registerMethod("pre", this);
	}
	
	public Level getActiveLevel() {
		return activeLevel;
	}
	
	public void setActiveLevel(Level level){
		activeLevel = level;
	}
	
	public void pre(){
		update();
		draw();
	}
	
	private void update(){
		Time.update(sketch);
		if(activeLevel != null){
			activeLevel.update();
		}
	}
	
	private void draw() {
		if(activeLevel != null){
			background.draw(sketch.g);
			activeLevel.draw();
		}
	}

	public void keyPressed(int keyCode){
		for(Key key : Key.keys){
			key.update(keyCode, true);
		}
	}

	public void keyReleased(int keyCode){
		for(Key key : Key.keys){
			key.update(keyCode, false);
		}
	}
	
	public PApplet getSketch(){
		return sketch;
	}

	public Time getTime(){
		return time;
	}

	public static LibraryManager getMe(){
		return me;
	}
}
