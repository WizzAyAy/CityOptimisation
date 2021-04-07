package model;

public class Batiment {
	private int x, y;
	private int height, width;
	
	private int numero;
	private boolean isPlaced;
	private boolean isLinked;
	
	// Constructor
	public Batiment(int x, int y, int height, int width, int numero) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		
		this.numero = numero;
		
		this.isPlaced = false;
		this.isLinked = false;
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
			s += Integer.toString(this.numero) + "  | ";
		else 
			s += Integer.toString(this.numero) + " | ";
		
		s +=  "(x:" + this.x + ", y:" + this.y + ", h:" + this.height + ", w:" +this.width + ")";
		if(this.isPlaced)
			s += " Placed";
		if(this.isLinked) {
			s += " Linked";
		}
		return s;
	}
	

	// Coordinates
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

	// Placed
	public boolean isPlaced() {
		return isPlaced;
	}
	
	public void setPlaced(boolean b) {
		this.isPlaced = b;
	}
	
	// Linked to CityHall
	public boolean isLinked()
	{
		return isLinked;
	}
	
	public void setLinked(boolean link)
	{
		this.isLinked = link;
	}
	
	//Area
	public int getArea() {
		return width * height;
	}

	// Is superimposed to another building
	public boolean isSuperimposed(Batiment b){
		// if x or y is > to end of building b, not superimposed, 
		// else, if end of this building inferior to start of building b, not superimposed, else superimposed
		// for the lore return (x > b.endX() || y > b.endY()) ? false : ((endX() < b.getX() || endY() < b.getY()) ? false : true);
		boolean tmp = (x > b.endX() - 1 || y > b.endY() - 1); 
		boolean tmp2 = (endX() - 1< b.getX() || endY() - 1 < b.getY());

		return tmp || tmp2;
	}
	
	public int area()
	{
		return height*width;
	}
}
