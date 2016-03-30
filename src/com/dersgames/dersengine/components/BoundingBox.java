package com.dersgames.dersengine.components;

import java.awt.Rectangle;

import com.dersgames.dersengine.components.Renderable2D.CoordinateSpace;
import com.dersgames.dersengine.core.CollisionManager;
import com.dersgames.dersengine.graphics.ColorRGBA;

public class BoundingBox extends GameComponent{
	
	public float x, y;
	public int width, height;

	private static int instanceCount;
	
	private Rectangle m_Rectangle;
	
	public BoundingBox(float x, float y, int width, int height){
		this("BoundingBox", x, y, width, height);
	}
	
	public BoundingBox(String tag, float x, float y, int width, int height){
		super(tag);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		m_Rectangle = new Rectangle((int)x, (int)y, width, height);
	}
	
	public void addCollisionSprite(){
		Renderable2D sprite = new Renderable2D("BoundingBoxSprite" + instanceCount++, width, height,
				ColorRGBA.RED, CoordinateSpace.WORLD_SPACE);
		m_GameObject.attachComponent(sprite);
	}
	
	public boolean intersects(BoundingBox box){
		if(m_Rectangle.intersects(box.getRectangle()) || m_Rectangle.contains(box.getRectangle()))
			return true;
		return false;
	}

	@Override
	public void update(float dt) {
		x = m_GameObject.getX();
		y = m_GameObject.getY();
		m_Rectangle.setLocation((int) x, (int)y);
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
	
	public Rectangle getRectangle(){
		return m_Rectangle;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
