package main.model.levels;

import gamelib.game.cameras.CameraFollowTwo;
import gamelib.game.entities.platforms.BasicPlatform;
import gamelib.game.lights.AmbientLight;
import gamelib.game.lights.SpotLight;
import main.model.entities.player.Player1;
import main.model.entities.player.Player2;
import processing.core.PConstants;
import processing.core.PVector;

public class Level2 extends StandardLevel implements PConstants {

	private SpotLight followLight;

	public Level2(){
		init();
	}

	private void init() {
		setGravity(1500);
		
		player1 = new Player1(this, -100, -675, -100);
		player2 = new Player2(this,  100, -690,  100);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
		
		setCamera(new CameraFollowTwo(this, player1, player2, 0, -200, 500));
		
		createPlatforms();
		createLights();
		
		changeState(State.Play);
	}

	private void createPlatforms() {
		float x = 0;
		float groundLevel = 50;
		
		new BasicPlatform(this, x, groundLevel,       0, 1250, 100, 800, groundStyle);
	}

	private void createLights() {
		new AmbientLight(this, 0x666666);
		followLight = new SpotLight(this, new PVector(0, 0, 0), player1, PConstants.TAU / 4, 2, 0xFFFFFF);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		
		PVector t1p = player1.getLocation();
		PVector t2p = player2.getLocation();
		PVector centroid = new PVector(
				(t1p.x + t2p.x) / 2,
				(t1p.y + t2p.y) / 2,
				(t1p.z + t2p.z) / 2
		);
		PVector centroidA = centroid.get();
		centroidA.y -= 300;
		followLight.setTarget(centroid);
		followLight.setLocation(centroidA);
	}

	@Override
	public void restart() {
		new Level2().makeActive();
	}
}
