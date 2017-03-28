package com.btwasilow.theabyss.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

	/**
	 * Boolean array for determining which key is pressed/released
	 */
	public boolean[] keys = new boolean[256];
	
	/**
	 * Sets the boolean keys array position to true for the
	 * specified key code
	 * 
	 * @param e		the KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	/**
	 * Sets the boolean keys array position to false for the
	 * specified key code
	 * 
	 * @param e		the KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	/**
	 * Unused but necessarily implemented KeyListener method
	 */
	public void keyTyped(KeyEvent e) { }
}
