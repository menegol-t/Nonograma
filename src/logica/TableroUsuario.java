package logica;

public class TableroUsuario extends Tablero 
{

    public TableroUsuario(int tamanio) 
    {
        
    	super(tamanio);
        
		generarJuego();
    }
    
    private static final int CASILLA_BLANCA = 0;
    
    private static final int CASILLA_CRUZ = 2;

    @Override
	public void generarJuego() 
    {
    	int tamanio = getTamanio();
    	
        setTablero(generarJuegoVacio(tamanio));
    }
    
    private int[][] generarJuegoVacio(int tamanio)
    {
    	int [][] tablero = new int[tamanio][tamanio];
    	
    	for(int i = 0; i<tablero.length; i++)
    		
    		for(int j = 0; j<tablero.length; j++)
    			
    			tablero[i][j] = 0;
    	
    	return tablero;
    }
    
    public void setCasilla(int fila, int columna, int estado)
    {
    	validarIndice(fila, columna);
    	
    	if(estado < CASILLA_BLANCA || estado > CASILLA_CRUZ) throw new IllegalArgumentException("Los unicos valores validos para el Estado de las casillas son 0, 1 y 2."); 

		int[][] matrizDelUsuario = getTablero();
		
		matrizDelUsuario[fila][columna] = estado;
		
		setTablero(matrizDelUsuario);
    }
    
    public int getCasilla(int fila, int columna)
    {
    	validarIndice(fila, columna);
    	
    	int[][] matrizDelUsuario = getTablero();
		
		return matrizDelUsuario[fila][columna];
    }
    
    private void validarIndice(int fila, int columna)
	{
		if (fila < 0 || fila > getTamanio()) throw new ArrayIndexOutOfBoundsException("El indice de la fila debe estar en el rango del Tablero.");
	
		else if (columna < 0 || columna > getTamanio()) throw new ArrayIndexOutOfBoundsException("El indice de la columna debe estar en el rango del Tablero.");
	}
}