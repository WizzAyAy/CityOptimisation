package controler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Batiment;
import model.Terrain;

public class City {
	
	private Terrain terrain;
	
	public City(String filename){
		
		try {
			generCityFile("./src/instances/"+filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generCityRandom(int xMax, int yMax, int nbBat) throws FileNotFoundException {
		//classe qui genere une city random;
		
		int xTerrain = (int) (Math.random() * xMax) + 5;
		int yTerrain = (int) (Math.random() * yMax) + 5;
		
		terrain = new Terrain(xTerrain, yTerrain);
		
		for(int i = 1; i <= nbBat; i++) {
			terrain.addBat(new Batiment(xMax / 3 + 2, yMax / 3 + 2, i));
		}
		
		terrain.placeHDV();
		
	}
	
	public void generCityFile(String fileName) throws FileNotFoundException {
		FileInputStream file = new FileInputStream(fileName);   
		Scanner scanner = new Scanner(file);  
		ArrayList<Batiment> tmpBats = new ArrayList<>();
		int compteur = 0;
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			
			String [] itemsLine = line.split(" ");
			if(compteur == 0) {
				//hdv
				tmpBats.add(new Batiment(0,0,Integer.parseInt(itemsLine[0]), Integer.parseInt(itemsLine[1]), compteur + 1));
				
			}
			else if (compteur == 1) {
				//taille
				terrain = new Terrain(Integer.parseInt(itemsLine[0]), Integer.parseInt(itemsLine[0]));
			}
			else {
				//les autres batiments
				tmpBats.add(new Batiment(0,0,Integer.parseInt(itemsLine[0]), Integer.parseInt(itemsLine[1]), compteur));
			}
			compteur++;
			
		
		}
		for(Batiment b : tmpBats)
			terrain.addBat(b);
		
		
		scanner.close();    
	}

	public void printCity() {
		System.out.println("Affichage de la ville : ");
		terrain.printTerrain();	
		System.out.println("");
		
		System.out.println("Affichage de la liste des bats: ");
		terrain.printAllBat();
		System.out.println("");
		
		System.out.println("Score :");
		System.out.println(terrain.score() + " / " + terrain.maxScore());
		System.out.println("");
		
	}
	
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public void glouton(String sortType) {
		terrain.placeHDV();		
		terrain.glouton(false, sortType);
		terrain.updateMap();
		printCity();
	}
	
	public void BestGlouton(String sortType) {
		int  XMax = terrain.getWidth();
		int  YMax = terrain.getHeight();
		
		/*optimisation des cases pour lequelles on regarde l'emplacement de depart du cityHall mais ca marche pas :/
		 * 
		 * Batiment cityHall = null;
		
		for(Batiment b : terrain.getBatiments()) {
			if(b.getNumero() == 1)
				cityHall = b;
		}
		
		int endX = cityHall.endX() + 1;
		int endY = cityHall.endY() + 1;
		*/
		
		int bestX, bestY;
		bestX = bestY = 0;
		int bestScore = -1;

		//on a besoin de check que le coin en haut a gauche les autres solutions sont juste un mirroir 
		for(int x = 0; x < XMax ; x++) {
			for(int y = 0; y < YMax ; y++) {
				terrain.reset();
				if(terrain.placeHDV(x, y)) {
					terrain.glouton(false,sortType);
					if(terrain.score() > bestScore) {
						bestScore = terrain.score();
						bestX = x;
						bestY = y;
						
					}
				}
			}	
		}
		
		
		terrain.placeHDV(bestX, bestY);
		terrain.glouton(false,sortType);
		terrain.updateMap();
		printCity();
	}
}
