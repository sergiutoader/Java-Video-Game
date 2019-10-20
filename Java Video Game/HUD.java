package game1;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH = 100;
	private int greenVal = 200, redVal = 0;
	private Handler handler;
	
	private int score = 0;
	private int level = 0;
	private int finalScore = 0;
	
	public HUD(Handler handler) {
		this.handler = handler;
	}
	
	public void tick(){
		HEALTH = (int) Game.clamp(HEALTH, 0, 100);	
		
		if(HEALTH == 0) {
			Game.gameState = Game.STATE.Menu; 
			removeAll();
			setFinalScore();
			setScore(0);
			setLevel(0);
			HEALTH = 100;
		}
		
		greenVal = HEALTH * 2;
		redVal = 255 - HEALTH * 2;
	}
	
	public void render(Graphics g){
		
		g.setColor(Color.GRAY);
		g.fillRect(15, 10, 200, 32);
		
		g.setColor(new Color(redVal, greenVal, 0));
		g.fillRect(15, 10, HEALTH * 2, 32);
		
		g.setColor(Color.WHITE);
		g.drawRect(15, 10, 200, 32);
		
		g.drawString(HEALTH + "%", 20, 30);
		g.drawString("Score: " + score , 550, 30);
		g.drawString("Level: " + level , 230, 30);
		
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setFinalScore() {
		finalScore = getScore();
	}
	
	public int getFinalScore() {
		return finalScore;
	}
	
	public void removeAll() {
		for(int i = 0; i < handler.object.size(); i++) {
			handler.removeObject(handler.object.get(i--));	
		}	
	}
}
