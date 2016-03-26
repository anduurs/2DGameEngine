package com.dersgames.testgame.states;

import com.dersgames.dersengine.core.CollisionManager;
import com.dersgames.dersengine.core.GameState;
import com.dersgames.dersengine.core.GameStateManager;
import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.utils.AssetsManager;
import com.dersgames.testgame.level.Level;

public class PlayState extends GameState{
	
	private Level m_Level; 
	private CollisionManager m_CollisionManager;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init(){
		AssetsManager.addAsset("playerspritesheet", "playerspritesheet.png");
		AssetsManager.addAsset("tileset", "tileset.png");
		AssetsManager.addAsset("map", "testmap.png");
		
		m_CollisionManager = new CollisionManager();
		m_Level = new Level("map", sceneGraph);
	}
	
	@Override
	public void update(float dt) {
		m_CollisionManager.update(dt);
		m_Level.update(dt);
		super.update(dt);
	}
	
	@Override
	public void render(RenderContext renderContext) {
		super.render(renderContext);
		renderContext.applyAmbientLight(m_Level.getAmbientLight());
	}
	
}
