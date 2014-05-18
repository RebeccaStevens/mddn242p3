package main.model.entities;

import processing.core.PGraphics;
import lib.level.Entity;
import lib.level.Level;

public class Player2 extends Entity{

	public Player2(Level level, float x, float y, float z, float width, float depth, float height) {
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
