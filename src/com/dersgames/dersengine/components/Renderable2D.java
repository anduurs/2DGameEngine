package com.dersgames.dersengine.components;

import com.dersgames.dersengine.core.SceneGraph;
import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.graphics.Sprite;
import com.dersgames.dersengine.graphics.SpriteSheet;

public class Renderable2D extends GameComponent{
	
	protected Sprite m_Sprite;
	
	public enum CoordinateSpace{WORLD_SPACE, SCREEN_SPACE};
	private CoordinateSpace m_Space;
	
	public Renderable2D(String tag, CoordinateSpace space){
		super(tag);
		m_Space = space;
	}
	
	public Renderable2D(String tag, SpriteSheet sheet, int startX, int startY, 
			int width, int height, CoordinateSpace space){
		super(tag);
		
		m_Space = space;
		m_Sprite = new Sprite(sheet, startX, startY, width, height);
	}
	
	public Renderable2D(String tag, int width, int height, int color, CoordinateSpace space){
		super(tag);
		
		m_Space = space;
		m_Sprite = new Sprite(width, height, color);
	}
	
	@Override
	public void update(float dt) {}
	
	public void render(RenderContext renderer){
		if(m_Space == CoordinateSpace.WORLD_SPACE){
			float camX = SceneGraph.getRoot().findChildByTag("MainCamera").getX();
			float camY = SceneGraph.getRoot().findChildByTag("MainCamera").getY();
			renderer.renderSprite(m_Sprite, m_GameObject.getX() - camX, m_GameObject.getY() - camY);
		}
			
		else renderer.renderSprite(m_Sprite, m_GameObject.getX(), m_GameObject.getY());
	}
	
	public int getWidth(){
		return m_Sprite.getWidth();
	}
	
	public int getHeight(){
		return m_Sprite.getHeight();
	}

	public Sprite getSprite() {
		return m_Sprite;
	}
	
}
