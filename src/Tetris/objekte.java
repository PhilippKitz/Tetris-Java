package Tetris;

import java.awt.Color;

public enum objekte {
	L(true,Color.BLUE, 1),	
	T(true,Color.YELLOW, 2),
	Z(true,Color.ORANGE, 3),
	W(false,Color.GREEN, 4),
	I(true,Color.RED, 5),
	;
	
	
	public static int[][] CL1 = {{0,0},{-1,0},{1,0},{-1,1}}; //{x,y}
	public static int[][] CL2 = {{0,0},{-1,0},{1,0},{1,1}};
	public static int[][] CZ1 = {{0,0},{-1,0},{0,1},{1,1}};
	public static int[][] CZ2 = {{0,0},{-1,1},{0,1},{1,0}};
	public static int[][] CT =  {{0,0},{0,1},{-1,0},{1,0}};	
	public static int[][] CW =  {{0,0},{0,1},{1,0},{1,1}};
	public static int[][] CI =  {{0,0},{-1,0},{1,0},{2,0}};
	
	boolean rotation; Color farbe; int Number;
	private objekte(boolean rotation, Color farbe, int num){
		this.rotation = rotation;
		this.farbe = farbe;
		this.Number = num;
	}
	
}
