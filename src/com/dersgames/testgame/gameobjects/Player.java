package com.dersgames.testgame.gameobjects;

import com.dersgames.dersengine.components.AnimationComponent;
import com.dersgames.dersengine.components.BoundingBox;
import com.dersgames.dersengine.components.RenderableComponent.CoordinateSpace;
import com.dersgames.dersengine.components.StaticSprite;
import com.dersgames.dersengine.core.Collideable;
import com.dersgames.dersengine.core.CollisionManager;
import com.dersgames.dersengine.core.GameObject;
import com.dersgames.dersengine.core.Vector2f;
import com.dersgames.dersengine.graphics.AnimationSequence;
import com.dersgames.dersengine.graphics.ColorRGBA;
import com.dersgames.dersengine.graphics.SpriteSheet;
import com.dersgames.testgame.components.player.PlayerInputComponent;
import com.dersgames.testgame.components.player.WeaponComponent;

public class Player extends GameObject implements Collideable{
	
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
		CollisionManager.addCollisionBox(m_CollisionBox);
		attachComponent(m_CollisionBox);
		attachComponent(new PlayerInputComponent("PlayerInput"));
		
		GameObject playerWeapon = new GameObject("PlayerWeapon");
		playerWeapon.attachComponent(new StaticSprite("WeaponSprite", 6, 6, ColorRGBA.GREEN, CoordinateSpace.WORLD_SPACE));
		playerWeapon.attachComponent(new WeaponComponent("Weapon"));
		attachChild(playerWeapon, 7, 20);
		
		AnimationComponent anim = new AnimationComponent("PlayerAnimation", m_Sheet, CoordinateSpace.WORLD_SPACE);
		
		int animSpeed = 10;
	
		anim.addAnimationSequence(new AnimationSequence("East",  animSpeed), 1, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("West",  animSpeed), 3, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("North", animSpeed), 0, 0, 32, 32, 3);
		anim.addAnimationSequence(new AnimationSequence("South", animSpeed), 2, 0, 32, 32, 3);
		
		attachComponent(anim);
	}

	@Override
	public void collisionWith(Collideable collideable) {
		
		
	}

	@Override
	public BoundingBox getCollisionBox() {
		return m_CollisionBox;
	}


}
