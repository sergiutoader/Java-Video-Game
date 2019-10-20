package game1;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;

	private Random r = new Random();
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
		
	public void tick(){
		
		if(hud.getLevel() % 15 == 10) {
			removeAllExceptPlayer();
			handler.addObject(new Boss(Game.WIDTH / 2 - 48,
				-64, ID.Boss));
		}
		
		else if(hud.getLevel() % 15 > 10) {
			GameObject tempObject = null;
			for(int i = 0; i < handler.object.size(); i++) {
				if(handler.object.get(i).getID() == ID.Boss) {
					tempObject = handler.object.get(i);
				}
			}
			
			if(hud.getScore() % 100 == 1) {
				handler.addObject(new Enemy((int) tempObject.getX() + 32,
						(int) tempObject.getY() + 32, ID.Enemy, handler,
						hud.getLevel()/15 + 1, hud.getLevel()/15 + 4));
				if(hud.getScore() % 400 == 1) {
					handler.addObject(new Enemy((int) tempObject.getX() + 32,
							(int) tempObject.getY() + 32, ID.Enemy, handler,
							hud.getLevel()/15 + 1, hud.getLevel()/15 + 4));
				}
			}
	
		}
		else if(hud.getLevel() % 15 == 0) {
			removeAllExceptPlayer();
		}
		else {
			if(hud.getScore() % 300 == 1){
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 16 - 14),
					r.nextInt(Game.HEIGHT - 16 - 37), ID.Enemy, handler,
					hud.getLevel()/15 + 1, hud.getLevel()/15 + 4));
			}
			if(hud.getScore() % 360 == 0 && hud.getScore() > 0) {
				handler.addObject(new HealthBonus(r.nextInt(Game.WIDTH - 12 -
					14), r.nextInt(Game.HEIGHT - 12 - 37), ID.HealthBonus));
			}
			if(hud.getScore() % 900 == 0 && hud.getScore() > 0) {
				handler.addObject(new SmartEnemy(r.nextInt(
					Game.WIDTH - 16 - 14), r.nextInt(Game.HEIGHT
					- 16 - 37), ID.SmartEnemy, handler));
			}
		}
		
		hud.setScore(hud.getScore() + 1);
		hud.setLevel(hud.getScore()/300 + 1);
		
	}	
	
	public void removeAllExceptPlayer() {
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() != ID.Player){
				handler.removeObject(handler.object.get(i--));
			}
		}	
	}
}