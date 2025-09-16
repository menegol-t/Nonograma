package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class vistaJugar extends JPanel
{
    private Frame controller;

    private JPanel panelTablero;
    private JPanel panelReferenciasHorizontales;
    private JPanel panelReferenciasVerticales;
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

//      configurarTitulo();

        configurarBotonesDeOpciones();

        generarCasillas();

        mostrarReferencias();
    }

    private JButton generarCasilla() 
    {
        JButton boton = new JButton("");
        boton.setBorder(null);
        boton.setFont(FUENTE_CASILLAS);  
        boton.setFocusable(false);
        boton.setBackground(Color.white);
        boton.setPreferredSize(new Dimension(30, 30));
        boton.setMaximumSize(new Dimension(30, 30));
        boton.setMinimumSize(new Dimension(30, 30));
        boton.setFocusPainted(false);
        
        return boton;
    }

    private void agregarAccionACasilla(JButton boton, int fila, int columna) 
    {

        boton.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {

                if (e.getButton() == MouseEvent.BUTTON1) 
                {
                    boton.setText("");
                    boton.setBackground(Color.BLACK);
                    controller.cambiarEstadoCasilla(fila, columna, CASILLA_NEGRA);

                    if (e.getClickCount() > 1) 
                    {
                        controller.cambiarEstadoCasilla(fila, columna, CASILLA_BLANCA);
                        boton.setBackground(Color.WHITE);
                        boton.setText("");
                    }

                } 
                else if (e.getButton() == MouseEvent.BUTTON3) 
                {
                    boton.setBackground(new Color(192, 192, 192));
                    boton.setForeground(Color.RED);
                    boton.setText("X");
                    controller.cambiarEstadoCasilla(fila, columna, CASILLA_MARCADA);

                    if (e.getClickCount() > 1) 
                    {
                        controller.cambiarEstadoCasilla(fila, columna, CASILLA_BLANCA);
                        boton.setBackground(Color.WHITE);
                        boton.setText("");
                    }
                }

                panelTablero.repaint();

                System.out.println("Fila: " + fila);
                System.out.println("Columna: " + columna);
                System.out.println();
            }
        });
    }

    private void generarCasillas()
    {    
        panelTablero.removeAll();

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
    	panelOpciones = new JPanel(new GridLayout(10, 1, 5, 5));
        panelOpciones.setBorder(new EmptyBorder(10, 10, 10, 11));
        panelOpciones.setBackground(new Color(200, 100, 50, 30));
        
        JPanel panelJuego = new JPanel(new GridBagLayout());
        panelJuego.setBackground(new Color(200, 100, 50, 30));

        add(panelJuego, BorderLayout.CENTER);
        add(panelOpciones, BorderLayout.EAST);
        
        configurarPanelJuego(panelJuego);
    }

    private void configurarPanelJuego(JPanel panelJuego) {
        panelTablero = new JPanel(new GridLayout(tamanioTablero, tamanioTablero));
        panelTablero.setBackground(Color.LIGHT_GRAY);

        panelReferenciasHorizontales = new JPanel(new GridLayout(tamanioTablero, 1, 2, 2));
        panelReferenciasHorizontales.setBackground(new Color(31, 43, 52, 200));

        panelReferenciasVerticales = new JPanel(new GridLayout(1, tamanioTablero, 2, 2));
        panelReferenciasVerticales.setBackground(new Color(31, 43, 52, 200));

        JPanel panelVacio = new JPanel();
        panelVacio.setBackground(new Color(31, 43, 52, 200));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelJuego.add(panelVacio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelJuego.add(panelReferenciasVerticales, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelJuego.add(panelReferenciasHorizontales, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelJuego.add(panelTablero, gbc);
    }
    
    private void mostrarReferencias() {
        // horizontales
        panelReferenciasHorizontales.setLayout(new GridLayout(tamanioTablero, 1, 2, 2));
        panelReferenciasHorizontales.setBackground(colorFondo2);

        for (int i = 0; i < tamanioTablero; i++) {
            JPanel fila = new JPanel(new GridLayout(1, referenciasFilas[i].length, 2, 2));
            fila.setBackground(colorFondo2);
            for (int valor : referenciasFilas[i]) {
                JLabel lbl = new JLabel(valor == 0 ? "" : String.valueOf(valor), SwingConstants.CENTER);
                lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
                lbl.setForeground(Color.WHITE);
                fila.add(lbl);
            }
            panelReferenciasHorizontales.add(fila);
        }

        // verticales
        panelReferenciasVerticales.setLayout(new GridLayout(1, tamanioTablero, 2, 2));
        panelReferenciasVerticales.setBackground(colorFondo2);

        for (int c = 0; c < tamanioTablero; c++) {
            JPanel col = new JPanel(new GridLayout(referenciasColumnas[c].length, 1, 2, 2));
            col.setBackground(colorFondo2);
            for (int valor : referenciasColumnas[c]) {
                JLabel lbl = new JLabel(valor == 0 ? "" : String.valueOf(valor), SwingConstants.CENTER);
                lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
                lbl.setForeground(Color.WHITE);
                col.add(lbl);
            }
            panelReferenciasVerticales.add(col);
        }
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

        for (int i = 0; i < tamanioTablero; i++) {
            for (int j = 0; j < tamanioTablero; j++) {
                JButton celda = casillas[i][j];
                if (celda == null) continue;
                celda.setText("");
                celda.setBackground(Color.WHITE);
                celda.setForeground(UIManager.getColor("Button.foreground"));
                controller.cambiarEstadoCasilla(i, j, CASILLA_BLANCA);
            }
        }
        revalidate();
        repaint();
    }

    private void agregarAccionBotonesDelOpciones() 
    {
        botonDePista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(botonDePista, "Toma una pista 🧩");
            }
        });

        botonLimpiarTablero.addActionListener(e -> limpiarTablero());

        botonDeVerificarResultado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            	
            	if(controller.verificarResultado())
            	{
            		controller.crearVistaGanaste();
            		controller.mostrarVista("ganaste");
            		
            	}
            	
            	else
            	{
            		controller.crearVistaPerdiste();
            		controller.mostrarVista("perdiste");
            	}
            }
            
            
        });
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
    
    private void configurarBotonDePista() {
        botonDePista = new JButton("Dame Una Pista");
        botonDePista.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelOpciones.add(botonDePista);
        botonDePista.setFont(FUENTE_BOTONES);
    }

//    private void configurarTitulo() 
//    {
//        titulo = new JLabel("NONOGRAMA");
//        titulo.setFont(FUENTE_TITULO);
//        titulo.setHorizontalAlignment(SwingConstants.CENTER);
//        add(titulo, BorderLayout.NORTH);
//    }
}
