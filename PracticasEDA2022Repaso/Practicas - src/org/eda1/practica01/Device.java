package org.eda1.practica01;

import java.util.Iterator;
import java.util.LinkedList;

public class Device implements Iterable<String>{
	private static int numDevices=0; //contador de dispositivos...atributo estatico
	private String name;
	private int id;
	protected LinkedList<String> words; 
	
	public Device() {
		//constructor predeterminado (3 lineas)
		//...
		this.name = "noname";
		this.words = new LinkedList<String>();
		this.id = ++numDevices;
	}
		
	public Device(String name) {
		//Si name == null hay que lanzar una excepcion de tipo RuntimeException()
		//4 lineas
		//...
		if (name == null) throw new RuntimeException("El atributo name no puede ser nulo");
		this.name = name.toLowerCase();
		this.words = new LinkedList<String>();
		this.id = ++numDevices;
	}
	
	public static void inicializaNumDevices() {
		numDevices = 0;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void clear() {
		this.words.clear();
	}
	
	public void sendMessage(String msg) {
		if (msg == null) return;
		//1 for()
		//Haced uso del metodo split(" ") para segmentar la frase en palabras, eliminando los espacios en blanco 
		//...
		for (String palabra : msg.toLowerCase().split(" ")) {
			if (palabra.isEmpty()) continue;
			if (this.words.contains(palabra)) continue;
			this.words.add(palabra);
			
		}
	}
	
	public boolean contains(String word) {
		return this.words.contains(word.toLowerCase());
	}
	
		
	public boolean substitute(String word1, String word2) {
		//Prohibido hacer uso de ListIterator<>
		//Hacemos uso de indexOf()...
		//...
		int index = this.words.indexOf(word1);
		if (index == -1 ) return false;
		this.words.remove(word1);
		if (word2 == null) {
			this.words.remove(word1);
		}else {
			this.words.add(index, word2);
		}
		return true;
	}
	
	@Override
	public String toString() {
		//1 unica linea
		return this.id +".- " + this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.name.equals(((Device)o).name);
	}

	@Override
	public Iterator<String> iterator() {
		//Iterar sobre Device equivale a iterar sobre la estructura LinkedList<String> words
		//1 unica linea
		return this.words.iterator();
	}
}