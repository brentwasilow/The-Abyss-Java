package com.btwasilow.theabyss.component;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.btwasilow.theabyss.constants.Consts;
import com.btwasilow.theabyss.level.Level;
import com.btwasilow.theabyss.utility.Util;

public class MapComponent {
	
	public static BufferedImage image = new BufferedImage(Consts.WIDTH, Consts.HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	static int textureOffsetVertical;
	static int textureOffsetHorizontal;
	static int subimageOffsetVerticalX;
	static int subimageOffsetVerticalY;
	static int subimageOffsetHorizontalX;
	static int subimageOffsetHorizontalY;
	
	static int[][] map = {{1, 1, 1, 1, 1},
						  {1, 0, 0, 0, 1},
						  {1, 0, 0, 0, 1},
						  {1, 0, 0, 0, 1},
						  {1, 1, 1, 1, 1}};
	
	public static void init() {
		
	}
	
	public static void render(Graphics2D g, Level level) {
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
			angle = Util.boundAngle(level.getPlayer().getAngle() + Math.toDegrees(ang));
		
			verticalDistance = verticalIntersection(angle, level);
			horizontalDistance = horizontalIntersection(angle, level);
			
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
			projectedSliceHeight = Consts.DISTANCE_TO_PROJECTION_TILE_SIZE / correctDistance;
			wallScale = Math.ceil(projectedSliceHeight) / Consts.TILE_SIZE;

			int wallTop = (Consts.HEIGHT_2) - (int) (Math.ceil(projectedSliceHeight)/2);
			if (wallTop < 0) {
				wallTop = 0;
			}
			
			int wallBottom = (Consts.HEIGHT_2) + (int) (Math.ceil(projectedSliceHeight)/2);
			if (wallBottom >= Consts.HEIGHT) {
				wallBottom = Consts.HEIGHT - 1;
			}
			
			/******************
			 * Wall Rendering *
			 ******************/
			
			int index = (wallTop * Consts.WIDTH + x); // * 3;
			int indexAdd = (Consts.WIDTH); // * 3);
			for (int y = wallTop; y <= wallBottom; y++) {
				pixels[index] = 255;
				
				index += indexAdd;
			}
			
			/*******************
			 * Floor Rendering *
			 *******************/
			
			for (int y = wallBottom + 1; y < Consts.HEIGHT; y++) {
				int floorIndex = y * Consts.WIDTH + x;
				
				pixels[floorIndex] = 16711680;
			}
			
			/*********************
			 * Ceiling Rendering *
			 *********************/
			
			for (int y = wallTop - 1; y >= 0; y--) {
				int ceilingIndex = y * Consts.WIDTH + x;
				
				pixels[ceilingIndex] = 65280;
			}
		}
		g.drawImage(image, 0, 0, Consts.WIDTH, Consts.HEIGHT, null);
	}
	
	private static double verticalIntersection(double angle, Level level) {
		double verticalX;
		double verticalY;
		double dx;
		double dy;
		double t = Math.tan(Math.toRadians(angle));
		
		if (angle == 90.0 || angle == 270.0) {
			return 1000000000.0;
		}
		
		if (angle > 90.0 && angle < 270.0) {
			verticalX = ((int) (level.getPlayer().getX()) / Consts.TILE_SIZE) * Consts.TILE_SIZE;
			dx = -Consts.TILE_SIZE;
			verticalY = level.getPlayer().getY() + (level.getPlayer().getX() - verticalX) * t;
			dy = t * Consts.TILE_SIZE;
			verticalX--;
		} else {
			verticalX = (((int) (level.getPlayer().getX()) / Consts.TILE_SIZE) * Consts.TILE_SIZE) + Consts.TILE_SIZE;
			dx = Consts.TILE_SIZE;
			verticalY = level.getPlayer().getY() + (level.getPlayer().getX() - verticalX) * t;
			dy = -t * Consts.TILE_SIZE;
		}
		int column = (int) (verticalX) / Consts.TILE_SIZE;
		int row = (int) (verticalY) / Consts.TILE_SIZE;
		
		if (row < 0 || row >= map.length || column < 0 || column >= map[row].length) {
			return 1000000000.0;
		}
		
		int block = map[row][column];
		
		while (block == 0) {
			verticalX += dx;
			verticalY += dy;
			
			column = (int) (verticalX) / Consts.TILE_SIZE;
			row = (int) (verticalY) / Consts.TILE_SIZE;
			
			if (row < 0 || row >= map.length || column < 0 || column >= map[row].length) {
				return 1000000000.0;
			}
			block = map[row][column];
		}
		if (block == 2) {
			double tempDistance = ((verticalX)+(dx/2.0)-(level.getPlayer().getX()))*((verticalX)+(dx/2.0)-(level.getPlayer().getX()))+
					((verticalY)+(dy/2.0)-(level.getPlayer().getY()))*((verticalY)+(dy/2.0)-(level.getPlayer().getY()));
			return Math.sqrt(tempDistance);
		}
		double tempDistance = ((verticalX - level.getPlayer().getX()) * (verticalX - level.getPlayer().getX())) + ((verticalY - level.getPlayer().getY()) * (verticalY - level.getPlayer().getY()));
		return Math.sqrt(tempDistance);
	}
	
	public static double horizontalIntersection(double angle, Level level) {
		double horizontalX;
		double horizontalY;
		double dx;
		double dy;
		double t = Math.tan(Math.toRadians(angle));
		double it = Math.tan(-Math.toRadians(angle));
		
		if (angle == 0.0 || angle == 180.0) {
			return 1000000000.0;
		}
		
		if (angle > 0.0 && angle < 180.0) {
			horizontalY = ((int) (level.getPlayer().getY()) / Consts.TILE_SIZE) * Consts.TILE_SIZE;
			dy = -Consts.TILE_SIZE;
			
			if (angle == 90.0) {
				horizontalX = level.getPlayer().getX();
				dx = 0.0;
			} else {
				horizontalX = level.getPlayer().getX() + ((level.getPlayer().getY() - horizontalY) / t);
				dx = Consts.TILE_SIZE / t;
			}
			horizontalY--;
		} else {
			horizontalY = (((int) (level.getPlayer().getY()) / Consts.TILE_SIZE) * Consts.TILE_SIZE) + Consts.TILE_SIZE;
			dy = Consts.TILE_SIZE;
			
			if (angle == -270.0) {
				horizontalX = level.getPlayer().getX();
				dx = 0.0;
			} else {
				horizontalX = level.getPlayer().getX() - ((level.getPlayer().getY() - horizontalY) / it);
				dx = Consts.TILE_SIZE / it;
			}
		}
		int column = (int) (horizontalX) / Consts.TILE_SIZE;
		int row = (int) (horizontalY) / Consts.TILE_SIZE;
		
		if (row < 0 || row >= map.length || column < 0 || column >= map[row].length) {
			return 1000000000.0;
		}
		
		int block = map[row][column];
		
		while (block == 0) {
			horizontalX += dx;
			horizontalY += dy;
			
			column = (int) (horizontalX) / Consts.TILE_SIZE;
			row = (int) (horizontalY) / Consts.TILE_SIZE;
			
			if (row < 0 || row >= map.length || column < 0 || column >= map[row].length) {
				return 1000000000.0;
			}
			block = map[row][column];
		}
		if (block == 2) {
			double tempDistance = ((horizontalX)+(dx/2.0)-(level.getPlayer().getX()))*((horizontalX)+(dx/2.0)-(level.getPlayer().getX()))+
					((horizontalY)+(dy/2.0)-(level.getPlayer().getY()))*((horizontalY)+(dy/2.0)-(level.getPlayer().getY()));
			return Math.sqrt(tempDistance);
		}
		double tempDistance = ((horizontalX - level.getPlayer().getX()) * (horizontalX - level.getPlayer().getX())) + ((horizontalY - level.getPlayer().getY()) * (horizontalY - level.getPlayer().getY()));
		return Math.sqrt(tempDistance);
	}
}
