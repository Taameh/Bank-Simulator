package Exceptions;


/**
 * Clase SaldoInsuficienteException
 * Excepcion que modela cuando el saldo de la cuenta es menor a el dinero que se quiere emitir
 * @author Maxi Fernandez - Tomas Arroyo
 */
public class SaldoInsuficienteException extends Exception {
	
	/**
	 * Crea una nueva excepcion 
	 * @param msj mensaje de error
	 */
	public SaldoInsuficienteException(String msj) {
		super(msj);
	}

}
