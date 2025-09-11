package logica;

public class TableroUsuario extends Tablero {

    public TableroUsuario(int tamanio) {
        
    	super(tamanio);
        
		generarJuego();
	
		generarReferencias();
    }

    @Override
	public void generarJuego() 
    {
    	int tamanio = getTamanio();
    	
        setTablero(generarJuegoVacio(tamanio));
    }
    
    private int[][] generarJuegoVacio(int tamanio)
    {
    	int [][] juego = new int[tamanio][tamanio];
    	
    	rellenarTablero(juego);
    	
    	return juego;
    }
    
    private void rellenarTablero(int[][] tablero)
    {
    	int largoTablero = tablero.length;
    	
    	for(int i = 0; i<largoTablero; i++)
    		
    		for(int j = 0; j<largoTablero; j++)
    			
    			tablero[i][j] = 0;
    }
    
    @Override
    public void generarReferencias()
    {
    	generarReferenciasPorFilas();
    	generarReferenciasPorColumna();
    }
    
    @Override
    public void generarReferenciasPorFilas() 
    {
    	int cantidadDeFilas = getTamanio();
    	
    	int maxReferenciasPorFila = (cantidadDeFilas + 1) / 2;
    	
    	int[][] referenciasVacias = new int[cantidadDeFilas][maxReferenciasPorFila];
    	
    	rellenarTablero(referenciasVacias);
    	
    	setReferenciasFila(referenciasVacias);
    }

    @Override
    public void generarReferenciasPorColumna() 
    {
    	int cantidadDeFilas = getTamanio();
    	
    	int maxReferenciasPorFila = (cantidadDeFilas + 1) / 2;
    	
    	int[][] referenciasVacias = new int[cantidadDeFilas][maxReferenciasPorFila];
    	
    	rellenarTablero(referenciasVacias);
    	
    	setReferenciasCol(referenciasVacias);
    }
}