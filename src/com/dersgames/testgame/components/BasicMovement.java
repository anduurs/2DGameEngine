package com.dersgames.testgame.components;

import com.dersgames.dersengine.components.GameComponent;

public class BasicMovement extends GameComponent{
	
	private float m_Speed = 3.0f;

	public BasicMovement(String tag) {
		super(tag);
		
	}

	@Override
	public void update(float dt) {
		getGameObject().getPosition().x += m_Speed * dt;
	}

}
