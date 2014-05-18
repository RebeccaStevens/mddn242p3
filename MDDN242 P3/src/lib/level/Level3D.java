package lib.level;


public class Level3D extends Level {

	public Level3D(){
		if(!getGraphics().is3D()) throw new RuntimeException("Cannot make a 3D level using 2D graphics.");
	}

	@Override
	public boolean is3D() {
		return true;
	}
}
