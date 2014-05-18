package main.model.entities;

import lib.level.Level;
import lib.level.entities.Actor;

public abstract class Player extends Actor{

	public Player(Level level, float x, float y, float z, float width, float depth, float height) {
		super(level, x, y, z, width, depth, height);
	}

}
