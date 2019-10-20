package game1;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public abstract class GameObject{
	
	protected double x, y;
	protected ID id;
	protected double vx, vy;

	public GameObject(double x, double y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	protected abstract Rectangle getBounds();

	// setters & getters

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setID(ID id){
		this.id = id;
	}

	public ID getID(){
		return id;
	}

	public void setVX(double vx){
		this.vx = vx;
	}

	public void setVY(double vy){
		this.vy = vy;
	}

	public double getVX(){
		return vx;
	}

	public double getVY(){
		return vy;
	}


	// custom methods
	
	public void increaseVX(double vx){
		this.vx += vx;
	}

	public void increaseVY(double vy){
		this.vy += vy;
	}
	
	public static double setRandVelocity(double vMin, double vMax) { 
		Random r = new Random();
		double v = r.nextInt(2 * (int) vMax) + 2 * (int) vMin;
		if(v % 2 == 1) v*= -1;
		v /= 2;
		
		return v;
	}

}