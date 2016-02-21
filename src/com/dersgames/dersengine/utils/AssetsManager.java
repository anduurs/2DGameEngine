package com.dersgames.dersengine.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class AssetsManager {
	
	private static HashMap<String, BufferedImage> m_ImageLib
			= new HashMap<String, BufferedImage>();
	
	private static AssetsManager instance = null;
	
	private AssetsManager(){
		
	}
	
	public static AssetsManager getInstance(){
		if(instance == null)
			instance = new AssetsManager();
		
		return instance;
	}
	
	public static void addAsset(String tag, String fileName){
		m_ImageLib.put(tag, loadImage(fileName));
	}
	
	private static synchronized BufferedImage loadImage(String path){
		BufferedImage img = null;
		try {
			img = ImageIO.read(AssetsManager.class.getResource("/images/" + path));
		} catch (IOException e) {
			System.err.println("Couldn't read image from resource folder");
			e.printStackTrace();
		}
		
		return img;
	}
	
	public static BufferedImage getImage(String name){
		return m_ImageLib.get(name);
	}

}
