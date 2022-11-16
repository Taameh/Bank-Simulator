package TDALista;

/**
 * Clase DNodo
 * Modela las operaciones sobre un nodo que almacena informacion e implementa a la interfaz position
 * @author Maxi Fernandez - Tomas Arroyo
 *
 * @param <E> tipo del elemento almacenado
 */
public class DNodo<E> implements Position<E> {

	protected E element;
	protected DNodo<E> prev;
	protected DNodo<E> next;
	
	
	public E element() {
		return element;
	}
	
	/**
	 * Crea un DNodo con rotulo item, con siguiente n y con anterior p
	 * @param item elemento a almacenar en el DNodo
	 * @param n DNodo siguiente
	 * @param p DNodo anterior
	 */
	public DNodo(E item,DNodo<E> n,DNodo<E> p) {
		element = item;
		this.next = n;
		this.prev =p;
	}
	
	/**
	 * Crea un DNodo con rotulo item, con siguiente nulo y anterior nulo
	 * @param element eleemnto a almacenar
	 */
	public DNodo(E element) {
		this(element,null,null);
	}
	
	/**
	 * Obtiene el DNodo anterior
	 * @return anterior DNodo
	 */
	public DNodo<E> getPrev() {
		return prev;
	}
	/**
	 * Setea el DNodo pasado por parametro como DNodo anterior 
	 * @param ant DNodo a setear como anterior al DNodo que recibe el mensaje
	 */
	public void setPrev(DNodo<E> ant) {
		this.prev = ant;
	}
	
	/**
	 * Obtiene el DNodo siguiente
	 * @return siguiente DNodo
	 */
	public DNodo<E> getNext() {
		return next;
	}

	/**
	 * Setea el DNodo pasado por parametro como DNodo siguiente
	 * @param ant DNodo a setear como siguiente al DNodo que recibe el mensaje
	 */
	public void setNext(DNodo<E> sig) {
		this.next = sig;
	}

	public void setElement(E element) {
		this.element = element;
	}
	
	
	
	

}
