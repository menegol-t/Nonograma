package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
		frame.setSize(800, 600);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		grilla = new JPanel();
		grilla.setBorder(new EmptyBorder(5, 5, 5, 5));
		grilla.setBounds(200, 150, 300, 300);
		grilla.setLayout(new GridLayout(5, 5, 5, 5));
		grilla.setBackground(colorFondo2);
		frame.getContentPane().add(grilla);

		JPanel panelPistasVerticales = new JPanel();
		panelPistasVerticales.setBounds(200, 60, 300, 80);
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

		JPanel panelPistasHorizontales = new JPanel();
		panelPistasHorizontales.setBounds(110, 150, 80, 300);
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
		
		
		
		/**************************************************/
		JButton btnPista = new JButton("Dame Una Pista");
		btnPista.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnPista.setBounds(200, 461, 300, 33);
		
		frame.getContentPane().add(btnPista);

		btnPista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(btnPista, "" + "Toma una pista 🧩");
			}
		});

		JButton btnVerificarResultado = new JButton("Verificar resultado");
		btnVerificarResultado.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnVerificarResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerificarResultado.setBounds(200, 505, 300, 33);
		frame.getContentPane().add(btnVerificarResultado);

		btnVerificarResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(btnVerificarResultado,
						"Que mal, Perdiste /\n" + "Felicidades, ganaste!! \n\n" + "█  █  █  █  █ \n" + // █ alt + 219
								"█  █  █  █  █ \n" + "█  █  █  █  █ \n" + "█  █  █  █  █ \n" + "█  █  █  █  █ \n\n");
			}
		});

		JLabel lblNewLabel = new JLabel("NONOGRAMA");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(110, 11, 390, 46);
		frame.getContentPane().add(lblNewLabel);

		for (int fila = 0; fila < 5; fila++) {
			for (int columna = 0; columna < 5; columna++) {
				crearBoton(fila, columna);
			}
		}
	}

	private void crearBoton(int fila, int columna) {
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
}
