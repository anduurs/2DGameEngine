package com.dersgames.dersengine.core;

import java.util.ArrayList;
import java.util.List;

import com.dersgames.dersengine.physics.AABB;

public class CollisionManager {
	
	private static List<AABB> m_CollisionBoxes = new ArrayList<AABB>();
	private GameObject m_Player;

	public CollisionManager(GameObject player){
		m_Player = player;
	}
	
	public static void addCollisionBox(AABB box){
		m_CollisionBoxes.add(box);
	}
	
	public void update(float dt){
		AABB pBox = (AABB)m_Player.findComponentByTag("PlayerBox");
		for(AABB box : m_CollisionBoxes){
			if(pBox.intersect(box))
				System.out.println("COLLISION!");
		}
	}

}
