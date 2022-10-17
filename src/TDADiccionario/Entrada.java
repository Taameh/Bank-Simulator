package TDADiccionario;

public class Entrada<K,V> implements Entry<K,V> {
	
	private K clave;
	private V valor;
	
	public Entrada(K clave,V valor) {
		this.clave = clave;
		this.valor = valor;
	}

	@Override
	public K getKey() {
		return clave;
	}

	@Override
	public V getValue() {
		return valor;
	}

	public void setClave(K clave) {
		this.clave = clave;
	}

	public void setValor(V valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Entrada [clave=" + clave + ", valor=" + valor + "]";
	}
	
	
	
	
	
	
	
	
	

}
