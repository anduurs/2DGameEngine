package com.dersgames.testgame.components.player;

import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.core.Debug;

public class Weapon extends GameComponent{
	
	private float xOffset, yOffset;

	public Weapon(String tag) {
		super(tag);
		
	}
	
	@Override
	public void init() {
		xOffset = m_GameObject.getParent().getxOffset();
		yOffset = m_GameObject.getParent().getyOffset();
	}
	
	private void hit(){
		
	}

	@Override
	public void receive(String message){
		switch(message){
		case "Hit":
			hit();
			break;
		case "NorthWeaponOffset":
			m_GameObject.getParent().setxOffset(yOffset);
			break;
		case "SouthWeaponOffset":
			m_GameObject.getParent().setxOffset(xOffset);
			break;
		case "EastWeaponOffset":
			m_GameObject.getParent().setxOffset(yOffset);
			break;
		case "WestWeaponOffset":
			m_GameObject.getParent().setxOffset(xOffset);
			break;
		}
	}
	
	@Override
	public void update(float dt) {
		m_GameObject.getPosition().x = m_GameObject.getParent().getX() + m_GameObject.getParent().getxOffset();
		m_GameObject.getPosition().y = m_GameObject.getParent().getY() + m_GameObject.getParent().getyOffset();
	}

}
