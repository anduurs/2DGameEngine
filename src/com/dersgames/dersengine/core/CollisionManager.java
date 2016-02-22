package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.List;

import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.graphics.Display;

public class CollisionManager {
	
	private Quadtree m_QuadTree = new Quadtree(0, new BoundingBox(0, 0, 
			Display.getDisplayWidth() * Display.SCALE, 
			Display.getDisplayHeight() * Display.SCALE));
	
	private static List<BoundingBox> m_CollisionBoxes = new ArrayList<BoundingBox>();
	private GameObject m_Player;

	public CollisionManager(GameObject player){
		m_Player = player;
	}
	
	public static void addCollisionBox(BoundingBox box){
		getCollisionBoxes().add(box);
	}
	
	public static void removeCollisionBox(BoundingBox box){
		getCollisionBoxes().remove(box);
	}
	
	public void update(float dt){
		BoundingBox pBox = (BoundingBox)m_Player.findComponentByTag("PlayerBox");
		getQuadtree().clear();
		
		for(int i = 0; i < getCollisionBoxes().size(); i++)
			getQuadtree().insert(getCollisionBoxes().get(i));
		
		List<BoundingBox> returnObjects = new ArrayList<BoundingBox>();
		
		for(int i = 0; i < getCollisionBoxes().size(); i++){
			returnObjects.clear();
			getQuadtree().retrieve(returnObjects, pBox);
			
			for(int j = 0; j < returnObjects.size(); j++){
				BoundingBox box = returnObjects.get(j);
				if(pBox.intersect(box)){
					//HANDLE THE COLLISIONS HERE
					box.getGameObject().destroy();
				}
			}
		}
	}
	
	private synchronized Quadtree getQuadtree(){
		return m_QuadTree;
	}
	
	private static synchronized List<BoundingBox> getCollisionBoxes(){
		return m_CollisionBoxes;
	}

}
