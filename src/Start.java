import java.io.FileNotFoundException;
import java.io.IOException;

import controler.City;
import view.ViewCtity;

public class Start {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Gotham !");
		City c = new City();
		
		ViewCtity view = new ViewCtity(c);
	}
}
