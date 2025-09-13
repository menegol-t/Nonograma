package interfaz;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf; 


public class VistaJuego {

	private JFrame frame;
	private JPanel grilla;
	private int ladoTablero = 0;
	private JPanel panelControles_1;
	private JPanel panelControles;
	private int n = 5;          // tamaño actual del tablero
	private int[][] refFilas;   // referencias de filas (n x ((n+1)/2))
	private int[][] refCols;    // referencias de columnas (n x ((n+1)/2))
	private JButton[][] celdas;  // <— NUEVO
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try { FlatDarkLaf.setup(); } catch (Exception e) { e.printStackTrace(); } 
            try {
                VistaJuego window = new VistaJuego();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


	/**
	 * Create the application.
	 */
	public VistaJuego() {

	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Color colorFondo2 = new Color(31, 43, 52, 200);
		//int[][] pistasFila = { { 2, 0, 2 }, { 0, 4, 0 }, { 0, 0, 1 }, { 0, 0, 2 }, { 0, 0, 2 }, };
		iniciarJuegoDesdeLogica(5);          // pide datos a la “lógica” 

		frame = new JFrame("TP1 P3 UNGS - NONOGRAMA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(843, 602);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("NONOGRAMA");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 651, 46);
		frame.getContentPane().add(lblNewLabel);

		controles(colorFondo2);
		pistasVerticales(colorFondo2, refCols);
		pistasHorizontales(colorFondo2, refFilas);
		grilla(colorFondo2);
	}

	private void controles(Color colorFondo2) {
		panelControles = new JPanel();
		panelControles.setDoubleBuffered(false);
		panelControles.setBorder(new EmptyBorder(10, 10, 10, 11));
		panelControles.setBounds(30, 121, 213, 389);
		panelControles.setBackground(new Color(200, 100, 50, 30));
		panelControles.setLayout(new GridLayout(10, 1, 5, 5));
		frame.getContentPane().add(panelControles);

		elegirNivel(panelControles);
		separador(panelControles, 1);
		botonPista(panelControles);
		separador(panelControles, 2);
		separador(panelControles, 2);
        botonLimpiarTablero(panelControles); //  este es para limpiar tablero 

		botonVerificarResulatado(panelControles);
	}

	private void botonVerificarResulatado(JPanel panelControles) {
		JButton btnVerificarResultado = new JButton("Verificar Resultado");
		btnVerificarResultado.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelControles.add(btnVerificarResultado);
		btnVerificarResultado.setFont(new Font("Segoe UI", Font.BOLD, 13));

		btnVerificarResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(btnVerificarResultado,
						"Que mal, Perdiste /\n" + "Felicidades, ganaste!! \n\n" + 
								"█  █  █  █  █ \n" + // █ alt + 219
								"█  █  █  █  █ \n" + 
								"█  █  █  █  █ \n" + 
								"█  █  █  █  █ \n" + 
								"█  █  █  █  █ \n\n");
			}
		});
	}

	private void botonLimpiarTablero(JPanel panelControles) {
		JButton btnLimpiarTablero = new JButton("Limpiar tablero");
		btnLimpiarTablero.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelControles.add(btnLimpiarTablero);
		btnLimpiarTablero.setFont(new Font("Segoe UI", Font.BOLD, 13));

		btnLimpiarTablero.addActionListener(e -> { //cambie mouselistener por actionlistener
			
			// 1) Lógica (cuando esté lista):
	        // logica.Main.limpiarTableroUsuario();
			
			// 2) Vista:
	        limpiarTableroUI();
	        // (opcional) enviar mensaje
	        // JOptionPane.showMessageDialog(btnLimpiarTablero, "Tablero limpiado.");
	    });
	}
			/*
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(btnLimpiarTablero, "¿Te limpio el tablero compa?");
			}
		*/
	

	private void elegirNivel(JPanel panelControles) {
		
		// Uso LinkedHashMap para conservar el orden de inserción
		Map<String, Integer> opciones = new LinkedHashMap<>();
		opciones.put("5 x 5", 5);
		opciones.put("10 x 10", 10);
		opciones.put("15 x 15", 15);

		// Lista de opciones para el ComboBox -
		String[] listaOpciones = opciones.keySet().toArray(new String[0]);

		/***************************************************************************/

		JPanel panelNivel = new JPanel();
		panelNivel.setBackground(new Color(226, 228, 229));
		panelNivel.setBorder(null);
		panelNivel.setLayout(new GridLayout(1, 3, 5, 5));

		JLabel lblNivel = new JLabel("Elegir Nivel:   ");
		lblNivel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNivel.setBackground(new Color(226, 228, 229));
		lblNivel.setHorizontalAlignment(SwingConstants.RIGHT);
		panelNivel.add(lblNivel);
		panelControles.add(panelNivel);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBorder(null);
		comboBox.setFocusable(false);
		comboBox.setFont(new Font("Segoe UI", Font.BOLD, 13));
		comboBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panelNivel.add(comboBox);

		comboBox.setModel(new DefaultComboBoxModel<String>(listaOpciones));

		JButton IniciarJuego = new JButton("Iniciar");
		IniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String opcionSeleccionada = listaOpciones[comboBox.getSelectedIndex()];
				int tamañoTablero = opciones.get(opcionSeleccionada);
                iniciarJuegoDesdeLogica(tamañoTablero);
                reconstruirVista(); // rehace pistas + grilla
                limpiarTableroUI();

				JOptionPane.showMessageDialog(IniciarJuego,
						"Se va acrear un tablero de: " + tamañoTablero + " x " + tamañoTablero);
			}
		});

		IniciarJuego.setBorder(new EmptyBorder(0, 10, 0, 10));
		IniciarJuego.setFont(new Font("Segoe UI", Font.BOLD, 13));
		panelControles.add(IniciarJuego);
	};

	private void separador(JPanel panelControles, int cantidad) {
		for (int i = 1; i <= cantidad; i++) {
			JLabel lblSeparador = new JLabel();
			panelControles.add(lblSeparador);
		}
	}

	private void botonPista(JPanel panelControles) {
		JButton btnPista = new JButton("Dame Una Pista");
		btnPista.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelControles.add(btnPista);
		btnPista.setFont(new Font("Segoe UI", Font.BOLD, 13));

		btnPista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(btnPista, "" + "Toma una pista 🧩");
			}
		});
	}

	/**********************************************************************************/

	private void pistasHorizontales(Color colorFondo2, int[][] referenciasFila) {
	    JPanel panelHorizontales = new JPanel();
	    panelHorizontales.setBounds(271, 212, 80, 298);
	    panelHorizontales.setBackground(colorFondo2);
	    panelHorizontales.setLayout(new GridLayout(n, 1, 5, 5)); // n filas
	    panelHorizontales.setBorder(new EmptyBorder(5,5,5,5));
	    frame.getContentPane().add(panelHorizontales);

	    for (int i = 0; i < n; i++) {
	        int ancho = referenciasFila[i].length; // ((n+1)/2)
	        JPanel row = new JPanel(new GridLayout(1, ancho, 5, 5));
	        row.setBackground(new Color(0,255,0,80));
	        panelHorizontales.add(row);

	        for (int j = ancho - 1; j >= 0; j--) {
	            String txt = referenciasFila[i][j] == 0 ? "" : Integer.toString(referenciasFila[i][j]);
	            JLabel lbl = new JLabel(txt, SwingConstants.CENTER);
	            lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
	            lbl.setForeground(new Color(245,245,245));
	            row.add(lbl);
	        }
	    }
	}

	private void pistasVerticales(Color colorFondo2, int[][] referenciasCol) {
	    JPanel panelVerticales = new JPanel();
	    panelVerticales.setBounds(361, 121, 300, 80);
	    panelVerticales.setBackground(colorFondo2);
	    panelVerticales.setLayout(new GridLayout(1, n, 5, 5)); // n columnas
	    panelVerticales.setBorder(new EmptyBorder(5,5,5,5));
	    frame.getContentPane().add(panelVerticales);

	    for (int c = 0; c < n; c++) {
	        int alto = referenciasCol[c].length; // ((n+1)/2)
	        JPanel col = new JPanel(new GridLayout(alto, 1, 5, 5));
	        col.setBackground(new Color(0,255,0,80));
	        panelVerticales.add(col);

	        for (int j = alto - 1; j >= 0; j--) {
	            String txt = referenciasCol[c][j] == 0 ? "" : Integer.toString(referenciasCol[c][j]);
	            JLabel lbl = new JLabel(txt, SwingConstants.CENTER);
	            lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
	            lbl.setForeground(new Color(245,245,245));
	            col.add(lbl);
	        }
	    }
	}

	private void grilla(Color colorFondo2) {
		grilla = new JPanel();
		grilla.setBorder(new EmptyBorder(5, 5, 5, 5));
		grilla.setBounds(361, 212, 300, 300);
		
		 // gap dinámico (ver #2)
	    int gap = calcularGapSugerido(n);
	    grilla.setLayout(new GridLayout(n, n, gap, gap));

		
		grilla.setLayout(new GridLayout(n, n, 5, 5));
		grilla.setBackground(colorFondo2);
		frame.getContentPane().add(grilla);
	    celdas = new JButton[n][n]; // nuevo para limpiar


		for (int fila = 0; fila < n; fila++) {
			for (int columna = 0; columna < n; columna++) {
				botonGrilla(fila, columna);
			}
		}
		

	    // recalcular fuente al crear y al redimensionar
	    ajustarFuenteCeldas();
	    grilla.addComponentListener(new ComponentAdapter() {
	        @Override public void componentResized(ComponentEvent e) {
	            ajustarFuenteCeldas();
	        }
	    });
	}
	
	private int calcularGapSugerido(int n) {
	    if (n >= 15) return 1;
	    if (n >= 10) return 2;
	    return 5; // 5×5 queda bien con 5 px
	}
	
	
	private void ajustarFuenteCeldas() {
	    if (celdas == null || grilla == null) return;
	    int cellW = Math.max(1, grilla.getWidth() / n);
	    int cellH = Math.max(1, grilla.getHeight() / n);
	    int base = Math.min(cellW, cellH);

	    // tamaño cómodo para ver la “X” completa
	    int fontSize = Math.max(10, (int)(base * 0.7)); // 70% del lado de celda

	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            JButton b = celdas[i][j];
	            if (b == null) continue;
	            b.setFont(new Font("Dialog", Font.BOLD, fontSize));
	        }
	    }
	}

	
	
	
	private void botonGrilla(int fila, int columna) {
		JButton boton = new JButton("");
		boton.setBorder(null);
		//boton.setFont(new Font("Dialog", Font.BOLD, 40));  ahora la fuente se elige de arriba
		boton.setFocusable(false);
		boton.setBackground(new Color(192, 192, 192));
		boton.putClientProperty("fila", fila);
		boton.putClientProperty("columna", columna);

		// Listener para clicks izquierdo/derecho
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = (int) boton.getClientProperty("fila");
				int columna = (int) boton.getClientProperty("columna");
				boolean valorCasilla = false;

				if (e.getButton() == MouseEvent.BUTTON1) {
					// Click izquierdo - color negro
					boton.setText("");
					boton.setBackground(Color.BLACK);
					valorCasilla = true;

				} else if (e.getButton() == MouseEvent.BUTTON3) {
					// Click derecho - marca "X"
					boton.setBackground(new Color(192, 192, 192));
					boton.setForeground(Color.RED);
					boton.setText("X");
					valorCasilla = false;

				}

				System.out.println("Fila: " + fila);
				System.out.println("Columna: " + columna);
				System.out.println(valorCasilla);
				System.out.println();
			}
		});

		grilla.add(boton);
        celdas[fila][columna] = boton; //  Nuevo para limpiar tablero guardamos referencia

	}


	public void showWindow() {
		if (frame != null) {
            frame.setVisible(true);
        }
    }
		
	// === NUEVO: punto único para “pedir” datos a la lógica ===
	private void iniciarJuegoDesdeLogica(int nuevoTamanio) {
	    this.n = nuevoTamanio;

	    // HOY (provisorio): usamos TableroUsuario para obtener matrices del tamaño correcto
	    this.refFilas = pedirReferenciasFila(n);
	    this.refCols  = pedirReferenciasCol(n);
	}
	
	/* Cuando esten listos los metodos sacar el de arriba y colocar el comentado
	 * private void iniciarJuegoDesdeLogica(int nuevoTamanio) {
    this.n = nuevoTamanio;

    // pedir a la lógica que genere un juego de tamaño n
    logica.Main.generarJuego(n);

    //  pedir las referencias reales (TablaResuelta)
    this.refFilas = logica.Main.getReferenciasFilas();      // 
    this.refCols  = logica.Main.getReferenciasColumnas();   // 

}
	 
	
	 */

	// Nuevo: “peticiones” (hoy devuelven tamaño correcto con ceros)
	
	
	//estos se van a borrar al tener los get, ahora son momentaneos 
	private int[][] pedirReferenciasFila(int tamanio) {
	    int maxRefs = (tamanio + 1) / 2;
	    int[][] refs = new int[tamanio][maxRefs];
	    // quedan en 0 vacías por ahora
	    return refs;
	}

	
	private int[][] pedirReferenciasCol(int tamanio) {
	    int maxRefs = (tamanio + 1) / 2;
	    int[][] refs = new int[tamanio][maxRefs];
	    return refs;
	}
	
	private void limpiarTableroUI() {
	    if (celdas == null) return;

	    Color base = new Color(192, 192, 192);
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            JButton b = celdas[i][j];
	            if (b == null) continue;
	            b.setText("");
	            b.setBackground(base);
	            b.setForeground(UIManager.getColor("Button.foreground"));
	        }
	    }
	    grilla.revalidate();
	    grilla.repaint();
	}
	
	
	// reconstruye pistas + grilla cuando cambia el tamaño
    private void reconstruirVista() {
        // borrar componentes anteriores de zonas variables
        // recrear todo el frame salvo panel de controles
        frame.getContentPane().removeAll();

        JLabel lblNewLabel = new JLabel("NONOGRAMA");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 651, 46);
        frame.getContentPane().add(lblNewLabel);

        // recolocamos panel controles + pistas + grilla
        controles(new Color(31, 43, 52, 200));
        pistasVerticales(new Color(31, 43, 52, 200), refCols);
        pistasHorizontales(new Color(31, 43, 52, 200), refFilas);
        grilla(new Color(31, 43, 52, 200));

        frame.revalidate();
        frame.repaint();
    }
	
	}

