package Programa;

import java.util.Iterator;
import Exceptions.*;
import TDALista.*;
import TDACola.*;
import TDADiccionario.*;
import TDAPila.*;
import TDAColaCP.*;

/**
 * Clase que modela una cuenta bancaria
 * @author Arroyo Tomas (126078) - Fernandez Maximo (131672)
 * @param nombre del propietario de la cuenta
 * @param apellido del propietario de la cuenta
 * @param DNI del propietario de la cuenta
 * @param saldo de la cuenta
 * @param historial de transacciones de la cuenta
 */
public class CuentaBancaria {
	
	//ATRIBUTOS
	
	protected String nombre;
	protected String apellido;
	protected int DNI;
	protected float saldo;
	protected PositionList<Transaccion> historial;
	
	// CONSTRUCTOR
	

	/**
	 * inicializa el nombre, apellido, DNI, saldo, historial seteandolos
	 * @param n
	 * @param a
	 * @param d
	 * @param s
	 */
	public CuentaBancaria(String n,String a,int d,float s) {

		nombre = n;
		apellido = a;
		DNI = d;
		saldo = s;
		historial = new ListaDoblementeEnlazada<Transaccion>();
	}
	
	
	/**
	 * Consulta el nombre del duenio de la CuentaBancaria
	 * @return Nombre del duenio de la cuenta
	 **/
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * asigna el nombre del duenio de la CuentaBancaria
	 * @param nombre
	 **/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Consulta el apellido del duenio de la CuentaBancaria
	 * @return Apellido del duenio de la cuenta
	 **/
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Setea el apellido del duenio de la CuentaBancaria
	 * @param apellido
	 **/
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/**
	 * Consulta el apellido del duenio de la CuentaBancaria
	 * @return Apellido del duenio de la cuenta
	 **/
	public int getDNI() {
		return DNI;
	}
	
	/**
	 * Setea el apellido del duenio de la CuentaBancaria
	 * @param dNI
	 **/
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	
	/**
	 * Consulta el Saldo del duenio de la CuentaBancaria
	 * @return Saldo del duenio de la cuenta
	 **/
	public float getSaldo() {
		return saldo;
	}
	
	/**
	 * Setea el apellido del duenio de la CuentaBancaria
	 * @param saldo
	 **/
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	/**
	 * Consulta el historial de transacciones de la cuenta
	 * @return historial de la cuenta
	 */
	public Iterable<Transaccion> getHistorial(){
		return historial;	
	}
	
	/**
	 * Inserta una cadena String en una cola
	 * @param s
	 * @param cola
	 */
	public static void insertarCadenaCola(String s,Queue<Character> cola) {
		for (int i = 0; i < s.length(); i++) {
			cola.enqueue(s.charAt(i));
		}
	}
	
	/**
	 * inserta una cadena String en una pila
	 * @param s
	 * @param pila
	 */
	public static void insertarCadenaPila(String s,Stack<Character> pila) {
		for (int i = 0; i < s.length(); i++) {
			pila.push(s.charAt(i));
		}
	}
	
	
	//SE ASUME QUE EL APELLIDO NO LLEVA X
	/**
	 * Verifica si la cadena de String respeta el formato AxA’A’. Donde A es el apellido del usuario, A’ su apellido invertido, y x el carácter ‘x’ 
	 * @param cadena
	 * @return verdadero si la cadena es valida, falso caso contrario
	 * @throws LogueoInvalidoException si la cadena es invalida
	 */
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
	
	/**
	 * Consulta las ultimas n transacciones realizadas 
	 * @param n transacciones a consultar
	 * @return Lista con las ultimas n transacciones
	 */
	public PositionList<Transaccion> ultimasN(int n){
		
		PositionList<Transaccion> l = new ListaDoblementeEnlazada<Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		int cant = 0;
		
		while (it.hasNext() && cant != n) {
			l.addFirst(it.next());
			cant++;
		}
		
		return l;
		
	}
	
	/**
	 * Realiza un debito de monto (monto) hacia el beneficiario
	 * @param monto de la cuenta bancaria
	 * @param beneficiario a recibir el monto
	 * @throws SaldoInsuficienteException si el monto a debitar es insuficiente
	 */
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
	
	/**
	 * Se acredita un monto al emisor de la cuenta
	 * @param monto a acreditar
	 * @param emisor
	 */
	public void credito(float monto,CuentaBancaria emisor) {
		
		saldo = saldo + monto;
		
		Transaccion nueva = new Transaccion('c',monto,emisor,this);
		
		historial.addLast(nueva);
	}
	
	//Dictionary transaccionesMismoValor() retorna un diccionario con Entry<Valor, Transaccion>
	
	/**
	 * Devuelve un Diccionario con entradas de un monto y transacciones de un mismo valor
	 * @return Diccionario de entradas de monto y transaccion
	 */
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
	
	/**
	 * Devuelve una lista con aquellas transacicones realizadas en la fecha pasada como parametro
	 * @param fecha
	 * @return Lista con las transacciones de una fecha determinada
	 */
	
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
	
	/**
	 * Devuelve una Lista de transacciones, dichas transacciones deben superar el monto m
	 * si deb es true, la lista incluye debitos, lo mismo con cred
	 * @param m cantidad de monto
	 * @param deb 
	 * @param cred
	 * @return una Lista de transacciones
	 */
	
	public PositionList<Transaccion> transaccionesEncimaDe(int m,boolean deb,boolean cred) {
		
		PositionList<Transaccion> nueva = new ListaDoblementeEnlazada<Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		Transaccion x;
		
		while (it.hasNext()) {
			
			x = it.next();
			if (deb == true  && x.getMonto() > m) {
				if (x.getTipo() == 'd') 
					nueva.addFirst(x);
			}else {
				if (cred == true && x.getMonto() > m) {
					if (x.getTipo() == 'c') 
						nueva.addFirst(x);
				}
			}
		}
		
		return nueva;
	}
	
	/**
	 * Devuelve una colaConPrioridad de las n transacciones de mayor valor, si n es menor a la cantidad de transacciones,
	 * devuelve todas las transacciones realizadas
	 * @param n
	 * @return colaConPrioridad 
	 */
	public PriorityQueue<Float,Transaccion> transaccionesPorValor(int n){
		
		PriorityQueue<Float,Transaccion> nueva = new ColaCPHeap<Float,Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		Transaccion x;
		int cant = 0;
		
		try {
			while (it.hasNext() && cant != n) {
				x = it.next();
				nueva.insert(x.getMonto(),x);
				cant++;
			}
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nueva;
		
	}
	
	/**
	 * Devuelve el saldo en una fecha especifica
	 * @param dia
	 * @param mes
	 * @param año
	 * @return saldo al inicio del dia
	 */
	public float saldoEnFechaEspecifica(int dia, int mes, int año) {
		float toReturn = saldo;
		boolean corte = false;
		Iterator<Transaccion> transacciones = historial.iterator();
		
		while (!corte && transacciones.hasNext()) {
			Transaccion t = transacciones.next();
			if (t.getAño() > año) {
				if (t.getTipo() == 'c')
					toReturn = toReturn - t.getMonto();
				else if (t.getTipo() == 'd')
					toReturn = toReturn + t.getMonto();
			}
			else if (t.getAño() == año && t.getMes() > mes) {
				if (t.getTipo() == 'c')
					toReturn = toReturn - t.getMonto();
				else if (t.getTipo() == 'd')
					toReturn = toReturn + t.getMonto();
			}
			else if (t.getAño() == año && t.getMes() == mes && t.getDia() > dia) {
				if (t.getTipo() == 'c')
					toReturn = toReturn - t.getMonto();
				else if (t.getTipo() == 'd')
					toReturn = toReturn + t.getMonto();
			}
			else {
				corte = true;
			}
		}
		return toReturn;
	}
	
	
	
	
	
}
