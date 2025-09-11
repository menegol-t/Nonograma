package logica;

public abstract class Tablero 
{
    private int[][] _tablero;
    private int[][] _referenciasCol;
    private int[][] _referenciasFila;
    private int _tamanio;
    
    
    public Tablero(int tamanio) 
    {
        _tablero = new int[tamanio][tamanio];
        _referenciasCol = new int[tamanio][];
        _referenciasFila = new int[tamanio][];
        _tamanio = tamanio;
    }
    
    public abstract void generarJuego();
    
    public abstract void generarReferencias();
    
    public abstract void generarReferenciasPorFilas();

    public abstract void generarReferenciasPorColumna();
    
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
    
    protected void setTablero(int[][] tablero) 
    {
        _tablero = tablero;
    }

    protected void setReferenciasCol(int[][] referenciasCol) 
    {
        _referenciasCol = referenciasCol;
    }

    protected void setReferenciasFila(int[][] referenciasFila) 
    {
        _referenciasFila = referenciasFila;
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

    @Override
    public String toString() {
        imprimirMatriz(_tablero);
        return "";
    }
}
