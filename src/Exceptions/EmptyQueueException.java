package Exceptions;


/**
 * Clase EmptyQueueException
 * Excepcion que se modela caundo una cola esta vacia
 * @author Maxi Fernandez - Tomas Arroyo
 *
 */
public class EmptyQueueException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public EmptyQueueException(String msj) {
		super(msj);
	}
}

