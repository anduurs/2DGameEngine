package com.dersgames.testgame.gameobjects;

import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.components.StaticSprite;
import com.dersgames.dersengine.components.RenderableComponent.CoordinateSpace;
import com.dersgames.dersengine.core.Collideable;
import com.dersgames.dersengine.core.CollisionManager;
import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.Vector2f;
import com.dersgames.dersengine.graphics.SpriteSheet;

public class Enemy extends GameObject implements Collideable{
	
	private static int instancecount = 0;
	
	private SpriteSheet m_Sheet;
	private BoundingBox m_CollisionBox;
	private int m_Width, m_Height;
	
	public Enemy(Vector2f pos, int width, int height, SpriteSheet sheet){
		super("Enemy" + instancecount++, pos);
		m_Sheet = sheet;
		m_Width = width;
		m_Height = height;
		CollisionManager.addCollisionBox(this);
	}
	
	public void init(){
		m_CollisionBox = new BoundingBox("EnemyBox", getX(), getY(), m_Width, m_Height);
		attachComponent(m_CollisionBox);
//		enemyBox.addCollisionSprite();
		
		attachComponent(new StaticSprite("EnemySprite", m_Sheet, 
				2, 1, m_Width, m_Height, CoordinateSpace.WORLD_SPACE));

	}

	@Override
	public void collisionWith(Collideable collideable) {
		
		if(collideable instanceof Player){
			destroy();
		}
	}

	@Override
	public BoundingBox getCollisionBox() {
		return m_CollisionBox;
	}

}
