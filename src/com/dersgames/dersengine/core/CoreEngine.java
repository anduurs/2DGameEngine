package com.dersgames.dersengine.core;

import com.dersgames.dersengine.graphics.Display;
import com.dersgames.dersengine.graphics.RenderContext;
import com.dersgames.dersengine.utils.AssetsManager;

public class CoreEngine implements Runnable{
	
	private boolean m_FpsCap;
	private volatile boolean m_Running = false;
	private Thread m_Thread;
	
	private Display m_Display;
	private GameStateManager m_GameStateManager;
	private RenderContext m_RenderContext;
	
	public CoreEngine(Display display, GameStateManager gsm, boolean fpsCap){
		AssetsManager.getInstance();
		m_Display = display;
		m_GameStateManager = gsm;
		m_FpsCap = fpsCap;
		m_RenderContext = new RenderContext(m_Display.getFrameBuffer());
	}
	
	public void pushStartingState(GameState state){
		m_GameStateManager.push(state);
	}
	
	public synchronized void start(){
		m_Running = true;
		if(m_Thread == null){
			m_Thread = new Thread(this, "Game-Thread");
			m_Thread.start();
		}
	}
	
	public synchronized void stop(){
		m_Running = false;
		try {
			m_Thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void getInput(){
		m_Display.getInput();
	}
	
	private void update(float dt){
		m_GameStateManager.update(dt);
	}
		
	private void render(){
		m_Display.clearBuffers();
		m_GameStateManager.render(m_RenderContext);
		m_Display.swapBuffers();
	}
	
	@Override
	public void run() {
		double previousTime = System.nanoTime();
		double currentTime;
		double passedTime;
		
		double lag = 0;
		double frameCounter = 0;
		
		int ups = 0, fps = 0;
		
		final double TARGET_UPS = 60.0;
		final double MS_PER_UPDATE = 1.0 / TARGET_UPS;
		float dt = (float) MS_PER_UPDATE;
		
		boolean shouldRender;
		
		while(m_Running){			
			currentTime = System.nanoTime();
			passedTime = (currentTime - previousTime) / 1000000000.0;
			
			if(passedTime > 0.25) passedTime = 0.25;
			
			lag += passedTime;
			frameCounter += passedTime;
			
			previousTime = currentTime;
			
			if(m_FpsCap){
				shouldRender = false;
			}else shouldRender = true;
			
			while(lag >= MS_PER_UPDATE){
				getInput();
				update(dt*10.0f);
				ups++;
				lag -= MS_PER_UPDATE;
				shouldRender = true;
			}

			if(shouldRender){
				render();
				fps++;
			}
				
			if(frameCounter >= 1){
				System.out.println(ups + " ups , " + fps + " fps");
				fps = 0;
				ups = 0;
				frameCounter = 0;
			}
		}
		
		stop();
	}
	
	public GameStateManager getGameStateManager(){
		return m_GameStateManager;
	}

}
