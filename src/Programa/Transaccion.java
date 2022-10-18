package Programa;

public class Transaccion {
	
	// ATRIBUTOS
	
	protected char tipo;
	protected int monto;
	protected CuentaBancaria emisor;
	protected CuentaBancaria receptor;
	
	//CONSTRUCTOR
	
	public Transaccion(char t,int m,CuentaBancaria e,CuentaBancaria r) {
		tipo = t;
		monto = m;
		emisor = e;
		receptor = r;
	}
	
	// METODOS
	
	public char getTipo() {
		return tipo;
	}
	
	public int getMonto() {
		return monto;
	}
	
	public CuentaBancaria getEmisor() {
		return emisor;
	}
	
	public CuentaBancaria getReceptor() {
		return receptor;
	}
	
	
	

}
