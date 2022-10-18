package TDACola;

import Exceptions.EmptyQueueException;

public class ColaConArregloCircular <E> implements Queue<E>{
	
	private int head;
	private int tail;
	private E[] queue;
	/**
	 * Constructor de la cola
	 */

	
	public ColaConArregloCircular() {
		head = 0;
		tail = 0;
		queue = (E[]) new Object[20];
	}
	
	/**
	 * Retorna la cantidad de elementos de la cola.
	 */
	public int size() {
		return ((queue.length)-head+tail)%queue.length;
	}
	
	
	
	/**
	 * Retorna verdadero si la cola no tiene elementos y falso en caso contrario
	 */
	public boolean isEmpty() {
		return tail == head;
	}
	
	/**
	 * Retorna el elemento del frente de la cola. Si la cola está vacía se produce un error.
	 */
	public E front() throws EmptyQueueException{
		if (isEmpty()) {
			throw new EmptyQueueException ("Es imposible solicitar el frente de una cola vacia.");
		}
		return queue[head];
	}
	
	/**
	 *  Inserta el elemento e en el rabo de la cola.
	 */
		public void enqueue (E element) {
			if (size()==queue.length-1)
				expandirCola();
			queue[(tail)%queue.length] = element;
			tail = (tail+1)%(queue.length);
		}
	
	/**
	 * Elimina el elemento del frente de la cola y lo retorna. Si la cola está vacía se produce un error.
	 */
	public E dequeue () throws EmptyQueueException{
		if (isEmpty()) 
			throw new EmptyQueueException ("No hay elementos en la cola para entregar.");
		E toReturn = queue[head];
		queue[head] = null;
		head = (head+1)%(queue.length);
		return toReturn;		
	}
	
	private void expandirCola() {
		E[] newQueue = (E[]) new Object[queue.length*2];
		try {
			int i = 0;
			while(!isEmpty()) {
				newQueue[i] = dequeue();
				i++;
			}
			head = 0;
			tail = i;
			queue = newQueue;
		}catch(EmptyQueueException e){
			System.out.println(e.getMessage());
		}

	}
	

}
