package main.model.levels;

import gamelib.game.cameras.CameraFollowTwo;
import gamelib.game.entities.actors.PushableBox;
import gamelib.game.entities.platforms.BasicPlatform;
import gamelib.game.lights.AmbientLight;
import gamelib.game.lights.SpotLight;
import main.model.entities.player.Player1;
import main.model.entities.player.Player2;
import main.model.levels.blocks.ButtonBox;
import main.model.levels.blocks.ButtonBoxAction;
import processing.core.PConstants;
import processing.core.PVector;

public class Level1 extends StandardLevel implements PConstants {

	private SpotLight followLight;

	public Level1(){
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
		x+=625 + 600;
		new BasicPlatform(this, x, groundLevel,       0, 1200, 100, 500, groundStyle);
		x+=600 + 750 + 150;
		new BasicPlatform(this, x, groundLevel,       0, 1500, 100, 500, groundStyle);
		new BasicPlatform(this, x-400, groundLevel-165,   0,   50, 50, 500, groundStyle);
		new BasicPlatform(this, x-300, groundLevel-165,   0,   50, 50, 500, groundStyle);
		new BasicPlatform(this, x-200, groundLevel-165,   0,   50, 50, 500, groundStyle);
		new BasicPlatform(this, x-100, groundLevel-165,   0,   50, 50, 500, groundStyle);
		new BasicPlatform(this, x, groundLevel-165,   0,   50, 50, 500, groundStyle);
		new BasicPlatform(this, x+100, groundLevel-165,   0,   50, 50, 500, groundStyle);
		new BasicPlatform(this, x+200, groundLevel-165,   0,   50, 50, 500, groundStyle);
		new BasicPlatform(this, x+300, groundLevel-165,   0,   50, 50, 500, groundStyle);
		new BasicPlatform(this, x+400, groundLevel-165,   0,   50, 50, 500, groundStyle);
		x+=750 + 250 + 150;
		new BasicPlatform(this, x, groundLevel,    -175,  500, 100, 150, groundStyle);
		new BasicPlatform(this, x, groundLevel,     175,  500, 100, 150, groundStyle);
		x+=250 + 1000 + 150;
		new BasicPlatform(this, x, groundLevel,       0, 2000, 100, 500, groundStyle);
		new BasicPlatform(this, x, groundLevel-200, -50,  600, 300, 390, groundStyle);
		final BasicPlatform pa = new BasicPlatform(this, x, groundLevel-230, 200,  600, 240,  110, groundStyle);
		final ButtonBox pb = new ButtonBox(this, x+500, groundLevel-60, 170, 60, 20, 60, buttonStyle);
		pb.setAction(new ButtonBoxAction() {
			@Override public boolean condition() {
				return pb  == player1.getGroundEntity() || pb == player2.getGroundEntity();
			}
			@Override public void event() {
				pa.remove();
				pb.remove();
			}
		});
		x+=1000 + 400 + 150;
		new BasicPlatform(this, x, groundLevel,       0, 800, 100, 500, groundStyle);
		new PushableBox  (this, x, groundLevel-400,  0, 80, 80, 500, 0.5F, pushableStyle);
		x+=400 + 400 + 200;
		new BasicPlatform(this, x, groundLevel,       0, 800, 100, 500, groundStyle);
		new PushableBox  (this, x, groundLevel-400,  0, 200, 200, 500, 0.5F, pushableStyle);
		x+=400 + 500 + 250;
		new BasicPlatform(this, x, groundLevel,       0, 1000, 100, 500, groundStyle);
		new PushableBox  (this, x-100, groundLevel-400,  0, 80, 80, 500, 0.5F, pushableStyle);
		new PushableBox  (this, x+100, groundLevel-400,  0, 200, 200, 500, 0.5F, pushableStyle);
		x+=500 + 500 + 300;
		new BasicPlatform(this, x, groundLevel,       0, 1000, 100, 500, groundStyle);
		new BasicPlatform(this, x-200, groundLevel-80,   0, 100, 80, 500, groundStyle);
		new BasicPlatform(this, x+300, groundLevel-125,  0, 400, 250, 500, groundStyle);
		new PushableBox  (this, x+300, groundLevel-400,  0, 130, 130, 130, 0.5F, pushableStyle);
		groundLevel-=200;
		x+=500;
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
		new Level1().makeActive();
	}
}
