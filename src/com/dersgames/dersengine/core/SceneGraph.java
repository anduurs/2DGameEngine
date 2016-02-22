package com.dersgames.dersengine.core;

import com.dersgames.dersengine.graphics.RenderContext;

public class SceneGraph {
	
	private static SceneGraph instance = null;
	private static GameObject m_Root;

	private SceneGraph(){
		m_Root = new GameObject("Root");
	}
	
	public static SceneGraph getInstance(){
		if(instance == null)
			instance = new SceneGraph();
	
		return instance;
	}
	
	public void addChild(GameObject gameObject){
		m_Root.addChild(gameObject);
	}
	
	public void destroy(){
		m_Root = null;
	}
	
	public void update(float dt){
		m_Root.updateAll(dt);
		m_Root.clearDeadGameObjects();
	}
	
	public void render(RenderContext renderContext){
		m_Root.renderAll(renderContext);
	}
	
	public static GameObject getRoot() {
		return m_Root;
	}

}
