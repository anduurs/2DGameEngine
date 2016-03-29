package com.dersgames.testgame.gameobjects;

import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.components.Renderable2D;
import com.dersgames.dersengine.components.Renderable2D.CoordinateSpace;
import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.Vector2f;
import com.dersgames.dersengine.graphics.SpriteSheet;

public class Enemy extends GameObject{
	
	private static int instancecount = 0;
	
	private SpriteSheet m_Sheet;
	private BoundingBox m_CollisionBox;
	private int m_Width, m_Height;
	
	public Enemy(Vector2f pos, int width, int height, SpriteSheet sheet){
		super("Enemy" + instancecount++, pos);
		m_Sheet = sheet;
		m_Width = width;
		m_Height = height;
	}
	
	public void init(){
		m_CollisionBox = new BoundingBox("EnemyBox", getX(), getY(), m_Width, m_Height);
		attachComponent(m_CollisionBox);
		m_CollisionBox.addCollisionSprite();
		
		attachComponent(new Renderable2D("EnemySprite", m_Sheet, 
				2, 1, m_Width, m_Height, CoordinateSpace.WORLD_SPACE));
	}

}
