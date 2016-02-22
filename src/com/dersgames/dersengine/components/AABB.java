package com.dersgames.dersengine.components;

import com.dersgames.dersengine.components.GameComponent;

public class AABB extends GameComponent{
	
	public float x, y;
	public int width, height;
	
	public AABB(String tag, float x, float y, int width, int height){
		super(tag);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersect(AABB box){
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
		x = m_GameObject.getX();
		y = m_GameObject.getY();
	}

}
