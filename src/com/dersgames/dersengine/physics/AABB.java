package com.dersgames.dersengine.physics;

public class AABB {
	
	public int x, y;
	public int width, height;
	
	public AABB(int x, int y, int width, int height){
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

}
