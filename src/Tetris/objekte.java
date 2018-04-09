package Tetris;

public enum objekte {
	L(2,3),	
	T(2,3),
	Z(2,3),
	W(2,2),
	I(1,4),
	;
	
	
	public static int[][] CL1 = {{0,0},{0,1},{0,2},{1,2}};
	public static int[][] CL2 = {{1,0},{1,1},{1,2},{0,2}};
	public static int[][] CZ1 = {{1,0},{0,1},{1,1},{0,2}};
	public static int[][] CZ2 = {{0,0},{0,1},{1,1},{1,2}};
	public static int[][] CT =  {{0,0},{0,1},{0,2},{1,1}};	
	public static int[][] CW =  {{0,0},{0,1},{1,0},{1,1}};
	public static int[][] CI =  {{0,0},{0,1},{0,2},{0,3}};
	
	int länge, breite;
	private objekte(int länge, int breite){
		this.länge = länge;
		this.breite = breite;
	}
	
}
