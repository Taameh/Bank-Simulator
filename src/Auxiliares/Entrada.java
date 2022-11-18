package Auxiliares;

/**
 * Clase Entrada 
 * Modela la interfaz Entry
 * @author Maxi Fernandez - Tomas Arroyo
 *
 * @param <K> representa la clave
 * @param <V> representa el valor
 */
public class Entrada<K,V> implements Entry<K,V> {
	
	private K key;
	private V value;
	
	/**
	 * Crea una nueva entrada
	 * @param k clave
	 * @param v valor
	 */
	public Entrada(K k,V v ) {
		this.key = k;
		this.value = v;
	}


	public K getKey() {
		return key;
	}

	
	public V getValue() {
		return value;
	}
	
	/**
	 * Setea la clave de la entrada 
	 * @param k clave de la entrada
	 */
	public void setClave(K k) {
		this.key = k;
	}
	
	/**
	 * Setea el valor de la entrada
	 * @param v valor de la entrada
	 */
	public void setValor(V v) {
		this.value = v;
	}

	/**
	 * Retorna una cadena con la clave y el valor
	 * @return una cadena con la clave y el valor
	 */
	public String toString() {
		return "Entrada [clave=" + key + ", valor=" + value + "]";
	}
	
	
	
	
	
	
	
	
	

}
