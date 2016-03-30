package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.List;

import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.graphics.Display;

public class CollisionManager {
	
	private Quadtree m_QuadTree = new Quadtree(0, new BoundingBox(0, 0, 
			Display.getDisplayWidth(), 
			Display.getDisplayHeight()));
	
	private static List<Collideable> m_CollisionBoxes = new ArrayList<Collideable>();
	private List<BoundingBox> returnObjects;

	public CollisionManager(){
		returnObjects = new ArrayList<BoundingBox>();
	}
	
	public static void addCollisionBox(Collideable obj){
		getCollisionBoxes().add(obj);
	}
	
	public static void removeCollisionBox(Collideable obj){
		getCollisionBoxes().remove(obj);
	}
	
	public void update(float dt){
		getQuadtree().clear();
		
		for(int i = 0; i < getCollisionBoxes().size(); i++)
			getQuadtree().insert(getCollisionBoxes().get(i).getCollisionBox());
		
		for(int i = 0; i < getCollisionBoxes().size(); i++){
			returnObjects.clear();
			
			BoundingBox box1 = getCollisionBoxes().get(i).getCollisionBox();
			getQuadtree().retrieve(returnObjects, box1);
			
			for(int j = 0; j < returnObjects.size(); j++){
				BoundingBox box2 = returnObjects.get(j);
		
				if(box1.intersects(box2)){
					if(box1.getGameObject() instanceof Collideable && 
							box2.getGameObject() instanceof Collideable){
						
						Collideable obj1 = (Collideable) box1.getGameObject();
						Collideable obj2 = (Collideable) box2.getGameObject();
						
						obj1.collisionWith(obj2);
					}
				}
			}
		}
	}
	
	private synchronized Quadtree getQuadtree(){
		return m_QuadTree;
	}
	
	private static synchronized List<Collideable> getCollisionBoxes(){
		return m_CollisionBoxes;
	}

}
