package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

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
		if(b.getX() >= 0 && b.getY() >= 0 && b.endX() <= this.width && b.endY() <= this.height)
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
		placeBats(batiments.get(1), 3, 3);
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
		
		if(stack == null)
		{
			return;
		}
		
		ArrayDeque<int[]> visited = stack.clone();
		
		while(!stack.isEmpty())
		{
			int[] currentPos = stack.removeFirst();
			int x = currentPos[0];
			int y = currentPos[1];
			
			
			if(x>0)
			{
				if(matrice[y][x-1] == 0)
				{
					int array[] = {x-1,y};
					if(!contains(visited, array) && !contains(stack, array) )
					{
						stack.addFirst(array);
						visited.add(array);
						
					}
						
				}
				else
				{
					this.batiments.get(matrice[y][x-1]).setLinked(true);
				}
			}
			
			if(y>0)
			{
				if(matrice[y-1][x] == 0)
				{
					int array[] = {x,y-1};
					if(!contains(visited, array) && !contains(stack, array))
					{
						stack.addFirst(array);
						visited.add(array);
					}
				}
				else
				{
					this.batiments.get(matrice[y-1][x]).setLinked(true);
				}
			}
			
			if(x<width-1)
			{
				if(matrice[y][x+1] == 0)
				{
					int array[] = {x+1,y};
					if(!contains(visited, array) && !contains(stack, array))
					{
						stack.addFirst(array);
						visited.add(array);
					}
				}
				else
				{
					this.batiments.get(matrice[y][x+1]).setLinked(true);
				}
			}
			
			
			
			if(y<height-1)
			{
				if(matrice[y+1][x] == 0)
				{
					int array[] = {x,y+1};
					if(!contains(visited, array) && !contains(stack, array))
					{
						stack.addFirst(array);
						visited.add(array);
					}
				}
				else
				{
					this.batiments.get(matrice[y+1][x]).setLinked(true);
				}
			}
			
			
		}
		
		showStack(stack);
		showStack(visited);
	}
	
	public ArrayDeque<int[]> initStack()
	{
		Batiment CityHall = batiments.get(0);
		
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
		
		public boolean contains(ArrayDeque<int[]> visited ,int[] array) {
			
			for (int[] item: visited) {
	            if(item[0] == array[0] && item[1] == array[1])
	            {
	            	return true;
	            }
	        }
			return false;
		}
	
}
