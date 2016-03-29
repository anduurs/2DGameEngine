package com.dersgames.dersengine.core;

public class Timer {
	
	private int m_Timer;
	private boolean m_Start = false, m_Stop = false;
	public enum TimeUnit{SECONDS, MILLISECONDS};
	
	public Timer(){
		
	}
	
	public void update(float dt){
		if(m_Start){
			m_Timer++;
		}
		
		if(m_Stop){
			m_Timer = 0;
		}
	}
	
	public float getTime(TimeUnit unit){
		if(unit == TimeUnit.SECONDS){
			return (float)(m_Timer/60.0f);
		}else{
			return (float)((m_Timer/60.0f) * 1000.0f);
		}
	}
	
	public void start(){
		m_Start = true;
		m_Stop = false;
	}
	
	public void stop(){
		m_Start = false;
		m_Stop = true;
	}
	
	
	

}
