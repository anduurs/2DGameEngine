package com.dersgames.dersengine.graphics;

import com.dersgames.dersengine.components.Tile;
import com.dersgames.dersengine.components.TileLayer;

public class RenderContext{
	
	private Bitmap m_Bitmap;
	
	private int xOffset, yOffset;
	
	public RenderContext(Bitmap bitmap){
		m_Bitmap = bitmap;
	}
	
	public void renderSprite(Sprite sprite, float xPos, float yPos){
		for(int y = 0; y < sprite.getHeight(); y++){
			int yAbs = (int) (y + yPos);
			for(int x = 0; x < sprite.getWidth(); x++){
				int xAbs = (int) (x + xPos);
				if(xAbs < -sprite.getWidth() || xAbs >= m_Bitmap.getWidth() || yAbs < 0 || yAbs >= m_Bitmap.getHeight())
					break;
				if(xAbs < 0) xAbs = 0;
				int col = sprite.getBitmap().getPixel(x, y);
				if(col != ColorRGBA.PINK)
					m_Bitmap.setPixel(xAbs, yAbs, col);
			}
		}
	}
	
	public void renderRectangle(int xPos, int yPos, int width, int height, int col){
		for(int y = 0; y < height; y++){
			int yAbs = y + yPos;
			for(int x = 0; x < width; x++){
				int xAbs = x + xPos;
				if(xAbs < -width || xAbs >= m_Bitmap.getWidth() || yAbs < 0 || yAbs >= m_Bitmap.getHeight())
					break;
				if(xAbs < 0) xAbs = 0;
				m_Bitmap.setPixel(xAbs, yAbs, col);
			}
		}
	}
	
	public void renderTile(Tile tile, int xPos, int yPos){
		xPos = xPos << 4;
		yPos = yPos << 4;
		
		xPos -= xOffset;
		yPos -= yOffset;
		
		for(int y = 0; y < Tile.SIZE; y++){
			int yAbs = y + yPos;
			for(int x = 0; x < Tile.SIZE; x++){
				int xAbs = x + xPos;
				if(xAbs < -Tile.SIZE || xAbs >= m_Bitmap.getWidth() || yAbs < 0 || yAbs >= m_Bitmap.getHeight())
					break;
				if(xAbs < 0) xAbs = 0;
				int col = tile.getSprite().getBitmap().getPixel(x, y);
				m_Bitmap.setPixel(xAbs, yAbs, col);
			}
		}
	}
	
	public void renderLayer(float xScroll, float yScroll, TileLayer layer){
		xOffset = (int)xScroll;
		yOffset = (int)yScroll;
		
		int x0 = (int) xScroll >> 4;
		int x1 = ((int)(xScroll + m_Bitmap.getWidth() + Tile.SIZE) >> 4);
		int y0 = (int) yScroll >> 4;
		int y1 = ((int)(yScroll + m_Bitmap.getHeight() + Tile.SIZE) >> 4);
		
		for(int y = y0; y < y1; y++)
			for(int x = x0; x < x1; x++)
				if(layer.getTile(x, y) != null)
					layer.getTile(x, y).render(x, y, this);
	}

}
