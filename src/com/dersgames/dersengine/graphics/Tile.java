package com.dersgames.dersengine.graphics;

import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.graphics.Sprite;

public class Tile{

	protected Sprite m_Sprite;
	protected boolean m_Solid;

	public Tile(Sprite sprite, boolean solid){
		m_Sprite = sprite;
		m_Solid = solid;
	}

	public void render(int x, int y, RenderContext renderContext) {
		renderContext.renderTile(this, x, y);
	}
	
	public Sprite getSprite(){
		return m_Sprite;
	}

	public boolean isSolid() {
		return m_Solid;
	}

}
