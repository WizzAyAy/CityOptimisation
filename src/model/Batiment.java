package model;

public class Batiment {
	private int x, y;
	private int height, width;
	
	private int numero;
	private boolean isPlaced;
	
	// Constructor
	public Batiment(int x, int y, int height, int width, int numero) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		this.numero = numero;
		
		this.isPlaced = false;
	}
	
	// Coordinates
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int endX()
	{
		return x+width;
	}
	
	public int endY()
	{
		return y+height;
	}

	// Size
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}


	// Numero
	public int getNumero() {
		return numero;
	}

	// Is placed
	public boolean isPlaced() {
		return isPlaced;
	}

	// Is superimposed to another building
	public boolean isSuperimposed(Batiment b)
	{
		// if x or y is > to end of building b, not superimposed, 
		// else, if end of this building inferior to start of building b, not superimposed, else superimposed
		return (x > b.endX() || y > b.endY()) ? false : ((endX() < b.getX() || endY() < b.getY()) ? false : true);
	}
}
