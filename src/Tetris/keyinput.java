package Tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyinput implements KeyListener {

	public boolean nextCube = false;
	public boolean rotate = false;
	public boolean mleft = false;
	public boolean mright = false;
	public boolean mdown = false;
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_O) { 
			nextCube = true;
		 }
		if (key == KeyEvent.VK_W) { 
			rotate = true;
		 }	
		if (key == KeyEvent.VK_A) { 
			mleft = true;
		 }
		if (key == KeyEvent.VK_D) { 
			mright = true;
		 }
		if (key == KeyEvent.VK_S) { 
			mdown = true;
		 }
	}


	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_O) { 
			nextCube = false;
		 }
		if (key == KeyEvent.VK_W) { 
			rotate = false;
		 }	
		if (key == KeyEvent.VK_A) { 
			mleft = false;
		 }
		if (key == KeyEvent.VK_D) { 
			mright = false;
		 }
		if (key == KeyEvent.VK_S) { 
			mdown = false;
		 }		
	}


	public void keyTyped(KeyEvent e) {
	
		
	}

}
