package model;

public class Batiment {
	private int x, y;
	private int height, width;
	
	private int numero;
	private boolean isPlaced;
	
	public Batiment(int x, int y, int height, int width, int numero) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		this.numero = numero;
		
		this.isPlaced = false;
	}
}
