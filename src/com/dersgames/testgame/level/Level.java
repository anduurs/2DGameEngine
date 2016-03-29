package com.dersgames.testgame.level;

import com.dersgames.dersengine.components.Camera2D;
import com.dersgames.dersengine.components.TileLayer;
import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.SceneGraph;
import com.dersgames.dersengine.core.Vector2f;
import com.dersgames.dersengine.graphics.AnimatedTile;
import com.dersgames.dersengine.graphics.AnimationSequence;
import com.dersgames.dersengine.graphics.ColorRGBA;
import com.dersgames.dersengine.graphics.Display;
import com.dersgames.dersengine.graphics.Sprite;
import com.dersgames.dersengine.graphics.SpriteSheet;
import com.dersgames.dersengine.graphics.Tile;
import com.dersgames.dersengine.utils.Randomizer;
import com.dersgames.testgame.gameobjects.Enemy;
import com.dersgames.testgame.gameobjects.Player;

public class Level {

	private static Player m_Player;
	private static TileLayer m_TileLayer;
	
	private int timer = 0;
	private float ambientLight = 1.0f;
	private boolean day = true, night = false;
	
	public Level(String mapName, SceneGraph sceneGraph){
		SpriteSheet playerSpriteSheet = new SpriteSheet("playerspritesheet");
		SpriteSheet tileSet = new SpriteSheet("tileset");
		
		GameObject tileMap = new GameObject("TileMap");
		m_TileLayer = new TileLayer("TileLayer", "map", 16);
		
		AnimationSequence waterAnim = new AnimationSequence("WaterAnimation", 15);
		waterAnim.addSequence(tileSet, 3, 0, 16, 16, 4);
		
		m_TileLayer.addTile(ColorRGBA.GRAY,   new Tile(new Sprite(tileSet, 0, 0, 16, 16), true));
		m_TileLayer.addTile(ColorRGBA.GREEN,  new Tile(new Sprite(tileSet, 1, 0, 16, 16), false));
		m_TileLayer.addTile(ColorRGBA.BROWN,  new Tile(new Sprite(tileSet, 2, 0, 16, 16), false));
		m_TileLayer.addTile(ColorRGBA.YELLOW, new Tile(new Sprite(tileSet, 1, 1, 16, 16), false));
		Tile waterTile =  new AnimatedTile(new Sprite(tileSet, 3, 0, 16, 16), waterAnim, false);
		m_TileLayer.addTile(ColorRGBA.BLUE,  waterTile);
		m_TileLayer.setVoidTile(waterTile);
		
		tileMap.attachComponent(m_TileLayer);
		
		m_Player = new Player(m_TileLayer.getPlayerStart(ColorRGBA.GREEN), 32, 32, playerSpriteSheet);
		m_Player.init();
		
		sceneGraph.addChild(tileMap);
		
		for(int i = 0; i < 100; i++){
			float x = Randomizer.getFloat(0, 16*100);
			float y = Randomizer.getFloat(0, 16*100);
			
			Enemy enemy = new Enemy(new Vector2f(x, y), 32, 32, playerSpriteSheet);
			enemy.init();
			
			sceneGraph.addChild(enemy);
		}
	
		GameObject camera = new GameObject("MainCamera");
		Camera2D cam = new Camera2D("Camera2D", m_Player, 
				Display.getFrameBufferWidth(), Display.getFrameBufferHeight());
		camera.attachComponent(cam);
		cam.init();
		
		sceneGraph.addChild(m_Player);
		sceneGraph.addChild(camera);
	}

	public void update(float dt) {
		if(timer < 7500) timer++;
		else timer = 0;
		
		dayAndNightCycle(dt);
	}
	
	private void dayAndNightCycle(float dt){
		if(ambientLight <= 0.1f){
			ambientLight = 0.1f;
			night = true;
			day = false;
		}
		
		if(ambientLight >= 1f){
			ambientLight = 1f;
			day = true;
			night = false;
		}
		
		if(day){
			if(timer % 5*60 == 0)
				ambientLight -= 0.01f*dt;
		}
		
		if(night){
			if(timer % 5*60 == 0)
				ambientLight += 0.01f*dt;
		}
	}

	public static Tile getTile(int x, int y){
		return m_TileLayer.getTile(x, y);
	}
	
	public float getAmbientLight() {
		return ambientLight;
	}
	
	public static Player getPlayer(){
		return m_Player;
	}
}
