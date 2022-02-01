package org.eda1.practica02.parte03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import edaAuxiliar.Pair;


public class Sujeto implements Comparable<Sujeto>, Iterable<Pair<String,ArrayList<Double>>> {

	private String sujetoID;
	AVLTreePair<String, ArrayList<Double>> pruebaPuntuaciones; // coleccoin de pares (nombrePrueba, lista de puntuaciones) 
		
	public Sujeto(String sujetoID) {
		//...
		this.sujetoID = sujetoID.trim();
		this.pruebaPuntuaciones = new AVLTreePair<String, ArrayList<Double>>();
	}
	
	public void clear() {
		this.pruebaPuntuaciones.clear();
	}
	
	public boolean add(String pruebaID, Double...puntuaciones) {
		ArrayList<Double> valores = new ArrayList<Double>(Arrays.asList(puntuaciones)); //intentamos evitar el uso de bucles en este metodo
		ArrayList<Double> current = pruebaPuntuaciones.get(pruebaID);
		//...
		if (current == null) {
			pruebaPuntuaciones.put(pruebaID, valores);
		}else {
			current.addAll(valores);
		}
		return current == null;
	}

	public double getMaximaPuntuacion() {
		double max = Double.MIN_VALUE;
		//2 for() anidados
		//...
		for (ArrayList<Double> valores : pruebaPuntuaciones.values()) {
			for (Double valor : valores) {
				if (valor > max) max = valor;
			}
		}
		return max;
	}
	
	public double getMaximaPuntuacion(String pruebaID) {
		double max = .0;
		ArrayList<Double> puntuaciones = pruebaPuntuaciones.get(pruebaID);
		if (puntuaciones != null) {
			for (Double puntuacion : puntuaciones) {
				if (puntuacion > max) max = puntuacion;
			}
		} else {
			return -1;
		}
		//1 for()
		//...
		return max;
	}
	
	public int getNumPuntuaciones() {
		int cont = 0;
		//1 for()
		//...
		for (ArrayList<Double> puntuaciones : pruebaPuntuaciones.values()) {
			cont += puntuaciones.size();
		}
		return cont;
	}
	
	public int getNumPuntuaciones(String pruebaID) {
		ArrayList<Double> puntuaciones = pruebaPuntuaciones.get(pruebaID);
		return puntuaciones == null ? -1 : puntuaciones.size();
	}
	
	@Override
	public String toString() {
		//Tener en cuenta que el unico caso en el que se escribe prueba (en singular) es si this.pruebaPuntuaciones.size() == 1; en caso contrario, pruebas (en plural)
		return this.sujetoID + "=<" + this.pruebaPuntuaciones.size() + (this.pruebaPuntuaciones.size() != 1 ? " pruebas>" : " prueba>");

	}
	
	@Override
	public boolean equals(Object o) {
		return this.compareTo((Sujeto)o) == 0; 
	}
	
	@Override
	public int compareTo(Sujeto o) {
		return this.sujetoID.compareTo(o.sujetoID);
	}

	@Override
	public Iterator<Pair<String,ArrayList<Double>>> iterator() {
		return  this.pruebaPuntuaciones.entrySet().iterator();
	}
}
