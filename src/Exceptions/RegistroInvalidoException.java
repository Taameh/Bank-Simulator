package Exceptions;

/**
 * Clase RegistroInvalidoException
 * Excepcio que modela cuando los datos para registrarte son invalidos
 * @author Maxi Fernandez - Tomas Arroyo
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
