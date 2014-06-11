package main.input;

import net.java.games.input.Component;

public class ControllerButton {
	
	private Component component;

	public ControllerButton(Component component){
		this.component = component;
	}
	
	public boolean isPressed(){
		return component.getPollData() > 0.5F;
	}
}
