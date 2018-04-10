package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



public class running {
	
	int spalten, reihen, raster;
	int[][] feld;
	keyinput key;
	
	
	boolean left= true, right=true, down;
	public void abfragen(int x,int y){
		if(x == 0){
			left = false;
		}
		if(x == spalten-1){
			right = false;
		}
		if(x > spalten-1){
			this.x--;
		}
		if(x < 0){
			this.x++;
		}
	}
	
	public running(int spalten, int reihen, int raster){
		this.spalten = spalten;
		this.reihen  = reihen;
		this.raster  = raster;

		
		feld = new int[spalten][reihen];
		
		for(int s= 0; s < spalten; s++){
			for(int r= 0; r < reihen; r++){
				feld[s][r] = 0;
			}
		}
		
		firstCube();
		nextCube();
	}
	


	int obj_anzahl = 7;
	
	int x = 6,y = 1;
	int tickteiler =  0;
	public void tick(){
		
		if(tickteiler == 0){
			y++;tickteiler++;
		}else if(tickteiler >= 7){
			tickteiler = 0;
		}else{
			tickteiler++;
		}
		
	}
	
	objekte nextObj = objekte.L; ///enum
	objekte Obj = objekte.L; ///enum
	
	Random r = new Random();
	int[][] cube ,nextCube;	
	int lCube, bCube;
	
	
	public void firstCube(){
		
		int r = this.r.nextInt(obj_anzahl);
		
		nextCube = setnextCube(r);
		cube = nextCube;
		Obj = nextObj;
	}
			
	public void nextCube(){
		 x = 6; y = 1;
		cube = nextCube;
		Obj = nextObj;
		int r = this.r.nextInt(obj_anzahl);	
		nextCube = setnextCube(r);		
	}
	
	public void rotate(){
		cube = rotateCube(cube);
	}
	
	public void move(int x,int y){
		
		
		if(left && x < 0){
			this.x = this.x + x;
		}
		if(right && x > 0){
			this.x = this.x + x;
		}
		
		this.y = this.y + y;
	}
	
	public int[][] setnextCube(int c){ //nextcube wird gewählt
		int[][] cube = null;
		switch(c){
		case 0:////////////////////////L1
			nextObj = objekte.L;
			cube = objekte.CL1;			
			break;
		case 1:////////////////////////L2
			nextObj = objekte.L;
			cube = objekte.CL2; 			
			break;
		case 2:////////////////////////Z1
			nextObj = objekte.Z;
			cube = objekte.CZ1;
			break;
		case 3:////////////////////////Z2
			nextObj = objekte.Z;
			cube = objekte.CZ2;
			break;
		case 4:////////////////////////T
			nextObj = objekte.T;
			cube = objekte.CT;
			break;
		case 5:////////////////////////W
			nextObj = objekte.W;
			cube = objekte.CW;
			break;
		case 6:////////////////////////I
			nextObj = objekte.I;
			cube = objekte.CI;
			break;
		}
		
		return cube;
	}
	
	
	public int[][] rotateCube(int[][] cube){
		
		int[][] rotatetCube = new int[cube.length][2];
		
		for(int a = 0; a < rotatetCube.length; a++){
			rotatetCube[a][0] = 0 * cube[a][0] + (-1) * cube[a][1] ;
			rotatetCube[a][1] = 1 * cube[a][0] +   0  * cube[a][1] ;
		}
		
		return rotatetCube;
	}
	

	
	public void render(Graphics g){
		left = true; right = true;
		//render objekt		
		for(int s = 0; s < cube.length; s++){
			abfragen(x+cube[s][0],y+cube[s][1]);
			g.setColor(Obj.farbe);
			g.fillRect(raster*(x+cube[s][0]), raster*(y+cube[s][1]), raster, raster);
			g.setColor(Color.BLACK);
			g.drawRect(raster*(x+cube[s][0]), raster*(y+cube[s][1]), raster, raster);			
		}		
				
		///////////////////////next Cube render
		g.setColor(nextObj.farbe);
		for(int s = 0; s < cube.length; s++){
			g.setColor(nextObj.farbe);
			g.fillRect(10*nextCube[s][0]+325,10*nextCube[s][1]+100, 10, 10);
			g.setColor(Color.BLACK);
			g.drawRect(10*nextCube[s][0]+325,10*nextCube[s][1]+100, 10, 10);
		}
		
		///////////////////////feld render
		for(int s= 0; s < spalten; s++){
			for(int r= 0; r < reihen; r++){
				
				if(feld[s][r] == 1){
					g.drawRect(raster*s,raster*r,raster,raster);
				}
			}
		}
		
	}
	
	private void einschreiben(){
		for(int s = 0; s < cube.length; s++){
			feld[cube[s][0]+x][cube[s][1]+y] = 1;
		}
	}
	
	
}
