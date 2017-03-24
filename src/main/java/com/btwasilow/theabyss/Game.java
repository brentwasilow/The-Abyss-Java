package com.btwasilow.theabyss;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.btwasilow.theabyss.display.Display;

public class Game implements Runnable {

	private Display display;
	
	private String title;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics2D g;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	private void init() {
		display = new Display(title, width, height);
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
		double ns = 1000000000.0 / 60.0;
		
		while (running) {
			display.getCanvas().requestFocus();
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
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
