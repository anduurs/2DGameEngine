package com.dersgames.dersengine.components;

import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.graphics.Sprite;

public class Tile{
	
	public static final int SIZE = 16;
	
	private Sprite m_Sprite;

	public Tile(Sprite sprite){
		m_Sprite = sprite;
	}

	public void render(int x, int y, RenderContext renderContext) {
		renderContext.renderTile(this, x, y);
	}
	
	public Sprite getSprite(){
		return m_Sprite;
	}

}
