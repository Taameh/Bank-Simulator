package Exceptions;

/**
 * Clase EmptyListException
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
