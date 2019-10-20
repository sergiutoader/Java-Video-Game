package game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthBonus extends GameObject{
	
	private int width = 12, height = 12;

	public HealthBonus(int x, int y, ID id){
		super(x, y, id);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), width, height) ;
	}

	public void tick(){}

	public void render(Graphics g){
		
		g.setColor(Color.GREEN);
		g.fillRect((int) getX(), (int) getY(), width, height);
	}
	
}