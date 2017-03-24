package com.btwasilow.theabyss.constants;

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
	
	public static final int TICKS_PER_SECOND = 60;
	public static final int SKIP_TICKS = (int) (1000.0F/TICKS_PER_SECOND);
	public static final int MAX_FRAMESKIP = 10;
	
	public static final int PIXEL_BUFFER_SIZE = (WIDTH * HEIGHT * 4);
	
	public static final double DOOR_OPEN_TICKS = 300.0;
	
	private Consts() {
	}
}
