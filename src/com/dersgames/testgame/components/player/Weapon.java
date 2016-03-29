package com.dersgames.testgame.components.player;

import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.core.Debug;

public class Weapon extends GameComponent{

	public Weapon(String tag) {
		super(tag);
		
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
			m_GameObject.getParent().setxOffset(20);
			break;
		case "SouthWeaponOffset":
			m_GameObject.getParent().setxOffset(7);
			break;
		case "EastWeaponOffset":
			m_GameObject.getParent().setxOffset(20);
			break;
		case "WestWeaponOffset":
			m_GameObject.getParent().setxOffset(7);
			break;
		}
	}
	
	@Override
	public void update(float dt) {
		m_GameObject.getPosition().x = m_GameObject.getParent().getX() + m_GameObject.getParent().getxOffset();
		m_GameObject.getPosition().y = m_GameObject.getParent().getY() + m_GameObject.getParent().getyOffset();
	}

}
