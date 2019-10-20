package game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private int width = 32, height = 32;
	private Handler handler;

	public Player(int x, int y, ID id, Handler handler){
		super(x, y, id);
		this.handler = handler;
	}
	
	protected Rectangle getBounds() {
		return new Rectangle((int) getX(), (int)getY(), width, height) ;
	}

	public void tick(){
		setX(getX() + getVX());
		setY(getY() + getVY());
		
		setX(Game.clamp(getX(), 0, Game.WIDTH - width - 14));
		setY(Game.clamp(getY(), 0, Game.HEIGHT - height - 37));
		
		handler.addObject(new Trail((int) getX(), (int) getY(), ID.Trail,
				handler, 0.2f, width, height, Color.BLUE));
		
		checkCollision();
	}
	
	private void checkCollision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Enemy ||
					tempObject.getID() == ID.SmartEnemy ||
					tempObject.getID() == ID.Boss) {
				if(getBounds().intersects(tempObject.getBounds()) ) { // intersects method already implemented in Rectangle class
					HUD.HEALTH--;
				}		
			}
			
			if(tempObject.getID() == ID.HealthBonus) {
				if(getBounds().intersects(tempObject.getBounds()) ) { // intersects method already implemented in Rectangle class
					HUD.HEALTH += 10;
					handler.removeObject(tempObject);
				}		
			}
		}
	}

	public void render(Graphics g){
		
		g.setColor(Color.BLUE);
		g.fillRect( (int) getX(), (int) getY(), width, height);
	}
	
}