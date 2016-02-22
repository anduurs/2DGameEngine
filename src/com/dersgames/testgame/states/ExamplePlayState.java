package com.dersgames.testgame.states;

import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.components.AnimationComponent;
import com.dersgames.dersengine.components.BasicInputComponent;
import com.dersgames.dersengine.components.Camera2D;
import com.dersgames.dersengine.components.RenderableComponent.CoordinateSpace;
import com.dersgames.dersengine.components.StaticSprite;
import com.dersgames.dersengine.components.Tile;
import com.dersgames.dersengine.components.TileLayer;
import com.dersgames.dersengine.core.CollisionManager;
import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.GameState;
import com.dersgames.dersengine.core.GameStateManager;
import com.dersgames.dersengine.graphics.AnimationSequence;
import com.dersgames.dersengine.graphics.ColorRGBA;
import com.dersgames.dersengine.graphics.Display;
import com.dersgames.dersengine.graphics.Sprite;
import com.dersgames.dersengine.graphics.SpriteSheet;
import com.dersgames.dersengine.input.KeyInput;
import com.dersgames.dersengine.utils.AssetsManager;
import com.dersgames.dersengine.utils.Randomizer;
import com.dersgames.testgame.components.WeaponComponent;

public class ExamplePlayState extends GameState{
	
	private CollisionManager m_CollisionManager;
	private int timer = 0;

	public ExamplePlayState(GameStateManager gsm) {
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
		
		tl.addTile(ColorRGBA.GRAY,   new Tile(new Sprite(tileSet, 0, 0, 16, 16)));
		tl.addTile(ColorRGBA.GREEN,  new Tile(new Sprite(tileSet, 1, 0, 16, 16)));
		tl.addTile(ColorRGBA.BROWN,  new Tile(new Sprite(tileSet, 2, 0, 16, 16)));
		tl.addTile(ColorRGBA.YELLOW, new Tile(new Sprite(tileSet, 3, 0, 16, 16)));
		tl.addTile(ColorRGBA.BLUE,   new Tile(new Sprite(tileSet, 4, 1, 16, 16)));
		
		tileLayer.attachComponent(tl);
		
		GameObject player = new GameObject("Player", tl.getPlayerStart(ColorRGBA.GRAY));
		m_CollisionManager = new CollisionManager(player);
		player.attachComponent(new BasicInputComponent("PlayerInput"));
		AnimationComponent anim = new AnimationComponent("PlayerAnimation", playerSpriteSheet, CoordinateSpace.WORLD_SPACE);
		player.attachComponent(new BoundingBox("PlayerBox", (int)player.getX(), (int)player.getY(), 32, 32));
		
		GameObject playerWeapon = new GameObject("PlayerWeapon");
		playerWeapon.attachComponent(new StaticSprite("WeaponSprite", 6, 6, ColorRGBA.RED, CoordinateSpace.WORLD_SPACE));
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
			enemy.attachComponent(new StaticSprite("EnemySprite", playerSpriteSheet, 
					2, 1, 32, 32, CoordinateSpace.WORLD_SPACE));
			
			BoundingBox enemyBox = new BoundingBox("EnemyBox", (int)enemy.getX(), (int)enemy.getY(), 32, 32);
			enemy.attachComponent(enemyBox);
			CollisionManager.addCollisionBox(enemyBox);
			
//			GameObject weapon = new GameObject("EnemyWeapon");
//			weapon.attachComponent(new StaticSprite("WeaponSprite", 10, 10, ColorRGBA.RED, CoordinateSpace.WORLD_SPACE));
//			enemy.attachChild(weapon, 2, 20);
			
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
		
		super.update(dt);
		m_CollisionManager.update(dt);
	}
	
}
