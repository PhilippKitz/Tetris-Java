package Tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyinput implements KeyListener {
	running run;
	
	public keyinput(running run){
		this.run = run;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { 
			run.nextCube();
		 }
		if (key == KeyEvent.VK_W) { 
			run.rotate();
		 }	
		
	}


	public void keyReleased(KeyEvent e) {
	
		
	}


	public void keyTyped(KeyEvent e) {
	
		
	}

}
