package main.model.levels;

import lib.level.cameras.CameraStatic;
import lib.level.entities.platforms.BasicPlatform;
import main.model.entities.Player1;
import main.model.entities.Player2;

public class Level1 extends StandardLevel {
	
	public Level1(){
		init();
	}

	private void init() {
		setCamera(new CameraStatic(this, 500, 300, -1000, 0, 0, 0));
		setGravity(500);
		
		player1 = new Player1(this, -100, -275, 0, 70, 150, 70);
		player2 = new Player2(this,  100, -290, 0, 50, 120, 50);
		
		new BasicPlatform(this, 0, 600, 0, 1500, 100, 800);
	}
}
