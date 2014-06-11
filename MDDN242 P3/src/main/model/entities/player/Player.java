package main.model.entities.player;

import processing.core.PVector;
import lib.Key;
import lib.level.Level;
import lib.level.entities.Actor;
import main.input.AnalogStick;
import main.input.ControllerButton;

public abstract class Player extends Actor{

	protected Key key_moveUp;
	protected Key key_moveDown;
	protected Key key_moveLeft;
	protected Key key_moveRight;
	protected Key key_jump;
	protected Key key_duck;
	
	protected boolean hasController = false;
	
	protected AnalogStick ctrl_analogStick;
	protected ControllerButton ctrl_duck;
	protected ControllerButton ctrl_jump;

	protected float walkSpeed;
	
	protected float walkForce;
	protected float jumpForce;
	
	Player otherPlayer;
	
	private PlayerState state;
	PlayerStateFall fallState;
	PlayerStateWalk walkState;
	PlayerStateJump jumpState;
	PlayerStateDuck duckState;
	
	public Player(Level level, float x, float y, float z, float width, float depth, float height, float mass) {
		super(level, x, y, z, width, depth, height, mass);
		
		fallState = new PlayerStateFall(this);
		walkState = new PlayerStateWalk(this);
		jumpState = new PlayerStateJump(this);
		duckState = new PlayerStateDuck(this);
		state = fallState;
	}

	@Override
	public void update(double delta) {
		state.updateState();
		state.update(delta);
	}
	
	@Override
	protected PVector move(double delta) {
		return state.move(delta);
	}

	protected PVector defaultMove(double delta) {
		return super.move(delta);
	}
	
	public void setOtherPlayer(Player otherPlayer){
		this.otherPlayer = otherPlayer;
	}
	
	void setState(PlayerState state){
		this.state.end();
		this.state = state;
		this.state.start();
	}
}