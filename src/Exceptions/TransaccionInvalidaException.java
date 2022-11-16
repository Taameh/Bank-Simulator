package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class TransaccionInvalidaException extends Exception {
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public TransaccionInvalidaException(String msj) {
		super(msj);
	}
}
