package com.dersgames.testgame.states;

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
import com.dersgames.dersengine.physics.AABB;
import com.dersgames.dersengine.utils.AssetsManager;

public class ExamplePlayState extends GameState{
	
	private CollisionManager cm;
	
	public ExamplePlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init(){
		AssetsManager.addAsset("spritesheet", "spritesheet.png");
		AssetsManager.addAsset("map", "testmap.png");
		
		SpriteSheet sheet = new SpriteSheet("spritesheet");
		
		GameObject tileLayer = new GameObject("TileLayer");
		TileLayer tl = new TileLayer("TileLayer", "map", 16);
		tl.addTile(ColorRGBA.GRAY, new Tile(new Sprite(sheet, 0, 6, 16, 16)));
		tileLayer.attachComponent(tl);
		
		GameObject player = new GameObject("Player", tl.getPlayerStart(ColorRGBA.GRAY));
		player.attachComponent(new BasicInputComponent("PlayerInput"));
		AnimationComponent anim = new AnimationComponent("PlayerAnimation", sheet, CoordinateSpace.WORLD_SPACE);
		player.attachComponent(new AABB("PlayerBox", player.getX(), player.getY(), 32, 32));
		cm = new CollisionManager(player);
		
		int animSpeed = 10;
	
		anim.addAnimationSequence(new AnimationSequence("East",  animSpeed), 1, 0, 32, 32, 4);
		anim.addAnimationSequence(new AnimationSequence("West",  animSpeed), 3, 0, 32, 32, 4);
		anim.addAnimationSequence(new AnimationSequence("North", animSpeed), 0, 0, 32, 32, 4);
		anim.addAnimationSequence(new AnimationSequence("South", animSpeed), 2, 0, 32, 32, 4);
		
		player.attachComponent(anim);
		
		GameObject enemy = new GameObject("Enemy", 300, 200);
		enemy.attachComponent(new StaticSprite("EnemySprite", sheet, 
				2, 1, 32, 32, CoordinateSpace.WORLD_SPACE));
		AABB enemyBox = new AABB("EnemyBox", enemy.getX(), enemy.getY(), 32, 32);
		enemy.attachComponent(enemyBox);
		CollisionManager.addCollisionBox(enemyBox);
//		enemy.attachComponent(new BasicMovement("BasicMove"));
		
		GameObject camera = new GameObject("MainCamera");
		Camera2D cam = new Camera2D("Camera2D", player, Display.getDisplayWidth(), Display.getDisplayHeight());
		camera.attachComponent(cam);
		cam.init();
		
		sceneGraph.addChild(tileLayer);
		sceneGraph.addChild(enemy);
		sceneGraph.addChild(player);
		sceneGraph.addChild(camera);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		cm.update(dt);
		if(KeyInput.SPACE){
			gsm.push(new ExamplePauseState(gsm));
		}
	}
	
}
