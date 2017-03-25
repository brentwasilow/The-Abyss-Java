package com.btwasilow.theabyss.utility;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public final class Util {
	
	private Util() {	
	}
	
	public static double boundAngle(double angle) {
		if (angle < 0.0) {
			angle += 360.0;
		} else if (angle >= 360.0) {
			angle -= 360.0;
		}
		return angle;
	}

	public static Image getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = (WritableRaster) image.getData();
		raster.setPixels(0, 0, width, height, pixels);
		return image;
	}
}
