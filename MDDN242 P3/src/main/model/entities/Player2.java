package main.model.entities;

import lib.level.Level;
import processing.core.PGraphics;

public class Player2 extends Player{

	public Player2(Level level, float x, float y, float z) {
		super(level, x, y, z, 50, 100, 50, 4);
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
