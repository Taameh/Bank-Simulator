package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
@SuppressWarnings("serial")
public class RegistroInvalidoException extends Exception {
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public RegistroInvalidoException(String msj) {
		super(msj);
	}
}
