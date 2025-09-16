package logica;

public abstract class Tablero 
{
    private int[][] _tablero;
    private int[][] _referenciasCol;
    private int[][] _referenciasFila;
    private int _tamanio;
    
    private static final int CASILLA_NEGRA = 1;
    
    public Tablero(int tamanio) 
    {
        _tablero = new int[tamanio][tamanio];
        _referenciasCol = new int[tamanio][];
        _referenciasFila = new int[tamanio][];
        _tamanio = tamanio;
    }
    
    public abstract void generarJuego();
    
    public void generarReferencias()
	{
    	setReferenciasFila(generarReferenciaFilas(_tablero));
		
    	setReferenciasCol(generarReferenciasColumnas(_tablero, _tamanio));
	}
    
    private static int[][] generarReferenciaFilas(int[][] tableroCasillasNegras) 
	{
		int maxReferenciasPorFila = (tableroCasillasNegras.length + 1) / 2;
	    
	    int[][] referenciasCalculadas = new int[tableroCasillasNegras.length][maxReferenciasPorFila];

	    return recorrerFilas(tableroCasillasNegras, referenciasCalculadas);
	}

    private static int[][] recorrerFilas(int[][] tableroCasillasNegras, int[][] referenciasCalculadas) 
	{
		for (int i = 0; i < tableroCasillasNegras.length; i++) 
	    {
	        int acumCasillasNegrasPorFila = 0, indiceReferenciasCalculadas = 0;
	        
	        boolean casillaAnteriorFueNegra = false;

	        acumularCasillasNegrasPorFila(tableroCasillasNegras, i, acumCasillasNegrasPorFila, casillaAnteriorFueNegra, referenciasCalculadas, indiceReferenciasCalculadas);
	    }
	    return referenciasCalculadas;
	}
	
	private static void acumularCasillasNegrasPorFila(int[][] tableroCasillasNegras,int filaActual, int acumCasillasNegrasPorFila,
			boolean casillaAnteriorFueNegra, int[][] referenciasCalculadas, int indiceReferenciasCalculadas) 
	{
		for (int j = 0; j < tableroCasillasNegras[filaActual].length; j++) 
        {	
            if (tableroCasillasNegras[filaActual][j] == CASILLA_NEGRA) 
            {
            	acumCasillasNegrasPorFila++;
            	
            	casillaAnteriorFueNegra = true;
            }
            else if (casillaAnteriorFueNegra) 
            {
            	referenciasCalculadas[filaActual][indiceReferenciasCalculadas] = acumCasillasNegrasPorFila;
            	
            	acumCasillasNegrasPorFila = 0;
            	
            	indiceReferenciasCalculadas++;
                
                casillaAnteriorFueNegra = false;
            }
        }
		
		if (casillaAnteriorFueNegra) referenciasCalculadas[filaActual][indiceReferenciasCalculadas] = acumCasillasNegrasPorFila;
	}
	
	private static int[][] generarReferenciasColumnas(int[][] tableroCasillasNegras, int largoDelTablero)
    {
    	int[][] tableroGirado90Grados = new int[largoDelTablero][largoDelTablero];
    	
    	for(int i = 0; i < largoDelTablero; i++)
    	{
    		for(int j = 0; j < largoDelTablero; j++)
    		{
    			tableroGirado90Grados[j][largoDelTablero - 1 - i] = tableroCasillasNegras[i][j];
    		}
    	}
    	
    	return generarReferenciaFilas(tableroGirado90Grados);
    }
    
    public int[][] getTablero() 
    {
        return _tablero;
    }

    public int[][] getReferenciasCol() {
        return _referenciasCol;
    }

    public int[][] getReferenciasFila() {
        return _referenciasFila;
    }

    public int getTamanio()
    {
    	return _tamanio;
    }
    
    public void setTablero(int[][] tablero) 
    {
        _tablero = tablero;
    }

    private void setReferenciasCol(int[][] referenciasCol) 
    {
        _referenciasCol = referenciasCol;
    }

    private void setReferenciasFila(int[][] referenciasFila) 
    {
        _referenciasFila = referenciasFila;
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
    

    @Override
    public String toString() {
    	
    	System.out.print("Grilla: \n");
        imprimirMatriz(_tablero);
        
        System.out.print("Referencias Columna: \n");
        imprimirMatriz(_referenciasCol);
        
        System.out.print("Referencias Fila: \n");
        imprimirMatriz(_referenciasFila);
        
        return "";
    }
}
