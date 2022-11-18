package Exceptions;

/**
 * Clase EmptyListException
 * Excepcion que se modela cuando una lista esta vacia
 * @author Maxi Fernandez - Tomas Arroyo
 */
@SuppressWarnings("serial")
public class EmptyListException extends Exception {
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public EmptyListException(String msj) {
		super(msj);
	}
}
