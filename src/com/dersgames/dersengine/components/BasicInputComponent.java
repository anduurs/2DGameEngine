package com.dersgames.dersengine.components;

import com.dersgames.dersengine.input.KeyInput;

public class BasicInputComponent extends GameComponent{
	
	private float m_Speed = 1.0f;

	public BasicInputComponent(String tag) {
		super(tag);
	}
	
	@Override
	public void update(float dt) {
		AnimationComponent animation = (AnimationComponent)m_GameObject.findComponentByTag("PlayerAnimation");
		
		if(KeyInput.UP){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("North"));
			m_GameObject.getPosition().y -= m_Speed;
		}
		
		if(KeyInput.DOWN){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("South"));
			m_GameObject.getPosition().y += m_Speed;
		}
		
		if(KeyInput.RIGHT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("East"));
			m_GameObject.getPosition().x += m_Speed;
		}
		
		if(KeyInput.LEFT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("West"));
			m_GameObject.getPosition().x -= m_Speed;
		}
		
		if(KeyInput.UP && KeyInput.LEFT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("North"));
		}
		
		if(KeyInput.UP && KeyInput.RIGHT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("North"));
		}
		
		if(KeyInput.DOWN && KeyInput.LEFT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("South"));
		}
		
		if(KeyInput.DOWN && KeyInput.RIGHT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("South"));
		}
		
		if(!KeyInput.LEFT && !KeyInput.RIGHT && !KeyInput.DOWN && !KeyInput.UP){
			animation.setMoving(false);
		}
	}
}
