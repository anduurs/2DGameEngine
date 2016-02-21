package com.dersgames.dersengine.components;

import java.util.HashMap;

import com.dersgames.dersengine.graphics.AnimationSequence;
import com.dersgames.dersengine.graphics.SpriteSheet;

public class AnimationComponent extends RenderableComponent{
	
	private HashMap<String, AnimationSequence> m_AnimationSequencesMap;
	private AnimationSequence currentSequence;
	private SpriteSheet m_Sheet;
	private int timer;
	
	private boolean moving = false;

	public AnimationComponent(String tag, SpriteSheet sheet, CoordinateSpace space) {
		super(tag, space);
		
		m_AnimationSequencesMap = new HashMap<String, AnimationSequence>();
		m_Sheet = sheet;
	}
	
	public void addAnimationSequence(AnimationSequence animSequence, int startX, int startY, 
			int spriteWidth, int spriteHeight, int numOfSprites){
		
		animSequence.addSequence(m_Sheet, startX, startY, spriteWidth, spriteHeight, numOfSprites);
		m_AnimationSequencesMap.put(animSequence.getTag(), animSequence);
		
		currentSequence = animSequence;
		m_Sprite = currentSequence.getCurrentSprite();
	}

	@Override
	public void update(float dt) {
		if(timer < 7500) timer++;
		else timer = 0;
	
		if(moving){
			currentSequence.playAnimation(timer);
			m_Sprite = currentSequence.getCurrentSprite();
		}
		
	}
	
	
	public AnimationSequence getSequence(String tag){
		return m_AnimationSequencesMap.get(tag);
	}
	
	public AnimationSequence getCurrentSequence() {
		return currentSequence;
	}

	public void setCurrentSequence(AnimationSequence currentSequence) {
		this.currentSequence = currentSequence;
	}
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
