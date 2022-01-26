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
	
	public int getFrecuencia(String word) {
		Integer freq = //...
		return //... 
	}
	
	public int getSumaFrecuencias() {
		int suma = 0;
		//1 for()
		//...
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
	
	public boolean add(String...lineas) {
		if (lineas == null) return false;
		Integer cont = null;
		//2 for() anidados
		//Atencion al uso de la expresion regular "[0123456789/(/)+-;,.¿?¡! ]+"
		//Atencion (tambien) al uso del metodo Auxiliar.isStopWord() --> ignoramos las palabras que se consideran stopWords
		
		return true;
	}
	
	public void remove(String palabra) {
		this.texto.remove(palabra);
	}
	
	public Articulo fusionar(Articulo otro) {
		Articulo aux = new Articulo(this.articuloID + "+" + otro.articuloID);
		Integer cont = null;
		//1 for()
		//...
		
		return aux;
	}
	
	public boolean sustituir(String palabra1, String palabra2) {
		Integer contW1 = null;
		Integer contW2 = null;
		if (palabra1 == null) return false;
		//Reglas de sustitucion
		//Si palabra1 no existe --> return false
		//Si palabra2 es igual a null --> borramos palabra1 y return true
		//Sustituimos palabra1 por palabra2 teniendo en cuenta que palabra2 puede que sea (o no) una palabra que exista en el articulo
		//Es decir, habra que tener en cuenta que contador(palabra2) puede ser null o igual a un valor > 0
		//Habra que actualizar el contador de palabra1, de manera que contador(palabra1) = contador(palabra1) + contador(palabra2)
		//...
		return true;
	}
	
	@Override
	public String toString() {
		return this.articuloID + ": " + this.texto.toString();
	}
	
	@Override
	public Iterator<Entry<String, Integer>> iterator() {
		//Iterar sobre un articulo equivale a iterar sobre el conjunto de pares (palabra, frecuencia) que contiene
		return //... 1 sola linea...
	}
	
	@Override
	public int compareTo(Articulo otro) {
		//Orden natural: articuloID (ascendente)
		return //...
	}
}