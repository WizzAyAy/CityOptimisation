package model;

public class Batiment {
	private int x, y;
	private int height, width;
	private boolean isPlaced;
	
	public Batiment(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		this.isPlaced = false;
	}
}
