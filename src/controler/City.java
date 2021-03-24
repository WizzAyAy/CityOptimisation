package controler;

import model.Batiment;
import model.Terrain;

public class City {
	
	private Terrain terrain;

	public void generCityRandom(int xMax, int yMax, int nbBat) {
		//classe qui genere une city random;
		
		int xTerrain = (int) (Math.random() * xMax) + 5;
		int yTerrain = (int) (Math.random() * yMax) + 5;
		
		terrain = new Terrain(xTerrain, yTerrain);
		
		
		
		for(int i = 1; i <= nbBat; i++) {
			terrain.addBat(new Batiment(xMax / 3 + 2, yMax / 3 + 2, i));
		}
		
		terrain.placeHDV();
		terrain.updateMap();
		printCity();
		
		terrain.links();
			

	}
	
	public void generCityFile(/*file du prof*/) {
		//classe qui genere une city random;
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
}
