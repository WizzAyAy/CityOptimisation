package model;

import java.util.ArrayList;

public class Terrain {
	int height, witdh;
	int [][] matrice;
	ArrayList<Batiment> batiments;
	
	//--------------------INIT & CONSTRUCTORS---------------------//
	
	public Terrain(int height, int witdh) {
	
		this.height = height;
		this.witdh = witdh;
		
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
	
	
	//---------------------PRINT---------------------//
	
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
	
	//---------------------BATs---------------------//
	
	public void addBat(Batiment b) {
		batiments.add(b);
	}
	
	public boolean isBatInside(Batiment b) {
		if(b.getX() <= 0 && b.getY() <= 0 && b.endX() <= this.witdh && b.endY() <= this.height)
			return true;
		else return false;
	}
	
	public boolean placeBats(Batiment b) {
		boolean placed = true;
		placed = placed && isBatInside(b);
		
		for(int i = 0; i < batiments.size(); i++) {
			if(b.getNumero() != batiments.get(i).getNumero()) {
				placed = placed && b.isSuperimposed(batiments.get(i));
			}
		}
		b.setPlaced(placed);
		return placed;
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
	
}
