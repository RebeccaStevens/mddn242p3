package main.input;

import net.java.games.input.Component;

public class ControllerButton {
	
	private Component component;
	private boolean inverse;

	public ControllerButton(Component component){
		this(component, false);
	}
	
	public ControllerButton(Component component, boolean inverse) {
		this.component = component;
		this.inverse = inverse;
	}

	public boolean isPressed(){
		return inverse ? component.getPollData() < -0.5F : component.getPollData() > 0.5F;
	}
}
