package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.List;

import com.dersgames.dersengine.components.AABB;
import com.dersgames.dersengine.graphics.Display;

public class CollisionManager {
	
	private Quadtree m_QuadTree = new Quadtree(0, new AABB(0, 0, Display.getDisplayWidth()*3, Display.getDisplayHeight()*3));
	private static List<AABB> m_CollisionBoxes = new ArrayList<AABB>();
	private GameObject m_Player;

	public CollisionManager(GameObject player){
		m_Player = player;
	}
	
	public static void addCollisionBox(AABB box){
		getCollisionBoxes().add(box);
	}
	
	public void update(float dt){
		AABB pBox = (AABB)m_Player.findComponentByTag("PlayerBox");
		getQuadtree().clear();
		
		for(int i = 0; i < getCollisionBoxes().size(); i++)
			getQuadtree().insert(getCollisionBoxes().get(i));
		
		List<AABB> returnObjects = new ArrayList<AABB>();
		
		for(int i = 0; i < getCollisionBoxes().size(); i++){
			returnObjects.clear();
			getQuadtree().retrieve(returnObjects, pBox);
			
			for(int j = 0; j < returnObjects.size(); j++){
				AABB box = returnObjects.get(j);
				if(pBox.intersect(box)){
					box.getGameObject().destroy();
				}
			}
		}
		
		
//		AABB pBox = (AABB)m_Player.findComponentByTag("PlayerBox");
//		for(AABB box : getCollisionBoxes()){
//			if(pBox.intersect(box)){
//				box.getGameObject().destroy();
//			}
//		}
	}
	
	private synchronized Quadtree getQuadtree(){
		return m_QuadTree;
	}
	
	private static synchronized List<AABB> getCollisionBoxes(){
		return m_CollisionBoxes;
	}

}
