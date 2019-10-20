package game1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	private int width = 16, height = 16;
	private Handler handler;
	private GameObject player;

	public SmartEnemy(int x, int y, ID id, Handler handler){
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).id == ID.Player) {
				player = handler.object.get(i);
				break;
			}
		}
		
	}
	
	protected Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), width, height) ;
	}

	public void tick(){
		setX(getX() + getVX());
		setY(getY() + getVY());
		
		double diffX = getX() - player.getX() - 8;
		double diffY = getY() - player.getY() - 8;
		double distance = Math.sqrt((diffX+8)*(diffX+8) + (diffY + 8)*(diffY + 8));
		
		setVX( ((-1/distance) * diffX));
		setVY( ((-1/distance) * diffY));
		
		
		if(getX() < 0 || getX() > Game.WIDTH - width - 14) setVX(getVX() * -1);
		
		if(getY() < 0 || getY() > Game.HEIGHT - height - 37) setVY(getVY() * -1);
		
		handler.addObject(new Trail((int) getX(), (int) getY(), ID.Trail,
				handler, 0.05f, width, height, Color.CYAN));
	}

	public void render(Graphics g){
		g.setColor(Color.CYAN);
		g.fillRect((int) x, (int) y, 16, 16);
	}
}