package Programa;

import java.awt.EventQueue;
import java.util.Iterator;

import Exceptions.LogueoInvalidoException;
import Exceptions.RegistroInvalidoException;
import Exceptions.SaldoInsuficienteException;
import Exceptions.TransaccionInvalidaException;
import GUI.LogInFrame;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class Logica {
	private static PositionList<CuentaBancaria> cuentas;
	private static CuentaBancaria sesionActual;

	
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
	 * @throws RegistroInvalidoException si alguno de los campos esta vacio
	 */
	public void signIn(String nombre, String apellido, int dni, int saldo) throws RegistroInvalidoException {
		if(!nombre.equals("") && !apellido.equals(""))
		cuentas.addLast(new CuentaBancaria(nombre, apellido, dni, saldo));
		else
			throw new RegistroInvalidoException ("Campos vacios");
	}
	
	/**
	 * permite el acceso a la manipulacion de la cuenta
	 * @param clave contrasenia de la cuenta
	 * @param nombre de la cuenta
	 * @param apellido de la cuenta
	 * @param dni de la cuenta
	 * @throws LogueoInvalidoException si algun dato es incorrecto
	 */
	public void logIn(String clave, String nombre, String apellido, int dni) throws LogueoInvalidoException{
		CuentaBancaria cuenta = buscarCuenta(dni);
		if ((cuenta!=null) && (nombre.equals(cuenta.getNombre())) && (apellido.equals(cuenta.getApellido())) && cuenta.validarCadena(clave)) {
			setSesionActual(cuenta);
		}
		else {throw new LogueoInvalidoException("Datos incorrectos");}
	}
	

	
	/**
	 * Permite el debito de dinero a una valida
	 * @param monto a debitar
	 * @param dni de la cuenta receptora del dinero
	 * @throws TransaccionInvalidaException
	 */
	public void debito(float monto, int dni) throws TransaccionInvalidaException{
		try{
			CuentaBancaria beneficiario = buscarCuenta(dni);
			if (beneficiario != null) {
				getSesionActual().debito(monto, beneficiario);
				beneficiario.credito(monto,getSesionActual()); //Las transferencias se ven reflejadas desde el lado del receptor como un credito
			}
			else {
				throw new TransaccionInvalidaException("Beneficiario invalido");
			}
		}catch(SaldoInsuficienteException e) {
			throw new TransaccionInvalidaException(e.getMessage());
			}
	}
	
	/**
	 * Permite acreditar dinero a una cuenta valida 
	 * @param monto a acreditar
	 * @param dni del emisor
	 * @throws TransaccionInvalidaException si el emisor es invalido
	 */
	public void credito(int monto, int dni) throws TransaccionInvalidaException{ //solo recibe dinero
		CuentaBancaria emisor = buscarCuenta(dni);
		if (emisor != null) {
			getSesionActual().credito(monto,emisor); //Las transferencias se ven reflejadas desde el lado del receptor como un credito		
		}
		else {
			throw new TransaccionInvalidaException("Emisor invalido");
		}
	}
	
	/**
	 * Muestra las ultimas n transacciones en la GUI
	 * @param n transacciones a mostrar
	 */
	public void mostrarUltimasN(int n) {
		for(Transaccion transaccion : getSesionActual().ultimasN(n)) {
			//mostrar en gui
		}
	}
	
	/**
	 * Muestra las transacciones de un mismo valor k
	 * @param k valor de la transaccion a buscar
	 */
	public void transaccionesValorK(int k) {
//		Iterable<Entry<Integer, Transaccion>> transacciones = sesionActual.transaccionesMismoValor().findAllK(k);
//		for(<Entry<Integer, Transaccion>> transaccion : transacciones) {
			//mostrar en gui
//		}
			
	}
	
	/**
	 * Muestra el historila de transacciones que se realizaron en una fecha especifica
	 * @param fecha de transacciones a buscar
	 */
	public void historialDia(String fecha) {
		
	}
	
	/**
	 * Busca la cuenta bancaria de la persona perteneciente al dni ingresado
	 * @param dni de la cuenta a buscar
	 * @return cuenta bancaria 
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
	 * 
	 * @return sesion que fue iniciada anteriormente, returna null en caso de no haber iniciado sesion anteriormente
	 */
	public static CuentaBancaria getSesionActual() {
		return sesionActual;
	}
	/**
	 * Asigna la sesion pasa por parametro a la sesion actual
	 * @param sesionActual 
	 */
	public static void setSesionActual(CuentaBancaria sesionActual) {
		Logica.sesionActual = sesionActual;
	}
}
//Se asume que el apellido no contiene el caracter x