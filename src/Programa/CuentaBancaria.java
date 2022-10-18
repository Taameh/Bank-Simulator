package Programa;

import TDALista.PositionList;

public class CuentaBancaria {
	
	//ATRIBUTOS
	
	protected String nombre;
	protected String apellido;
	protected int DNI;
	protected int saldo;
	protected PositionList<Transaccion> historial;
	
	// CONSTRUCTOR
	
	public CuentaBancaria(String n,String a,int d,int s,PositionList<Transaccion> e) {
		nombre = n;
		apellido = a;
		DNI = d;
		saldo = s;
		historial = e;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	public Iterable<Transaccion> getHistorial(){
		return historial;	
	}
	
	
	
}
