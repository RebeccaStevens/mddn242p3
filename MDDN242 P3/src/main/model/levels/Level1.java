package main.model.levels;

import lib.game.Light;
import lib.game.cameras.CameraFollowTwo;
import lib.game.entities.actors.PushableBox;
import lib.game.entities.platforms.BasicPlatform;
import lib.game.lights.AmbientLight;
import lib.game.lights.PointLight;
import main.model.entities.player.Player1;
import main.model.entities.player.Player2;
import processing.core.PConstants;
import processing.core.PVector;

public class Level1 extends StandardLevel implements PConstants {

	private Light followLight;
	private Light p1FollowLight;
	private Light p2FollowLight;

	public Level1(){
		init();
	}

	private void init() {
		setGravity(1500);
		
		player1 = new Player1(this, -100, -675, -100);
		player2 = new Player2(this,  100, -690,  100);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
		
		setCamera(new CameraFollowTwo(this, player1, player2, 0, -300, 500));
		
		new BasicPlatform(this, 0, 50, 0, 1250, 100, 800);
		new BasicPlatform(this, 1625, 50, 0, 2000, 100,  500);

		new PushableBox(this, 500, -50, 0, 100, 100,  100, 0.5F);
		
		new AmbientLight(this, 0x666666);
		followLight = new PointLight(this, 0, 0, 0, 0xFFFFFF);
		p1FollowLight = new PointLight(this, 0, 0, 0, 0x300000);
		p2FollowLight = new PointLight(this, 0, 0, 0, 0x000030);
		
		changeState(State.Play);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		PVector t1p = player1.getLocation();
		PVector t2p = player2.getLocation();
		
		t1p.y -= 300;
		t2p.y -= 300;
		
		PVector centroid = new PVector(
				(t1p.x + t2p.x) / 2,
				(t1p.y + t2p.y) / 2,
				(t1p.z + t2p.z) / 2
		);
		
		followLight.setLocation(centroid);
		p1FollowLight.setLocation(t1p);
		p2FollowLight.setLocation(t2p);
	}

	@Override
	public void restart() {
		new Level1().makeActive();
	}
}
