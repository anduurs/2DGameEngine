package com.dersgames.dersengine.components;

import java.util.HashMap;

import com.dersgames.dersengine.graphics.AnimationSequence;
import com.dersgames.dersengine.graphics.SpriteSheet;

public class AnimationComponent extends Renderable2D{
	
	private HashMap<String, AnimationSequence> m_AnimationSequencesMap;
	private AnimationSequence m_CurrentSequence;
	private SpriteSheet m_Sheet;
	
	private int m_Timer;
	private boolean m_Moving = false;

	public AnimationComponent(String tag, SpriteSheet sheet, CoordinateSpace space) {
		super(tag, space);
		
		m_AnimationSequencesMap = new HashMap<String, AnimationSequence>();
		m_Sheet = sheet;
	}
	
	public void addAnimationSequence(AnimationSequence animSequence, int startX, int startY, 
			int spriteWidth, int spriteHeight, int numOfSprites){
		
		animSequence.addSequence(m_Sheet, startX, startY, spriteWidth, spriteHeight, numOfSprites);
		m_AnimationSequencesMap.put(animSequence.getTag(), animSequence);
		
		m_CurrentSequence = animSequence;
		m_Sprite = m_CurrentSequence.getCurrentSprite();
	}

	@Override
	public void update(float dt) {
		if(m_Timer < 7500) m_Timer++;
		else m_Timer = 0;
	
		if(m_Moving){
			m_CurrentSequence.playAnimation(m_Timer);
			m_Sprite = m_CurrentSequence.getCurrentSprite();
		}
	}
	
	public void receive(String message){
		switch(message){
		case "NorthAnim":
			setMoving(true);
			setCurrentSequence(getSequence("North"));
			break;
		case "SouthAnim":
			setMoving(true);
			setCurrentSequence(getSequence("South"));
			break;
		case "EastAnim":
			setMoving(true);
			setCurrentSequence(getSequence("East"));
			break;
		case "WestAnim":
			setMoving(true);
			setCurrentSequence(getSequence("West"));
			break;
		case "Stopped":
			setMoving(false);
			break;
		}
	}
	
	public AnimationSequence getSequence(String tag){
		return m_AnimationSequencesMap.get(tag);
	}
	
	public AnimationSequence getCurrentSequence() {
		return m_CurrentSequence;
	}

	public void setCurrentSequence(AnimationSequence currentSequence) {
		this.m_CurrentSequence = currentSequence;
	}
	
	public boolean isMoving() {
		return m_Moving;
	}

	public void setMoving(boolean moving) {
		this.m_Moving = moving;
	}

}
