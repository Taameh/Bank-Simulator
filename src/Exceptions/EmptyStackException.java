package Exceptions;

/**
 * Clase EmptyStackException
 * Excepcion que se modela cuando una pila esta vacia
 * @author Maxi Fernandez - Tomas Arroyo
 *
 */
public class EmptyStackException extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public EmptyStackException(String msj) {
		super(msj);
	}
}
