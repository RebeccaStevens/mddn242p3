package main.model.levels;

import gamelib.Time;
import gamelib.game.Level3D;
import processing.core.PGraphics;
import main.model.entities.player.Player1;
import main.model.entities.player.Player2;

public abstract class StandardLevel extends Level3D{

	protected static final float TIME_FROM_LOSE_UNTIL_RESTART = 1.5F;
	protected static final float START_FADE_IN_TIME = 1F;
	
	protected final gamelib.Style groundStyle = new gamelib.Style();
	protected final gamelib.Style pushableStyle = new gamelib.Style();
	protected final gamelib.Style floatStyle = new gamelib.Style();
	protected final gamelib.Style buttonStyle = new gamelib.Style();
	
	protected Player1 player1;
	protected Player2 player2;
	
	enum State{
		Play, Win, Lose;
	}
	
	protected State state;
	protected float stateLastChanged;

	private float timeSinceStateLastChanged;
	
	public StandardLevel(){
		groundStyle.fill(0xFFa0e664);
		groundStyle.noStroke();
		
		pushableStyle.fill(0xFF14b4ff);
		pushableStyle.noStroke();
		
		floatStyle.fill(0xB0000000);
		floatStyle.noStroke();
		
		buttonStyle.fill(0xFFDD66FF);
		buttonStyle.noStroke();
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
