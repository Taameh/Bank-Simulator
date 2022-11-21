package TDAColaCP;

/**
 * Clase Default_Comparator
 * @author Maxi Fernandez - Tomas Arroyo
 *
 * @param <E> tipo generico
 */
public class DefaultComparator<E> implements java.util.Comparator<E> {

	public int compare(E a, E b) throws ClassCastException {
		return ((Comparable<E>) a).compareTo(b);
	}
}
