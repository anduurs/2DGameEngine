package com.dersgames.dersengine.components;

import com.dersgames.dersengine.core.SceneGraph;
import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.graphics.Sprite;

public abstract class RenderableComponent extends GameComponent{
	
	protected Sprite m_Sprite;
	
	public enum CoordinateSpace{WORLD_SPACE, SCREEN_SPACE};
	protected CoordinateSpace m_Space;
	
	public RenderableComponent(String tag){
		super(tag);
		
	}
	
	public RenderableComponent(String tag, CoordinateSpace space){
		super(tag);
		m_Space = space;
	}
	
	@Override
	public void update(float dt) {}
	
	public void render(RenderContext renderer){
		if(m_Space == CoordinateSpace.WORLD_SPACE){
			float x = SceneGraph.getRoot().findChildByTag("MainCamera").getX();
			float y = SceneGraph.getRoot().findChildByTag("MainCamera").getY();
			renderer.renderSprite(m_Sprite, m_GameObject.getX() - x, m_GameObject.getY() - y);
		}else{
			renderer.renderSprite(m_Sprite, m_GameObject.getX(), m_GameObject.getY());
		}
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
