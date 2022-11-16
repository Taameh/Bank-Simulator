package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class LogueoInvalidoException extends Exception {
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public LogueoInvalidoException(String msj) {
		super(msj);
	}
}