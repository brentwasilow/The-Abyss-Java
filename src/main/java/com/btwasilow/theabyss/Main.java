package com.btwasilow.theabyss;

import com.btwasilow.theabyss.constants.Consts;

/*
 * The Main class is launches the game using our main method.
 * Specifically, we create a Game object and start the game thread.
 */
public class Main {
	
	/*
	 * Launches the game
	 */
	public static void main(String[] args) {
		Game game = new Game(Consts.TITLE, Consts.WIDTH, Consts.HEIGHT);
		game.start();
	}
}
