package main.input;

import main.Main;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ControllerInputManager {
	
	private static Controller gameController;
	private static AnalogStick analogStickL, analogStickR;
	private static final int buttonStart=0;
	private static final int buttonSelecet=1;
	private static final int buttonR1=2;
	private static final int buttonR2=3;
	private static final int buttonL1=4;
	private static final int buttonL2=5;
	
	private static ControllerButton[] buttons = new ControllerButton[6];
	
	private ControllerInputManager(){}

	public static void init(){
		for(Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()){
			if(isXBoxController(c)){
				initXBox360(c);
				return;
			}
			if(isPS2Controller(c)){
				initPS2(c);
				return;
			}
			if(gameController == null && (c.getType() == Controller.Type.GAMEPAD || c.getType() == Controller.Type.STICK)){
				gameController = c;
			}
		}
		
		if(gameController == null) return;
		// There is a controller but it's not a xbox 360 or PS2 controller
		
		if(gameController.getType() == Controller.Type.GAMEPAD){
			initXBox360(gameController);
		}
		else if(gameController.getType() == Controller.Type.STICK){
			initPS2(gameController);
		}
	}
	
	private static boolean isXBoxController(Controller controller){
		return controller.getType() == Controller.Type.GAMEPAD && controller.getName().equals("Controller (XBOX 360 For Windows)");
	}
	
	private static boolean isPS2Controller(Controller controller){
		return controller.getType() == Controller.Type.STICK && controller.getName().equals("Twin USB Joystick");
	}
	
	private static void initXBox360(Controller controller){
		gameController = controller;
		
		analogStickL = new AnalogStick(gameController.getComponent(Component.Identifier.Axis.X), gameController.getComponent(Component.Identifier.Axis.Y));
		analogStickR = new AnalogStick(gameController.getComponent(Component.Identifier.Axis.RX), gameController.getComponent(Component.Identifier.Axis.RY));
		
		buttons[buttonL1] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._4));
		buttons[buttonR1] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._5));
		
		buttons[buttonL2] = new ControllerButton(gameController.getComponent(Component.Identifier.Axis.Z));
		buttons[buttonR2] = new ControllerButton(gameController.getComponent(Component.Identifier.Axis.Z), true);
		
		buttons[buttonStart] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._9));
		buttons[buttonSelecet] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._8));
	}
	
	private static void initPS2(Controller controller){
		gameController = controller;
		
		analogStickL = new AnalogStick(gameController.getComponent(Component.Identifier.Axis.X), gameController.getComponent(Component.Identifier.Axis.Y));
		analogStickR = new AnalogStick(gameController.getComponent(Component.Identifier.Axis.RZ), gameController.getComponent(Component.Identifier.Axis.Z));
		
		buttons[buttonL1] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._6));
		buttons[buttonR1] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._7));
		
		buttons[buttonL2] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._4));
		buttons[buttonR2] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._5));
		
		buttons[buttonStart] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._9));
		buttons[buttonSelecet] = new ControllerButton(gameController.getComponent(Component.Identifier.Button._8));
		
		buttons[buttonStart].onPress(new Runnable(){
			@Override public void run() {
				Main.getMe().toggleMenu();
			}
		});
	}
	
	public static boolean gameControllerExist(){
		return gameController != null;
	}
	
	public static void update(){
		if(!gameControllerExist()) return;
		if(!gameController.poll()){
			// Controller Disconnected
		}
		for(ControllerButton b : buttons){
			if(b==null)continue;
			b.update();
		}
	}
	
	public static AnalogStick getAnalogStickL(){
		return analogStickL;
	}
	
	public static AnalogStick getAnalogStickR(){
		return analogStickR;
	}
	
	public static ControllerButton getButtonStart() {
		return buttons[buttonStart];
	}

	public static ControllerButton getButtonSelecet() {
		return buttons[buttonSelecet];
	}

	public static ControllerButton getButtonR1() {
		return buttons[buttonR1];
	}
	
	public static ControllerButton getButtonR2() {
		return buttons[buttonR2];
	}

	public static ControllerButton getButtonL1() {
		return buttons[buttonL1];
	}

	public static ControllerButton getButtonL2() {
		return buttons[buttonL2];
	}

	public static void print() {
		for(Component c : gameController.getComponents()){
			System.out.println(c + ": " + c.getPollData());
		}
	}
	
	public static void printConrollers() {
		for(Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()){
			System.out.println(c + "    " + c.getType());
		}
	}
}
