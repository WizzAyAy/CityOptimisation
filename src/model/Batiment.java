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
	
	public Batiment(int maxWidth, int maxHeight, int numero) {
		this.x = 0;
		this.y = 0;
		this.height = (int) (Math.random() * maxHeight) + 1;
		this.width = (int) (Math.random() * maxWidth) + 1;
		
		this.numero = numero;
		
		this.isPlaced = false;
	}
	
	public String toString() {
		String s = "Bat n°";
		if(this.numero < 10)
			s += Integer.toString(this.numero) + "  |";
		else 
			s += Integer.toString(this.numero) + " |";
		
		s +=  "(x:" + this.x + ", y:" + this.y + ", w:" + this.width + ", h:" +this.height + ")";
		return s;
	}
}
