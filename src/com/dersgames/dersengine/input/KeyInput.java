package com.dersgames.dersengine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
	
	private boolean[] keys = new boolean[128];
	public static boolean UP, DOWN, RIGHT, LEFT, SPACE, ESCAPE, B;
	
	public void tick(){
		UP 		= keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
		DOWN 	= keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
		RIGHT 	= keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		LEFT 	= keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		SPACE 	= keys[KeyEvent.VK_SPACE];
		ESCAPE 	= keys[KeyEvent.VK_ESCAPE]; 
		B 		= keys[KeyEvent.VK_B]; 
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
