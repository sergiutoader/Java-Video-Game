package game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{
	
	private int width = 16, height = 16;
	private Handler handler;

	public Enemy(int x, int y, ID id, Handler handler, int vMin, int vMax){
		super(x, y, id);
		this.handler = handler;
		
		setVX(setRandVelocity(vMin, vMax));
		setVY(setRandVelocity(vMin, vMax));
		
	}
	
	protected Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), width, height) ;
	}

	public void tick(){
		setX(getX() + getVX());
		setY(getY() + getVY());
		
		if(getX() < 0 || getX() > Game.WIDTH - width - 14) setVX(getVX() * -1);
		
		if(getY() < 0 || getY() > Game.HEIGHT - height - 37) setVY(getVY() * -1);
		
		handler.addObject(new Trail((int) getX(), (int) getY(), ID.Trail,
				handler, 0.05f, width, height, Color.YELLOW));
	}

	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int) getX(), (int) getY(), 16, 16);
	}
}