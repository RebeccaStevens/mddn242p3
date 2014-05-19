package main.model.entities;

import lib.level.Level;
import processing.core.PGraphics;

public class Player1 extends Player{

	public Player1(Level level, float x, float y, float z, float width, float depth, float height) {
		super(level, x, y, z, width, depth, height);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(PGraphics g, float delta) {
		g.box(getWidth(), getHeight(), getDepth());
	}

}
