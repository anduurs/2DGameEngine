package com.dersgames.dersengine.components;

import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.graphics.Sprite;

public class Tile{

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
