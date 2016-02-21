package com.dersgames.dersengine.core;

import com.dersgames.dersengine.graphics.RenderContext;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected static SceneGraph sceneGraph = SceneGraph.getInstance();
	
	protected boolean m_BlockUpdating = true;
	protected boolean m_BlockRendering = true;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();

	public void update(float dt) {
		sceneGraph.update(dt);
	}

	public void render(RenderContext renderContext) {
		sceneGraph.render(renderContext);
	}
	
	public boolean isUpdatingBlocked() {
		return m_BlockUpdating;
	}
	
	public boolean isRenderingBlocked() {
		return m_BlockRendering;
	}
	
	public void setUpdatingBlocked(boolean blockTicking) {
		m_BlockUpdating = blockTicking;
	}
	
	public void setRenderingBlocked(boolean blockRendering) {
		m_BlockRendering = blockRendering;
	}

}
