package Programa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaccion {
	
	// ATRIBUTOS
	
	protected char tipo;
	protected int monto;
	protected String fecha;
	protected String hora;
	protected CuentaBancaria emisor;
	protected CuentaBancaria receptor;
	
	//CONSTRUCTOR
		
	public Transaccion(char t,int m,CuentaBancaria e,CuentaBancaria r) {
		tipo = t;
		monto = m;
		emisor = e;
		receptor = r;
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		Date f = new Date();
		
		fecha = formatoFecha.format(f);
		hora = formatoHora.format(f);
		
	}
	
	// METODOS
	
	public char getTipo() {
		return tipo;
	}
	
	public int getMonto() {
		return monto;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getHora() {
		return hora;
	}
	
	public CuentaBancaria getEmisor() {
		return emisor;
	}
	
	public CuentaBancaria getReceptor() {
		return receptor;
	}
	
}
