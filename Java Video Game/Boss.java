package game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boss extends GameObject{
	
	private int width = 64, height = 64;
	private int timer1 = 100, timer2 = 50;

	public Boss(int x, int y, ID id){
		super(x, y, id);
		
		setVX(0);
		setVY(1);
		
	}
	
	protected Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), width, height) ;
	}

	public void tick(){
		setX(getX() + getVX());
		setY(getY() + getVY());
		
		if(timer1 <= 0) {
			setVY(0);
			if(timer2 == 0) {
				setVX(3);
			}
			timer2--;
		}
		timer1--;
		
		if(getX() < 0 || getX() > Game.WIDTH - width - 14) setVX(getVX() * -1);
		
	}

	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int) getX(), (int) getY(), 64, 64);
	}
}