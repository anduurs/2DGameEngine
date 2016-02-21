package com.dersgames.dersengine.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.dersgames.dersengine.input.KeyInput;

public class Display extends Canvas{

	private static final long serialVersionUID = 1L;
	
	private static int m_Width, m_Height;
	private final static int m_Scale = 3;
	
	private BufferedImage m_DisplayImage;
	private int[] m_PixelData;
	private BufferStrategy m_BufferStrategy;
	
	private Bitmap m_FrameBuffer;
	private Graphics m_GraphicsContext;
	private KeyInput m_KeyInput;
	private static String m_Title;
	
	public Display(int width, int height, String title){
		m_Width  = width;
		m_Height = height;
		m_Title  = title;
		
		Dimension size = new Dimension(width, height);
		
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		JFrame frame = new JFrame(title);
		
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createBufferStrategy(1);
		m_BufferStrategy = getBufferStrategy();
		
		frame.setVisible(true);
		
		m_DisplayImage = new BufferedImage(width / m_Scale, height / m_Scale, BufferedImage.TYPE_INT_RGB);
		m_PixelData = ((DataBufferInt)m_DisplayImage.getRaster().getDataBuffer()).getData();
		
		m_FrameBuffer = new Bitmap(width / m_Scale, height / m_Scale);
		m_KeyInput = new KeyInput();
		
		addKeyListener(m_KeyInput);
		requestFocus();
	}
	
	public void getInput(){
		m_KeyInput.tick();
		if(KeyInput.ESCAPE)
			System.exit(0);
	}
	
	public void clearBuffers(int col){
		m_FrameBuffer.setPixels(col);
	}
	
	public void clearBuffers(){
		clearBuffers(ColorRGBA.BLACK);
	}
	
	public void swapBuffers(){
		m_FrameBuffer.copyToIntArray(m_PixelData);
		do{
			try{
				m_GraphicsContext = m_BufferStrategy.getDrawGraphics();
				m_GraphicsContext.drawImage(m_DisplayImage, 0, 0, getWidth(), getHeight(), null);
			}finally{
				m_GraphicsContext.dispose();
			}
			m_BufferStrategy.show();
		}while(m_BufferStrategy.contentsLost());
	}
	
	public Bitmap getFrameBuffer() {
		return m_FrameBuffer;
	}
	
	public static int getDisplayWidth(){
		return m_Width / m_Scale;
	}
	
	public static int getDisplayHeight(){
		return m_Height / m_Scale;
	}
	
	public static void setTitle(String title){
		m_Title = title;
	}
	
	public static String getTitle(){
		return m_Title;
	}
}
