package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.List;

import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.graphics.Display;

public class CollisionManager {
	
	private Quadtree m_QuadTree = new Quadtree(0, new BoundingBox(0, 0, 
			Display.getDisplayWidth(), 
			Display.getDisplayHeight()));
	
	private static List<BoundingBox> m_CollisionBoxes = new ArrayList<BoundingBox>();
	private List<BoundingBox> returnObjects;

	public CollisionManager(){
		returnObjects = new ArrayList<BoundingBox>();
	}
	
	public static void addCollisionBox(BoundingBox obj){
		getCollisionBoxes().add(obj);
	}
	
	public static void removeCollisionBox(BoundingBox obj){
		getCollisionBoxes().remove(obj);
	}
	
	public void update(float dt){
		getQuadtree().clear();
		
		for(int i = 0; i < getCollisionBoxes().size(); i++)
			getQuadtree().insert(getCollisionBoxes().get(i));
		
		for(int i = 0; i < getCollisionBoxes().size(); i++){
			returnObjects.clear();
			BoundingBox box1 = getCollisionBoxes().get(i);
			getQuadtree().retrieve(returnObjects, box1);
			
			for(int j = 0; j < returnObjects.size(); j++){
				BoundingBox box2 = returnObjects.get(j);
				if(box1.intersect(box2)){
					box1.getGameObject().send("Collision", box1);
					box2.getGameObject().send("Collision", box2);
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
