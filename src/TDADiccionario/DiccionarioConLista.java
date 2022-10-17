package TDADiccionario;

import Exceptions.InvalidEntryException;

import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.*;

public class DiccionarioConLista<K,V> implements Dictionary<K,V> {
	
	protected DoublyLinkedList<Entry<K,V>> d;	
	
	public DiccionarioConLista() {
		
		d = new DoublyLinkedList<Entry<K,V>>();
	}
	
	
	public int size() {
		
		return d.size();
	}

	@Override
	public boolean isEmpty() {
		
		return d.isEmpty();
	}

	/**
	 * Busca una entrada con clave igual a una clave dada y la devuelve, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("CLAVE INVALIDA");
		for (Position<Entry<K,V>> p: d.positions())
			if (p.element().getKey().equals(key))
				return p.element();
			
		return null;
	}

	/**
	 * Retorna una colecci�n iterable que contiene todas las entradas con clave igual a una clave dada.
	 * @param key Clave de las entradas a buscar.
	 * @return Colecci�n iterable de las entradas encontradas.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		
		if (key == null) {
			throw new InvalidKeyException("key nula");
		}
		
		PositionList<Entry<K, V>> l = new DoublyLinkedList<Entry<K,V>>();
		
		for(Position<Entry<K,V>> p: d.positions())
			if (p.element().getKey().equals(key))
				l.addLast(p.element());
		
		return l;
	}

	/**
	 * Inserta una entrada con una clave y un valor dado en el diccionario y retorna la entrada creada.
	 * @param key Clave de la entrada a crear.
	 * @return value Valor de la entrada a crear.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		
		if (key == null)
			throw new InvalidKeyException("CLAVE INVALIDA");
		
		Entry<K,V> e = new Entrada<K,V>(key,value);
		d.addLast(e);
		
		return e;
	}

	/**
	 * Remueve una entrada dada en el diccionario y devuelve la entrada removida.
	 * @param e Entrada a remover.
	 * @return Entrada removida.
	 * @throws InvalidEntryException si la entrada no est� en el diccionario o es inv�lida.
	 */
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		for (Position<Entry<K,V>> p: d.positions())
			if (p.element() == e) {
				try {
					d.remove(p);
				} catch (InvalidPositionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return e;
			}
		throw new InvalidEntryException("LA ENTRADA NO SE ENCUENTRA EN EL DICCIONARIO");
	}

	/**
	 * Retorna una colecci�n iterable con todas las entradas en el diccionario.
	 * @return Colecci�n iterable de todas las entradas.
	 */
	public Iterable<Entry<K, V>> entries() {
		return d;
	}

}
