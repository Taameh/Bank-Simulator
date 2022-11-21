package Exceptions;

/**
 * Clase InvalidKeyException
 * Excepcion que se modela cuando una clave es invalida
 * @author Maxi Fernandez - Tomas Arroyo
 */
public class InvalidKeyException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */	
	
	public InvalidKeyException(String msj){
		super(msj);
	}
}
