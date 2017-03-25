package com.btwasilow.theabyss;

import com.btwasilow.theabyss.constants.Consts;

public class Main {
	
	public static void main(String[] args) {
		Game game = new Game(Consts.TITLE, Consts.WIDTH, Consts.HEIGHT);
		game.start();
	}
}
