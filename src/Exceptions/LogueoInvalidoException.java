package Exceptions;

/**
 * Clase LogueInvalidoException
 * Exceppcion que se modela cuando el logueo es invalido
 * @author Maxi Fernandez - Tomas Arroyo
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