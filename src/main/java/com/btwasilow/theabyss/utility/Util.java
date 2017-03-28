package com.btwasilow.theabyss.utility;

public final class Util {
	
	/*
	 * Don't let anyone instantiate this class
	 */
	private Util() {	
	}
	
	/*
	 * Bound an angle between 0 and 360 degrees
	 */
	public static double boundAngle(double angle) {
		if (angle < 0.0) {
			angle += 360.0;
		} else if (angle >= 360.0) {
			angle -= 360.0;
		}
		return angle;
	}
}
