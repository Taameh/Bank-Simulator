package Programa;

import java.util.Iterator;
import Exceptions.*;
import TDALista.*;
import TDACola.*;
import TDADiccionario.*;
import TDAPila.*;
import TDAColaCP.*;

/**
 * @author Arroyo Tomas (126078) - Fernandez Maximo (131672)
 */
public class CuentaBancaria {
	
	protected String nombre;
	protected String apellido;
	protected int DNI;
	protected float saldo;
	protected PositionList<Transaccion> historial;
	

	/**
	 * inicializa el nombre, apellido, DNI, saldo, historial seteandolos
	 * @param n nombre del propietario de la cuenta
	 * @param a apellido del propietario de la cuenta
	 * @param d dni del propietario de la cuenta
	 * @param s saldo del propietario de la cuenta
	 */
	public CuentaBancaria(String n,String a,int d,float s) {

		nombre = n;
		apellido = a;
		DNI = d;
		saldo = s;
		historial = new ListaDoblementeEnlazada<Transaccion>();
	}
	
	
	/**
	 * Consulta el nombre del propietario de la CuentaBancaria
	 * @return Nombre del propietario de la cuenta
	 **/
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * asigna el nombre del propietario de la CuentaBancaria
	 * @param nombre ingresado
	 **/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Consulta el apellido del propietario de la CuentaBancaria
	 * @return Apellido del propietario de la cuenta
	 **/
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Setea el apellido del propietario de la CuentaBancaria
	 * @param apellido ingresado
	 **/
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/**
	 * Consulta el DNI del duenio de la CuentaBancaria
	 * @return DNI del propietario de la cuenta
	 **/
	public int getDNI() {
		return DNI;
	}
	
	/**
	 * Setea el apellido del propietario de la CuentaBancaria
	 * @param dNI ingresado
	 **/
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	
	/**
	 * Consulta el Saldo del propietario de la CuentaBancaria
	 * @return Saldo del propietario de la cuenta
	 **/
	public float getSaldo() {
		return saldo;
	}
	
	/**
	 * Setea el SALDO del duenio de la CuentaBancaria
	 * @param saldo ingresado
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
	 * @param s cadena a insertar
	 * @param cola donde encolar la cadena
	 */
	public static void insertarCadenaCola(String s,Queue<Character> cola) {
		for (int i = 0; i < s.length(); i++) {
			cola.enqueue(s.charAt(i));
		}
	}
	
	/**
	 * inserta una cadena String en una pila
	 * @param s cadena a insertar
	 * @param pila donde pushear la cadena
	 */
	public static void insertarCadenaPila(String s,Stack<Character> pila) {
		for (int i = 0; i < s.length(); i++) {
			pila.push(s.charAt(i));
		}
	}
	
	
	//SE ASUME QUE EL APELLIDO NO LLEVA X
	/**
	 * Verifica si la cadena de String respeta el formato AxA’A’. Donde A es el apellido del usuario, A’ su apellido invertido, y x el carácter ‘x’
	 * se asume que el apellido no lleva x 
	 * @param cadena a validar
	 * @return verdadero si la cadena es valida, falso caso contrario
	 */
	public boolean validarCadena(String cadena){
		
		
		boolean valida = true;
		
		Queue<Character> q = new ColaConArregloCircular<Character>();
		insertarCadenaCola(cadena,q);
		

		Stack<Character> pilaAux1= new PilaEnlazada<Character>();
		Stack<Character> pilaAux2= new PilaEnlazada<Character>();
		int tamanioOriginal = q.size();
		try {
			if (!((q.size() - 1 ) % 3 == 0))
				valida = false;
			while(valida && q.size() > (((tamanioOriginal/3)*2)+1)) {
				Character aux = q.dequeue();
				pilaAux1.push(aux);
				pilaAux2.push(aux);
			}
			if (! (q.dequeue() == 'x') )  //Comprueba caracter x
				valida = false;
			else {
				while(valida && q.size()>tamanioOriginal/3) { //reconoce parte inversa
					if((q.dequeue() != pilaAux1.pop())) 
						valida = false;
				}
				while(valida && !pilaAux2.isEmpty()) { //reconoce parte al derecho
					if((q.dequeue() != pilaAux2.pop())) 
						valida = false;
				}
				if (!q.isEmpty())
					valida = false;
			}
			
		}
		catch(EmptyQueueException e) {System.out.println("La se encuentra vacia");}
		catch(EmptyStackException e) {System.out.println(e.getMessage());}
		return valida;
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
	 * @return true si la transaccion fue realizada y false en caso contrario.
	 */
	public boolean debito(float monto,CuentaBancaria beneficiario) {
		boolean toReturn;

		if (saldo < monto) 
			toReturn = false;
		else {
			saldo = saldo - monto;
			Transaccion nueva = new Transaccion('d',monto,this,beneficiario);
			historial.addFirst(nueva);
			toReturn = true;
		}
		return toReturn;
	}
	
	/**
	 * Se acredita un monto a la cuenta
	 * @param monto a acreditar
	 * @param emisor del credito
	 */
	public void credito(float monto,CuentaBancaria emisor) {
		
		saldo = saldo + monto;
		
		Transaccion nueva = new Transaccion('c',monto,emisor,this);
		
		historial.addFirst(nueva);
	}
	
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
	 * Devuelve una lista con aquellas transacciones realizadas en la fecha pasada como parametro
	 * @param fecha especificada
	 * @return Lista con las transacciones de una fecha determinada
	 */
	
	public PositionList<Transaccion> historialDia(String fecha){
		
		PositionList<Transaccion> nueva = new ListaDoblementeEnlazada<Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		
		Transaccion x;
		
		while (it.hasNext()) {
			x = it.next();
			if (x.getFecha().equals(fecha)) {
				nueva.addLast(x);
			}
		}
		
		
		return nueva;
		
	}
	
	/**
	 * Devuelve una Lista de transacciones, dichas transacciones deben superar el monto m
	 * si deb es true, la lista incluye debitos, lo mismo con cred
	 * @param monto cantidad de monto
	 * @param deb si es de tipi debito
	 * @param cred si es de tipo debito 
	 * @return una Lista de transacciones
	 */
	
	public PositionList<Transaccion> transaccionesEncimaDe(float monto,boolean deb,boolean cred) {
		
		PositionList<Transaccion> nueva = new ListaDoblementeEnlazada<Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		Transaccion x;
		
		while (it.hasNext()) {
			
			x = it.next();
			if (deb == true  && x.getMonto() > monto) {
				if (x.getTipo() == 'd') 
					nueva.addFirst(x);
			}
			
			if (cred == true && x.getMonto() > monto) {
				if (x.getTipo() == 'c') 
					nueva.addFirst(x);
			}
		
		}
		
		return nueva;
	}
	
	/**
	 * Devuelve una ColaCPHeap de las transacciones todas las transacciones realizadas
	 * @return ColaCPHeap con las transacciones realizadas  
	 */
	public PriorityQueue<Float,Transaccion> transaccionesPorValor(){
		
		PriorityQueue<Float,Transaccion> nueva = new HeapPQueue<Float,Transaccion>();
		Iterator<Transaccion> it = historial.iterator();
		Transaccion x;
		
		try {
			while (it.hasNext()) {
				x = it.next();
				nueva.insert(x.getMonto(),x);
			}
		} catch (InvalidKeyException e) {
			System.out.println("Key invalida");
		}
		
		return nueva;
		
	}
	
	/**
	 * Devuelve el saldo de la cuenta correspondiente a el final de una fecha especifica
	 * @param dia especificado
	 * @param mes especificado
	 * @param anio especificado
	 * @return saldo en la fecha especificada
	 */
	public float saldoEnFechaEspecifica(int dia, int mes, int anio) {
		float toReturn = saldo;
		boolean corte = false;
		Iterator<Transaccion> transacciones = historial.iterator();
		
		while (!corte && transacciones.hasNext()) {
			Transaccion t = transacciones.next();
			if (t.getAnio() > anio) {
				if (t.getTipo() == 'c')
					toReturn = toReturn - t.getMonto();
				else if (t.getTipo() == 'd')
					toReturn = toReturn + t.getMonto();
			}
			else if (t.getAnio() == anio && t.getMes() > mes) {
				if (t.getTipo() == 'c')
					toReturn = toReturn - t.getMonto();
				else if (t.getTipo() == 'd')
					toReturn = toReturn + t.getMonto();
			}
			else if (t.getAnio() == anio && t.getMes() == mes && t.getDia() > dia) {
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
