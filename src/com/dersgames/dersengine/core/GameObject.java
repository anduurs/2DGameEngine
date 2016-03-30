package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.components.Renderable2D;
import com.dersgames.dersengine.graphics.RenderContext;

public class GameObject {
	
	private List<GameComponent> m_Components;
	private List<Renderable2D> m_RenderableComponents;
	private List<GameObject> m_Children;
	
	protected String m_Tag;
	protected Vector2f m_Position;
	protected GameObject m_Parent;
	protected boolean m_Alive;
	
	private float m_ChildX, m_ChildY;
	
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
		m_RenderableComponents = new ArrayList<Renderable2D>();
		
		m_Position = position;
	}
	
	public void sendToComponents(String message){
		for(GameComponent gc : getComponents())
			gc.receive(message);
	}
	
	public void send(String message){
		sendToComponents(message);
		for(GameObject go : getChildren())
			go.send(message);
	}
	
	public void sendToComponents(String message, GameComponent sender){
		for(GameComponent gc : getComponents())
			gc.receive(message, sender);
	}
	
	public void send(String message, GameComponent sender){
		sendToComponents(message, sender);
		for(GameObject go : getChildren())
			go.send(message, sender);
	}
	
	public GameComponent attachComponent(GameComponent component){
		component.setGameObject(this);
		
		if(component instanceof Renderable2D){
			Renderable2D rc = (Renderable2D) component;
			getRenderableComponents().add(rc);
		}
		
		getComponents().add(component);
		
		return component;
	}
	
	public GameComponent findComponentByTag(String tag){
		for(GameComponent c : getComponents())
			if(c.getTag().equals(tag))
				return c;
		return null;
	}
	
	public GameObject addChild(GameObject gameObject){
		gameObject.setParent(this);
		getChildren().add(gameObject);
		return gameObject;
	}
	
	public GameObject attachChild(GameObject gameObject, float xPos, float yPos){
		gameObject.setParent(this);
		this.m_ChildX = xPos;
		this.m_ChildY = yPos;
		gameObject.getPosition().x = m_Position.x + xPos;
		gameObject.getPosition().y = m_Position.y + yPos;
		getChildren().add(gameObject);
		return gameObject;
	}
	
	public GameObject findChildByTag(String tag){
		for(GameObject go : getChildren()){
			if(go.getTag().equals(tag))
				return go;
			else go.findChildByTag(tag);
		}
			
		return null;
	}
	
	public void updateComponents(float dt){
		for(GameComponent c : getComponents())
			if(c.isEnabled())
				c.update(dt);
	}
	
	public void updateAll(float dt){
		updateComponents(dt);
		for(GameObject go : getChildren())
			go.updateAll(dt);
	}
	
	public void renderComponents(RenderContext renderContext){
		for(Renderable2D rc : getRenderableComponents())
			if(rc.isEnabled())
				rc.render(renderContext);
	}
	
	public void renderAll(RenderContext renderContext){
		renderComponents(renderContext);
		for(GameObject go : getChildren())
			go.renderAll(renderContext);
	}
	
	public void clearDeadGameObjects(){
		Iterator<GameObject> i = getChildren().iterator();
		while(i.hasNext()){
			GameObject go = i.next();
			if(!go.isAlive()){
				if(go instanceof Collideable){
					CollisionManager.removeCollisionBox((Collideable)go);
				}
				i.remove();
			}
			else go.clearDeadGameObjects();
		}
	}
	
	public void destroy(){
		m_Alive = false;
	}
	
	public void removeChild(GameObject child){
		getChildren().remove(child);
	}
	
	private synchronized List<GameObject> getChildren(){
		return m_Children;
	}
	
	private synchronized List<GameComponent> getComponents(){
		return m_Components;
	}
	
	private synchronized List<Renderable2D> getRenderableComponents(){
		return m_RenderableComponents;
	}

	public String getTag() {
		return m_Tag;
	}
	
	public void setPosition(Vector2f position){
		m_Position = position;
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
	
	public void setLive(boolean live){
		m_Alive = live;
	}

	public void setParent(GameObject parent) {
		m_Parent = parent;
	}
	
	public int numOfChildren(){
		return m_Children.size();
	}

	public float getxOffset() {
		return m_ChildX;
	}

	public void setxOffset(float xOffset) {
		this.m_ChildX = xOffset;
	}

	public float getyOffset() {
		return m_ChildY;
	}

	public void setyOffset(float yOffset) {
		this.m_ChildY = yOffset;
	}
}
