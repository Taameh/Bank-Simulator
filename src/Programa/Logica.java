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
	
	public void signIn(String nombre, String apellido, int dni, float saldo) throws RegistroInvalidoException {
		if(!nombre.equals("") && !apellido.equals(""))
		cuentas.addLast(new CuentaBancaria(nombre, apellido, dni, saldo));
		else
			throw new RegistroInvalidoException ("Campos vacios");
	}
	
	public void logIn(String clave, String nombre, String apellido, int dni) throws LogueoInvalidoException{
		CuentaBancaria cuenta = buscarCuenta(dni);
		if ((cuenta!=null) && (nombre.equals(cuenta.getNombre())) && (apellido.equals(cuenta.getApellido())) && cuenta.validarCadena(clave)) {
			setSesionActual(cuenta);
		}
		else {throw new LogueoInvalidoException("Datos incorrectos");}
	}
	

	
	
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
	
	public void credito(float monto, int dni) throws TransaccionInvalidaException{ //solo recibe dinero
		CuentaBancaria emisor = buscarCuenta(dni);
		if (emisor != null) {
			sesionActual.credito(monto,emisor); //Las transferencias se ven reflejadas desde el lado del receptor como un credito		
		}
		else {
			throw new TransaccionInvalidaException("Emisor invalido");
		}
	}
	
	public void mostrarUltimasN(int n) {
		for(Transaccion transaccion : sesionActual.ultimasN(n)) {
			//mostrar en gui
		}
	}
	
	public void transaccionesValorK(int k) {
//		Iterable<Entry<Integer, Transaccion>> transacciones = sesionActual.transaccionesMismoValor().findAllK(k);
//		for(<Entry<Integer, Transaccion>> transaccion : transacciones) {
			//mostrar en gui
//		}
			
	}
	
	public void historialDia(String fecha) {
		
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