package logica;

public class Juego 
{
	
	private TableroUsuario _tableroUsuario;
	
	private TableroRespuesta _tableroRespuesta;
	
	
	public Juego(int tamanioDelTablero)
	{
		this._tableroUsuario = new TableroUsuario(tamanioDelTablero);
		this._tableroRespuesta = new TableroRespuesta(tamanioDelTablero);
	}
	
	
	public int[][] getReferenciasCol()
	{
		return _tableroRespuesta.getReferenciasCol();
	}
	
	public int[][] getReferenciasFilas()
	{
		return _tableroRespuesta.getReferenciasFila();
	}
	
	public void setCasilla(int Estado, int fila, int columna)
	{
		
		validarParametros(Estado, fila, columna);
		
		int[][] matrizDelUsuario = _tableroUsuario.getTablero();
		
		matrizDelUsuario[fila][columna] = Estado;
		
		_tableroUsuario.setTablero(matrizDelUsuario);
		
	}
	
	
	public boolean validarJuego()
	{
		int[][] referenciasFilasUsuario = _tableroUsuario.getReferenciasFila();
		int[][] referenciasColumnasUsuario = _tableroUsuario.getReferenciasCol();
		
		
		int[][] referenciasFilasRespuesta = _tableroRespuesta.getReferenciasFila();
		int[][] referenciasColumnasRespuesta = _tableroRespuesta.getReferenciasCol();
		
		return (matrizesSonIguales(referenciasFilasUsuario, referenciasFilasRespuesta) 
			 && matrizesSonIguales(referenciasColumnasUsuario, referenciasColumnasRespuesta));
		
		
	}
	
	private boolean matrizesSonIguales(int[][] primeraMatriz, int[][] segundaMatriz)
	{
		return true;
	}
	
	
	private  void validarParametros(int Estado, int fila, int columna)
	{
		//if Estado < 0 || Estado > 2 --> Lanzar Excepcion
		if(Estado < 0 || Estado > 2)
		{
			throw new IllegalArgumentException("Los unicos valores validos para el Estado de las casillas son 0, 1 y 2. "); 
		}
		
		else if (fila < 0 || fila > _tableroUsuario.getTamanio())
		{
			throw new ArrayIndexOutOfBoundsException("El indice de la fila debe estar en el rango del Tablero.");
	
		}
	
		else if (columna < 0 || columna > _tableroUsuario.getTamanio())
		{
			throw new ArrayIndexOutOfBoundsException("El indice de la columna debe estar en el rango del Tablero.");
	
		}
	}

	

	
	
}

