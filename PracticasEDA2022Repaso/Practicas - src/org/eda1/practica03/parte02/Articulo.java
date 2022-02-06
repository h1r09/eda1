package org.eda1.practica03.parte02;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Articulo implements Comparable<Articulo>, Iterable<Entry<String, Integer>> {
	private String articuloID;
	private TreeMap<String, Integer> texto;
	
	public Articulo(String articuloID) {
		this.articuloID = articuloID;
		this.texto =  new TreeMap<String, Integer>();
	}
	
	public String getArticuloID() {
		return this.articuloID;
	}
	
	public int size() {
		return this.texto.size();
	}
	
	public void clear() {
		this.texto.clear();
	}
	
	/**
	 * obtengo la frecuencia de la palabra, que Trimeo y paso a minúsculas.
	 * @param word
	 * @return -1 o la frecuencia
	 */
	public int getFrecuencia(String word) {
		Integer freq = texto.get(word.trim().toLowerCase());
		return freq == null ? -1 : freq;
	}
	
	/**
	 * Recorro los values de texto, los contadores y los voy sumando.
	 * @return suma de frecuencias
	 */
	public int getSumaFrecuencias() {
		int suma = 0;
		for (Integer contador : texto.values()) {
			suma += contador;
		}
		return suma;
	}
	
	public void load(String file) {
		Scanner scan = null;
		this.texto.clear();
		try {
			scan = new Scanner(new File(file));
		}catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		while (scan.hasNextLine()){
			this.add(scan.nextLine());
		}
	}
	
	/**
	 * Recorro el array de lineas, si la linea está vacia continuo.
	 * recorro la linea y lo paso a minúsculas y spliteo por la cadena dada. Añado el trim porque en el split no me quita el espacio en blanco
	 * Si es un stopWord o la palabra está vacia continuo.
	 * si la palabra es nueva la inicializo a 1, sino le sumo 1 a su frecuencia.
	 * @param lineas
	 * @return True is successful
	 */
	public boolean add(String...lineas) {
		if (lineas == null) return false;
		Integer cont = null;
		
		for (String linea : lineas) {
			if(linea.isEmpty()) continue;
			for (String palabra : linea.toLowerCase().split("[0123456789/(/)+-;,.¿?¡! ]+")) {
				if(palabra.isEmpty() || Auxiliar.isStopWord(palabra.trim())) continue;
				cont = this.texto.get(palabra);
				cont = cont == null ? 0 : cont;
				texto.put(palabra, cont+1);
			}
		}
		return true;
	}
	
	public void remove(String palabra) {
		this.texto.remove(palabra);
	}
	
	/**
	 * Fusiono 2 articulos sumando las frecuncias de las palabras coincidentes.
	 * recorro el otro articulo y en contador guardo la frecuencia de cada palabra
	 * si no existe le pongo el valor de la palabra, si existe sumo ambas.
	 * @param otro
	 * @return aux
	 */
	public Articulo fusionar(Articulo otro) {
		Articulo aux = new Articulo(this.articuloID + "+" + otro.articuloID);
		Integer cont = null;
		aux.texto.putAll(this.texto);
		for (Entry<String, Integer> palabra : otro) {
			cont = aux.getFrecuencia(palabra.getKey());
			cont = cont == -1 ? palabra.getValue() : cont + palabra.getValue();
			aux.texto.put(palabra.getKey(), cont);
		}
		return aux;
	}
	
	/**
	 * compruebo que palabra 1 existe, compruebo si palabra2 es null y si lo es borro la palabra1.
	 * obtengo las frecuencia de ambas palabras, compruebo si contw2 es null o tiene valor > 0.
	 * Añado la palabra2 sumando las frecuencias de ambas y borro palabra1.
	 * @param palabra1
	 * @param palabra2
	 * @return True if successful
	 */
	public boolean sustituir(String palabra1, String palabra2) {
		Integer contW1 = null;
		Integer contW2 = null;
		if (palabra1 == null) return false;
		if (!this.texto.containsKey(palabra1)) return false;
		if (palabra2 == null) {
			texto.remove(palabra1);
			return true;
		}
		
		contW1 = texto.get(palabra1);
		contW2 = texto.get(palabra2);
		
		contW2 = contW2 == null ? 0 : contW2;
		
		texto.put(palabra2, contW1+contW2);
		texto.remove(palabra1);
		return true;
	}
	
	@Override
	public String toString() {
		return this.articuloID + ": " + this.texto.toString();
	}
	
	/*
	 * Devuelve un iterator de tipo entry de <String, Integer>
	 */
	@Override
	public Iterator<Entry<String, Integer>> iterator() {
		return this.texto.entrySet().iterator();
	}
	
	/**
	 * comparo el objeto con el otro especificado
	 * @param o
	 * @return -1,0,1 menor, igual, mayor
	 */
	@Override
	public int compareTo(Articulo otro) {
		return this.articuloID.compareTo(otro.articuloID);
	}
}