package lib.game.lights;

import processing.core.PGraphics;
import lib.game.Level;
import lib.game.Light;

public class PointLight extends Light {

	public PointLight(Level level, float x, float y, float z, int color) {
		super(level, x, y, z, color);
	}

	@Override
	public void apply(PGraphics g) {
		g.pointLight(getRed(), getGreen(), getBlue(), getX(), getY(), getZ());
	}

}
