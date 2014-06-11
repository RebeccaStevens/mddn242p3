package main.model.entities.player;

import lib.Key;
import lib.level.Level;
import main.input.ControllerInputManager;
import processing.core.PGraphics;

public class Player1 extends Player{

	public Player1(Level level, float x, float y, float z) {
		super(level, x, y, z, 70, 150, 70, 7);

		key_moveUp		= new Key('W');
		key_moveDown	= new Key('S');
		key_moveLeft	= new Key('A');
		key_moveRight	= new Key('D');
		key_jump		= new Key('C');
		key_duck		= new Key('Z');
		
		if(ControllerInputManager.gameControllerExist()){
			hasController = true;
			ctrl_analogStick = ControllerInputManager.getAnalogStickL();
			ctrl_jump = ControllerInputManager.getButtonL1();
		}
		
		walkForce = 50000;
		jumpForce = -3000000;
		
		walkSpeed = 500;
	}

	@Override
	public void draw(PGraphics g, double delta) {
		g.box(getWidth(), getHeight(), getDepth());
	}

	@Override
	public String toString() {
		return "Player 1";
	}
}