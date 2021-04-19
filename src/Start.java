import java.io.FileNotFoundException;
import java.io.IOException;

import controler.City;
import view.ViewCtity;

public class Start {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Gotham !");
		String filename = "instance05.dat";
		City c = new City(filename);
		//random || congestion || air
		//pour avoir tous les prints il faut modifier le boolean dans glouton
		c.glouton("none");
		//c.BestGlouton("none");
		ViewCtity view = new ViewCtity(c, filename);
	}
}
