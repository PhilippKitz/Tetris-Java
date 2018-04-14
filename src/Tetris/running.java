package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JOptionPane;



public class running {
	
	int spalten, reihen, raster;
	int[][] feld;
	keyinput key;
	int Score;
	
	
	
	Random r = new Random();
	
	public running(keyinput key,int spalten, int reihen, int raster, int seed){
		this.spalten = spalten;
		this.reihen  = reihen;
		this.raster  = raster;
		this.key = key;
		
		if(seed == 0){
			System.out.println("noSeed");
		}else{
			System.out.println("Seed würde gesetzt auf: "+ seed);
			r.setSeed(seed);
		}
		
		
		feld = newGameField(reihen,spalten);

		
		firstCube();
		nextCube();
	}
	
	

	int obj_anzahl = 7;
	
	
	int x = 6,y = 1;
	
	int tickteiler =  0;
	int tickTeilungnorm = 7;
	int teilung = tickTeilungnorm;
	
	boolean running=true;
	
	public void tick(){
		
		if(running){
			if(Score >= 1200 && Score < 3000){
				teilung = 6;
			}else if(Score >= 3000 && Score < 5500){
				teilung = 4;
			}else if(Score >= 5500 && Score < 7000){
				teilung = 2;
			}else if(Score >= 7000){
				teilung = 1;
			}
			
			if(tickteiler == 0){
				Move(false,false,true);
				tickteiler++;
				
			}else if(tickteiler >= teilung){
				tickteiler = 0;
			}else{
				tickteiler++;
			}
			
			if(key.nextCube){
				nextCube();
			}
			if(key.rotate){
				rotate();
				key.rotate = false;
			}
			
			Move(key.mleft,key.mright,key.mdown);
	
			if(key.mdown){
				Score++;
			}
			FieldTest();
		}
	}
	
	objekte nextObj = objekte.L1; ///enum
	objekte Obj = objekte.L1; ///enum
	
	
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
		
		for(int i=0; i < cube.length; i++)	//gameover abfrage
			if(GameField(x+cube[i][0],y+cube[i][1])){ 
		    	GameOver();
			}
			
		}
	
	//Controlling
	public void rotate(){
		int[][] oldCube = cube;
		cube = rotateCube(cube);
		
		for(int i= 0; i < cube.length; i++){
			int tx = x+cube[i][0], ty = y+cube[i][1];
			
			if(tx < 0){
				x= x+1;
			}
			if(ty < 0){
				y= y+1;
			}	
			if(tx > spalten-1){
				x= x-1;
			}
			if(ty > reihen-1){
				y= y-1;
			}
			
			if(GameField(tx,ty)){
				cube = oldCube;
				System.out.println("Rotation nicht möglich");
			}
		}
	}
	
	int wait = 0;
	public void Move(boolean left, boolean right, boolean down){
		for(int i = 0; i < cube.length;i++){
			int tx = x+cube[i][0], ty = y+cube[i][1];
			if(GameField(tx-1,ty) || tx-1 <0 ){
				left = false;
			}
			if(GameField(tx+1,ty) || tx+1 > spalten-1){
				right = false;
			}
			if(ty == reihen-1 || GameField(tx,ty+1)){				
				down = false;
				if(wait == 10){
					einschreiben();
					wait=0;
					return;					
				}else{
					wait++;
				}
			}
		}
		
		if(left && x > 0){
			this.x --;
		}
		if(right && x < spalten-1){
			this.x ++;
		}
		if(down && y < reihen-1){
			this.y ++;
		}
	}
	
	
	public void moveDown(){
		this.y ++;
	}
	
	public void moveLeft(){
		this.x --;
	}
	
	public void moveRight(){
		this.x ++;
	}
	

	
	
	//game field
	boolean GameField(int x, int y){
		
		if(x < 0){
			x = 0;
		}
		if(y < 0){
			y = 0;
		}
		
		if(x > spalten-1){
			x = spalten-1;
		}
		if(y > reihen-1){
			y = reihen-1;
		}
		
		if(feld[x][y] == 0){
			return false;
		}else{
			return true;
		}
		
	}
	

	private void einschreiben(){	
		for(int s = 0; s < cube.length; s++){
			feld[cube[s][0]+x][cube[s][1]+y] = Obj.Number;
		}
		nextCube();
	}
	
	
	
	public int[][] newGameField(int reihen, int spalten){
		int gameField[][] = new int[spalten][reihen];
		for(int s= 0; s < spalten; s++){
			for(int r= 0; r < reihen; r++){
				
				gameField[s][r] = 0;
				//System.out.print(feld[s][r] + " ");
			}			
		}
		return gameField;
	}
	
	public void FieldTest(){
		int[][] newField = newGameField(reihen,spalten);	
		boolean[] voll = new boolean[reihen];
		int AnzahlReihen = 0;
		for(int r=0; r < reihen; r++){
			
			voll[r] = true;
			for(int s=0; s < spalten; s++){
				if(feld[s][r] == 0){
					voll[r] = false;
				}
			}
			
		}
		
		int newReihe = reihen;
		for(int r=reihen-1; r >= 0; r--){			
			
			
			if(voll[r]){
				System.out.println("voll");
				AnzahlReihen++;
				continue;
			}else{
				newReihe--;
			}
			
//			System.out.println("alteReihe: "+r+" neueReihe: " + newReihe + " reihevoll: " + voll[r] );
			for(int s=0; s < spalten; s++){
				newField[s][newReihe]= feld[s][r];
			}
			
		}
		feld = newField;
		Score = Score + AnzahlReihen*100;
		
		if(AnzahlReihen == 1){
			Score = Score + 50;
		}else if(AnzahlReihen == 2){
			Score = Score + 100;
		}else if(AnzahlReihen == 3){
			Score = Score + 200;
		}else if(AnzahlReihen == 4){
			Score = Score + 400;
		}else if(AnzahlReihen > 4){
			Score = Score + 800;
		}
	}

	//cube veränderungen
	public int[][] setnextCube(int c){ //nextcube wird gewählt
		int[][] cube = null;
		switch(c){
		case 0:////////////////////////L1
			nextObj = objekte.L1;
			cube = objekte.CL1;			
			break;
		case 1:////////////////////////L2
			nextObj = objekte.L2;
			cube = objekte.CL2; 			
			break;
		case 2:////////////////////////Z1
			nextObj = objekte.Z1;
			cube = objekte.CZ1;
			break;
		case 3:////////////////////////Z2
			nextObj = objekte.Z2;
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
	
	public void GameOver(){
		running = false;
		JOptionPane.showMessageDialog(null,
			    "Dein Score: "+ Score);
		reset();		
	}
	
	public void reset(){
		feld = newGameField(reihen, spalten);
		Score = 0;
		running = true;
		teilung = tickTeilungnorm;
	}
	
	
	public int[][] rotateCube(int[][] cube){
		
		int[][] rotatetCube = new int[cube.length][2];
		
		for(int a = 0; a < rotatetCube.length; a++){
			rotatetCube[a][0] = 0 * cube[a][0] + (-1) * cube[a][1] ;
			rotatetCube[a][1] = 1 * cube[a][0] +   0  * cube[a][1] ;
		}
		
		return rotatetCube;
	}

	//rendering 
	public void render(Graphics g){
		//text
		g.setColor(Color.black);
		g.drawString("Score: "+ Score, 280, 190);
		
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
					if(feld[s][r] == objekte.L1.Number){g.setColor(objekte.L1.farbe);}
					if(feld[s][r] == objekte.L2.Number){g.setColor(objekte.L2.farbe);}
					if(feld[s][r] == objekte.Z1.Number){g.setColor(objekte.Z1.farbe);}
					if(feld[s][r] == objekte.Z2.Number){g.setColor(objekte.Z2.farbe);}
					if(feld[s][r] == objekte.W.Number){g.setColor(objekte.W.farbe);}
					if(feld[s][r] == objekte.I.Number){g.setColor(objekte.I.farbe);}
					g.fillRect(raster*s,raster*r,raster,raster);
					
					g.setColor(Color.BLACK);;
					g.drawRect(raster*s,raster*r,raster,raster);
				}
			}
		}
		
	}
	

	
	
}
