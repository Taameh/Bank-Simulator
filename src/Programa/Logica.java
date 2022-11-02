package Programa;

import java.awt.EventQueue;
import java.util.Iterator;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;
import Exceptions.LogueoInvalidoException;
import Exceptions.RegistroInvalidoException;
import Exceptions.SaldoInsuficienteException;
import Exceptions.TransaccionInvalidaException;
import GUI.LogInFrame;
import TDAColaCP.PriorityQueue;
import TDADiccionario.Entry;
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
	 * 
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param saldo
	 * @throws RegistroInvalidoException
	 */
	public void signIn(String nombre, String apellido, int dni, float saldo) throws RegistroInvalidoException {
		if(!nombre.equals("") && !apellido.equals(""))
		cuentas.addLast(new CuentaBancaria(nombre, apellido, dni, saldo));
		else
			throw new RegistroInvalidoException ("Campos vacios");
	}
	
	/**
	 * 
	 * @param clave
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @throws LogueoInvalidoException
	 */
	public void logIn(String clave, String nombre, String apellido, int dni) throws LogueoInvalidoException{
		CuentaBancaria cuenta = buscarCuenta(dni);
		if ((cuenta!=null) && (nombre.equals(cuenta.getNombre())) && (apellido.equals(cuenta.getApellido())) && cuenta.validarCadena(clave)) {
			setSesionActual(cuenta);
		}
		else {throw new LogueoInvalidoException("Datos incorrectos");}
	}
	

	
	/**
	 * 
	 * @param monto
	 * @param dni
	 * @throws TransaccionInvalidaException
	 */
	public void debito(float monto, int dni) throws TransaccionInvalidaException{
		try{
			CuentaBancaria beneficiario = buscarCuenta(dni);
			if (beneficiario != null) {
				sesionActual.debito(monto, beneficiario);
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
	 * @param monto
	 * @param dni
	 * @throws TransaccionInvalidaException
	 */
	public void credito(float monto, int dni) throws TransaccionInvalidaException{ //solo recibe dinero

		CuentaBancaria emisor = buscarCuenta(dni);
		if (emisor != null) {
			sesionActual.credito(monto,emisor);
		}
		else {
			throw new TransaccionInvalidaException("Emisor invalido");
		}
	}
	
	
	/**
	 * 
	 * @param k
	 * @throws InvalidKeyException 
	 */
	public PositionList<Transaccion> transaccionesValorK(float k) throws InvalidKeyException {
		PositionList<Transaccion> toReturn = new ListaDoblementeEnlazada();
		try {
			for(Entry<Float, Transaccion> e : sesionActual.transaccionesMismoValor().findAll(k))
				toReturn.addFirst(e.getValue());
		} catch (InvalidKeyException e) {
			throw new InvalidKeyException ("Ingrese un valor valido");
		}
		return toReturn;
	}
	
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
	
	

	public static CuentaBancaria getSesionActual() {
		return sesionActual;
	}

	public static void setSesionActual(CuentaBancaria sesionActual) {
		Logica.sesionActual = sesionActual;
	}
}
//Se asume que el apellido no contiene el caracter x