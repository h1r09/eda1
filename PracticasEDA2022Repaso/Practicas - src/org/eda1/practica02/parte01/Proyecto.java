package org.eda1.practica02.parte01;

import java.util.Iterator;

import edaAuxiliar.BSTree;

public class Proyecto implements Comparable<Proyecto>, Iterable<String> {
	private String proyectoID; //Identificador de proyecto (clave) --> nombre del proyecto
	private BSTree<String> ciudades; //BST -> binary search tree (árbol binario de búsqueda) --> conjunto de ciudades
	
	public Proyecto(String proyectoID){
		//Recordad el uso de trim() y toLowerCase()
		//2 lineas
		//...
		this.proyectoID = proyectoID.trim().toLowerCase();
		this.ciudades = new BSTree<String>();
	}
	
	public boolean add(String ciudad) {
		return !ciudades.contains(ciudad.toLowerCase().trim()) ? ciudades.add(ciudad.toLowerCase().trim()) : false;
	}
	
	public boolean remove(String ciudad) {
		return this.ciudades.remove(ciudad.toLowerCase().trim());
	}

	public String getProyectoID(){
		return this.proyectoID;
	}
	
	public int size() {
		return ciudades.size();
	}

	public void clear() {
		this.ciudades.clear();
	}
	
	public boolean contains(String ciudad){
		return this.ciudades.contains(ciudad);
	}
		
	@Override
	public String toString(){
		return this.proyectoID + ": " + ciudades.toString();	
	}
	
	@Override
	public boolean equals(Object o) {
		return this.compareTo((Proyecto)o) == 0;
	}
	
	@Override
	public int compareTo(Proyecto otro) {
		//Orden natural: proyectoID (ascendente)
		return this.proyectoID.compareTo(otro.proyectoID);
	}
	
	@Override
	public Iterator<String> iterator() {
		//Iterar sobre un proyecto equivale a iterar sobre las ciudades en las que se está desarrollando
		return this.ciudades.iterator();
	}
}
