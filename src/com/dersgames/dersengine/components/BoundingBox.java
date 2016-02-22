package com.dersgames.dersengine.components;

import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.core.CollisionManager;

public class BoundingBox extends GameComponent{
	
	public int x, y;
	public int width, height;
	
	public BoundingBox(int x, int y, int width, int height){
		this("BoundingBox", x, y, width, height);
	}
	
	public BoundingBox(String tag, int x, int y, int width, int height){
		super(tag);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersect(BoundingBox box){
		  if(this.x < box.x + box.width && 
			this.x + this.width > box.x && 
			this.y < box.y + box.height && 
			this.height + this.y > box.y){
			return true;
		}
		  return false;
	}

	@Override
	public void update(float dt) {
		if(!m_GameObject.isAlive()){
			CollisionManager.removeCollisionBox(this);
		}
		x = (int)m_GameObject.getX();
		y = (int)m_GameObject.getY();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
