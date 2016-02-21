package com.dersgames.dersengine.components;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.SceneGraph;
import com.dersgames.dersengine.core.Vector2f;
import com.dersgames.dersengine.graphics.Bitmap;
import com.dersgames.dersengine.graphics.ColorRGBA;
import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.utils.AssetsManager;
import com.dersgames.dersengine.utils.MathUtil;

public class TileLayer extends RenderableComponent{
	
	private HashMap<Integer, Tile> m_Tiles;
	private Bitmap m_Bitmap;
	
	private int m_Width;
	private int m_Height;
	
	public static int TILE_SIZE;
	public static int SHIFT_VALUE;
	
	public TileLayer(String tag, int width, int height, int tileSize){
		super(tag);
		
		TILE_SIZE = tileSize;
		SHIFT_VALUE = MathUtil.log2(TILE_SIZE);
		
		m_Bitmap = new Bitmap(width, height);
		
		m_Width = m_Bitmap.getWidth();
		m_Height = m_Bitmap.getHeight();
		
		m_Tiles = new HashMap<Integer, Tile>();
		
		m_Bitmap.setPixels(ColorRGBA.GRAY);
	}
	
	public TileLayer(String tag, String imageTag, int tileSize){
		super(tag);
		
		TILE_SIZE = tileSize;
		SHIFT_VALUE = MathUtil.log2(TILE_SIZE);
		
		m_Tiles = new HashMap<Integer, Tile>();
		
		BufferedImage image = AssetsManager.getImage(imageTag);
		m_Width = image.getWidth();
		m_Height = image.getHeight();
		m_Bitmap = new Bitmap(m_Width, m_Height);
		
		image.getRGB(0, 0, m_Width, m_Height, m_Bitmap.getPixelArray(), 0, m_Width);
	}
	
	
	public Vector2f getPlayerStart(int spawnTileColorID){
		Vector2f playerStart = null;
		
		for(int y = 0; y < m_Bitmap.getHeight(); y++)
			for(int x = 0; x < m_Bitmap.getWidth(); x++){
				if(m_Bitmap.getPixel(x, y) == ColorRGBA.RED){
					playerStart = new Vector2f(x*TILE_SIZE,y*TILE_SIZE);
					m_Bitmap.setPixel(x, y, spawnTileColorID);
					break;
				}
			}
		
		return playerStart;
	}
	
	public void addTile(int color, Tile tile){
		m_Tiles.put(color, tile);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || x >= m_Width || y < 0 || y >= m_Height)
			return null;
		
		for(Integer i : m_Tiles.keySet())
			if(m_Bitmap.getPixel(x, y) == i)
				return m_Tiles.get(i);
		
		return null;
	}

	@Override
	public void render(RenderContext renderer){
		GameObject go = SceneGraph.getRoot().findChildByTag("MainCamera");
		renderer.renderLayer(go.getX(), go.getY(), this);
	}
	
	public int getWidth(){
		return m_Width;
	}
	
	public int getHeight(){
		return m_Height;
	}
}
