package Exceptions;

/**
 * Clase EmptyPriorityQueueException
 * Implementa la excepcion de la cola vacia
 * @author MaxiC
 */
@SuppressWarnings("serial")
public class EmptyPriorityQueueException extends Exception{

	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public EmptyPriorityQueueException(String msg) {
		super(msg);
	}

}
