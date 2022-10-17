package TDALista;

public class DNodo<E> implements Position<E> {

	protected E elemento;
	protected DNodo<E> ant;
	protected DNodo<E> sig;
	
	
	public E element() {
		return elemento;
	}
	
	public DNodo(E item,DNodo<E> siguiente,DNodo<E> anterior) {
		elemento = item;
		this.sig = siguiente;
		this.ant = anterior;
	}
	
	DNodo(E element) {
		this(element,null,null);
	}

	public DNodo<E> getAnt() {
		return ant;
	}

	public void setAnt(DNodo<E> ant) {
		this.ant = ant;
	}

	public DNodo<E> getSig() {
		return sig;
	}

	public void setSig(DNodo<E> sig) {
		this.sig = sig;
	}

	@Override
	public String toString() {
		return "DNodo [elemento=" + elemento + ", ant=" + ant + ", sig=" + sig + "]";
	}

	public void setElemento(E element) {
		elemento = element;
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
