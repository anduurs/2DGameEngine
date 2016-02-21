package com.dersgames.dersengine.graphics;

public class Sprite {
	
	private Bitmap m_Bitmap;
	
	public Sprite(SpriteSheet sheet, int startX, int startY, 
			int width, int height){
		
		m_Bitmap = new Bitmap(width, height);
		
		startX = startX * m_Bitmap.getWidth();
		startY = startY * m_Bitmap.getHeight();
		
		for(int y = 0; y < height; y++)
			for(int x = 0; x < width; x++)
				m_Bitmap.setPixel(x, y, sheet.getBitmap().getPixel(startX + x, startY + y));				
	}
	
	public Sprite(int width, int height, int color){
		m_Bitmap = new Bitmap(width, height);
		m_Bitmap.setPixels(color);		
	}
	
	public Bitmap getBitmap(){
		return m_Bitmap;
	} 
	
	public int getWidth(){
		return m_Bitmap.getWidth();
	}
	
	public int getHeight(){
		return m_Bitmap.getHeight();
	}

}
