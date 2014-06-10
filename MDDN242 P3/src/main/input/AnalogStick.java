package main.input;

import processing.core.PVector;
import net.java.games.input.Component;

public class AnalogStick {
	
	private Component x, y;
	private float errorCorrector;

	public AnalogStick(Component x, Component y) {
		this.x = x;
		this.y = y;
		setCenterErrorCorrector(0.25F);
	}
	
	public void setCenterErrorCorrector(float errorCorrector){
		this.errorCorrector = errorCorrector;
	}

	public float getX(){
		float v = x.getPollData();
		float av = Math.abs(v);
		if(av < errorCorrector) return 0;
		
		v = Math.signum(v) * ((av - errorCorrector) / (1 - errorCorrector));
		return v;
	}
	
	public float getY(){
		float v = y.getPollData();
		float av = Math.abs(v);
		if(av < errorCorrector) return 0;
		
		v = Math.signum(v) * ((av - errorCorrector) / (1 - errorCorrector));
		return v;
	}
	
	public PVector getData(){
		return new PVector(getX(), getY());
	}
}
