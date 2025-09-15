package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.FlatDarkLaf;

public class MenuTest {

	private JFrame marcoPrincipal;
	
	private JLabel titulo;
	
	private JComboBox<String> listaDeModos;
	
	private JButton botonComoJugar;
	private JButton botonDeJugar;
	
	private JPanel panelDeBotones;
	private JPanel panelListaDeModos;
	
	String[] modosDeJuego = {"5x5", "10x10", "15x15"};
	
	private static final String TITULO_APP = "Nonograma - Programación III";
    private static final Font FUENTE_TITULO = new Font("SansSerif", Font.BOLD, 24);
    private static final Font FUENTE_BOTONES = new Font("SansSerif", Font.BOLD, 16);
    private static final Font FUENTE_OPCIONES= new Font("SansSerif", Font.BOLD, 16);
	
    private int largoSeleccionado;
    
	public MenuTest()
	{
		configurarComponentes();
		agregarAcciones();
	}

	
 	private void configurarComponentes() {
		marcoPrincipal = new JFrame();
		
		configurarMarcoPrincipal();
		
		configurarTitulo();
		
		panelDeBotones = new JPanel();
		
		configurarBotonComoJugar();
		
		configurarBotonDeJugar();
		
        marcoPrincipal.add(panelDeBotones, BorderLayout.SOUTH);
		
		configurarListaDeModos();
	}

	private void configurarBotonDeJugar() {
		
		botonDeJugar = new JButton("Jugar");
		botonDeJugar.setFont(FUENTE_BOTONES);
        botonDeJugar.setPreferredSize(new Dimension(150, 50));
//      botonDeJugar.setBackground(new Color(255, 215, 0));
        botonDeJugar.setFocusPainted(false);
	
        panelDeBotones.add(botonDeJugar);
        
	}

	private void configurarListaDeModos() 
	{
		GridBagConstraints diseñoListaDeModos = new GridBagConstraints();
		
		panelListaDeModos = new JPanel(new GridBagLayout());
		
		
		JLabel etiquetaDeListaDeModos = new JLabel("Elige el modo de juego: ");
		
		etiquetaDeListaDeModos.setFont(FUENTE_OPCIONES);	
		
		
		diseñoListaDeModos.gridx = 0; diseñoListaDeModos.gridy = 0;
		
		diseñoListaDeModos.anchor = GridBagConstraints.LINE_END;
		
		panelListaDeModos.add(etiquetaDeListaDeModos,diseñoListaDeModos);
		
		
		listaDeModos = new JComboBox<>(modosDeJuego);
		
		listaDeModos.setPreferredSize(new Dimension(100, 40));
		
		listaDeModos.setFont(FUENTE_OPCIONES);
		
		
		diseñoListaDeModos.gridx = 1; diseñoListaDeModos.gridy = 0;
		
		diseñoListaDeModos.anchor = GridBagConstraints.LINE_START;
		
		panelListaDeModos.add(listaDeModos, diseñoListaDeModos);
		
		
		marcoPrincipal.add(panelListaDeModos, BorderLayout.CENTER);
		
	}

	private void configurarBotonComoJugar() 
	{
		
		botonComoJugar = new JButton("¿Cómo jugar?");
	    botonComoJugar.setFont(FUENTE_BOTONES);
        botonComoJugar.setPreferredSize(new Dimension(150, 50));
//      botonComoJugar.setBackground(new Color(255, 215, 0));
        botonComoJugar.setFocusPainted(false);
        
        panelDeBotones.add(botonComoJugar);
	}

	private void configurarTitulo() {
		titulo = new JLabel("Nonograma - Programacion III", SwingConstants.CENTER);
		titulo.setFont(FUENTE_TITULO);
		marcoPrincipal.add(titulo, BorderLayout.NORTH);
	}

	private void configurarMarcoPrincipal() {
		marcoPrincipal.setTitle(TITULO_APP);
		marcoPrincipal.setSize(600, 400);
		marcoPrincipal.setLocationRelativeTo(null);
		marcoPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marcoPrincipal.setResizable(false);
	}

	public static void generarVentana()
	{
		
		EventQueue.invokeLater(() -> 
		{
            try {
            	
                FlatDarkLaf.setup();
            } catch (Exception e) {
                e.printStackTrace();
            }

            MenuTest window = new MenuTest();
            window.marcoPrincipal.setVisible(true);
        });
	
	}

	private void agregarAcciones()
	{
		agregarAccionListaDeModos();
//		
//		agregarAccionBotonJugar();
//		
//		agregarAccionBotonComoJugar();
		
		
	}
	
	private void agregarAccionListaDeModos()
	{
		listaDeModos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evento)
			{
				JComboBox<String> menuDesplegable = (JComboBox<String>) evento.getSource();
				
				String opcionSeleccionada = (String) menuDesplegable.getSelectedItem();
				
				definirLargoTableroElegido(opcionSeleccionada);
				
				System.out.println(opcionSeleccionada);
			}
		 });
	}
	
	private void definirLargoTableroElegido(String opcionSeleccionada) 
	{
		if(opcionSeleccionada.equals("5x5")) largoSeleccionado = 5;
		
		else if(opcionSeleccionada.equals("10x10")) largoSeleccionado = 10;
		
		else if(opcionSeleccionada.equals("15x15")) largoSeleccionado = 15;
	}
	
	
//	private void agregarAccionBotonComoJugar()
	
	
	public int getLargoSeleccionado()
	{
		return largoSeleccionado;
	}
}		
	


