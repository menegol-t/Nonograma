package logica;
import java.util.ArrayList;
import java.util.Random;


public class Tablero {

	private int[][]  _tablero;
	private int[][]  _referenciasCol;
	private int[][]  _referenciasFila;
	
	private Tablero(int[][] tablero, int[][]referenciasCol, int[][] referenciasFila)
	{
		_tablero = tablero;
				
		_referenciasCol = referenciasCol;
		
		_referenciasFila = referenciasFila;
	}
	

	public static void generarJuego(int largoDelTablero)
	{
		int[][] tablero = generarTablero(largoDelTablero);
		
//		int [][] referenciasCol = generarReferenciasCol(tablero);
//		
//		int [][] referenciasFila = generarReferenciasFila(tablero);
		
		
	}
	
	
	private static int[][] generarTablero(int largoDelTablero)
	{	
		
		int[][] tablero = new int[largoDelTablero][largoDelTablero];
		
		int[] patronDeFilasAleatorio = randomSinRepetir(largoDelTablero);
		
		int totalCasillasNegras = conseguirTotalCasillasNegras(largoDelTablero);
		
		rellenarTablero(tablero, totalCasillasNegras, largoDelTablero, patronDeFilasAleatorio);
		
		imprimirMatriz(tablero);
		
		return tablero;
	}
	
	
	private static void rellenarTablero(int[][] tablero, int totalCasillasNegras, int cantidadDeFilas, int[] patronAleatorio)
	{
		
		int casillasNegrasDisponibles = totalCasillasNegras;
		
		int maximo = 0;
		int minimo = 0;
		
		for (int i = 0; i<cantidadDeFilas; i++)
		{
			 //Repensar el nombre
			if(nosPasamosDeLaMitad(totalCasillasNegras, casillasNegrasDisponibles))
			{	
				maximo = 4; minimo = 1;
			}
			else maximo = 2; minimo = 1;
			
			int[] filaActual = tablero[patronAleatorio[i]];
			
			casillasNegrasDisponibles -= rellenarFilasYActualizar(filaActual, casillasNegrasDisponibles, maximo, minimo);
			
		}
		
	}
	
	private static boolean nosPasamosDeLaMitad(int totalCasillasNegras, int casillasNegrasDisponibles)
	{
		return (totalCasillasNegras / 2 < casillasNegrasDisponibles);
	}
	
	private static int rellenarFilasYActualizar(int[] fila, int casillasNegrasDisponibles, int maximo, int minimo)
	{	
		
		int cantidadCeldasNegrasEnFila = conseguirMaximoMinimo(maximo, minimo);

		System.out.println(cantidadCeldasNegrasEnFila);
		
		ArrayList<Integer> posicionesDisponibles = new ArrayList<>();
		
		int largoFila = fila.length;
		
		agregarIndices(largoFila, posicionesDisponibles);

		for (int i = 0; i < largoFila; i++) 
		{
		    int indiceAleatorio = randomEnRango(posicionesDisponibles.size());
		    
		    int posicionElegida = indiceAleatorio;
		    
		    fila[posicionElegida] = 1;
		    
		    posicionesDisponibles.remove(indiceAleatorio);
		    
		}	
		return cantidadCeldasNegrasEnFila;
	}

	private static int conseguirMaximoMinimo(int max, int min)
	{
		Random aleatorio = new Random();
		
		return aleatorio.nextBoolean() ? max : min;
		
	}
	
	private static int[] randomSinRepetir(int rango)
	{		
		ArrayList<Integer> _indicesDisponibles = new ArrayList<Integer>();
		
		agregarIndices(rango, _indicesDisponibles);
		
		System.out.println(_indicesDisponibles);
		
	    int[] _patronAleatorio = generarPatronAleario(_indicesDisponibles);
		
	    return _patronAleatorio;
    }
	
	private static void agregarIndices(int rango, ArrayList<Integer> listaDeIndices)
	{
	    for (int i = 0; i<rango; i++)
		{
			listaDeIndices.add(convertirorAInteger(i));
		}
	}
	
	private static Integer convertirorAInteger(int valor)
	{
	    return (Integer)(valor); 
	}
	
	private static int[] generarPatronAleario(ArrayList<Integer> indicesDisponibles)
    {
		
		int cantidadDeIndices = indicesDisponibles.size();
		
		int[] patronAleatorio = new int[cantidadDeIndices];
		
		int posicionActual = 0;
		
		while (!(indicesDisponibles.isEmpty()))
		{
			
			int indiceAleatorio = randomEnRango(cantidadDeIndices);
			
		    patronAleatorio[posicionActual] = indicesDisponibles.get(indiceAleatorio);
			
			indicesDisponibles.remove(indiceAleatorio);
			
			cantidadDeIndices --;
			
			posicionActual ++;
		}
		
		return patronAleatorio;
    }
	
	private static int randomEnRango(int rango)
	{
		Random aleatorio = new Random();
		
		return aleatorio.nextInt(rango);
	}
	
	private static int conseguirTotalCasillasNegras(int largoDelTablero)
	{
	    float porcentajeDeNegrasDelTablero = 0.5f;
	    
	    int totalCasillasDelTablero = largoDelTablero * largoDelTablero;
	    
	    int retorno = Math.round(totalCasillasDelTablero * porcentajeDeNegrasDelTablero);
	    
	    return retorno;
	}
	
	public static void imprimirMatriz(int[][] mat)
	{
		for (int i = 0; i < mat.length; i++) {
		    for (int j = 0; j < mat[i].length; j++) {
		        System.out.print(mat[i][j] + " ");
		    }
		    System.out.println();
		}
	}
	
	
	

	
	
	
		
	}