package main.model.levels;

import lib.level.entities.platforms.BasicPlatform;
import main.model.entities.Player1;
import main.model.entities.Player2;

public class Level1 extends StandardLevel {
	
	public Level1(){
		init();
	}

	public void init() {
		player1 = new Player1(this, 100, 275, 100, 70, 150, 70);
		player2 = new Player2(this, 200, 290, 100, 50, 120, 50);
		
		new BasicPlatform(this, 0, 400, 0, 1000, 100, 1000);
	}
}
