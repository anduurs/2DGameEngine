package com.dersgames.dersengine.components;

import com.dersgames.dersengine.core.GameObject;

public class Camera2D extends GameComponent{
	
	private GameObject m_Player;
	private int m_FrustumWidth, m_FrustumHeight;
	float steps = 40.0f;
	
	public Camera2D(String tag, GameObject player, int frustumWidth, int frustumHeight){
		super(tag);
		
		m_Player = player;
		m_FrustumWidth = frustumWidth;
		m_FrustumHeight = frustumHeight;
	}
	
	public void init(){
		m_GameObject.getPosition().x = m_Player.getX() - (m_FrustumWidth / 2 - 32);
		m_GameObject.getPosition().y = m_Player.getY() - (m_FrustumHeight / 2 - 32);
	}

	@Override
	public void update(float dt) {
//		float currentX = m_GameObject.getPosition().x;
//		float currentY = m_GameObject.getPosition().y;
//		float targetX = m_Player.getX() - (m_FrustumWidth / 2 - 32);
//		float targetY = m_Player.getY() - (m_FrustumHeight / 2 - 32);
//		float diffX = (targetX - currentX) / steps;
//		float diffY = (targetY - currentY) / steps;
//		
//		m_GameObject.getPosition().x += diffX;
//		m_GameObject.getPosition().y += diffY;
		
		m_GameObject.getPosition().x = m_Player.getX() - (m_FrustumWidth / 2 - 32);
		m_GameObject.getPosition().y = m_Player.getY() - (m_FrustumHeight / 2 - 32);
	}

}
