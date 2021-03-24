package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

public class Terrain {
	int height, width;
	int [][] matrice;
	ArrayList<Batiment> batiments;
	
	public Terrain(int height, int witdh) {
	
		this.height = height;
		this.width = witdh;
		
		matrice = new int [witdh][height];
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
	
	public void printTerrain() {
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
	
	public void addBat(Batiment b) {
		batiments.add(b);
	}
	
	public void isLinked()
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
