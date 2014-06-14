package main.model.entities.player;

import lib.Key;
import lib.game.Level;
import main.input.ControllerInputManager;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Player2 extends Player{

	public Player2(Level level, float x, float y, float z) {
		super(level, x, y, z, 50, 100, 50, 4);

		key_moveUp		= new Key(PConstants.UP);
		key_moveDown	= new Key(PConstants.DOWN);
		key_moveLeft	= new Key(PConstants.LEFT);
		key_moveRight	= new Key(PConstants.RIGHT);
		key_moveRun		= new Key(96);	// number pad 0 
		key_jump		= new Key('/');
		key_duck		= new Key('.');
		
		if(ControllerInputManager.gameControllerExist()){
			hasController = true;
			ctrl_analogStick = ControllerInputManager.getAnalogStickR();
			ctrl_jump = ControllerInputManager.getButtonR1();
			ctrl_duck = ControllerInputManager.getButtonR2();
		}
		
		moveForce = 40000;
		jumpForce = -650000;
		
		walkSpeed = 150;
		runSpeed = 300;
	}

	@Override
	public void draw(PGraphics g, float delta) {
		g.box(getWidth(), getHeight(), getDepth());
	}

	@Override
	public String toString() {
		return "Player 2";
	}
}
