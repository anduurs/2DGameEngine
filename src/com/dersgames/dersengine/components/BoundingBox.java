package com.dersgames.dersengine.components;

import com.dersgames.dersengine.components.RenderableComponent.CoordinateSpace;
import com.dersgames.dersengine.core.CollisionManager;
import com.dersgames.dersengine.graphics.ColorRGBA;

public class BoundingBox extends GameComponent{
	
	public float x, y;
	public int width, height;

	private static int instanceCount;
	
	public BoundingBox(float x, float y, int width, int height){
		this("BoundingBox", x, y, width, height);
	}
	
	public BoundingBox(String tag, float x, float y, int width, int height){
		super(tag);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void addCollisionSprite(){
		StaticSprite m_StaticSprite = new StaticSprite("BoundingBoxSprite" + instanceCount++, width, height,
				ColorRGBA.RED, CoordinateSpace.WORLD_SPACE);
		
		m_GameObject.attachComponent(m_StaticSprite);
	}
	
	public boolean intersect(BoundingBox box){
		  if(this.x <= box.x + box.width && 
			this.x + this.width >= box.x && 
			this.y <= box.y + box.height && 
			this.height + this.y >= box.y){
			return true;
		}
		  return false;
	}

	@Override
	public void update(float dt) {
		if(!m_GameObject.isAlive())
			CollisionManager.removeCollisionBox(this);
		
		x = m_GameObject.getX();
		y = m_GameObject.getY();
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
