package com.dersgames.testgame.states;

import com.dersgames.dersengine.components.AnimationComponent;
import com.dersgames.dersengine.components.BasicInputComponent;
import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.components.Camera2D;
import com.dersgames.dersengine.components.RenderableComponent.CoordinateSpace;
import com.dersgames.dersengine.components.StaticSprite;
import com.dersgames.dersengine.components.TileLayer;
import com.dersgames.dersengine.core.CollisionManager;
import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.GameState;
import com.dersgames.dersengine.core.GameStateManager;
import com.dersgames.dersengine.graphics.AnimatedTile;
import com.dersgames.dersengine.graphics.AnimationSequence;
import com.dersgames.dersengine.graphics.ColorRGBA;
import com.dersgames.dersengine.graphics.Display;
import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.graphics.Sprite;
import com.dersgames.dersengine.graphics.SpriteSheet;
import com.dersgames.dersengine.graphics.Tile;
import com.dersgames.dersengine.utils.AssetsManager;
import com.dersgames.dersengine.utils.Randomizer;
import com.dersgames.testgame.components.player.WeaponComponent;

public class PlayState extends GameState{
	
	private CollisionManager m_CollisionManager;
	
	private int timer = 0;
	private float ambientFactor = 1.0f;
	private boolean day = true, night = false;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init(){
		AssetsManager.addAsset("playerspritesheet", "playerspritesheet.png");
		AssetsManager.addAsset("tileset", "tileset.png");
		AssetsManager.addAsset("map", "testmap.png");
		
		SpriteSheet playerSpriteSheet = new SpriteSheet("playerspritesheet");
		SpriteSheet tileSet = new SpriteSheet("tileset");
		
		GameObject tileLayer = new GameObject("TileLayer");
		TileLayer tl = new TileLayer("TileLayer", "map", 16);
		
		AnimationSequence waterAnim = new AnimationSequence("WaterAnimation", 15);
		waterAnim.addSequence(tileSet, 3, 0, 16, 16, 4);
		
		tl.addTile(ColorRGBA.GRAY,   new Tile(new Sprite(tileSet, 0, 0, 16, 16), true));
		tl.addTile(ColorRGBA.GREEN,  new Tile(new Sprite(tileSet, 1, 0, 16, 16), false));
		tl.addTile(ColorRGBA.BROWN,  new Tile(new Sprite(tileSet, 2, 0, 16, 16), false));
		tl.addTile(ColorRGBA.YELLOW, new Tile(new Sprite(tileSet, 1, 1, 16, 16), false));
		tl.addTile(ColorRGBA.BLUE,   new AnimatedTile(new Sprite(tileSet, 3, 0, 16, 16), waterAnim, false));
		
		tileLayer.attachComponent(tl);
		
		GameObject player = new GameObject("Player", tl.getPlayerStart(ColorRGBA.GRAY));
		m_CollisionManager = new CollisionManager(player);
		BoundingBox playerBox = new BoundingBox("PlayerBox", player.getX(), player.getY(), 32, 32);
		player.attachComponent(playerBox);
//		playerBox.addCollisionSprite();
		
		player.attachComponent(new BasicInputComponent("PlayerInput"));
		AnimationComponent anim = new AnimationComponent("PlayerAnimation", playerSpriteSheet, CoordinateSpace.WORLD_SPACE);
		
		GameObject playerWeapon = new GameObject("PlayerWeapon");
		playerWeapon.attachComponent(new StaticSprite("WeaponSprite", 6, 6, ColorRGBA.GREEN, CoordinateSpace.WORLD_SPACE));
		playerWeapon.attachComponent(new WeaponComponent("Weapon"));
		player.attachChild(playerWeapon, 7, 20);
		
		int animSpeed = 10;
	
		anim.addAnimationSequence(new AnimationSequence("East",  animSpeed), 1, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("West",  animSpeed), 3, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("North", animSpeed), 0, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("South", animSpeed), 2, 0, 32, 32, 3);
		
		player.attachComponent(anim);
		
		sceneGraph.addChild(tileLayer);
		
		for(int i = 0; i < 50; i++){
			float x = Randomizer.getFloat(50, 1000);
			float y = Randomizer.getFloat(50, 1000);
			
			GameObject enemy = new GameObject("Enemy", x, y);
			BoundingBox enemyBox = new BoundingBox("EnemyBox", enemy.getX(), enemy.getY(), 32, 32);
			enemy.attachComponent(enemyBox);
//			enemyBox.addCollisionSprite();
			
			CollisionManager.addCollisionBox(enemyBox);
			enemy.attachComponent(new StaticSprite("EnemySprite", playerSpriteSheet, 
					2, 1, 32, 32, CoordinateSpace.WORLD_SPACE));

			sceneGraph.addChild(enemy);
		}
		
		GameObject camera = new GameObject("MainCamera");
		Camera2D cam = new Camera2D("Camera2D", player, Display.getDisplayWidth(), Display.getDisplayHeight());
		camera.attachComponent(cam);
		cam.init();
		
		sceneGraph.addChild(player);
		sceneGraph.addChild(camera);
	}
	
	@Override
	public void update(float dt) {
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(ambientFactor <= 0.1f){
			ambientFactor = 0.1f;
			night = true;
			day = false;
		}
		
		if(ambientFactor >= 1f){
			ambientFactor = 1f;
			day = true;
			night = false;
		}
		
		if(day){
			if(timer % 5*60 == 0){
				ambientFactor -= 0.01f*dt;
			}
		}
		
		if(night){
			if(timer % 5*60 == 0){
				ambientFactor += 0.01f*dt;
			}
		}
		
		super.update(dt);
		m_CollisionManager.update(dt);
	}
	
	public void render(RenderContext renderContext) {
		super.render(renderContext);
		renderContext.applyAmbientLight(ambientFactor);
	}
	
}
