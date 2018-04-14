package Tetris;

import java.awt.Color;

public enum objekte {
	L1(true,Color.BLUE, 1),
	L2(true,Color.ORANGE, 2),	
	T(true,Color.PINK, 3),
	Z1(true,Color.RED, 4),
	Z2(true,Color.GREEN, 5),
	W(false,Color.YELLOW, 6),
	I(true,Color.CYAN, 7),
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
