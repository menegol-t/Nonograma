package interfaz;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		int[][] pistasFila = { { 2, 0, 2 }, { 0, 4, 0 }, { 0, 0, 1 }, { 0, 0, 2 }, { 0, 0, 2 }, };

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
		pistasVerticales(colorFondo2, pistasFila);
		pistasHorizontales(colorFondo2, pistasFila);
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
		botonLimpiarTablero(panelControles);
		separador(panelControles, 2);
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

		btnLimpiarTablero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(btnLimpiarTablero, "¿Te limpio el tablero compa?");
			}
		});
	}

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

	private void pistasHorizontales(Color colorFondo2, int[][] pistasFila) {
		JPanel panelPistasHorizontales = new JPanel();
		panelPistasHorizontales.setBounds(271, 212, 80, 298);
		panelPistasHorizontales.setBackground(colorFondo2);
		panelPistasHorizontales.setLayout(new GridLayout(5, 1, 5, 5));
		panelPistasHorizontales.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.getContentPane().add(panelPistasHorizontales);

		for (int i = 0; i < 5; i++) {
			JPanel panelP = new JPanel();
			panelP.setLayout(new GridLayout(1, 3, 5, 5));
			panelP.setBackground(new Color(0, 255, 0, 80));

			panelPistasHorizontales.add(panelP);

			// junto los valores distintos de 0
			List<String> valores = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				if (pistasFila[i][j] != 0) {
					valores.add(Integer.toString(pistasFila[i][j]));
				}
			}

			// Relleno con "" hasta llegar a 3 valores
			while (valores.size() < 3) {
				valores.add("");
			}

			// Inserto los valores en el panel
			for (int j = valores.size() - 1; j >= 0; j--) {
				JLabel lblPista = new JLabel(valores.get(j));
				lblPista.setFont(new Font("Segoe UI", Font.BOLD, 14));
				lblPista.setHorizontalAlignment(SwingConstants.CENTER);
				lblPista.setForeground(new Color(245, 245, 245));
				panelP.add(lblPista);
			}
		}
	}

	private void pistasVerticales(Color colorFondo2, int[][] pistasFila) {
		JPanel panelPistasVerticales = new JPanel();
		panelPistasVerticales.setBounds(361, 121, 300, 80);
		panelPistasVerticales.setBackground(colorFondo2);
		panelPistasVerticales.setLayout(new GridLayout(1, 5, 5, 5));
		panelPistasVerticales.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.getContentPane().add(panelPistasVerticales);

		for (int i = 0; i < 5; i++) {
			JPanel panelP = new JPanel();
			panelP.setLayout(new GridLayout(3, 1, 5, 5));
			panelP.setBackground(new Color(0, 255, 0, 80));

			panelPistasVerticales.add(panelP);

			// junto los valores distintos de 0
			List<String> valores = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				if (pistasFila[i][j] != 0) {
					valores.add(Integer.toString(pistasFila[i][j]));
				}
			}

			// Relleno con "" hasta llegar a 3 valores
			while (valores.size() < 3) {
				valores.add("");
			}

			// Inserto los valores en el panel
			for (int j = valores.size() - 1; j >= 0; j--) {
				JLabel lblPista = new JLabel(valores.get(j));
				lblPista.setFont(new Font("Segoe UI", Font.BOLD, 14));
				lblPista.setHorizontalAlignment(SwingConstants.CENTER);
				lblPista.setForeground(new Color(245, 245, 245));
				panelP.add(lblPista);
			}
		}
	}

	private void grilla(Color colorFondo2) {
		grilla = new JPanel();
		grilla.setBorder(new EmptyBorder(5, 5, 5, 5));
		grilla.setBounds(361, 212, 300, 300);
		grilla.setLayout(new GridLayout(5, 5, 5, 5));
		grilla.setBackground(colorFondo2);
		frame.getContentPane().add(grilla);

		for (int fila = 0; fila < 5; fila++) {
			for (int columna = 0; columna < 5; columna++) {
				botonGrilla(fila, columna);
			}
		}
	}

	private void botonGrilla(int fila, int columna) {
		JButton boton = new JButton("");
		boton.setBorder(null);
		boton.setFont(new Font("Dialog", Font.BOLD, 40));
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
	}


	public void showWindow() {
		if (frame != null) {
            frame.setVisible(true);
        }
    }
		
	}

