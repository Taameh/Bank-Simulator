package Exceptions;

/**
 * Clase EmptyPriorityQueueException
 * Excepcion que se modela cuando cola con prioridad esta vacia
 * @author Maxi Fernandez - Tomas Arroyo
 */
@SuppressWarnings("serial")
public class EmptyPriorityQueueException extends Exception{

	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public EmptyPriorityQueueException(String msj) {
		super(msj);
	}

}
