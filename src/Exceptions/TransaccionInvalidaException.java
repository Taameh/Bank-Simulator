package Exceptions;

/**
 * Clase TransaccionInvalidaException
 * Excepcion que modela cuando una transaccion es invalida
 * @author Maxi Fernandez - Tomas Arroyo
 *
 */
@SuppressWarnings("serial")

public class TransaccionInvalidaException extends Exception {
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public TransaccionInvalidaException(String msj) {
		super(msj);
	}
}
