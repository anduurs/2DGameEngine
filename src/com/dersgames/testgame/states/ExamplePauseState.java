package com.dersgames.testgame.states;

import com.dersgames.dersengine.components.RenderableComponent.CoordinateSpace;
import com.dersgames.dersengine.components.StaticSprite;
import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.GameState;
import com.dersgames.dersengine.core.GameStateManager;
import com.dersgames.dersengine.core.SceneGraph;
import com.dersgames.dersengine.graphics.ColorRGBA;
import com.dersgames.dersengine.input.KeyInput;

public class ExamplePauseState extends GameState{

	public ExamplePauseState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		GameObject obj = new GameObject("RectObj", 50, 50);
		obj.attachComponent(new StaticSprite("Rectangle", 32, 32, ColorRGBA.BLUE, CoordinateSpace.SCREEN_SPACE));
		sceneGraph.addChild(obj);
	}
	
	@Override
	public void update(float dt) {
		if(KeyInput.B){
			SceneGraph.getRoot().findChildByTag("RectObj").destroy();
			gsm.pop();
		}
	}

}
