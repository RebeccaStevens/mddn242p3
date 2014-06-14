package main.model.levels;

import processing.core.PGraphics;
import lib.Time;
import lib.game.Level3D;
import main.model.entities.player.Player1;
import main.model.entities.player.Player2;

public abstract class StandardLevel extends Level3D{

	protected static final float TIME_FROM_LOSE_UNTIL_RESTART = 1.5F;
	protected static final float START_FADE_IN_TIME = 1F;
	
	protected Player1 player1;
	protected Player2 player2;
	
	enum State{
		Play, Win, Lose;
	}
	
	protected State state;
	protected float stateLastChanged;

	private float timeSinceStateLastChanged;
	
	public StandardLevel(){
		
	}

	@Override
	public void draw(PGraphics g) {
		if(state == State.Play && timeSinceStateLastChanged < START_FADE_IN_TIME) drawStart(g);
		if(state == State.Lose) drawLost(g);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		timeSinceStateLastChanged = Time.getTimeStamp() - stateLastChanged;
		if(state == State.Lose){
			if(timeSinceStateLastChanged >= TIME_FROM_LOSE_UNTIL_RESTART){
				restart();
			}
		}
		checkForWin();
		checkForLost();
	}

	protected void changeState(State newState) {
		System.out.println(newState);
		state = newState;
		stateLastChanged = Time.getTimeStamp();
	}
	
	protected void drawStart(PGraphics g) {
		g.fill(0x000000 | Math.min(Math.max((int)((1 - timeSinceStateLastChanged / START_FADE_IN_TIME) * 0xFF), 0), 0xFF) << 24);
		g.noStroke();
		g.rect(0, 0, g.width, g.height);
	}
	
	protected void drawLost(PGraphics g) {
		g.fill(0x000000 | Math.min(Math.max((int)(timeSinceStateLastChanged / TIME_FROM_LOSE_UNTIL_RESTART * 1.5F * 0xFF), 0), 0xFF) << 24);
		g.noStroke();
		g.rect(0, 0, g.width, g.height);
	}

	protected void checkForWin() {
		// TODO Auto-generated method stub
		
	}

	protected void checkForLost() {
		if(state != State.Play) return;
		if(player1.getY() > 0 || player2.getY() > 0){
			changeState(State.Lose);
		}
	}

	public abstract void restart();
}
