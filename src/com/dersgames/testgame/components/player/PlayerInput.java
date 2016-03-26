package com.dersgames.testgame.components.player;

import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.input.KeyInput;

public class PlayerInput extends GameComponent{
	
	public PlayerInput(String tag) {
		super(tag);
	}
	
	@Override
	public void update(float dt) {
		if(KeyInput.UP){
			m_GameObject.send("NorthAnim");
			m_GameObject.send("NorthMove");
			m_GameObject.setxOffset(20);
		}
		
		if(KeyInput.DOWN){
			m_GameObject.send("SouthAnim");
			m_GameObject.send("SouthMove");
			m_GameObject.setxOffset(7);
		}
		
		if(KeyInput.RIGHT){
			m_GameObject.send("EastAnim");
			m_GameObject.send("EastMove");
			m_GameObject.setxOffset(20);
		}
		
		if(KeyInput.LEFT){
			m_GameObject.send("WestAnim");
			m_GameObject.send("WestMove");
			m_GameObject.setxOffset(7);
		}
		
		if(KeyInput.UP && KeyInput.LEFT){
			m_GameObject.send("NorthAnim");
			m_GameObject.setxOffset(20);
		}
		
		if(KeyInput.UP && KeyInput.RIGHT){
			m_GameObject.send("NorthAnim");
			m_GameObject.setxOffset(20);
		}
		
		if(KeyInput.DOWN && KeyInput.LEFT){
			m_GameObject.send("SouthAnim");
			m_GameObject.setxOffset(7);
		}
		
		if(KeyInput.DOWN && KeyInput.RIGHT){
			m_GameObject.send("SouthAnim");
			m_GameObject.setxOffset(7);
		}
		
		if(!KeyInput.LEFT && !KeyInput.RIGHT && !KeyInput.DOWN && !KeyInput.UP){
			m_GameObject.send("Stopped");
		}
	}

	@Override
	public void receive(String message) {
		
		
	}
}
