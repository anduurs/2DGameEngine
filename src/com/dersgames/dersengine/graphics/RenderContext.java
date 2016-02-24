package com.dersgames.dersengine.graphics;

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
	
	public void applyAmbientLight(float ambientFactor){
		m_Bitmap.applyAmbientLight(ambientFactor);
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
		xPos = xPos << TileLayer.SHIFT_VALUE;
		yPos = yPos << TileLayer.SHIFT_VALUE;
		
		xPos -= xOffset;
		yPos -= yOffset;
		
		for(int y = 0; y < TileLayer.TILE_SIZE; y++){
			int yAbs = y + yPos;
			for(int x = 0; x < TileLayer.TILE_SIZE; x++){
				int xAbs = x + xPos;
				if(xAbs < -TileLayer.TILE_SIZE || xAbs >= m_Bitmap.getWidth() || yAbs < 0 || yAbs >= m_Bitmap.getHeight())
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
		
		//defining the render region/clipping space
		int x0 = (int) xScroll >> TileLayer.SHIFT_VALUE;
		int x1 = ((int)(xScroll + m_Bitmap.getWidth() + TileLayer.TILE_SIZE) >> TileLayer.SHIFT_VALUE);
		int y0 = (int) yScroll >> TileLayer.SHIFT_VALUE;
		int y1 = ((int)(yScroll + m_Bitmap.getHeight() + TileLayer.TILE_SIZE) >> TileLayer.SHIFT_VALUE);
		
		for(int y = y0; y < y1; y++)
			for(int x = x0; x < x1; x++)
				if(layer.getTile(x, y) != null)
					layer.getTile(x, y).render(x, y, this);
	}

}
