package main.model.entities;

import lib.Key;
import lib.level.Level;
import processing.core.PGraphics;
import processing.core.PVector;

public class Player2 extends Player{

	private Key moveUp		= new Key(38);	// ARROW KEYS
	private Key moveDown	= new Key(40);
	private Key moveLeft	= new Key(37);
	private Key moveRight	= new Key(39);
	
	private float walkForce = 40000;
	private PVector walk;
	
	public Player2(Level level, float x, float y, float z) {
		super(level, x, y, z, 50, 100, 50, 4);
		walk = new PVector();
		limitVelocityHorizontal(500);
	}

	@Override
	public void update(float delta) {
		walk.set(((moveRight.isPressed() ? 1 : -1) - (moveLeft.isPressed() ? 1 : -1)), 0, ((moveDown.isPressed() ? 1 : -1) - (moveUp.isPressed() ? 1 : -1)));
		walk.normalize();
		walk.mult(walkForce);
		
		applyForce(walk, 0);
	}

	@Override
	public void draw(PGraphics g, float delta) {
		g.box(getWidth(), getHeight(), getDepth());
	}

}
