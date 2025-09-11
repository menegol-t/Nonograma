package logica;

public abstract class Tablero {

    private int[][]  _tablero;
    private int[][]  _referenciasCol;
    private int[][]  _referenciasFila;
    
    private Tablero (int[][] tablero, int[][]referenciasCol, int[][] referenciasFila) 
    {
        _tablero = tablero;
        
        _referenciasCol = referenciasCol;
        
        _referenciasFila = referenciasFila;
    }
	
    
    public static int[][] generarReferenciaFilas(int[][] tableroConCasillasNegras)
    {
    	return null;
    }
    
    public static int[][] generarReferenciaColumnas(int[][] tableroConCasillasNegras)
    {
    	return null;
    }
    
    public int[][] obtenerReferenciasFilas()
    {
    	return _referenciasFila;
    }

    public int[][] obtenerReferenciasColumnas()
    {
    	return _referenciasCol;
    }
}