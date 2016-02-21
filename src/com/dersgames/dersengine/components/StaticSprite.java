package com.dersgames.dersengine.components;

import com.dersgames.dersengine.graphics.Sprite;
import com.dersgames.dersengine.graphics.SpriteSheet;

public class StaticSprite extends RenderableComponent{
	
	public StaticSprite(String tag, SpriteSheet sheet, int startX, int startY, 
			int width, int height, CoordinateSpace space){
		super(tag, space);
		m_Sprite = new Sprite(sheet, startX, startY, width, height);
	}
	
	public StaticSprite(String tag, int width, int height, int color, CoordinateSpace space){
		super(tag, space);
		m_Sprite = new Sprite(width, height, color);
	}
	
	
}
