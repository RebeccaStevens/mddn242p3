package main.model.levels;

import processing.core.PConstants;
import lib.level.cameras.CameraStatic;
import lib.level.entities.platforms.BasicPlatform;
import main.model.entities.Player1;
import main.model.entities.Player2;

public class Level1 extends StandardLevel implements PConstants {
	
	public Level1(){
		init();
	}

	private void init() {
		setCamera(new CameraStatic(this, 0, -500, 800, 0, 0, 0));
		setGravity(1500);
		
		player1 = new Player1(this, -100, -675, -100);
		player2 = new Player2(this,  100, -690,  100);
		
		new BasicPlatform(this, 0, 0, 0, 1250, 100, 800);
		new BasicPlatform(this, 1625, 0, 0, 2000, 100,  500);
	}
}
