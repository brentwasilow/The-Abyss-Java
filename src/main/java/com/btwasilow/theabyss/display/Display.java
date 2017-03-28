package com.btwasilow.theabyss.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	/*
	 * The frame component of our display to be used as the outer window
	 * container, as well as the inner drawable canvas component
	 */
	private JFrame frame;
	private Canvas canvas;
	
	/*
	 * Display container attributes
	 */
	private String title;
	private int width, height;
	
	/*
	 * Constructor for our display, consisting of setting the attributes
	 * accordingly, as well as delegating the display creation
	 */
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	/*
	 * Defines the specific attributes of our frame and drawable canvas
	 * components (i.e., size, allowable operations, and attachments)
	 */
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
	}
	
	/*
	 * JFrame getter
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/*
	 * Canvas getter
	 */
	public Canvas getCanvas() {
		return canvas;
	}
}
