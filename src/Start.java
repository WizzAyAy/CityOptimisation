import controler.City;
import view.ViewCtity;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Gotham !");
		City c = new City();
		c.generCityRandom(1, 1, (int) (Math.random() * 5) + 2);
		
		ViewCtity view = new ViewCtity(c);
	}
}
