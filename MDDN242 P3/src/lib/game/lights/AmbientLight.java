package lib.game.lights;

import processing.core.PGraphics;
import lib.game.Level;
import lib.game.Light;

public class AmbientLight extends Light {

	public AmbientLight(Level level, int color) {
		super(level, 0, 0, 0, color);
	}

	@Override
	public void apply(PGraphics g) {
		g.ambientLight(getRed(), getGreen(), getBlue(), getX(), getY(), getZ());
	}

}
