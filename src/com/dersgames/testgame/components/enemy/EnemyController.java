package com.dersgames.testgame.components.enemy;

import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.core.Debug;
import com.dersgames.testgame.gameobjects.Player;

public class EnemyController extends GameComponent{

	public EnemyController(String tag) {
		super(tag);
		
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void receive(String message, GameComponent sender){
		switch(message){
		case "Collision":
			if(sender.getGameObject() instanceof Player){
				m_GameObject.destroy();
			}
			break;
		}
	}

	@Override
	public void update(float dt) {
		
		
	}

	

}
