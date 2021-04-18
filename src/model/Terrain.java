package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;


public class Terrain {
	int height, width;
	int [][] matrice;
	ArrayList<Batiment> batiments;
	
	//--------------------INIT & CONSTRUCTORS---------------------//
	
	public Terrain(int height, int width) {
	
		this.height = height;
		this.width = width;
		
		matrice = new int [height][width];
		this.batiments = new ArrayList<Batiment>();
		
		initMatrice();
	}
	
	public void initMatrice() {
		for(int i = 0; i < matrice.length; i++) {
			for(int j = 0; j < matrice[i].length; j++) {
				matrice[i][j] = 0;
			}
		}
	}
	
	public void initBatiments() {
		for(Batiment b : batiments) {
			b.setLinked(false);
			b.setPlaced(false);
			b.setX(0);
			b.setY(0);
		}
	}
	
	public void reset() {
		initMatrice();
		initBatiments();
	}
	
	
	//---------------------PRINT---------------------//
	
	public void printTerrain() {
		System.out.println("Terrain de taille | w:"+this.width+ " h:"+this.height );
		for(int i = 0; i < matrice.length; i++) {
			for(int j = 0; j < matrice[i].length; j++) {
				if(matrice[i][j] < 10)
					System.out.print("  " + matrice[i][j]);
				else
					System.out.print(" " + matrice[i][j]);
			}
			System.out.println("");
		}
	}
	
	public void printAllBat() {
		for(int i = 0; i < batiments.size(); i++) {
			System.out.println(batiments.get(i).toString());
			}
	}
	
	//---------------------BATs---------------------//
	
	public void addBat(Batiment b) {
		batiments.add(b);
	}

	//bat dans les cloues
	public boolean isBatInside(Batiment b) {
		if(b.getX() >= 0 && b.getY() >= 0 && b.endX() <= this.width && b.endY() <= this.height)
			return true;
		else return false;
	}
	
	public boolean testPlaceBat(Batiment b, int x, int y) {
		boolean placed = true;
		b.setX(x);
		b.setY(y);

		//System.out.println(b.toString());
		//System.out.println("test en " + x + ", " + y);
		placed = placed && isBatInside(b);
		//System.out.println("test isBatInside " + placed);
		
		for(int i = 0; i < batiments.size(); i++) {
			if(batiments.get(i) != b && batiments.get(i).isPlaced()) {
				//System.out.println("regarde si bat " + b.getNumero() + " is insde "+ batiments.get(i).getNumero());
				placed = placed && b.isSuperimposed(batiments.get(i));
			}
		}
		return placed;
	}
	
	public void placeBat(Batiment b, int x, int y) {
		if(testPlaceBat(b,x,y)) 
			b.setPlaced(true);
		else 
			b.setPlaced(false);
	}
	
	public void placeHDV() {
		//random dans le coin sup gauche
		int x = (int) (Math.random() * width / 2);
		int y = (int) (Math.random() *height / 2);
		placeBat(batiments.get(0), x, y);
		batiments.get(0).setLinked(true);
		updateMap();
	}
	
	public boolean placeHDV(int x, int y) {
		Batiment cityHall = null;
		for(Batiment b : batiments) {
			if(b.getNumero() == 1)
				cityHall = b;
		}
		if(cityHall.endX() < x && cityHall.endY() < y)
			return false;
		placeBat(batiments.get(0), x, y);
		cityHall.setLinked(true);
		updateMap();
		return true;
	}
	
	public void updateMap() {
		for(int i = 0; i < batiments.size(); i++) {
			
			if(batiments.get(i).isPlaced()) {
				for(int a = batiments.get(i).getY(); a < batiments.get(i).endY(); a++) {
					for(int b = batiments.get(i).getX(); b < batiments.get(i).endX(); b++) {
						matrice[a][b] = batiments.get(i).getNumero();
					}
				}
			}
			else {
				batiments.get(i).setLinked(false);
			}
		}
	}
	
	//---------------------SCORES---------------------//
	
	public int maxScore() {
		int score = 0;
		for(int i = 0; i < batiments.size(); i++) {
			score += batiments.get(i).getArea();
		}
		return score;
	}
	
	public int score() {
		int score = 0;
		for(int i = 0; i < batiments.size(); i++) {
			if (batiments.get(i).isPlaced()) score += batiments.get(i).getArea();
		}
		return score;
	}
	
	//---------------------ROAD LINKS---------------------//
	
	// Test links to the CityHall
	public void links(){
		
		for(Batiment b : batiments) {
			b.setLinked(false);
		}
		ArrayDeque<int[]> stack = initStack();
				
		if(stack == null){
			return;
		}
		
		ArrayDeque<int[]> visited = stack.clone();
		
		while(!stack.isEmpty()){
			int[] currentPos = stack.removeFirst();
			int x = currentPos[0];
			int y = currentPos[1];
			
			
			if(x>0){
				
				if(matrice[y][x-1] == 0){
					int array[] = {x-1,y};
					if(!contains(visited, array) && !contains(stack, array) ){
						stack.addFirst(array);
						visited.add(array);
					}
				}
				else{
					this.batiments.get(matrice[y][x-1] - 1).setLinked(true);
				}
			}
			
			if(y>0){
				if(matrice[y-1][x] == 0){
					int array[] = {x,y-1};
					if(!contains(visited, array) && !contains(stack, array)){
						stack.addFirst(array);
						visited.add(array);
					}
				}
				else{
					this.batiments.get(matrice[y-1][x] - 1).setLinked(true);
				}
			}
			
			if(x<width-1){
				if(matrice[y][x+1] == 0){
					int array[] = {x+1,y};
					if(!contains(visited, array) && !contains(stack, array)){
						stack.addFirst(array);
						visited.add(array);
					}
				}
				else{
					this.batiments.get(matrice[y][x+1] - 1).setLinked(true);
				}
			}
						
			
			if(y<height-1){
				if(matrice[y+1][x] == 0){
					int array[] = {x,y+1};
					if(!contains(visited, array) && !contains(stack, array)){
						stack.addFirst(array);
						visited.add(array);
					}
				}
				else{
					this.batiments.get(matrice[y+1][x] - 1).setLinked(true);
				}
			}
		}

		
	}
	
	public ArrayDeque<int[]> initStack()
	{
		Batiment CityHall = null;
		
		for(Batiment b : batiments) {
			if(b.getNumero() == 1) {
				CityHall = b;
				break;
			}
		}
				
		if(!CityHall.isPlaced())
		{
			return null;
		}
		
		ArrayDeque<int[]> stack = new ArrayDeque<>();
		
		if(CityHall.getX() > 0)
		{
			for(int i = CityHall.getY(); i < CityHall.endY(); i++)
			{
				int array[] = {CityHall.getX()-1,i};
				stack.add(array);
			}
		}
		if(CityHall.getY() > 0)
		{
			for(int i = CityHall.getX(); i < CityHall.endX(); i++)
			{
				int array[] = {i, CityHall.getY()-1};
				stack.add(array);
			}
		}
		if(CityHall.endX() < this.width)
		{
			for(int i = CityHall.getY(); i < CityHall.endY(); i++)
			{
				int array[] = {CityHall.endX(),i};
				stack.add(array);
			}
		}
		if(CityHall.endY() < this.height)
		{
			for(int i = CityHall.getX(); i < CityHall.endX(); i++)
			{
				int array[] = {i, CityHall.endY()};
				stack.add(array);
			}
		}
		
		return stack;
	}
	
	public void glouton(boolean debug, String sortType) {

		if(sortType.equals("air")) {
			sortByAir();
		}
		else if(sortType.equals("congestion")) {
			sortByCongestion();
		}
		else if(sortType.equals("random")) {
			sortRandom();
		}
		
		if(debug) {
			System.out.println("boucle de " + matrice.length + " * " + matrice[0].length);
		}
		
		//on iter sur toutes les cases
		for(int x = 0; x < matrice.length; x++) {
			for(int y = 0; y < matrice[x].length; y++) {
				//on check si la case est libre
				if(debug)
					System.out.println("case en " + x + ", " + y);
				if(matrice[y][x] == 0) {
					if(debug) {
						System.out.println("case en " + x + ", " + y + " == 0");
					}
					//cette case est libre on tente de placer les batiments
					Batiment picked = null;
					for(Batiment b : batiments) {
						if(!b.isPlaced()) {
							if(debug) {
								System.out.println("bat : " + b.toString());
							}
							placeBat(b, x, y);
							if(b.isPlaced()) {
								if(debug) {
									System.out.println("bat : " + b.toString() + " has been placed");
								}
								picked = b;
							}
						
							updateMap();
							links();
							
							boolean allLink = true;
							for(Batiment b2 : batiments) {
								if(b2.isPlaced())
									allLink &= b2.isLinked();
							}
							
							//ici on a cassé des links donc on enleve le batiment picked
							if(picked != null && !allLink) {
								removeBat(picked);
								updateMap();
								links();
							}								
							updateMap();
						}
					}
				}
			}
		}
	}

	private void sortByAir() {
		ArrayList<Batiment> tmp = new ArrayList<>();
		
		while(!batiments.isEmpty()) {
			Batiment chosen = null;
			double airMax = Double.NEGATIVE_INFINITY;
			for(Batiment b : batiments) {
				if(b.getArea() > airMax) {
					airMax = b.getArea();
					chosen = b;
				}
			}
			batiments.remove(chosen);
			tmp.add(chosen);
		}
		batiments = (ArrayList<Batiment>) tmp.clone();
	}
	
	private void sortByCongestion() {
		ArrayList<Batiment> tmp = new ArrayList<>();
		
		while(!batiments.isEmpty()) {
			Batiment chosen = null;
			double Congestion = Double.NEGATIVE_INFINITY;
			for(Batiment b : batiments) {
				if(b.getWidth() + b.getHeight() > Congestion) {
					Congestion = b.getWidth() + b.getHeight();
					chosen = b;
				}
			}
			batiments.remove(chosen);
			tmp.add(chosen);
		}
		batiments = (ArrayList<Batiment>) tmp.clone();
	}
	
	private void sortRandom() {
		Collections.shuffle(batiments);
	}

	private void removeBat(Batiment picked) {
		picked.setLinked(false);
		picked.setPlaced(false);
		
		for(int a = picked.getY(); a < picked.endY(); a++) {
			for(int b = picked.getX(); b <picked.endX(); b++) {
				matrice[a][b] = 0;
			}
		}		
		
	}

	// Utilities, used to show content of stack
	public void showStack(ArrayDeque<int[]> stack)
	{
		for(int[] i: stack)
		{
			System.out.println(i[0]+";"+i[1]);
		}
		
		System.out.println("-------------");
	}

		
		//---------------------GETTER AND SETTER---------------------//
		
		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int[][] getMatrice() {
			return matrice;
		}

		public void setMatrice(int[][] matrice) {
			this.matrice = matrice;
		}
		
		public ArrayList<Batiment> getBatiments(){
			return batiments;
		}
		
		public void SetBatiments(ArrayList<Batiment> batiments){
			this.batiments = batiments;
		}
		
		public boolean contains(ArrayDeque<int[]> visited ,int[] array) {
			
			for (int[] item: visited) {
	            if(item[0] == array[0] && item[1] == array[1]){
	            	return true;
	            }
	        }
			return false;
		}
	
}
