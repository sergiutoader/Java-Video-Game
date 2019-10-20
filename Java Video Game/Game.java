package game1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 640, HEIGHT = WIDTH / 4 * 3;
	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE{
		Menu,
		Game
	};
	
	public static STATE gameState = STATE.Game;

	// --- CONSTRUCTOR AND MAIN --- //

	public Game(){

		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Game1", this);
		
		// adding HUD & spawner 
		hud = new HUD(handler);
		spawner = new Spawn(handler, hud);
		menu = new Menu();
		
		if(gameState == STATE.Game) {
			// adding player
			handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32,
				ID.Player, handler));	
		}	
	}

	public static void main(String[] args){
		new Game();
	}

	// --- START AND STOP --- //

	public synchronized void start(){
		thread = new Thread(this); // a class that implements runnable is required (this)
		thread.start();
		running = true;
	}

	public synchronized void stop(){
		try{
			thread.join(); // stops the thread
			running = false;
		}catch(Exception e){
			e.printStackTrace(); // print out the error
		}
	}

	// --- RUN, TICK AND RENDER --- //

	public void run(){
		
		this.requestFocus(); // get focus on window automatically when application starts
		
		// run code
		long lastTime = System.nanoTime();
		double ammountOfTicks = 60.0;
		double ns = 1000000000 / ammountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while(delta >= 1){
				tick();
				delta--;
			}

			if(running)
				render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick(){
		handler.tick();
		
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		}
		
	}

	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3); // 3 = how many buffers it creates
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}
		else {
			g.setColor(Color.WHITE);
			g.drawString("Game over! Final score: " + hud.getFinalScore(), WIDTH / 2 - 100, HEIGHT / 2 - 32);
			g.drawString("Press Enter to play again or Esc to exit", WIDTH / 2 - 100, HEIGHT / 2 - 18);
		}
		
		g.dispose();
		bs.show();
	}
	
	// clamp method
	
	public static double clamp(double var, double min, double max) {
		if(var < min) return min;
		else if(var > max) return max;
		return var;
	}
}