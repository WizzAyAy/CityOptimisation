package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

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

	public boolean isBatInside(Batiment b) {
		if(b.getX() <= 0 && b.getY() <= 0 && b.endX() <= this.width && b.endY() <= this.height)
			return true;
		else return false;
	}
	
	public boolean placeBats(Batiment b, int x, int y) {
		boolean placed = true;
		b.setX(x);
		b.setY(y);
		placed = placed && isBatInside(b);
		
		for(int i = 0; i < batiments.size(); i++) {
			if(b.getNumero() != batiments.get(i).getNumero()) {
				placed = placed && b.isSuperimposed(batiments.get(i));
			}
		}
		b.setPlaced(placed);
		return placed;
	}
	
	public void placeHDV() {
		placeBats(batiments.get(0), 0, 0);
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
		public void links()
		{
			ArrayDeque<int[]> stack = initStack();
			// To remove
			//showStack(stack);
			
			while(!stack.isEmpty())
			{
				int[] currentPos = stack.removeFirst();
				
				
			}
		}
		
		public ArrayDeque<int[]> initStack()
		{
			Batiment CityHall = batiments.get(0);
			
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
					int array[] = {CityHall.getY()-1,i};
					stack.add(array);
				}
			}
			if(CityHall.endX() < this.width)
			{
				for(int i = CityHall.getY(); i < CityHall.endY(); i++)
				{
					int array[] = {CityHall.endX()+1,i};
					stack.add(array);
				}
			}
			if(CityHall.endY() < this.height)
			{
				for(int i = CityHall.getX(); i < CityHall.endX(); i++)
				{
					int array[] = {CityHall.endY()+1,i};
					stack.add(array);
				}
			}
			
			return stack;
		}
		
		// Utilities, used to show content of stack
		public void showStack(ArrayDeque<int[]> stack)
		{
			for(int[] i: stack)
			{
				System.out.println(i[1]+";"+i[2]);
			}
		}
}
