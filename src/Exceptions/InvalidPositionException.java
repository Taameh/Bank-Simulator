package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public InvalidPositionException(String msj) {
		super(msj);
	}

}
