import java.io.FileNotFoundException;
import java.io.IOException;

import controler.City;
import view.ViewCtity;

public class Start {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Gotham !");
		String filename = "instance02.dat";
		City c = new City(filename);
		//random || congestion || air
		c.glouton("air");
		ViewCtity view = new ViewCtity(c, filename);
	}
}
