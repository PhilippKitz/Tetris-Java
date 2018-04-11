package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



public class running {
	
	int spalten, reihen, raster;
	int[][] feld;
	keyinput key;
	
	
	boolean left= true, right=true, down = true;

	
	
	boolean GameField(int x, int y){
		if(feld[x][y] == 0){
			return false;
		}else{
			return true;
		}
		
	}
	
	public running(keyinput key,int spalten, int reihen, int raster){
		this.spalten = spalten;
		this.reihen  = reihen;
		this.raster  = raster;
		this.key = key;
		
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
			move(0,1);
			tickteiler++;
			
		}else if(tickteiler >= 7){
			tickteiler = 0;
		}else{
			tickteiler++;
		}
		
		if(key.nextCube){
			nextCube();
		}
		if(key.rotate){
			rotate();
		}
		if(key.mleft){
			move(-1,0);
		}
		if(key.mright){
			move(1,0);
		}
		if(key.mdown && y <= reihen-2){
			move(0,1);
		}
		
		key.nextCube = false;
		key.rotate = false;
		key.mleft = false;
		key.mright = false;
		key.mdown = false;
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
	
	int wait= 0;
	public void move(int x,int y){	
		
		for(int s = 0; s < cube.length; s++){
			int tx =this.x+cube[s][0];
			int ty =this.y+cube[s][1];
			
			if(tx > spalten-1  ){
				this.x--;
			}
			if(tx < 0){
				this.x++;
			}
			
			if(tx == 0 || GameField(tx-1,ty)){
				left = false;
			}
			System.out.println("vor if " + tx);
			if(tx == spalten-1 || GameField(tx+1,ty)){
				right = false;
				System.out.println("in if " + tx);
			}
			
			if(ty==reihen-1 || GameField(tx,ty+1)){
				down = false;
				if(wait == 4){
					einschreiben();
					wait=0;
				}else{
					wait++;
				}							
			}else{
				down = true;
			}
		}
		
		if(left && x < 0){
			this.x = this.x + x;
		}			
		if(right && x > 0){
			this.x = this.x + x;
		}			
		if(down){
				this.y = this.y + y;
		}
		
		left = true; right = true;  
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
		
		//render objekt		
		for(int s = 0; s < cube.length; s++){
			
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
				
				if(feld[s][r] >= 1){
					
					if(feld[s][r] == objekte.T.Number){g.setColor(objekte.T.farbe);}
					if(feld[s][r] == objekte.L.Number){g.setColor(objekte.L.farbe);}
					if(feld[s][r] == objekte.Z.Number){g.setColor(objekte.Z.farbe);}
					if(feld[s][r] == objekte.W.Number){g.setColor(objekte.W.farbe);}
					if(feld[s][r] == objekte.I.Number){g.setColor(objekte.I.farbe);}
					g.fillRect(raster*s,raster*r,raster,raster);
					
					g.setColor(Color.BLACK);;
					g.drawRect(raster*s,raster*r,raster,raster);
				}
			}
		}
		
	}
	
	private void einschreiben(){
		for(int s = 0; s < cube.length; s++){
			feld[cube[s][0]+x][cube[s][1]+y] = Obj.Number;
		}
		nextCube();

	}
	
	
}
