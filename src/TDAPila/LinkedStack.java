package TDAPila;

import Exceptions.EmptyStackException;


public class LinkedStack <E> implements Stack{
	private Nodo<E> top;
	private int size = 0;
	
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Object top() throws EmptyStackException {
		if (size == 0) {
			throw new EmptyStackException("La Pila esta vacia");
		}
		return top.getElement();
	}

	@Override
	public void push(Object element) {
		Nodo<E> n = new Nodo(element, top);
		top = n;
		size ++;
		
	}

	@Override
	public Object pop() throws EmptyStackException {
		if (size == 0) {
			throw new EmptyStackException("La Pila esta vacia");
		}
		Nodo <E> toReturn = top;
		top = top.getNext();
		size--;
		return toReturn.getElement();
	}

}
