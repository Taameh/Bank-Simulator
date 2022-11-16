package Exceptions;

/**
 * 
 * @author MaxiC
 *
 */
public class InvalidKeyException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */	
	
	public InvalidKeyException(String msg){
		super(msg);
	}
}
