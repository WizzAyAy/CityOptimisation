package model;

import java.util.ArrayList;

public class Terrain {
	int height, witdh;
	ArrayList<Batiment> batiments;
	
	public Terrain(int height, int witdh) {
	
		this.height = height;
		this.witdh = witdh;
		this.batiments = new ArrayList<Batiment>();
	}

}
