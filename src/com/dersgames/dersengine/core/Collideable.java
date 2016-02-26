package com.dersgames.dersengine.core;

import com.dersgames.dersengine.components.BoundingBox;

public interface Collideable {
	
	public void collisionWith(Collideable collideable);
	public BoundingBox getCollisionBox();

}
