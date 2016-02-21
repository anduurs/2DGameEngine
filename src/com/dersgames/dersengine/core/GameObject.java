package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.components.RenderableComponent;
import com.dersgames.dersengine.graphics.RenderContext;

public class GameObject {
	
	private List<GameComponent> m_Components;
	private List<RenderableComponent> m_RenderComponents;
	private List<GameObject> m_Children;
	
	private String m_Tag;
	private Vector2f m_Position;
	private GameObject m_Parent;
	private boolean m_Alive;
	
	public GameObject(){
		this("GameObject");
	}
	
	public GameObject(String tag){
		this(tag, 0, 0);
	}
	
	public GameObject(String tag, float x, float y){
		this(tag, new Vector2f(x,y));
	}
	
	public GameObject(String tag, Vector2f position){
		m_Tag = tag;
		m_Parent = null;
		m_Alive = true;
		
		m_Children = new ArrayList<GameObject>();
		m_Components = new ArrayList<GameComponent>();
		m_RenderComponents = new ArrayList<RenderableComponent>();
		
		m_Position = position;
	}
	
	public GameComponent attachComponent(GameComponent component){
		component.setGameObject(this);
		
		if(component instanceof RenderableComponent){
			RenderableComponent rc = (RenderableComponent) component;
			m_RenderComponents.add(rc);
		}
		
		m_Components.add(component);
		
		return component;
	}
	
	public GameComponent findComponentByTag(String tag){
		for(GameComponent c : m_Components)
			if(c.getTag().equals(tag))
				return c;
		return null;
	}
	
	public GameObject attachChild(GameObject gameObject){
		gameObject.setParent(this);
		m_Children.add(gameObject);
		return gameObject;
	}
	
	public GameObject findChildByTag(String tag){
		for(GameObject go : m_Children){
			if(go.getTag().equals(tag))
				return go;
			else go.findChildByTag(tag);
		}
			
		return null;
	}
	
	public void updateComponents(float dt){
		for(GameComponent c : m_Components)
			if(c.isEnabled())
				c.update(dt);
	}
	
	public void updateAll(float dt){
		updateComponents(dt);
		for(GameObject go : m_Children)
			go.updateAll(dt);
	}
	
	public void renderComponents(RenderContext renderContext){
		for(RenderableComponent rc : m_RenderComponents)
			if(rc.isEnabled())
				rc.render(renderContext);
	}
	
	public void renderAll(RenderContext renderContext){
		renderComponents(renderContext);
		for(GameObject go : m_Children)
			go.renderAll(renderContext);
	}
	
	public void clearDeadGameObjects(){
		Iterator<GameObject> i = m_Children.iterator();
		while(i.hasNext()){
			GameObject go = i.next();
			if(!go.isAlive())
				i.remove();
			else go.clearDeadGameObjects();
		}
	}
	
	public void destroy(){
		m_Alive = false;
		for(GameObject go : m_Children)
			go.destroy();
		
		m_Children.clear();
		m_Components.clear();
		m_RenderComponents.clear();
	}

	public String getTag() {
		return m_Tag;
	}

	public Vector2f getPosition() {
		return m_Position;
	}
	
	public float getX(){
		return m_Position.x;
	}
	
	public float getY(){
		return m_Position.y;
	}
	
	public GameObject getParent() {
		return m_Parent;
	}
	
	public boolean isAlive(){
		return m_Alive;
	}

	public void setParent(GameObject parent) {
		m_Parent = parent;
	}
	
	public int numOfChildren(){
		return m_Children.size();
	}
	
	

}
