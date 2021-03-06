package com.btwasilow.theabyss.constants;

/*
 * Static class holding all global constants used throughout the game
 * (they are final and cannot be modified)
 */
public final class Consts {
	
	public static final String TITLE = "The Abyss (Java)";
	
	public static final int WIDTH = 640;
	public static final int WIDTH_2 = (WIDTH/2);
	public static final int HEIGHT = 480;
	public static final int HEIGHT_2 = (HEIGHT/2);
	
	public static final double FOV_D = 60.0;
	public static final double FOV_R = Math.toRadians(FOV_D);
	public static final double FOV_2_D = (FOV_D/2);
	public static final double FOV_2_R = Math.toRadians(FOV_2_D);
	
	public static final int TILE_SIZE = 64;
	public static final int TILE_SIZE_2 = (TILE_SIZE/2);
	public static final int TILE_SIZE_MASK = (int) (Math.log(TILE_SIZE)/Math.log(2));
	public static final int TILE_SIZE_MASK_2 = (int) (Math.log(TILE_SIZE_2)/Math.log(2));
	
	public static final int DISTANCE_TO_PROJECTION = (int) (WIDTH_2/Math.tan(FOV_2_R));
	public static final int DISTANCE_TO_PROJECTION_TILE_SIZE = DISTANCE_TO_PROJECTION * TILE_SIZE;
	
	public static final double TICKS_PER_SECOND = 60.0;
	public static final double SKIP_TICKS = 1000000000.0 / TICKS_PER_SECOND;
	
	public static final int PIXEL_BUFFER_SIZE = (WIDTH * HEIGHT * 4);
	
	public static final double DOOR_OPEN_TICKS = 300.0;
	
	public static final double PLAYER_HEIGHT = 32.0;
	
	public static final int EMPTY_BLOCK = 0;
	public static final int BRICK = 1;
	public static final int DOOR = 2;
	
	/*
	 * Don't let anyone instantiate this class
	 */
	private Consts() {}
}
