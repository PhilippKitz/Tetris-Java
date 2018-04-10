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
		
		if (key == KeyEvent.VK_O) { 
			run.nextCube();
		 }
		if (key == KeyEvent.VK_W) { 
			run.rotate();
		 }	
		if (key == KeyEvent.VK_A) { 
			run.move(-1,0);
		 }
		if (key == KeyEvent.VK_D) { 
			run.move(1,0);
		 }
		if (key == KeyEvent.VK_S) { 
			run.move(0,1);
		 }
	}


	public void keyReleased(KeyEvent e) {

		
	}


	public void keyTyped(KeyEvent e) {
	
		
	}

}
