package Auxiliares;

/**
 * Clase Nodo
 * @author Maxi Fernandez - Tomas Arroyo
 *	
 * @param <E> tipo del elemento
 */

public class Nodo <E>{
	private E element;
	private Nodo<E> next;
	
	/**
	 * Crea una instancia de un nodo 
	 * @param e rotulo del nodo
	 * @param n nodo siguiente
	 */
	public Nodo (E e, Nodo<E> n){
		element = e;
		next = n;
	}
	
	/**
	 * Setea el elemento pasado por parametro
	 * @param e elemento a setear
	 */
	public void setElement(E e) {
		element = e;
	}
	
	/**
	 * Setea el Nodo pasado por parametro como siguiente del Nodo original
	 * @param n nodo a setear
	 */
	public void setNext(Nodo<E> n) {
		next = n;
	}
	
	/**
	 * Devulve el elemento
	 * @return element
	 */
	public E getElement() {
		return element;
	}
	
	/**
	 * Devuelve el Nodo siguiente
	 * @return nodo siguiente
	 */
	public Nodo<E> getNext(){
		return next;
	}

}
