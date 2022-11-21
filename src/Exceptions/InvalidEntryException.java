package Exceptions;

/**
 * Clase InvalidEntryException
 * Excepcion que se modela cuando una entrada es invalida
 * @author Maxi Fernandez - Tomas Arroyo
 *
 */
public class InvalidEntryException extends Exception{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public InvalidEntryException(String msj){
		super(msj);
	}
}
