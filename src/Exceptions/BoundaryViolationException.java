package Exceptions;

/**
 * Clase BoundaryViolationException
 * Excepcion a utilizarse cuando se excede el limite de una estructura
 * @author Maxi Fernandez - Tomas Arroyo
 *
 */
public class BoundaryViolationException extends Exception {
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public BoundaryViolationException(String msj) {
		super(msj);
	}

}
