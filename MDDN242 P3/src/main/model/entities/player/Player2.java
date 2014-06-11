package main.model.entities.player;

import lib.Key;
import lib.level.Level;
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
		key_jump		= new Key('/');
		key_duck		= new Key('.');
		
		if(ControllerInputManager.gameControllerExist()){
			hasController = true;
			ctrl_analogStick = ControllerInputManager.getAnalogStickR();
			ctrl_jump = ControllerInputManager.getButtonR1();
		}
		
		walkForce = 40000;
		jumpForce = -1000000;
		
		walkSpeed = 400;
	}

	@Override
	public void draw(PGraphics g, double delta) {
		g.box(getWidth(), getHeight(), getDepth());
	}

	@Override
	public String toString() {
		return "Player 2";
	}
}
