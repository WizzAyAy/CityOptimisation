package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

import controler.City;
import model.Terrain;

public class ViewCtity extends JFrame {

	public City city;
	public ViewCtity(City city) {
	super("This is Gotham");
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
				if(matrice[i][j] == 0)
					g.drawRect(50 + 40 * j ,50 + 40 * i,40,40);
				else {
					g.setColor(Color.red);
					g.fillRect(50 + 40 * j ,50 + 40 * i,40,40);
					g.setColor(Color.black);
					g.drawRect(50 + 40 * j ,50 + 40 * i,40,40);
					
				}
			}
		}
        
        
    }
}
