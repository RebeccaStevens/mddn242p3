package main;

import gamelib.GameManager;
import main.input.ControllerInputManager;
import main.menu.MainMenu;
import main.model.levels.Level1;
import processing.core.PApplet;

public class Main extends PApplet{

	private static final long serialVersionUID = -5944089766784769746L;
	
	enum GameState{
		MAIN_MENU, IN_GAME;
	}

	private static Main me;
	private static GameManager gameManager;
	private MainMenu mainMenu;
	
	private GameState state;
	
	public Main(){
		me = this;
	}
	
	@Override
	public void init(){
		frame.setTitle("Untitled");
		super.init();
	}

	@Override
	public void setup(){
		size(1024, 768, P3D);
		smooth(8);
		gameManager = new GameManager(this);
		gameManager.setAutoDraw(false);
		mainMenu = new MainMenu();
		state = GameState.MAIN_MENU;
	}
	
	public void update(){
		ControllerInputManager.update();
		switch (state) {
		case IN_GAME:
			gameManager.update();
			break;
		case MAIN_MENU:
			if(ControllerInputManager.getButtonSelecet().isPressed()){
				startGame();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void draw(){
		update();
		switch (state) {
		case IN_GAME:
			gameManager.draw();
			break;
		case MAIN_MENU:
			mainMenu.draw(g);
			break;
		default:
			break;
		}
	}
	
	public static Main getMe() {
		return me;
	}
	
	public static GameManager getLibraryManager() {
		return gameManager;
	}
	
	public static void main(String[] args){
		ControllerInputManager.init();
		PApplet.main(Main.class.getName());
	}

	public void startGame() {
		new Level1().makeActive();
		state = GameState.IN_GAME;
	}
	
	@Override
	public void keyPressed() {
		if(keyCode == ESC){
			toggleMenu();
		}
		key = 0;
	}
	
	public void toggleMenu(){
		if(state == GameState.IN_GAME){
			state = GameState.MAIN_MENU;
		}
		else if(state == GameState.MAIN_MENU && gameManager.getActiveLevel() != null){
			state = GameState.IN_GAME;
		}
	}

	public void resumeGame() {
		state = GameState.IN_GAME;
	}
}
