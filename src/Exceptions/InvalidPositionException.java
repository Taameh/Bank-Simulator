package Exceptions;

/**
 * Clase InvalidPositionException
 * Excepcion que se modela cuando una posicion es invalida
 * @author Maxi Fernandez - Tomas Arroyo
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
