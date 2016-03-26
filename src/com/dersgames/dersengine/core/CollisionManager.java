package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.List;

import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.graphics.Display;

public class CollisionManager {
	
	private Quadtree m_QuadTree = new Quadtree(0, new BoundingBox(0, 0, 
			Display.getDisplayWidth(), 
			Display.getDisplayHeight()));
	
	private static List<Collideable> m_Collideables = new ArrayList<Collideable>();
	private List<BoundingBox> returnObjects;

	public CollisionManager(){
		returnObjects = new ArrayList<BoundingBox>();
	}
	
	public static void addCollisionBox(Collideable obj){
		getCollideables().add(obj);
	}
	
	public static void removeCollisionBox(Collideable obj){
		getCollideables().remove(obj);
	}
	
	public void update(float dt){
		getQuadtree().clear();
		
		for(int i = 0; i < getCollideables().size(); i++)
			getQuadtree().insert(getCollideables().get(i).getCollisionBox());
		
		for(int i = 0; i < getCollideables().size(); i++){
			returnObjects.clear();
			BoundingBox box1 = getCollideables().get(i).getCollisionBox();
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
	
	private static synchronized List<Collideable> getCollideables(){
		return m_Collideables;
	}

}
