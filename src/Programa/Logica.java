package Programa;

import java.awt.EventQueue;
import java.util.Iterator;

import Auxiliares.Entry;
import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;
import GUI.LogInFrame;
import TDAColaCP.PriorityQueue;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

/**
 * @author Arroyo Tomas (126078) - Fernandez Maximo (131672)
 *
 */
public class Logica {
	private static PositionList<CuentaBancaria> cuentas;
	private static CuentaBancaria sesionActual;

	/**
	 * Crea una instancia de  cuenta bancaria
	 */
	public Logica() {
		cuentas = new ListaDoblementeEnlazada<CuentaBancaria>();
		setSesionActual(null);
	}
	

	/**
	 * Perimte crear una cuenta nueva y aniadirl al listado de cuentas bancarias
	 * @param nombre del propietario
	 * @param apellido del priopietario
	 * @param dni del propietario
	 * @param saldo del propietario
	 */
	public void signIn(String nombre, String apellido, int dni, float saldo){
		cuentas.addLast(new CuentaBancaria(nombre, apellido, dni, saldo));
	}
	
	/**
	 * permite el acceso a la manipulacion de la cuenta
	 * @param clave contrasenia de la cuenta
	 * @param nombre de la cuenta
	 * @param apellido de la cuenta
	 * @param dni de la cuenta
	 * @return boolean, true si logra ingresar a la cuenta, false en caso contrario.
	 */
	public boolean logIn(String clave, String nombre, String apellido, int dni){
		CuentaBancaria cuenta = buscarCuenta(dni);
		boolean toReturn = false;
		if ((cuenta!=null) && (nombre.equals(cuenta.getNombre())) && (apellido.equals(cuenta.getApellido())) && cuenta.validarCadena(clave)) {
			setSesionActual(cuenta);
			toReturn = true;
		}
		return toReturn;
	}
	

	
	/**
	 * Permite el debito de dinero a una valida
	 * @param monto a debitar
	 * @param dni de la cuenta receptora del dinero
	 * @return boolean, true si la transaccion fue realizada, false en lo contrario.
	 */
	public boolean debito(float monto, int dni){
		boolean toReturn = true;
				
		CuentaBancaria beneficiario = buscarCuenta(dni);
		if (beneficiario != null) {
			if (!sesionActual.debito(monto, beneficiario)) {
				toReturn = false;
			}
			else {
				beneficiario.credito(monto,getSesionActual()); //Las transferencias se ven reflejadas desde el lado del receptor como un credito
			}
		}
		else {
			toReturn = false;
		}
		return toReturn;
		
	}
	
	/**
	 * Permite acreditar dinero a una cuenta valida 
	 * @param monto a acreditar
	 * @param dni del emisor
	 * @return boolean, true si la transaccion fue realizada, false en lo contrario.
	 */
	public boolean credito(float monto, int dni) { //solo recibe dinero
		boolean toReturn = true;
		CuentaBancaria emisor = buscarCuenta(dni);
		if (emisor != null) {
			if (!emisor.debito(monto, sesionActual)) {
				toReturn = false;
			}
			else {
				sesionActual.credito(monto,emisor);
			}
		}
		else {
			toReturn = false;
		}
		return toReturn;
	}

	/**
	 * Muestra las transacciones de un mismo valor k
	 * @param k valor de la transaccion a buscar
	 * @throws InvalidKeyException si el valor es invalido
	 * @return lista con transacciones de valor k
	 */
	public PositionList<Transaccion> transaccionesValorK(float k) throws InvalidKeyException {
		PositionList<Transaccion> toReturn = new ListaDoblementeEnlazada<Transaccion>();
		try {
			for(Entry<Float, Transaccion> e : sesionActual.transaccionesMismoValor().findAll(k))
				toReturn.addFirst(e.getValue());
		} catch (InvalidKeyException e) {
			throw new InvalidKeyException ("Ingrese un valor valido");
		}
		return toReturn;
	}
	

	/**
	 * Devuelve una lista iterable con f transacciones con mayor valor
	 * @param f numero de transacciones a insertar
	 * @return Retorna una lista con n transaccionnes mayores que f
	 */

	public Iterable<Transaccion> nMayores(float f){
		PositionList<Transaccion> toReturn = new ListaDoblementeEnlazada<Transaccion>();
		PriorityQueue <Float,Transaccion> todasLasTransacciones = sesionActual.transaccionesPorValor();
		try {
			while(!todasLasTransacciones.isEmpty() && todasLasTransacciones.size() > f)
					todasLasTransacciones.removeMin();
			while(!todasLasTransacciones.isEmpty())
				toReturn.addFirst(todasLasTransacciones.removeMin().getValue());
		} catch (EmptyPriorityQueueException e) {
			System.out.println("Error en el manejor de la estructura");
		}
		return toReturn;
	}
	
	/**
	 * Busca la cuenta bancaria de la persona perteneciente al dni ingresado
	 * @param dni de la cuenta a buscar
	 * @return cuenta bancaria asociada al dni pasado por parametro
	 */
	private CuentaBancaria buscarCuenta(int dni) {
		boolean encontre = false;
		CuentaBancaria toReturn = null;
		Iterator<CuentaBancaria> it = cuentas.iterator();
		while(!encontre && it.hasNext()) {
			CuentaBancaria aux = it.next();
			if (aux.getDNI() == dni) {
				encontre = true;
				toReturn = aux;
			}
		}
				return toReturn;
	}
	
	
	/**
	 * Devuelve la sesion iniciada anteriormente
	 * @return sesion que fue iniciada anteriormente, returna null en caso de no haber iniciado sesion anteriormente
	 */
	public static CuentaBancaria getSesionActual() {
		return sesionActual;
	}
	/**
	 * Asigna la sesion pasada por parametro a la sesion actual
	 * @param sesionActual sesion actual
	 */
	public static void setSesionActual(CuentaBancaria sesionActual) {
		Logica.sesionActual = sesionActual;
	}
	
	
	
	 public static boolean isNumeric(String str)
	  {
	      try
	      {
	          float d = Float.parseFloat(str);
	      }
	      catch(NumberFormatException nfe)
	      {  
	          return false;
	      }
	      return true;
	  }
}

