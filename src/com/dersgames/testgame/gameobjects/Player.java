package com.dersgames.testgame.gameobjects;

import com.dersgames.dersengine.components.AnimationComponent;
import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.components.Renderable2D;
import com.dersgames.dersengine.components.Renderable2D.CoordinateSpace;
import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.Vector2f;
import com.dersgames.dersengine.graphics.AnimationSequence;
import com.dersgames.dersengine.graphics.ColorRGBA;
import com.dersgames.dersengine.graphics.SpriteSheet;
import com.dersgames.testgame.components.player.PlayerInput;
import com.dersgames.testgame.components.player.PlayerMovement;
import com.dersgames.testgame.components.player.Weapon;

public class Player extends GameObject{
	
	private SpriteSheet m_Sheet;
	private BoundingBox m_CollisionBox;
	private int m_Width, m_Height;
	
	public Player(Vector2f pos, int width, int height, SpriteSheet sheet){
		super("Player", pos);
		m_Sheet = sheet;
		m_Width = width;
		m_Height = height;
	}
	
	public void init(){
		m_CollisionBox = new BoundingBox("PlayerBox", getX(), getY(), m_Width, m_Height);
		attachComponent(m_CollisionBox);
		m_CollisionBox.addCollisionSprite();
		attachComponent(new PlayerInput("PlayerInput"));
		attachComponent(new PlayerMovement("PlayerMove", 1.0f));
		
		GameObject playerWeapon = new GameObject("PlayerWeapon");
		playerWeapon.attachComponent(new Renderable2D("WeaponSprite", 6, 6, ColorRGBA.GREEN, CoordinateSpace.WORLD_SPACE));
		playerWeapon.attachComponent(new Weapon("Weapon"));
		playerWeapon.attachComponent(new BoundingBox("PlayerWeaponBox", playerWeapon.getX(), playerWeapon.getY(), 6, 6));
		attachChild(playerWeapon, 7.0f, 20.0f);
		
		AnimationComponent anim = new AnimationComponent("PlayerAnimation", m_Sheet, CoordinateSpace.WORLD_SPACE);
		
		int animSpeed = 10;
	
		anim.addAnimationSequence(new AnimationSequence("East",  animSpeed), 1, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("West",  animSpeed), 3, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("North", animSpeed), 0, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("South", animSpeed), 2, 0, 32, 32, 3);
		
		attachComponent(anim);
	}



}
