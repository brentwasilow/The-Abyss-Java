package com.btwasilow.theabyss;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.btwasilow.theabyss.component.MapComponent;
import com.btwasilow.theabyss.constants.Consts;
import com.btwasilow.theabyss.display.Display;
import com.btwasilow.theabyss.input.InputManager;
import com.btwasilow.theabyss.level.Level;
import com.btwasilow.theabyss.player.Player;

/*
 * The Game class is the highest level representation of launching our
 * game, specifically it implements a Thread in-and-of itself. 
 * 
 * The Game class holds all of the Managers, States, and fundamentally
 * implements the rendering and updating methods controlled by a
 * 60 updates-per-second game loop.
 */
public class Game implements Runnable {

	/*
	 * Holds our display (will be passed around to graphics
	 * and update methods)
	 */
	private Display display;
	
	/*
	 * Game attributes
	 */
	private String title;
	private int width, height;
	
	/*
	 * Game loop/thread components
	 */
	private boolean running = false;
	private Thread thread;
	
	/*
	 * Graphics components
	 */
	private BufferStrategy bs;
	private Graphics2D g;
	
	/*
	 * Managers
	 */
	private InputManager input;
	private Level level;
	private Player player;
	
	/*
	 * Initializes the high-level game component (i.e., the starting point of
	 * the game) 
	 */
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	/*
	 * Initializes all game components/objects
	 */
	private void init() {
		input = new InputManager();
		level = new Level("res/level1.png");
		player = new Player();
		
		display = new Display(title, width, height);
		display.getCanvas().addKeyListener(input);
	}
	
	/*
	 * Performs all update routine checks (i.e., keyboard, physics, graphics)
	 */
	private void update() {
		//*******************//
		//* Update Routines *//
		//*******************//
	}
	
	/*
	 * Performs high-level creation of graphics components. Also delegates
	 * graphics routine updates using a switch statement (i.e., rendering the
	 * map, enemies, sprites
	 */
	private void render() {
		// create buffer component for drawing and grab associated
		// graphics component
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();

		// clear the screen
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, Consts.WIDTH, Consts.HEIGHT);
		
		//**********************//
		//* Rendering Routines *//
		//**********************//
		MapComponent.render(g, level, player);
		
		
		bs.show();
		g.dispose();
	}
	
	/*
	 * Begins the game loop thread, represented using
	 * a variable fps but stable update timing
	 */
	public void run() {
		init();
		
		// start timing this loop
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while (running) {
			// request mouse pointer focus
			display.getCanvas().requestFocus();
			
			// check how long our game loop time has elapsed since the "start
			// timing" portion
			long now = System.nanoTime();
			delta += (now - lastTime) / Consts.SKIP_TICKS;
			lastTime = now;
			
			// update as many times as necessary to provide a consistent 
			// update rate based upon relative elapsed time
			// (represented through our delta var)
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			
			// perform unbounded rendering
			render();
			frames++;
			
			// display our fps and ups (will be removed from production
			// value code as println() is a costly method across
			// multiple running threads
			if ((System.currentTimeMillis() - timer) > 1000) {
				timer += 1000;
				System.out.println(updates + " ups | " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	/*
	 * Accessed by our Main class starting the game thread specifically
	 */
	public synchronized void start() {
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	/*
	 * Will be accessed by the Main class to end the game thread safely
	 */
	public synchronized void stop() {
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
