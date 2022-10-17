package TDALista;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class DoublyLinkedList<E> implements PositionList<E> {

	protected DNodo<E> trailer;
	protected DNodo<E> header;
	protected int tamanio;
	
	public DoublyLinkedList() {
		header = new DNodo<E>(null);
		trailer = new DNodo<E>(null);
		header.setSig(trailer);
		header.setAnt(null);
		trailer.setAnt(header);
		trailer.setSig(null);
		tamanio = 0;
	}
	
	public int size() {
		
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		
		return tamanio == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		
		if (isEmpty())
			throw new EmptyListException("LISTA VACIA");
		
		return header.getSig();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("LISTA VACIA");
		
		return trailer.getAnt();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		DNodo<E> n = checkPosition(p);
		
		if (n == trailer.getAnt())
			throw new BoundaryViolationException("Next: Suiguiente de ultimo");
		
		return n.getSig();
	}
	
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		DNodo<E> retorno = null;
		try {
			
			if (p == null)
				throw new InvalidPositionException("POSICION NULA");
//			if (p.element() == null)
//				throw new InvalidPositionException("p ELIMINADA PREVIAMENTE");
			if (p == header)
				throw new InvalidPositionException("POSICION INVALIDA");
			if (p == trailer)
				throw new InvalidPositionException("POSICION INVALIDA");
			if(tamanio==0)
				throw new InvalidPositionException("");
			
			retorno =  (DNodo<E>) p;
			
		}catch(ClassCastException e) {
			throw new InvalidPositionException("p NO ES UN NODO DE LISTA");
			
		}
		return retorno;
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		DNodo<E> prev = n.getAnt();
		
		if (prev == header)
			throw new BoundaryViolationException("NO HAY UN ANTERIOR PARA LA PRIMERA POSICION");
		
		return prev;
	}

	@Override
	public void addFirst(E element) {
		
		DNodo<E> nuevo = new DNodo<E>(element,null,null);
		
		if (tamanio == 0) {
			header.getSig().setAnt(nuevo);
			nuevo.setSig(header.getSig());
			nuevo.setAnt(header);
			header.setSig(nuevo);
			trailer.setAnt(nuevo);
		}
		else {
			header.getSig().setAnt(nuevo);
			nuevo.setSig(header.getSig());
			nuevo.setAnt(header);
			header.setSig(nuevo);
		}
		
		tamanio++;
		
	}

	@Override
	public void addLast(E element) {
		
		
		DNodo<E> nuevo = new DNodo<E>(element,trailer,trailer.getAnt());
		trailer.getAnt().setSig(nuevo);
		trailer.setAnt(nuevo);
		tamanio++;
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		nuevo.setSig(pos.getSig());
		nuevo.setAnt(pos);
		nuevo.getSig().setAnt(nuevo);
		pos.setSig(nuevo);
		tamanio++;
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		
		checkPosition(p);
		try {
			if (p == first())
				addFirst(element);
			else {
				addAfter(prev(p),element);
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		
		DNodo<E> v = checkPosition(p);
		
		if (tamanio == 0)
			throw new InvalidPositionException("LISTA VACIA");
		
		tamanio--;
		DNodo<E> vPrev = v.getAnt();
		DNodo<E> vNext = v.getSig();
		vPrev.setSig(vNext);
		vNext.setAnt(vPrev);
		E vElem = v.element();
		// Desliga la posicion de la lista y la hace invalida
		v.setSig(null);
		v.setAnt(null);
		v.setElemento(null);
		return vElem;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		
		DNodo<E> n = checkPosition(p);
		E aux = n.element();
		n.setElemento(element);
		
		return aux;
	}

	public Iterator<E> iterator() {
		
		// Creo un elementIteratorsobre la lista this a iterar;
			return new ElementIterator<>(this);
		}

		/*Retorna un objeto Iterable conteniendo las posiciones asociadas a
		los elementos almacenados en la ED*/

	public Iterable<Position<E>> positions() {
			
		PositionList<Position<E>> p = new DoublyLinkedList<>();
		if (!isEmpty()) {
				
			Position<E> pos = header.getSig();
				
			try {
				while (pos != last()) {
					p.addLast(pos);
					pos = next(pos);
				}
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			p.addLast(pos);
		}
			
		return p;
	}

}
