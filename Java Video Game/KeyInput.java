package game1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean upPressed = false, downPressed = false,
			leftPressed = false, rightPressed = false;

	public KeyInput(Handler handler){
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getID() == ID.Player){	

				if(key == KeyEvent.VK_UP){
					tempObject.setVY(-5); upPressed = true;
				}
				if(key == KeyEvent.VK_LEFT){
					tempObject.setVX(-5); leftPressed = true;
				}
				if(key == KeyEvent.VK_DOWN){
					tempObject.setVY(5); downPressed = true;
				}
				if(key == KeyEvent.VK_RIGHT){
					tempObject.setVX(5); rightPressed = true;
				
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(Game.gameState == Game.STATE.Menu && key == KeyEvent.VK_ENTER) {
			Game.gameState = Game.STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32,
					ID.Player, handler));
		}
	}

	public void keyReleased(KeyEvent e){

		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getID() == ID.Player){
				if(key == KeyEvent.VK_UP) {
					upPressed = false;
					if(downPressed) tempObject.setVY(5);
					else tempObject.setVY(0);
				}
				
				if(key == KeyEvent.VK_DOWN) {
					downPressed = false;
					if(upPressed) tempObject.setVY(-5);
					else tempObject.setVY(0);
				}
				
				if(key == KeyEvent.VK_LEFT) {
					leftPressed = false;
					if(rightPressed) tempObject.setVX(5);
					else tempObject.setVX(0);
				}
				
				if(key == KeyEvent.VK_RIGHT) {
					rightPressed = false;
					if(leftPressed) tempObject.setVX(-5);
					else tempObject.setVX(0);
				}
			}
			
		}

	}

}