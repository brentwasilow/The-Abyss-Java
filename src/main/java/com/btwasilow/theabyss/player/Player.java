package com.btwasilow.theabyss.player;

public class Player {

	private double health;
	private double attack;
	private double xp;
	private double movementSpeed;
	private double angle;
	private double angleSpeed;
	private double x;
	private double y;

	public Player() {
		init();
	}
	
	public void init() {
		health = 100.0;
		attack = 1.0;
		xp = 0.0;
		movementSpeed = 5.0;
		angle = 0.0;
		angleSpeed = 3.0;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}
}
