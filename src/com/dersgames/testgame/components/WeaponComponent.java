package com.dersgames.testgame.components;

import com.dersgames.dersengine.components.GameComponent;

public class WeaponComponent extends GameComponent{

	public WeaponComponent(String tag) {
		super(tag);
		
	}

	@Override
	public void update(float dt) {
		m_GameObject.getPosition().x = m_GameObject.getParent().getX() + m_GameObject.getParent().getxOffset();
		m_GameObject.getPosition().y = m_GameObject.getParent().getY() + m_GameObject.getParent().getyOffset();
	}

}
