package com.dersgames.dersengine.core;

import com.dersgames.dersengine.graphics.RenderContext;

public class GameStateManager {
	
	private LinkedStack<GameState> states;
	
	public GameStateManager(){
		states = new LinkedStack<GameState>();
	}

	public void push(GameState state){
		states.push(state);
	}
	
	public void pop(){
		states.pop();
	}
	
	public GameState next(){
		return states.top.next.data;
	}
	
	public GameState peek(){
		return states.peek();
	}
	
	public void update(float dt){
		update(states.top, dt);
	}
	
	private void update(Node<GameState> state, float dt){
		state.data.update(dt);
		if(!state.data.isUpdatingBlocked())
			update(state.next, dt);
	}
	
	public void render(RenderContext renderContext){
		render(states.top, renderContext);
	}
	
	private void render(Node<GameState> state, RenderContext renderContext){
		if(!state.data.isRenderingBlocked())
			render(state.next, renderContext);
		state.data.render(renderContext);
	}

}
