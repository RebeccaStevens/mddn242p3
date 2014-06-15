package main.model.entities.player;

import gamelib.Key;
import gamelib.game.Level;
import gamelib.game.entities.Actor;
import processing.core.PVector;
import main.input.AnalogStick;
import main.input.ControllerButton;

public abstract class Player extends Actor{

	protected Key key_moveUp;
	protected Key key_moveDown;
	protected Key key_moveLeft;
	protected Key key_moveRight;
	protected Key key_moveRun;
	protected Key key_jump;
	protected Key key_duck;
	
	protected boolean hasController = false;
	
	protected AnalogStick ctrl_analogStick;
	protected ControllerButton ctrl_duck;
	protected ControllerButton ctrl_jump;

	protected int color;
	
	protected float moveSpeed;
	protected float walkSpeed;
	protected float runSpeed;
	
	protected float moveForce;
	protected float jumpForce;
	
	Player otherPlayer;
	
	private PlayerState state;
	final PlayerStateFall fallState;
	final PlayerStateWalk walkState;
	final PlayerStateJump jumpState;
	final PlayerStateDuck duckState;
	
	public Player(Level level, float x, float y, float z, float width, float depth, float height, float mass, int color) {
		super(level, x, y, z, width, depth, height, mass);
		this.color = color;
		
		fallState = new PlayerStateFall(this);
		walkState = new PlayerStateWalk(this);
		jumpState = new PlayerStateJump(this);
		duckState = new PlayerStateDuck(this);
		state = fallState;
	}

	@Override
	public void update(float delta) {
		state.updateState();
		state.update(delta);
	}
	
	@Override
	protected PVector getMoveToLocation(float delta) {
		PVector newLocation = super.getMoveToLocation(delta);
		if(state.canMove(newLocation)) return newLocation;
		setVelocity(0, 0, 0);
		return getLocation();
	}
	
	public void setOtherPlayer(Player otherPlayer){
		this.otherPlayer = otherPlayer;
	}
	
	void setState(PlayerState state){
		if(state.canActivate() && this.state.canDeactivate()){
			this.state.end();
			this.state = state;
			this.state.start();
		}
	}
	
	public int getColor() {
		return color;
	}
	
	PlayerState getState() {
		return state;
	}
}