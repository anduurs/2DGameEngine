package com.dersgames.testgame.components.player;

import com.dersgames.dersengine.components.AnimationComponent;
import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.input.KeyInput;

public class PlayerInputComponent extends GameComponent{
	
	private float m_Speed = 1f;

	public PlayerInputComponent(String tag) {
		super(tag);
	}
	
	@Override
	public void update(float dt) {
		AnimationComponent animation = (AnimationComponent)m_GameObject.findComponentByTag("PlayerAnimation");
		
		if(KeyInput.UP){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("North"));
			m_GameObject.getPosition().y -= m_Speed;
			m_GameObject.setxOffset(20);
		}
		
		if(KeyInput.DOWN){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("South"));
			m_GameObject.getPosition().y += m_Speed;
			m_GameObject.setxOffset(7);
		}
		
		if(KeyInput.RIGHT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("East"));
			m_GameObject.getPosition().x += m_Speed;
			m_GameObject.setxOffset(20);
		}
		
		if(KeyInput.LEFT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("West"));
			m_GameObject.getPosition().x -= m_Speed;
			m_GameObject.setxOffset(7);
		}
		
		if(KeyInput.UP && KeyInput.LEFT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("North"));
			m_GameObject.setxOffset(20);
		}
		
		if(KeyInput.UP && KeyInput.RIGHT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("North"));
			m_GameObject.setxOffset(20);
		}
		
		if(KeyInput.DOWN && KeyInput.LEFT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("South"));
			m_GameObject.setxOffset(7);
		}
		
		if(KeyInput.DOWN && KeyInput.RIGHT){
			animation.setMoving(true);
			animation.setCurrentSequence(animation.getSequence("South"));
			m_GameObject.setxOffset(7);
		}
		
		if(!KeyInput.LEFT && !KeyInput.RIGHT && !KeyInput.DOWN && !KeyInput.UP){
			animation.setMoving(false);
		}
	}
}
