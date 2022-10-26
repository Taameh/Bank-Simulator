package Programa;

import java.util.Iterator;
import Exceptions.*;
import TDALista.*;
import TDACola.*;
import TDADiccionario.*;
import TDAPila.*;
import TDAColaCP.*;

public class CuentaBancaria {
	
	//ATRIBUTOS
	
	protected String nombre;
	protected String apellido;
	protected int DNI;
	protected int saldo;
	protected PositionList<Transaccion> historial;
	
	// CONSTRUCTOR
	
	public CuentaBancaria(String n,String a,int d,int s) {
		nombre = n;
		apellido = a;
		DNI = d;
		saldo = s;
		historial = new ListaDoblementeEnlazada<Transaccion>();
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
	
	//formato: AxA’A’
	
	public static void insertarCadenaCola(String s,Queue<Character> cola) {
		for (int i = 0; i < s.length(); i++) {
			cola.enqueue(s.charAt(i));
		}
	}
	
	public static void insertarCadenaPila(String s,Stack<Character> cola) {
		for (int i = 0; i < s.length(); i++) {
			cola.push(s.charAt(i));
		}
	}
	
	
	//SE ASUME QUE EL APELLIDO NO LLEVA X
	
	public boolean validarCadena(String cadena) {
		
		
		boolean valido = true;
		
		Queue<Character> col = new ColaConArregloCircular<Character>();
		insertarCadenaCola(cadena,col);
		
		Stack<Character> s = new PilaEnlazada<Character>();
		Stack<Character> s2 = new PilaEnlazada<Character>();
		Queue<Character> c2 = new ColaConArregloCircular<Character>();
		
		insertarCadenaPila(getApellido(),s);
		insertarCadenaPila(getApellido(),s2);
		insertarCadenaCola(getApellido(),c2);
		
		try {
		
			while (col.front() != 'x' && valido && !col.isEmpty()) {
				Character aux = col.dequeue();
				if (aux != c2.dequeue())
					valido = false;
			}
			
			if (col.front() == 'x')
				col.dequeue();
			else
				valido = false;
			
			
			while (!col.isEmpty() && !s.isEmpty() && valido) {
				if (s.pop() != col.dequeue())
					valido = false;
			}
			
			while (!col.isEmpty() && !s2.isEmpty() & valido) {
				if (s2.pop() != col.dequeue()) {
					valido = false;
				}
			}
				
		}catch(EmptyQueueException e) {
			e.printStackTrace();
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valido;
	}
	
	public PositionList<Transaccion> ultimasN(int n){
		
		PositionList<Transaccion> l = new ListaDoblementeEnlazada<Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		int cant = 0;
		
		while (it.hasNext() && cant != n) {
			l.addLast(it.next());
			cant++;
		}
		
		return l;
		
	}
	
	public void debito(int monto,CuentaBancaria beneficiario) throws SaldoInsuficienteException {
		
		if (getSaldo() < monto) 
			throw new SaldoInsuficienteException("NO HAY SUFICIENTE SALDO PARA DEBITAR");
		
		setSaldo(getSaldo() - monto);
		
		Transaccion nueva = new Transaccion('d',monto,this,beneficiario);
		
		historial.addLast(nueva);
			
	}
	
	public void credito(int monto,CuentaBancaria emisor) {
		
		setSaldo(getSaldo() + monto);
		
		Transaccion nueva = new Transaccion('c',monto,emisor,this);
		
		historial.addLast(nueva);
	}
	
	//Dictionary transaccionesMismoValor() retorna un diccionario con Entry<Valor, Transaccion>
	
	public Dictionary<Integer,Transaccion> transaccionesMismoValor() {
		
		Iterator<Transaccion> it = historial.iterator();
		
		Dictionary<Integer,Transaccion> nueva = new DiccionarioHashAbierto<Integer,Transaccion>();
		
		Transaccion x;
		try {
	
			while (it.hasNext()) {
				x = it.next();
				nueva.insert(x.getMonto(), x);
			}
			
		}catch(InvalidKeyException e) {
			e.printStackTrace();
		}
		
		
		return nueva;
		
	}
	
	
	//PositionList<Transaccion> historialDia(int fecha) retorna transacciones de una fecha determinada
	
	public PositionList<Transaccion> historialDia(String fecha){
		
		PositionList<Transaccion> nueva = new ListaDoblementeEnlazada<Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		
		Transaccion x;
		
		while (it.hasNext()) {
			x = it.next();
			if (x.getFecha() == fecha) {
				nueva.addLast(x);
			}
		}
		
		
		return nueva;
		
	}
	
	
	/*PriorityQueue<Entry<int, Transaccion>> TransaccionesEncimaDe(int m, boolean deb, boolean cred) retorna ColaConPrioridad
	  de entradas con monto y la transaccion, dichas entradas deben superar el monto m.
	  si deb es true, la lista incluye debitos, lo mismo con cred.*/
	
	public PriorityQueue<Integer,Transaccion> transaccionesEncimaDe(int m,boolean deb,boolean cred) {
		
		PriorityQueue<Integer,Transaccion> nueva = new ColaCPHeap<Integer,Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		Transaccion x;
		
		try {
			while (it.hasNext()) {
				
				x = it.next();
				if (deb == true) {
					if (x.getTipo() == 'd') 
						nueva.insert(x.getMonto(),x);
				}else {
					if (cred == true) {
						if (x.getTipo() == 'c') 
							nueva.insert(x.getMonto(), x);
					}
				}
			}
			
			
		}catch(InvalidKeyException e) {
			e.printStackTrace();
		}
		
		return nueva;
	}
	
	
	
	
	
}
