package com.btwasilow.theabyss.level;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.btwasilow.theabyss.constants.Consts;
import com.btwasilow.theabyss.player.Player;

/*
 * Represents a Level in our game. Note that we use a matrix to represent
 * our map, which is loaded using a color-indexed PNG file. Our Level class
 * was designed so as to maintain encapsulation between the Player class
 * as follows: each Level class is loaded using a specific PNG with the
 * starting x and y coords of our player being recorded inside the Level
 * class. We then create a method such that we can change the player's coords
 * to the new level's coords at any given time, for which we will delegate
 * this process to the LevelManager class.
 */
public class Level {
	
	/*
	 * Representation of the map using a 2D matrix structure
	 */
	private int[][] map;
	
	/*
	 * Starting location of the player
	 */
	private int x, y;
	
	/*
	 * Level constructor handles loading a map every time a level
	 * object is created
	 */
	public Level(String filename) {
		loadMap(filename);
	}
	
	/*
	 * handles loading the map array with the necessary integer
	 * values using a path to a png (representing a color coded index)
	 */
	private void loadMap(String filename) {
		try {
			BufferedImage image = ImageIO.read(new File(filename));
			
			int width = image.getWidth();
			int height = image.getHeight();
			map = new int[width][height];
			
			// for each color in the image, store the appropriate 
			// constant-block value, player info, or sprite info 
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
						this.x = x * Consts.TILE_SIZE + Consts.TILE_SIZE_2;
						this.y = y * Consts.TILE_SIZE + Consts.TILE_SIZE_2;
					}
				}
			} 
		} catch (Exception e) {
			System.out.println("Could not load map file.");
			System.exit(1);
		}
	}
	
	/*
	 * Modifies the Player object's starting coordinates to this
	 * maps starting coordinates
	 */
	public void loadPlayerStartingCoords(Player player) {
		player.setX(this.x);
		player.setY(this.y);
	}
}
