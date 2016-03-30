package com.dersgames.testgame.components.player;

import com.dersgames.dersengine.components.GameComponent;
import com.dersgames.dersengine.core.Vector2f;

public class PlayerMovement extends GameComponent{
	
	private float m_Speed;
	private Vector2f m_CurrentDirection;
	private Vector2f m_InputDirection;
	private Vector2f m_Velocity;

	public PlayerMovement(String tag, float speed) {
		super(tag);
		m_Speed = speed;
		m_Velocity = new Vector2f(speed,speed);
		m_InputDirection = new Vector2f(0,0);
		m_CurrentDirection = new Vector2f(0,0);
	}
	
	public void receive(String message){
		switch(message){
		case "Stop":
			m_Velocity.x = 0;
			m_Velocity.y = 0;
			
			m_CurrentDirection.x = 0;
			m_CurrentDirection.y = 0;
			
			break;
		case "NorthMove":
			m_InputDirection.y = -1;
			m_GameObject.getPosition().y -= m_Speed;
			break;
		case "SouthMove":
			m_InputDirection.y = 1;
			m_GameObject.getPosition().y += m_Speed;
			break;
		case "EastMove":
			m_InputDirection.x = 1;
			m_GameObject.getPosition().x += m_Speed;
			break;
		case "WestMove":
			m_InputDirection.x = -1;
			m_GameObject.getPosition().x -= m_Speed;
			break;
		}
	}

	@Override
	public void update(float dt) {
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}


/*
public float speed;

private Vector3 currentDirection = Vector3.zero;

void Update()
{
    if (currentDirection.Equals(Vector3.zero))
    {
        Vector3 inputDirection = new Vector3(Input.GetAxis("Horizontal"), Input.GetAxis("Vertical"), 0);
        if (!inputDirection.Equals(Vector3.zero))
        {
            currentDirection = inputDirection;
            this.rigidbody2D.velocity = currentDirection * speed;
        }
    }
}

void OnCollisionEnter2D(Collision2D col)
{
    currentDirection = Vector3.zero;
    this.rigidbody2D.velocity = Vector3.zero;
}

*/
