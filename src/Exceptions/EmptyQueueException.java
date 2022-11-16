package Exceptions;


/**
 * 
 * @author MaxiC
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

