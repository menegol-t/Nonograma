package main;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatDarkLaf;

import interfaz.Frame;
import logica.Juego;

public class Main 
{

	public static void main(String[] args) 
	{
		Juego juego = new Juego();
		
		
		SwingUtilities.invokeLater(() -> 
		{
            try {
                
                FlatDarkLaf.setup();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Frame nonogramFrame = new Frame(juego);
            nonogramFrame.setVisible(true);
            
            
        });
		
		
		
	}

}
