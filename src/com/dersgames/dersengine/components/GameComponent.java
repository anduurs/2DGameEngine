package com.dersgames.dersengine.components;

import com.dersgames.dersengine.core.GameObject;

public abstract class GameComponent {
	
	protected GameObject m_GameObject;
	protected boolean m_Enabled;
	protected String m_Tag;
	
	public GameComponent(String tag){
		m_Tag = tag;
		m_Enabled = true;
		m_GameObject = null;
	}
	
	public void init(){}
	public abstract void update(float dt);
	public void receive(String message){}
	public void receive(String message, GameComponent sender){}
	
	public GameObject getGameObject() {
		return m_GameObject;
	}

	public void setGameObject(GameObject gameObject) {
		m_GameObject = gameObject;
	}

	public boolean isEnabled() {
		return m_Enabled;
	}
	
	public void enable(){
		m_Enabled = true;
	}
	
	public void disable(){
		m_Enabled = false;
	}

	public String getTag() {
		return m_Tag;
	}

}
