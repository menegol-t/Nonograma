package logica;

import java.util.ArrayList;
import java.util.Random;

public class TableroRespuesta extends Tablero
{

	public TableroRespuesta(int tamanio) 
	{
		super(tamanio);
		
		generarJuego();
		
		generarReferencias();
	}
	
    private static final Random RANDOM = new Random();

//	  Todavia sin testear a fondo para hacerlo funcionar con constante	
//    private static final int CASILLA_NEGRA = 1;
	  
// 	  Esto no se puede hacer a menos que getTablero y tablero sean static (pensar)
//    private static final int[][] tableroConCasillasNegras = getTablero();
	
	@Override
	public void generarJuego() 
	{	
		int tamanio = getTamanio();
		
		setTablero(generarJuegoAleatorio(tamanio));
	}

	private static int[][] generarJuegoAleatorio(int largoDelTablero) 
	    {
	        int[][] juego = new int[largoDelTablero][largoDelTablero];
	        
	        int[] patronDeFilasAleatorio = randomSinRepetir(largoDelTablero);
	        
	        int totalCasillasNegras = conseguirTotalCasillasNegras(largoDelTablero);
	        
	        rellenarTablero(juego, totalCasillasNegras, largoDelTablero, patronDeFilasAleatorio);
	        
	        return juego;
	    }

    private static void rellenarTablero(int[][] tablero, int totalCasillasNegras, int cantidadDeFilas, int[] patronAleatorio) 
    {
        int casillasNegrasDisponibles = totalCasillasNegras;
        
        for (int i = 0; i<cantidadDeFilas; i++) 
        {
            int maximo, minimo;
            
            if (nosPasamosDeLaMitad(totalCasillasNegras, casillasNegrasDisponibles)) 
            {	
                maximo = (int) Math.round(cantidadDeFilas * 0.8); //originalmente 4 para 5x5
                minimo = (int) Math.round(cantidadDeFilas * 0.6); //originalmente 3 para 5x5
            } else {
                maximo = (int) Math.round(cantidadDeFilas * 0.4); //originalmente 2 para 5x5
                minimo = (int) Math.round(cantidadDeFilas * 0.2); //originalmente 1 para 5x5
                
                if (minimo == 0) minimo = 1;
                
            }
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
        
        ArrayList<Integer> posicionesDisponibles = new ArrayList<>();
        
        int largoFila = fila.length;
        
        agregarIndices(largoFila, posicionesDisponibles);

        for (int i = 0; i < cantidadCeldasNegrasEnFila; i++) 
        {
            int indiceAleatorio = randomEnRango(posicionesDisponibles.size());
            
            int posicionElegida = posicionesDisponibles.get(indiceAleatorio);
            
            fila[posicionElegida] = 1;
            
            posicionesDisponibles.remove(indiceAleatorio);
        }	
        return cantidadCeldasNegrasEnFila;
    }

    private static int conseguirMaximoMinimo(int max, int min) 
    {
        return RANDOM.nextBoolean() ? max : min; 
    }

    private static int[] randomSinRepetir(int rango) 
    {		
        ArrayList<Integer> indicesDisponibles = new ArrayList<>();
        
        agregarIndices(rango, indicesDisponibles);
        
        return generarPatronAleario(indicesDisponibles);
    }

    private static void agregarIndices(int rango, ArrayList<Integer> listaDeIndices) 
    {
        for (int i = 0; i<rango; i++) 
        {
            listaDeIndices.add(i);
        }
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
        return RANDOM.nextInt(rango);
    }

    private static int conseguirTotalCasillasNegras(int largoDelTablero) 
    {
        float porcentajeDeNegrasDelTablero = 0.5f;
        
        int totalCasillasDelTablero = largoDelTablero * largoDelTablero;
        
        return Math.round(totalCasillasDelTablero * porcentajeDeNegrasDelTablero);
    }

	
}