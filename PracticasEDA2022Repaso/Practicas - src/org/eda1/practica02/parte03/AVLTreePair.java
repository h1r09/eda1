package org.eda1.practica02.parte03;

import java.util.ArrayList;

import edaAuxiliar.AVLTree;
import edaAuxiliar.Pair;

public class AVLTreePair<K extends Comparable<K>,V> {
	
	private AVLTree<Pair<K,V>> tree = new AVLTree<Pair<K,V>>(); 
	
	public boolean put(K key, V value) {
		//Si clave no existe, se inserta el par (key, value)
		//Si clave existe --> el valor antiguo se sustituye por value (actualizacion)
		Pair<K,V> par = //...
		//...
		return par == null;
	}
	
	public V get(K key) {
		//Devolvemos el valor asociado a la clave key
		//null si key no existe
		Pair<K,V> aux =//...
		return aux == null ?  ...
	}
	
	public boolean containsKey(K key) {
		return //...
	}
	
	public void clear() {
		this.tree.clear();
	}
	
	public boolean isEmpty() {
		return this.tree.isEmpty();
	}

	public int size() {
		return this.tree.size();
	}

	public ArrayList<K> keySet(){
		ArrayList<K> resultado  = new ArrayList<K>();
		//Devolvemos el conjunto de claves
		//1 for()
		//...	
		return resultado;
	}
	
	public ArrayList<V> values(){
		ArrayList<V> resultado  = new ArrayList<V>();
		//Devolvemos el conjunto de valores
		//1 for()
		//...
		return resultado;
	}
	
	public ArrayList<Pair<K,V>> entrySet(){
		ArrayList<Pair<K,V>> resultado  = new ArrayList<Pair<K,V>>();
		//Devolvemos el conjunto de pares
		//1 for()
		//...
		return resultado;
	}

	@Override
	public String toString() {
		return //... 
	}
}