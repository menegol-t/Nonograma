package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class vistaGanar extends JPanel 
{

    private Frame controller;
    private int[][] tableroUsuario;
    private int tamanio;

    public vistaGanar(Frame frame) 
    {
        this.controller = frame;
        this.tamanio = controller.getTamanioJuego();
        this.tableroUsuario = controller.conseguirTableroUsuario();

        // Configuración del panel principal
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(40, 40, 40, 40));
        setBackground(new Color(25, 25, 25));

        generarContenido();
        
        int[][] tableroCorrecto = controller.conseguirTableroRespuesta();
        
        imprimirMatriz(tableroUsuario);
        imprimirMatriz(tableroCorrecto);
    }

    private void generarContenido() 
    {
        JLabel titulo = new JLabel("¡Ganaste!", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 48));
        titulo.setForeground(new Color(50, 255, 50));
        add(titulo, BorderLayout.NORTH);

        JPanel panelUsuario = crearPanelTablero("Tu Respuesta", tableroUsuario);
        add(panelUsuario, BorderLayout.CENTER);

        JButton botonMenu = new JButton("Volver al Menú Principal");
        botonMenu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonMenu.addActionListener(e -> controller.mostrarVista("menu"));

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(25, 25, 25));
        panelBoton.add(botonMenu);

        add(panelBoton, BorderLayout.SOUTH);
    }

    private JPanel crearPanelTablero(String titulo, int[][] tablero) 
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(40, 40, 40));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, BorderLayout.NORTH);

        JPanel tableroGrid = new JPanel(new GridLayout(tamanio, tamanio, 2, 2));
        tableroGrid.setBackground(new Color(40, 40, 40));
        tableroGrid.setPreferredSize(new Dimension(300, 300)); 

        for (int i = 0; i < tamanio; i++) 
        {
            for (int j = 0; j < tamanio; j++) 
            {
                JPanel celda = new JPanel();
                celda.setPreferredSize(new Dimension(30, 30));

                if (tablero[i][j] == 1) {
                    celda.setBackground(Color.BLACK);
                } else if (tablero[i][j] == 2) {
                    celda.setBackground(Color.LIGHT_GRAY);
                    JLabel xLabel = new JLabel("X");
                    xLabel.setForeground(Color.RED);
                    xLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
                    celda.add(xLabel);
                } 
                else 
                {
                    celda.setBackground(Color.WHITE);
                }
                tableroGrid.add(celda);
            }
        }

        panel.add(tableroGrid, BorderLayout.CENTER);
        return panel;
    }
    
    private static void imprimirMatriz(int[][] mat) 
    {	
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}