package com.btwasilow.theabyss;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.btwasilow.theabyss.component.MapComponent;
import com.btwasilow.theabyss.constants.Consts;
import com.btwasilow.theabyss.display.Display;
import com.btwasilow.theabyss.level.Level;

public class Game implements Runnable {

	private Display display;
	
	private String title;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics2D g;
	
	private Level level;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	private void init() {
		display = new Display(title, width, height);
		
		level = new Level("res/level1.png");
	}
	
	private void update() {
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();

		// draw here
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, Consts.WIDTH, Consts.HEIGHT);
		
		MapComponent.render(g, level);
		
		bs.show();
		g.dispose();
	}
	
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while (running) {
			display.getCanvas().requestFocus();
			
			long now = System.nanoTime();
			delta += (now - lastTime) / Consts.SKIP_TICKS;
			lastTime = now;
			
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if ((System.currentTimeMillis() - timer) > 1000) {
				timer += 1000;
				System.out.println(updates + " ups | " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public synchronized void start() {
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
