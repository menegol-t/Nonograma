package main;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatDarkLaf;

import interfaz.Frame;
import logica.Juego;

public class Main {

	public static void main(String[] args) 
	{
		Juego juego = new Juego();
		
		
		SwingUtilities.invokeLater(() -> 
		{
            try {
                // Set the Look and Feel once, at the very beginning
                FlatDarkLaf.setup();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Create and show your Frame after the L&F is set up
            Frame nonogramFrame = new Frame(juego);
            nonogramFrame.setVisible(true);
            
            
        });
		
		
		
	}

}
