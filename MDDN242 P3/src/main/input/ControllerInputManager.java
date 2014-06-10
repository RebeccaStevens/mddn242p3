package main.input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ControllerInputManager {
	
	private static Controller gameController;
	private static AnalogStick analogStick1;
	private static AnalogStick analogStick2;
	
	private ControllerInputManager(){}

	public static void init(){
		for(Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()){
			if(c.getName().toLowerCase().startsWith("controller")){
				gameController = c;
				break;
			}
		}
		if(gameController == null) return;
		
		analogStick1 = new AnalogStick(gameController.getComponent(Component.Identifier.Axis.X), gameController.getComponent(Component.Identifier.Axis.Y));
		analogStick2 = new AnalogStick(gameController.getComponent(Component.Identifier.Axis.RX), gameController.getComponent(Component.Identifier.Axis.RY));
	}
	
	public static boolean gameControllerExist(){
		return gameController != null;
	}
	
	public static void update(){
		if(!gameControllerExist()) return;
		if(!gameController.poll()){
			// Controller Disconnected
		}
	}
	
	public static AnalogStick getAnalogStick1(){
		return analogStick1;
	}
	
	public static AnalogStick getAnalogStick2(){
		return analogStick2;
	}
}
