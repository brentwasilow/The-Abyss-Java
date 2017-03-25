package com.btwasilow.theabyss.component;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.VolatileImage;

import com.btwasilow.theabyss.constants.Consts;
import com.btwasilow.theabyss.utility.Util;

public class MapComponent {
	
	static int[] pixels = new int[Consts.PIXEL_BUFFER_SIZE];
	
	static double playerAngle = 90.0;
	
	static int textureOffsetVertical;
	static int textureOffsetHorizontal;
	static int subimageOffsetVerticalX;
	static int subimageOffsetVerticalY;
	static int subimageOffsetHorizontalX;
	static int subimageOffsetHorizontalY;
	
	public static void init() {
		
	}
	
	public static void render(Graphics2D g) {
		double numerator = Consts.PLAYER_HEIGHT * Consts.DISTANCE_TO_PROJECTION;
		double angle;
		
		double distance;
		double verticalDistance;
		double horizontalDistance;
		double correctDistance;
		
		double projectedSliceHeight;
		double wallScale;
		
		int textureOffset;
		int subimageOffsetX;
		int subimageOffsetY;
		
		for (int x = 0; x < Consts.WIDTH; x++) {
			double ang = Math.atan((double) (x - Consts.WIDTH_2) / Consts.DISTANCE_TO_PROJECTION);
			angle = Util.boundAngle(playerAngle + Math.toDegrees(ang));
		
			verticalDistance = verticalIntersection(angle);
			horizontalDistance = horizontalIntersection(angle);
			
			if (horizontalDistance < verticalDistance) {
				distance = horizontalDistance;
				
				textureOffset = textureOffsetHorizontal;
				subimageOffsetX = subimageOffsetHorizontalX;
				subimageOffsetY = subimageOffsetHorizontalY;
			} else {
				distance = verticalDistance;
				
				textureOffset = textureOffsetVertical;
				subimageOffsetX = subimageOffsetVerticalX;
				subimageOffsetY = subimageOffsetVerticalY;
			}
			correctDistance = distance * Math.cos(ang);
			projectedSliceHeight = Consts.DISTANCE_TO_PROJECTION / correctDistance;
			wallScale = Math.ceil(projectedSliceHeight) / Consts.TILE_SIZE;
			
			// Color color = determineDepthShade(distance);
			// level.zBuffer[x] = distance;
			
			int wallCounter = 0;
			int wallTop = (Consts.HEIGHT_2) - (int) (Math.ceil(projectedSliceHeight)/2);
			if (wallTop < 0) {
				wallCounter += -wallTop;
				wallTop = 0;
			}
			
			int wallBottom = Consts.HEIGHT_2 - (int) (Math.ceil(projectedSliceHeight)/2);
			if (wallBottom > Consts.HEIGHT) {
				wallBottom = Consts.HEIGHT;
			}
			
			/******************
			 * Wall Rendering *
			 ******************/
			
			int index = (wallTop * Consts.WIDTH + x) * 4;
			int indexAdd = (Consts.WIDTH * 4);
			for (int y = wallTop; y <= wallBottom; y++) {
				pixels[index] = 255;
				pixels[index+1] = 0;
				pixels[index+2] = 0;
				pixels[index+3] = 255;
				
				
				wallCounter++;
				index += indexAdd;
			}
			g.drawImage(Util.getImageFromArray(pixels, Consts.WIDTH, Consts.HEIGHT), 0, 0, Consts.WIDTH, Consts.HEIGHT, null);
		}
	}
}
