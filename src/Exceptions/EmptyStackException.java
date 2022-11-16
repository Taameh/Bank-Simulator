package Exceptions;

/**
 * 
 * @author MaxiC
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
