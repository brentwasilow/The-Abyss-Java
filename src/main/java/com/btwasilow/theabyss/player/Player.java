package com.btwasilow.theabyss.player;

public class Player {

	/*
	 * angle the player is facing according to default cartesian
	 * coordinates/grid (positive x-axis corresponds to 0 degrees)
	 */
	private double angle;
	
	/*
	 * Coordinate values of the player on the map where a value of
	 * 0 is the top left of the map boundary
	 */
	private double x;
	private double y;
	
	//***********************//
	//* Getters and Setters *//
	//***********************//
	
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
