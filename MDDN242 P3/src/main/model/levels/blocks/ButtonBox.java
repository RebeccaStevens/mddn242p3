package main.model.levels.blocks;

import gamelib.Style;
import gamelib.game.Level;
import gamelib.game.entities.platforms.BasicPlatform;

public class ButtonBox extends BasicPlatform {

	private ButtonBoxAction action;

	public ButtonBox(Level level, float x, float y, float width, float height) {
		super(level, x, y, width, height);
	}
	
	public ButtonBox(Level level, float x, float y, float width, float height, Style style) {
		super(level, x, y, width, height, style);
	}
	
	public ButtonBox(Level level, float x, float y, float z, float width, float height, float depth) {
		super(level, x, y, z, width, height, depth);
	}
	
	public ButtonBox(Level level, float x, float y, float z, float width, float height, float depth, Style style) {
		super(level, x, y, z, width, height, depth, style);
	}

	@Override
	public void update(float delta) {
		if(action != null){
			if(action.condition()){
				action.event();
			}
		}
	}

	public void setAction(ButtonBoxAction action) {
		this.action = action;
	}
}
