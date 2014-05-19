package main;

import lib.LibraryManager;
import main.model.levels.Level1;
import processing.core.PApplet;

public class Main extends PApplet{

	private static final long serialVersionUID = -5944089766784769746L;
	
	private static Main me;
	private static LibraryManager libManager;
	
	public Main(){
		me = this;
	}

	@Override
	public void setup(){
		size(1024, 768, P3D);
		frame.setTitle("by Mike Stevens");
		libManager = new LibraryManager(this);
		new Level1().makeActive();
	}
	
	public void update(){
		
	}

	@Override
	public void draw(){
		update();
		lights();
	}
	
	public static Main getMe() {
		return me;
	}
	
	public static LibraryManager getLibraryManager() {
		return libManager;
	}
	
	public static void main(String[] args){
		PApplet.main(Main.class.getName());
	}
}
