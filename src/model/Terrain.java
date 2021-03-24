package model;

import java.util.ArrayList;

public class Terrain {
	int height, witdh;
	int [][] matrice;
	ArrayList<Batiment> batiments;
	
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
}
