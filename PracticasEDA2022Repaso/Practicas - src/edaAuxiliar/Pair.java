package edaAuxiliar;

import java.util.Map;

public class Pair <K extends Comparable<K>, V> implements Map.Entry<K, V>, Comparable<Pair<K,V>>{
	
	private final K key;
	private V value;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}

	@Override
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}
	
	@Override
	public String toString() {
		return "<" + key + ", " + value + ">";
	}

	@Override
	public int compareTo(Pair<K,V> o) {
		return this.key.compareTo(o.key);
	}
}
