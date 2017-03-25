package com.btwasilow.theabyss.level;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.btwasilow.theabyss.constants.Consts;
import com.btwasilow.theabyss.player.Player;

public class Level {
	
	private Player player;
	private int[][] map;
	
	public Level(String filename) {
		// initialize player and load the first map when the level object is created
		// from here on out we will only need to reload maps
		player = new Player();
		loadMap(filename);
	}
	
	public void loadMap(String filename) {
		try {
			BufferedImage image = ImageIO.read(new File(filename));
			
			int width = image.getWidth();
			int height = image.getHeight();
			map = new int[width][height];
			
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int color = image.getRGB(x, y);
				
					if (color == Color.WHITE.getRGB()) {
						map[y][x] = Consts.EMPTY_BLOCK;
					} else if (color == Color.BLACK.getRGB()) {
						map[y][x] = Consts.BRICK;
					} else if (color == Color.BLUE.getRGB()) {
						map[y][x] = Consts.DOOR;
					} else if (color == Color.RED.getRGB()) {
						player.setX(x * Consts.TILE_SIZE + Consts.TILE_SIZE_2);
						player.setY(y * Consts.TILE_SIZE + Consts.TILE_SIZE_2);
					}
				}
			} 
		} catch (Exception e) {
			System.out.println("Could not load map file.");
			System.exit(1);
		}
	}
	
	public Player getPlayer() {
		return player;
	}
}
