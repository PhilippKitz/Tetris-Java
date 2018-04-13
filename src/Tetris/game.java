package Tetris;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class game implements Runnable, WindowListener {
	
	JFrame menu;
	
	public game(JFrame menu){
		this.menu = menu;
	}


	int flänge = 400, fbreite = 400,raster = 20;
	/////////////////////////////////////////
	
	render draw;	
	keyinput key;
	int seed = 0;
	running r;

	///////////////////////////////////////// Frame
	
	public void startGame(int seed){
		key = new keyinput();
		r = new running(key,(flänge-140)/raster, fbreite/raster,raster, seed);	
		draw = new render(flänge, fbreite,raster,r);
		
		
		JFrame frame = new JFrame();
		frame.setSize(flänge+6, fbreite+29);
		frame.setResizable(false);		
		frame.addWindowListener(this);
		frame.addKeyListener(key);		
		frame.add(draw);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		start();
		
		System.out.println(draw.getSize());
	}
	
	
	///////////////////////////////////////// running // fps // ...	
	
	String fpsS = "0";
	boolean running= false; 
	
	Thread t;
	
	public void run() {
		System.out.println("Starting.....");

	      long lastTime = System.nanoTime();
	      final double amountOfTicks = 16.0;
	      double ns = 1000000000 / amountOfTicks;
	      double delta = 0;
	      int frames = 0;
	      long timer = System.currentTimeMillis();
	      
	      
	      while(running){
	          long now = System.nanoTime();
	          delta += (now - lastTime) / ns;
	          lastTime = now;
	          if (delta >= 1) {
	             delta--;
	             frames++;
	             
	             r.tick();	             
	             draw.repaint();
             	             
	          }
		          
	          if (System.currentTimeMillis() - timer > 1000) {
	             timer += 1000;
	             fpsS = Integer.toString(frames);
	             //System.out.println("FPS: " + fpsS);
	             frames = 0;
	          }
	   	          
		}
	}
	
	////////////////////////////////////////////////// start // stop
	public synchronized void start(){
		if(running)return;
		running = true;
		t= new Thread(this);
		t.start();
	}
	
	public synchronized void stop(){
		System.out.println("Stopping...");
		if(!running)return;
		running = false;
		System.out.println("Stopped");
	}

	
	
	/////////////////////////////////////////////// Windows Listender
	
	public void windowActivated(WindowEvent e) {
		System.out.println("[WindowsListener] Fenster ist Aktiv");
		r.reset();
	}

	
	public void windowClosed(WindowEvent e) {		
		
	}

	
	public void windowClosing(WindowEvent e) {
			System.out.println("[WindowsListener] Game Fenster würde geschlossen");
			running = false;
			
			menu.setVisible(true);
			stop();
	}

	
	public void windowDeactivated(WindowEvent e) {}

	
	public void windowDeiconified(WindowEvent e) {}

	
	public void windowIconified(WindowEvent e) {}

	
	public void windowOpened(WindowEvent e) {}

	////////////////////////////////////////////////////

}


