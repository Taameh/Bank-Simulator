package Programa;

import java.awt.EventQueue;
import java.util.Iterator;

import javax.swing.JFrame;

import Exceptions.LogueoInvalidoException;
import Exceptions.TransaccionInvalidaException;
import GUI.LogInFrame;
import TDADiccionario.Entry;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class Logica {
	private static PositionList<CuentaBancaria> cuentas;
	private static CuentaBancaria sesionActual;

	

	
	
	public void signIn(String nombre, String apellido, int dni, int saldo) {
		cuentas.addLast(new CuentaBancaria(nombre, apellido, dni, saldo));
	}
	
	public void logIn(String clave, String nombre, String apellido, int dni) throws LogueoInvalidoException{
		CuentaBancaria cuenta = buscarCuenta(dni);
		if ((cuenta!=null) && (nombre.equals(cuenta.getNombre())) && (apellido.equals(cuenta.getApellido())) && cuenta.validarClave(clave)) {
			sesionActual = cuenta;
		}
		else {throw new LogueoInvalidoException("Datos incorrectos");}
	}
	

	
	
	public void debito(int monto, int dni) throws TransaccionInvalidaException{
		try{
			CuentaBancaria beneficiario = buscarCuenta(dni);
			if (beneficiario != null) {
				sesionActual.debito(monto, beneficiario);
				beneficiario.credito(monto,sesionActual); //Las transferencias se ven reflejadas desde el lado del receptor como un credito
				actualizarSaldo();
			}
			else {
				throw new TransaccionInvalidaException("Beneficiario invalido");
			}
		}catch(SaldoInsuficienteException e) {
			throw new TransaccionInvalidaException(e.getMessage());
			}
	}
	
	public void credito(int monto, int dni) throws TransaccionInvalidaException{ //solo recibe dinero
		CuentaBancaria emisor = buscarCuenta(dni);
		if (emisor != null) {
			sesionActual.credito(monto,emisor); //Las transferencias se ven reflejadas desde el lado del receptor como un credito
			actualizarSaldo();			
		}
		else {
			throw new TransaccionInvalidaException("Emisor invalido");
		}
	}
	
	public void mostrarUltimasN(int n) {
		for(Position<Transaccion> transaccion : sesionActual.ultimasN(n)) {
			//mostrar en gui
		}
	}
	
	public void transaccionesValorK(int k) {
		Iterable<Entry<Integer, Transaccion>> transacciones = sesionActual.transaccionesMismoValor().findAllK(k);
		for(<Entry<Integer, Transaccion>> transaccion : transacciones) {
			//mostrar en gui
		}
			
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
	
	
	private void actualizarSaldo() {
		//actualiza el saldo en la gui
	}
}
//Se asume que el apellido no contiene el caracter x