package Tetris;

import java.awt.Graphics;
import java.util.Random;



public class running {
	
	int spalten, reihen, raster;
	int[][] feld;
	keyinput key;
	
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
	public void tick(){
		//y++;
		if(y==7){
			einschreiben();
		}
	}
	
	objekte nextObj = objekte.L; ///enum
	Random r = new Random();
	int[][] cube ,nextCube;	
	int lCube, bCube;
	
	int rotation = 0;
	int[][] rotatetCube;
	
	public void firstCube(){
		
		int r = this.r.nextInt(obj_anzahl);
		
		setnextCube(r);
		
		rotatetCube = cube;
	}
	
		
	public void nextCube(){
		rotation = 0;
		cube = nextCube;
		
		int r = this.r.nextInt(obj_anzahl);
		
		setnextCube(r);
		rotatetCube = cube;
	}
	
	public void setnextCube(int c){ //nextcube wird gewählt
		switch(c){
		case 0:////////////////////////L1
			nextObj = objekte.L;
			nextCube = objekte.CL1;
			lCube= nextObj.länge;  bCube = nextObj.breite;
			break;
		case 1:////////////////////////L2
			nextObj = objekte.L;
			nextCube = objekte.CL2; 
			lCube= nextObj.länge;  bCube = nextObj.breite;
			break;
		case 2:////////////////////////Z1
			nextObj = objekte.Z;
			nextCube = objekte.CZ1;
			lCube= nextObj.länge;  bCube = nextObj.breite;
			break;
		case 3:////////////////////////Z2
			nextObj = objekte.Z;
			nextCube = objekte.CZ2;
			lCube= nextObj.länge;  bCube = nextObj.breite;
			break;
		case 4:////////////////////////T
			nextObj = objekte.T;
			nextCube = objekte.CT;
			lCube= nextObj.länge;  bCube = nextObj.breite;
			break;
		case 5:////////////////////////W
			nextObj = objekte.W;
			nextCube = objekte.CW;
			lCube= nextObj.länge;  bCube = nextObj.breite;
			break;
		case 6:////////////////////////I
			nextObj = objekte.I;
			nextCube = objekte.CI;
			lCube= nextObj.länge;  bCube = nextObj.breite;
			break;
		}
	}
	


	
	public void rotate(){
		rotation++;		
		if(rotation==4){rotation=0;}		
		System.out.println("Rotation: "+ rotation);
		
		switch(rotation){
	///////////////////////
		case 0:
			rotatetCube = null;
			rotatetCube = cube;

	///////////////////////
		case 1:
			rotatetCube = null;
			rotatetCube = cube;

			break;
	///////////////////////			
		case 2:
			rotatetCube = null;
			rotatetCube = cube;

			break;  
	///////////////////////
		case 3:	
			rotatetCube = null;
			rotatetCube = cube;
			for(int i = 0; i < rotatetCube.length; i++){
				rotatetCube[i][0] = 2-cube[i][1]; //x achse
				rotatetCube[i][1] = cube[i][0]; //y achse
			}
			break;
	///////////////////////		
		}
		for(int s = 0; s < rotatetCube.length; s++){
			System.out.println(rotatetCube[s][0]+" "+rotatetCube[s][1]);
		}
	}
		
	public void render(Graphics g){
		
		//render objekt
		for(int s = 0; s < cube.length; s++){
			g.drawRect(raster*(x+rotatetCube[s][0]), raster*(y+rotatetCube[s][1]), raster, raster);	
		}		
				
		///////////////////////next Cube render
		for(int s = 0; s < cube.length; s++){
			g.drawRect(5*nextCube[s][0]+305,5*nextCube[s][1]+85, 5, 5);	
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
