package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controler.City;
import model.Terrain;

public class ViewCtity extends JFrame {

	public City city;
	private ArrayList<Color> colors = new ArrayList<>();
	
	public ViewCtity(City city, String filename) {
	super("This is Gotham " + filename);
	generColor();
	this.city = city;
	setLocationRelativeTo(null);

	WindowListener l = new WindowAdapter() {
		public void windowClosing(WindowEvent e){
            System.exit(0);
         }
      };

      addWindowListener(l);
      Terrain t = city.getTerrain();
      setSize(t.getWidth() * 40 + 100 ,t.getHeight() * 40 + 100);
      setVisible(true);
      }
	
	public void paint(Graphics g) {
        g.setColor(Color.black);
        int [][] matrice = city.getTerrain().getMatrice();
        for(int i = 0; i < matrice.length; i++) {
			for(int j = 0; j < matrice[i].length; j++) {
				if(matrice[i][j] == 0) {
					g.drawRect(50 + 40 * j ,50 + 40 * i,40,40);
					
				}
				else {
					g.setColor(setColorBat(matrice[i][j]));
					g.fillRect(50 + 40 * j ,50 + 40 * i,40,40);
					g.setColor(Color.black);
					g.drawRect(50 + 40 * j ,50 + 40 * i,40,40);
					
				}
			}
		}
    }
	
	public void generColor() {
		for(int i = 0; i < 1000; i++) {
			
			int r = (int) (Math.random() * 255);
			int g = (int) (Math.random() * 255);
			int b = (int) (Math.random() * 255);
			
			colors.add(new Color(r,g,b));
		}
	}
	
	public Color setColorBat(int numero) {
		if(numero == 1) {
			return new Color(63, 135, 245);
		}
		return colors.get(numero);
		
	}
}
