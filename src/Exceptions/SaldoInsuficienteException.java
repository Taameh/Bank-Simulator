package Exceptions;


/**
 * 
 * @author MaxiC
 *
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
