package com.dersgames.dersengine.graphics;

public class Bitmap {
	
	private int m_Width, m_Height;
	private int[] m_Pixels;
	
	public Bitmap(int width, int height){
		m_Width = width;
		m_Height = height;
		
		m_Pixels = new int[width * height];
	}
	
	public void setPixels(int color){
		for(int i = 0; i < m_Pixels.length; i++)
			m_Pixels[i] = color;
	}
	
	public void copyToIntArray(int[] pixels){
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = m_Pixels[i];
	}
	
	public int getPixel(int x, int y){
		return m_Pixels[x + y * m_Width];
	}
	
	public void setPixel(int x, int y, int color){
		m_Pixels[x + y * m_Width] = color;
	}

	public int getWidth() {
		return m_Width;
	}

	public int getHeight() {
		return m_Height;
	}
	
	public int[] getPixelArray(){
		return m_Pixels;
	}
	
	public int getSize(){
		return m_Pixels.length;
	}
}
