package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class InvalidEntryException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public InvalidEntryException(String msg){
		super(msg);
	}
}
