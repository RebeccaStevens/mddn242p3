package main.menu;

import main.Main;
import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;

public class MainMenu {

	private static final int bg_color = 0xFF000000;
	
	private ControlP5 cp5;

	public MainMenu(){
		PApplet sketch = Main.getMe();
		cp5 = new ControlP5(sketch);
		cp5.setAutoDraw(false);
		
		addMain(sketch);
	}

	private void addAll(PApplet sketch) {
		float x = (sketch.width - 350) / 2;
		float y = 150;
		cp5.addButton("mm_resume")
			.setPosition(x,y)
			.setImages(sketch.loadImage("mm_resume0.png"), sketch.loadImage("mm_resume1.png"), sketch.loadImage("mm_resume2.png"))
			.updateSize()
			.addListener(new ControlListener() {
				public void controlEvent(ControlEvent theEvent) {
					Main.getMe().resumeGame();
				}
			});
		y += 200;
		cp5.addButton("mm_newgame")
		.setPosition(x,y)
		.setImages(sketch.loadImage("mm_newgame0.png"), sketch.loadImage("mm_newgame1.png"), sketch.loadImage("mm_newgame1.png"))
		.updateSize()
		.addListener(new ControlListener() {
			public void controlEvent(ControlEvent theEvent) {
				Main.getMe().startGame();
			}
		});
		y += 200;
		cp5.addButton("mm_quit")
			.setPosition(x,y)
			.setImages(sketch.loadImage("mm_quit0.png"), sketch.loadImage("mm_quit1.png"), sketch.loadImage("mm_quit2.png"))
			.updateSize()
			.addListener(new ControlListener() {
				public void controlEvent(ControlEvent theEvent) {
					System.exit(0);
				}
			});
	}
	
	private void addMain(PApplet sketch) {
		float x = (sketch.width - 350) / 2;
		float y = 250;
		cp5.addButton("mm_newgame")
		.setPosition(x,y)
		.setImages(sketch.loadImage("mm_newgame0.png"), sketch.loadImage("mm_newgame1.png"), sketch.loadImage("mm_newgame1.png"))
		.updateSize()
		.addListener(new ControlListener() {
			public void controlEvent(ControlEvent theEvent) {
				Main.getMe().startGame();
			}
		});
		y += 200;
		cp5.addButton("mm_quit")
			.setPosition(x,y)
			.setImages(sketch.loadImage("mm_quit0.png"), sketch.loadImage("mm_quit1.png"), sketch.loadImage("mm_quit2.png"))
			.updateSize()
			.addListener(new ControlListener() {
				public void controlEvent(ControlEvent theEvent) {
					System.exit(0);
				}
			});
	}
	
	public void draw(PGraphics g){
		g.background(bg_color);
		if(Main.getLibraryManager().getActiveLevel() != null && cp5.get("mm_resume") == null){
			cp5.remove("mm_resume");
			cp5.remove("mm_newgame");
			cp5.remove("mm_quit");
			addAll(Main.getMe());
		}
		cp5.draw();
	}
}
