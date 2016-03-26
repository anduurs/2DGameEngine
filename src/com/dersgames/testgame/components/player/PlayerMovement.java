package com.dersgames.testgame.components.player;

import com.dersgames.dersengine.components.GameComponent;

public class PlayerMovement extends GameComponent{
	
	private float m_Speed;

	public PlayerMovement(String tag, float speed) {
		super(tag);
		m_Speed = speed;
	}
	
	public void receive(String message){
		switch(message){
		case "NorthMove":
			m_GameObject.getPosition().y -= m_Speed;
			break;
		case "SouthMove":
			m_GameObject.getPosition().y += m_Speed;
			break;
		case "EastMove":
			m_GameObject.getPosition().x += m_Speed;
			break;
		case "WestMove":
			m_GameObject.getPosition().x -= m_Speed;
			break;
		}
	}

	@Override
	public void update(float dt) {
		
		
	}

}
