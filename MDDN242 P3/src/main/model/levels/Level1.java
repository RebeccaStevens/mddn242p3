package main.model.levels;

import lib.game.cameras.CameraFollowTwo;
import lib.game.entities.platforms.BasicPlatform;
import main.model.entities.player.Player1;
import main.model.entities.player.Player2;
import processing.core.PConstants;

public class Level1 extends StandardLevel implements PConstants {
	
	public Level1(){
		init();
	}

	private void init() {
		setDrawBoundingBoxes(true);
		setGravity(1500);
		
		player1 = new Player1(this, -100, -675, -100);
		player2 = new Player2(this,  100, -690,  100);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
		
		setCamera(new CameraFollowTwo(this, player1, player2, 0, -300, 500));
		
		new BasicPlatform(this, 0, 0, 0, 1250, 100, 800);
		new BasicPlatform(this, 1625, 0, 0, 2000, 100,  500);
	}
}
