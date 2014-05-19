package main.model.entities;

import lib.Key;
import lib.level.Level;
import processing.core.PGraphics;
import processing.core.PVector;

public class Player1 extends Player{
	
	private Key moveUp		= new Key('W');
	private Key moveDown	= new Key('S');
	private Key moveLeft	= new Key('A');
	private Key moveRight	= new Key('D');
	
	private float walkForce = 10000;
	private PVector walk;

	public Player1(Level level, float x, float y, float z) {
		super(level, x, y, z, 70, 150, 70, 7);
		walk = new PVector();
	}

	@Override
	public void update(float delta) {
		walk.set(((moveRight.isPressed() ? 1 : -1) - (moveLeft.isPressed() ? 1 : -1)), 0, ((moveDown.isPressed() ? 1 : -1) - (moveUp.isPressed() ? 1 : -1)));
		walk.normalize();
		walk.mult(walkForce);
		
		applyForce(walk);
	}

	@Override
	public void draw(PGraphics g, float delta) {
		g.box(getWidth(), getHeight(), getDepth());
	}

}
