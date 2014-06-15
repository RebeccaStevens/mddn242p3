package main.input;

import net.java.games.input.Component;

public class ControllerButton {
	
	private Component component;
	private boolean inverse;
	
	private Runnable event;
	
	private boolean pressed;
	private boolean pPressed;

	public ControllerButton(Component component){
		this(component, false);
	}
	
	public ControllerButton(Component component, boolean inverse) {
		this.component = component;
		this.inverse = inverse;
	}

	public boolean isPressed(){
		return pressed;
	}

	public void onPress(Runnable event) {
		this.event = event;
	}
	
	public void update(){
		pPressed = pressed;
		pressed =  inverse ? component.getPollData() < -0.5F : component.getPollData() > 0.5F;
		if(!pPressed && pressed && event!=null){
			event.run();
		}
	}
}
