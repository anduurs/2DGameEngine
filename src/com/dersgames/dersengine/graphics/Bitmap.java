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
	
	public void applyAmbientLight(float ambientFactor){
		for(int i = 0; i < m_Pixels.length; i++){
			//extract the red channel of the pixel
			int r = ((m_Pixels[i] >> 16) & 0xFF);
			//extract the green channel of the pixel
			int g = ((m_Pixels[i] >> 8) & 0xFF);
			//extract the blue channel of the pixel
			int b = ((m_Pixels[i]) & 0xFF);
			
			//multiply each channel by the ambient factor
			r *= ambientFactor;
			g *= ambientFactor * 0.46;
			b *= ambientFactor;
		
			//make sure they dont exceed 255
			if(r > 255) r = 255;
			if(g > 255) g = 255;
			if(b > 255) b = 255;
			
			//recombine the new modified channels into a pixel
			m_Pixels[i] = r << 16 | g << 8 | b;
		}
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
