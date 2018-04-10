package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class render extends JComponent{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int fl�nge, fbreite, raster, reihen, spalten;
	
	////////////////////////////////////////Img
	
	
	ImageIcon l = new ImageIcon(getClass().getResource("tetris.png"));
	Image logo = l.getImage();
	running run;
	
	
	//////////////////////////////////////////////
	public render(int fl,int fb,int raster, running run){
		this.run = run;
		
		this.fl�nge = fl;
		this.fbreite = fb;
		this.raster = raster;	
		
		spalten = (fl�nge-140)/raster;
		reihen = fbreite/raster;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.create();
		
		//info feld hintergrund
		g.setColor(Color.lightGray);
		g.fillRect(fl�nge-140, 0, 140, fbreite);
		
		//spiel feld hintergrund
		g.setColor(Color.gray);
		g.fillRect(0, 0, fl�nge-140, fbreite);
		
		//raster im game feld
		g.setColor(Color.lightGray);
		for(int s= 1; s < spalten; s++){
			g.drawLine(s*20, 0, s*20, fbreite);
		}
		for(int r= 0; r <= reihen; r++){
			g.drawLine(0, r*20, fl�nge-140, r*20);
		}
		
		//grenz linie zwischen info und spiel feld
		g.setColor(Color.black);
		g.drawLine(fl�nge-140, 0, fl�nge-140, fbreite);
		
		//logo
		g.drawImage(logo, fl�nge-135, 10, null);
		
		//copyright
		g.setColor(Color.black);
		g.drawString("� Philipp Kitzm�ller", fl�nge-120, fbreite-10);
		
		//n�chster Cube feld
		g.setColor(Color.white);
		g.fillRect(fl�nge-100, 80, 60, 60);
		g.setColor(Color.black);
		g.drawRect(fl�nge-100, 80, 60, 60);
		
		
		// game bereich		
		run.render(g);
		
	}

}
