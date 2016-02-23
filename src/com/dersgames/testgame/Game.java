package com.dersgames.testgame;

import com.dersgames.dersengine.core.CoreEngine;
import com.dersgames.dersengine.core.GameStateManager;
import com.dersgames.dersengine.graphics.Display;
import com.dersgames.testgame.states.PlayState;

public class Game {
	
	public static void main(String[] args){
		CoreEngine engine = new CoreEngine(new Display(1200, 675, "DersEngine v0.01"), 
				new GameStateManager(), false);
		engine.pushStartingState(new PlayState(engine.getGameStateManager()));
		engine.start();		
	}

}
