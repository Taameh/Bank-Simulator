package Programa;

import java.util.Iterator;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

public class Logica {
	private static PositionList<CuentaBancaria> cuentas;
	private static CuentaBancaria sesionActual;
	
	public static void main(String[] args) {
		cuentas = new ListaDoblementeEnlazada<CuentaBancaria>();
		sesionActual = null;
	}
	
	
	private CuentaBancaria buscarCuenta(int dni) {
		boolean encontre = false;
		CuentaBancaria toReturn = null;
		Iterator<CuentaBancaria> it = cuentas.iterator();
		while(!encontre && it.hasNext()) {
			if (((CuentaBancaria) it).getDNI() == dni) {
				encontre = true;
				toReturn = (CuentaBancaria)it;
			}
		}
				return toReturn;
	}
	
	public void signIn(String nombre, String apellido, int dni, int saldo) {
		cuentas.addLast(new CuentaBancaria(nombre, apellido, dni, saldo));
	}
	
	public void logIn(String clave, String nombre, String apellido, int dni) {
		CuentaBancaria cuenta = buscarCuenta(dni);
		if ((cuenta!=null) && (nombre == cuenta.getNombre()) && (apellido == cuenta.getApellido()) && cuenta.validarClave(clave)) {
			sesionActual = cuenta;
		}
	}
	

	
	
	private void debito(int monto, int dni) {
		if (monto < sesionActual.getSaldo()){
			CuentaBancaria beneficiario = buscarCuenta(dni);
			if (beneficiario != null) {
				sesionActual.debito(monto, beneficiario);
				beneficiario.credito(monto,sesionActual); //Las transferencias se ven reflejadas desde el lado del receptor como un credito
				actualizarSaldo();
				
			}
			else {
				//beneficiario invalido
			}
		}
	}
	
	private void credito(int monto, int dni) {
		CuentaBancaria emisor = buscarCuenta(dni);
		if (emisor != null) {
			sesionActual.credito(monto,emisor); //Las transferencias se ven reflejadas desde el lado del receptor como un credito
			actualizarSaldo();
			
		}
		else {
			//emisor invalido
		}
	}
	
	
	
	private void actualizarSaldo() {
		
	}
}
//Se asume que el apellido no contiene el caracter x
//modificar credito y debito para que pidan monto y una CuentaBancaria