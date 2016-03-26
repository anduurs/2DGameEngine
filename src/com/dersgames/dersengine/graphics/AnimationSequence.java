package com.dersgames.dersengine.graphics;

import java.util.ArrayList;

import com.dersgames.dersengine.graphics.Sprite;
import com.dersgames.dersengine.graphics.SpriteSheet;

public class AnimationSequence {
	
	private int currentFrame = 0;
	private int m_AnimationSpeed;
	
	private Sprite currentSprite;
	private String m_Tag;

	private ArrayList<Sprite> m_Sprites;
	
	public AnimationSequence(String tag, int animSpeed) {
		m_Tag = tag;
		m_AnimationSpeed = animSpeed;
		m_Sprites = new ArrayList<Sprite>();
	}
	
	public void addSequence(SpriteSheet sheet, int startX, int startY, 
			int spriteWidth, int spriteHeight, int numOfSprites){
		
		for(int i = 0; i < numOfSprites - 1; i++)
			m_Sprites.add(new Sprite(sheet, startX, i, spriteWidth, spriteHeight));
		
		currentSprite = m_Sprites.get(0);
	}
	
	public void playAnimation(int timer){
		if(timer % m_AnimationSpeed == 0 && currentFrame < m_Sprites.size()){
			currentSprite = m_Sprites.get(currentFrame);
			currentFrame++;
		}
		
		if(currentFrame == m_Sprites.size()) currentFrame = 0;
	}

	public void setAnimationSpeed(int animationSpeed) {
		m_AnimationSpeed = animationSpeed;
	}

	public String getTag() {
		return m_Tag;
	}

	public int getAnimationSpeed() {
		return m_AnimationSpeed;
	}

	public Sprite getCurrentSprite() {
		return currentSprite;
	}

}
