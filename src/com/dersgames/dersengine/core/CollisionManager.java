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

	public CollisionManager(){}
	
	public static void addCollisionBox(BoundingBox box){
		getCollisionBoxes().add(box);
	}
	
	public static void removeCollisionBox(BoundingBox box){
		getCollisionBoxes().remove(box);
	}
	
	public void update(float dt){
		getQuadtree().clear();
		
		for(int i = 0; i < getCollisionBoxes().size(); i++)
			getQuadtree().insert(getCollisionBoxes().get(i));
		
		List<BoundingBox> returnObjects = new ArrayList<BoundingBox>();
		
		for(int i = 0; i < getCollisionBoxes().size(); i++){
			returnObjects.clear();
			BoundingBox box1 = getCollisionBoxes().get(i);
			getQuadtree().retrieve(returnObjects, box1);
			
			for(int j = 0; j < returnObjects.size(); j++){
				BoundingBox box2 = returnObjects.get(j);
				if(box1.intersect(box2)){
					if(box1.getGameObject() instanceof Collideable
							&& box2.getGameObject() instanceof Collideable){
						
						Collideable obj1 = (Collideable)box1.getGameObject();
						Collideable obj2 = (Collideable)box2.getGameObject();
						
						obj1.collisionWith(obj2);
					}
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
