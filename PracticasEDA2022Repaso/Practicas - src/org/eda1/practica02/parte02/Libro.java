package org.eda1.practica02.parte02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import edaAuxiliar.AVLTree;

public class Libro implements Comparable<Libro>, Iterable<PalabraFrecuencia>{
	private String libroID; //El identificador de un libro es su titulo
	private Usuario usuarioPrestamo; //Referencia al usuario que tiene actualmente prestado el libro; null si no está prestado
	private AVLTree<PalabraFrecuencia> palabrasFrecuencias; //Almacenamos palabras y su frecuencia (evitando tener palabras repetidas)
	
	public Libro(String libroID) {
		//...
		this.libroID = libroID.trim().toLowerCase();
		this.palabrasFrecuencias = new AVLTree<PalabraFrecuencia>();
	}
	
	public String getLibroID() {
		return this.libroID;
	}
	
	public boolean getPrestado() {
		//Determinamos que el libro no está prestado si usuarioPrestamo tiene una direccion nula
		return (usuarioPrestamo == null) ? false : true;
	}
	
	public Usuario getUsuarioPrestamo() {
		return this.usuarioPrestamo;
	}
	
	public void setUsuarioPrestamo(Usuario usuario) {
		this.usuarioPrestamo = usuario;
	}
	
	public void clear() {
		this.palabrasFrecuencias.clear();
	}
		
	public void load(String fileName) {
		Scanner scan = null;
		String line = "";
		try {
			scan = new Scanner(new File(fileName));
		}catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		while (scan.hasNextLine()){
			line = scan.nextLine().trim();
			if (line.isEmpty()) continue;
			this.add(line.split(" "));
		}
		scan.close();
	}
	
	public void add(String...palabras) {
		PalabraFrecuencia palabraFreqAux = null;
		PalabraFrecuencia palabraFreqCurrent = null;
		for (String palabra: palabras) {
			//Tenemos que buscar cada palabra y ver si esta en el arbol; si esta, se incrementa su frecuencia; en caso contrario se inserta...
			// Hago uso de las 2 estructuras auxiliares para poder ver si existe o no la palabra.
			palabraFreqAux = new PalabraFrecuencia(palabra);
			palabraFreqCurrent = this.palabrasFrecuencias.find(palabraFreqAux);
			if (palabraFreqCurrent == null) {
				this.palabrasFrecuencias.add(palabraFreqAux);
			}else {
				palabraFreqCurrent.incFrecuencia();
			}
			
		}
	}
	
	public ArrayList<String> getPalabrasComunes(Libro otro) {
		ArrayList<String> aux = new ArrayList<String>();
		PalabraFrecuencia pFOtro = null;
		//Obtenemos un ArrayList<String> con las palabras en comun de los libros this y otro
		//1 for()
		// recorro el otro y busco cada palabra dentro de palabrasFrecuencia. Si existe lo añado al array.
		for (PalabraFrecuencia palabraFrecuencia : otro) {
			if (this.palabrasFrecuencias.contains(palabraFrecuencia)) {
				aux.add(palabraFrecuencia.getPalabra());
			}
		}
		
		return aux;
	}

	public double getDistancia(Libro otro) {
		int sim = 0;
		PalabraFrecuencia pFOtro;
		//Obtenemos la distancia euclidea entre los libros this y otro basandonos en las frecuencias de las palabras que tienen en comun
		//1 for()
		//...
		// Comparo las palabras del otro libro y si existe calculo la distancia segun la formula dada.
		for (PalabraFrecuencia palabraFrecuencia : otro) {
			pFOtro = this.palabrasFrecuencias.find(palabraFrecuencia);
			if (pFOtro != null) {
				sim += Math.pow(pFOtro.getFrecuencia() - palabraFrecuencia.getFrecuencia(), 2);
			}
		}
		return Math.sqrt(sim);
	}
		
	@Override
	public String toString() {
		return this.libroID + " -> " + this.palabrasFrecuencias.toString();
	}

	@Override
	public boolean equals(Object o) {
		return this.compareTo((Libro)o) == 0;
	}
	
	@Override
	public int compareTo(Libro o) {
		//Orden natural: libroID (ascendente)
		return this.libroID.compareTo(o.libroID);
	}
	
	@Override
	public Iterator<PalabraFrecuencia> iterator() {
		//Iterar sobre libro equivale a iterar sobre las palabras que contiene (palabrasFrecuencia)
		return this.palabrasFrecuencias.iterator();
	}
}