package com.btwasilow.theabyss;

import com.btwasilow.theabyss.constants.Consts;

public class Main {
	
	/*
	 * Launches the game
	 */
	public static void main(String[] args) {
		Game game = new Game(Consts.TITLE, Consts.WIDTH, Consts.HEIGHT);
		game.start();
	}
}
