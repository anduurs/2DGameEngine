package com.dersgames.dersengine.graphics;

import java.awt.image.BufferedImage;

import com.dersgames.dersengine.utils.AssetsManager;

public class SpriteSheet {
	
	private Bitmap m_Bitmap;
	
	public SpriteSheet(String name){
		BufferedImage img = AssetsManager.getImage(name);
		
		m_Bitmap = new Bitmap(img.getWidth(), img.getHeight());
		
		img.getRGB(0, 0, m_Bitmap.getWidth(), m_Bitmap.getHeight(),
				m_Bitmap.getPixelArray(), 0, m_Bitmap.getWidth());
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
