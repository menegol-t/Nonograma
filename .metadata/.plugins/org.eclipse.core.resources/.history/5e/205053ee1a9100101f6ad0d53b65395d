package logica;

public class TableroUsuario extends Tablero {

    public TableroUsuario(int tamanio) {
        
    	super(tamanio);
        
		generarJuego();
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
    
}