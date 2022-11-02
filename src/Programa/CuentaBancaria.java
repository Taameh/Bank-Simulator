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
	protected float saldo;
	protected PositionList<Transaccion> historial;
	
	// CONSTRUCTOR
	
	public CuentaBancaria(String n,String a,int d,float s) {
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

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
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
	
	public boolean validarCadena(String cadena) throws LogueoInvalidoException {
		
		
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
		
				while (valido && !col.isEmpty() && col.front() != 'x') {
					Character aux = col.dequeue();
					if (aux != c2.dequeue()) 
						valido = false;
				}
				
				if (valido && !col.isEmpty() && col.front() == 'x')
					col.dequeue();
				else 
					valido = false;
				
				while (valido && !col.isEmpty() && !s.isEmpty()) {
					if (s.pop() != col.dequeue()) 
						valido = false;
				}
				
				while (valido && !col.isEmpty() && !s2.isEmpty()) {
					if (s2.pop() != col.dequeue()) 
						valido = false;
					
				}
					
				if(!col.isEmpty() || !s.isEmpty() || !s2.isEmpty()) 
					valido = false;
				
			}
		catch(EmptyQueueException | EmptyStackException e) {
			System.out.println("Error en el manejo de la estructura de datos.");
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
	
	public void debito(float monto,CuentaBancaria beneficiario) throws SaldoInsuficienteException {

		System.out.println("saldo previo: " + saldo);
		
		if (saldo < monto) 
			throw new SaldoInsuficienteException("No hay saldo suficiente para realizar la operación.");
		else {
			saldo = saldo - monto;

			System.out.println("saldo intermedio: " + saldo);
			
			Transaccion nueva = new Transaccion('d',monto,this,beneficiario);
			
			historial.addLast(nueva);
		}

		System.out.println("saldo post: " + saldo);
	}
	
	public void credito(float monto,CuentaBancaria emisor) {
		
		saldo = saldo + monto;
		
		Transaccion nueva = new Transaccion('c',monto,emisor,this);
		
		historial.addLast(nueva);
	}
	
	//Dictionary transaccionesMismoValor() retorna un diccionario con Entry<Valor, Transaccion>
	
	public Dictionary<Float,Transaccion> transaccionesMismoValor() {
		
		Iterator<Transaccion> it = historial.iterator();
		
		Dictionary<Float,Transaccion> nueva = new DiccionarioHashAbierto<Float,Transaccion>();
		
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
	
	public PriorityQueue<Float,Transaccion> transaccionesEncimaDe(int m,boolean deb,boolean cred) {
		
		PriorityQueue<Float,Transaccion> nueva = new ColaCPHeap<Float,Transaccion>();
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
