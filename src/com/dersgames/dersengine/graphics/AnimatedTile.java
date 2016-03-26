package com.dersgames.dersengine.graphics;

public class AnimatedTile extends Tile{
	
	private AnimationSequence m_Sequence;
	private int timer;

	public AnimatedTile(Sprite sprite, AnimationSequence sequence, boolean solid) {
		super(sprite, solid);
		m_Sequence = sequence;
	}
	
	public void update(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
		
		m_Sequence.playAnimation(timer);
		m_Sprite = m_Sequence.getCurrentSprite();
	}
	
	

}
