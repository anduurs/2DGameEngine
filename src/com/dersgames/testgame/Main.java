package com.dersgames.testgame;

import com.dersgames.dersengine.core.Game;
import com.dersgames.dersengine.core.GameStateManager;
import com.dersgames.dersengine.graphics.Display;
import com.dersgames.testgame.states.PlayState;

public class Main {
	
	public static void main(String[] args){
		Game game = new Game(new Display(1200, 675, "TestGame"), 
				new GameStateManager(), false);
		game.pushStartingState(new PlayState(game.getGameStateManager()));
		game.start();	
	}

}
