package main.model.entities.player;

import processing.core.PVector;
import lib.Key;
import lib.game.Level;
import lib.game.entities.Actor;
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

	protected float moveSpeed;
	protected float walkSpeed;
	protected float runSpeed;
	
	protected float moveForce;
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
		if(state.canActivate()){
			this.state.end();
			this.state = state;
			this.state.start();
		}
	}
}