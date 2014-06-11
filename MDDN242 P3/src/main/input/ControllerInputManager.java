package main.input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ControllerInputManager {
	
	private static Controller gameController;
	private static AnalogStick analogStickL, analogStickR;
	private static ControllerButton buttonR1, buttonL1;
	
	private ControllerInputManager(){}

	public static void init(){
		for(Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()){
			if(c.getName().toLowerCase().startsWith("controller")){
				gameController = c;
				break;
			}
		}
		if(gameController == null) return;
		
		analogStickL = new AnalogStick(gameController.getComponent(Component.Identifier.Axis.X), gameController.getComponent(Component.Identifier.Axis.Y));
		analogStickR = new AnalogStick(gameController.getComponent(Component.Identifier.Axis.RX), gameController.getComponent(Component.Identifier.Axis.RY));
		
		buttonL1 = new ControllerButton(gameController.getComponent(Component.Identifier.Button._4));
		buttonR1 = new ControllerButton(gameController.getComponent(Component.Identifier.Button._5));
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
	
	public static AnalogStick getAnalogStickL(){
		return analogStickL;
	}
	
	public static AnalogStick getAnalogStickR(){
		return analogStickR;
	}

	public static ControllerButton getButtonR1() {
		return buttonR1;
	}
	
	public static ControllerButton getButtonL1() {
		return buttonL1;
	}

	public static void print() {
		for(Component c : gameController.getComponents()){
			System.out.println(c + ": " + c.getPollData());
		}
	}
}
