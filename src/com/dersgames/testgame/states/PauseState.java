package com.dersgames.testgame.states;

import com.dersgames.dersengine.core.GameState;
import com.dersgames.dersengine.core.GameStateManager;
import com.dersgames.dersengine.core.Timer;
import com.dersgames.dersengine.core.Timer.TimeUnit;

public class PauseState extends GameState{

	private Timer timer;
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		timer = new Timer();
//		GameObject obj = new GameObject("RectObj", 50, 50);
//		obj.attachComponent(new StaticSprite("Rectangle", 32, 32, ColorRGBA.BLUE, CoordinateSpace.SCREEN_SPACE));
//		sceneGraph.addChild(obj);
	}
	
	@Override
	public void update(float dt) {
		
		timer.update(dt);
		timer.start();
		if(timer.getTime(TimeUnit.SECONDS) == 3){
			System.out.println("HELLO");
			timer.stop();
		}
		
//		if(KeyInput.B){
//			SceneGraph.getRoot().findChildByTag("RectObj").destroy();
//			gsm.pop();
//		}
	}

}
