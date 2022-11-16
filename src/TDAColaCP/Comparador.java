package TDAColaCP;

/**
 * Clase Comparador
 * @author Maxi Fernandez - Tomas Arroyo
 *
 * @param <E>
 */
public class Comparador<E> implements java.util.Comparator<E> {

	@SuppressWarnings("unchecked")
	@Override
	public int compare(E o1, E o2) {
		
		return ((Comparable<E>) o1).compareTo(o2);
	}
	
	

}
