package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf; 

public class vistaJugar extends JPanel
{

	private Frame controller;
	
	private int ladoTablero = 0;
	
	private JPanel panelTablero;
	private JPanel panelOpciones;
	
	private JLabel titulo;
	
	private static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
	private static final Font FUENTE_BOTONES = new  Font("Segoe UI", Font.BOLD, 13);
	private static final Font FUENTE_CASILLAS = new Font("Dialog", Font.BOLD, 40);
	
	private static final int CASILLA_BLANCA = 0;
	private static final int CASILLA_NEGRA = 1;
	private static final int CASILLA_MARCADA = 2;
	
	
	private int tamanioTablero;
	
	private int[][] referenciasFilas;
	private int[][] referenciasColumnas; 
	
	private JButton botonDePista;
	private JButton botonLimpiarTablero;
	private JButton botonDeVerificarResultado;
	
	private JButton[][] casillas; 
	
	Color colorFondo2 = new Color(31, 43, 52, 200);
	
	public vistaJugar(Frame frame)
	{
		this.setLayout(new BorderLayout());		
		
		controller = frame;
		
		tamanioTablero = controller.getTamanioJuego();
		
		referenciasFilas = controller.conseguirReferenciasFila();
		referenciasColumnas = controller.conseguirReferenciasCol();
		
		generarPaneles();
		
		configurarTitulo();
		
		configurarBotonesDeOpciones();
		
		configurarPanelTablero();
		
		generarCasillas();
		
		
	}
	

	private JButton generarCasilla() 
	{
		JButton boton = new JButton("");
		boton.setBorder(null);
		boton.setFont(FUENTE_CASILLAS);  
		boton.setFocusable(false);
		boton.setBackground(Color.white);
		
		
		return boton;
	}

	private void agregarAccionACasilla(JButton boton, int fila, int columna)
	{
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) 
			{
				
				int estadoActual = controller.GetEstadoCasilla(fila, columna);
				
				if (SwingUtilities.isLeftMouseButton(evento)) 
				{
	                if (estadoActual != CASILLA_NEGRA) {
	                    controller.cambiarEstadoCasilla(fila, columna, CASILLA_NEGRA);
	                    boton.setText("");
	                    boton.setBackground(Color.BLACK);
	                } else {
	                    controller.cambiarEstadoCasilla(fila, columna, CASILLA_BLANCA);
	                    boton.setText("");
	                    boton.setBackground(Color.WHITE);
	                }
	            } 
	            else if (SwingUtilities.isRightMouseButton(evento)) 
	            {
	                if (estadoActual != CASILLA_MARCADA) {
	                    controller.cambiarEstadoCasilla(fila, columna, CASILLA_MARCADA);
	                    boton.setText("X");
	                    boton.setForeground(Color.RED);
	                    boton.setBackground(new Color(192, 192, 192));
	                } else {
	                    controller.cambiarEstadoCasilla(fila, columna, CASILLA_BLANCA);
	                    boton.setText("");
	                    boton.setBackground(Color.WHITE);
	                    boton.setForeground(UIManager.getColor("Button.foreground"));
	                }
	            }
				
				System.out.println("Fila: " + fila);
				System.out.println("Columna: " + columna);
				System.out.println();
	        }
	    });
				
	}
	
	
	private void generarCasillas()
	{	
		panelTablero.removeAll();
		
		System.out.println(tamanioTablero);
		
		casillas = new JButton[tamanioTablero][tamanioTablero];
		
		
		for(int i = 0; i<tamanioTablero; i++)
		{
			for(int j = 0; j<tamanioTablero; j++)
			{	
				JButton casilla = generarCasilla();
			
				agregarAccionACasilla(casilla, i, j);
				
				panelTablero.add(casilla);
				casillas[i][j] = casilla;

			}
		}
		
		panelTablero.revalidate();
		panelTablero.repaint();
	}

	private void generarPaneles()
	{
		panelOpciones = new JPanel();
		panelTablero = new JPanel();
		
		panelTablero.setLayout(new GridLayout(tamanioTablero, tamanioTablero));
		panelTablero.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panelOpciones.setLayout(new GridLayout(10, 1, 5, 5));
        panelOpciones.setBorder(new EmptyBorder(10, 10, 10, 11));
        panelOpciones.setBackground(new Color(200, 100, 50, 30));
		
		add(panelTablero, BorderLayout.CENTER);
		add(panelOpciones, BorderLayout.EAST);
	}
	
	
	private void configurarPanelTablero() 
	{
		panelTablero.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	

	private void configurarBotonesDeOpciones() 
	{
		
		configurarBotonDePista();
		
		configurarBotonLimpiarTablero();
	
		configurarBotonVerificarResultado();
		
		agregarAccionBotonesDelOpciones();
	}

	private void configurarBotonVerificarResultado() 
	{
		botonDeVerificarResultado = new JButton("Verificar Resultado");
		
		botonDeVerificarResultado.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		panelOpciones.add(botonDeVerificarResultado);
	}

	private void configurarBotonLimpiarTablero() 
	{
		botonLimpiarTablero = new JButton("Limpiar tablero");
		botonLimpiarTablero.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelOpciones.add(botonLimpiarTablero);
		botonLimpiarTablero.setFont(FUENTE_BOTONES);
	}
	
	private void limpiarTablero() 
	{
	    
		if (casillas == null) return;

	    Color base = new Color(192, 192, 192);
	    
	    for (int i = 0; i < tamanioTablero; i++) 
	    {
	        for (int j = 0; j < tamanioTablero; j++) {
	            
	        	JButton celdaActual = casillas[i][j];
	            
	        	if (celdaActual == null) continue;
	            celdaActual.setText("");
	            celdaActual.setBackground(base);
	            celdaActual.setForeground(UIManager.getColor("Button.foreground"));
	            
	            controller.cambiarEstadoCasilla(i, j, CASILLA_BLANCA);
	        }
	    }
	    revalidate();
	    repaint();
	}

	private void agregarAccionBotonesDelOpciones() 
	{
		botonDePista.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				//Agregar Pista de verdad
				JOptionPane.showMessageDialog(botonDePista, "" + "Toma una pista 🧩");
			}
		});
		
		botonLimpiarTablero.addActionListener(e -> 
		{ 
	        limpiarTablero();
	    });
		
		botonDeVerificarResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//controller.crearVistaGanaste();
				controller.mostrarVista("ganaste");
			}
		});
	}

	private void configurarBotonDePista() {
		botonDePista = new JButton("Dame Una Pista");
		botonDePista.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelOpciones.add(botonDePista);
		
		botonDePista.setFont(FUENTE_BOTONES);
	}


	private void configurarTitulo() 
	{
		
		titulo = new JLabel("NONOGRAMA");
		titulo.setFont(FUENTE_TITULO);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
//		titulo.setBounds(10, 11, 651, 46);
		add(titulo, BorderLayout.NORTH);
	}
}
