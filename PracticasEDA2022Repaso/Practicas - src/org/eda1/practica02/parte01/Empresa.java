package org.eda1.practica02.parte01;

import java.util.Iterator;

import edaAuxiliar.AVLTree;

public class Empresa implements Comparable<Empresa> { //Falta alguna interfaz???
	
	private String empresaID;
	private AVLTree<Proyecto> proyectos;
	
	
	public Empresa(String empresaID) {
		//...
	}

	public boolean add(String proyectoID, String...ciudades) {
		//proyectoAux es el objeto que utilizamos para buscar el proyecto en el árbol (haciendo uso del método find()) (sin ciudades)
		//proyectoCurr puede tener dos posibles valores: (1) null si el proyecto no existe; (2) referencia al objeto insertado en el árbol (con sus ciudades)
		//Si no existía el proyecto, habrá que insertarlo; en caso contrario, habrá que actualizar su conjunto de cuidades con las que contiene el parámetro ciudades
		Proyecto proyectoAux = new Proyecto(proyectoID);
		Proyecto proyectoCurr = //...
		//...
		//1 for()
		//...
		return proyectoCurr == null;				
	}

	public String getEmpresaID(){
		return this.empresaID;
	}
	
	public int size() {
		return proyectos.size();
	}
	
	public Proyecto findProyecto(Proyecto proyecto){
		return //...
	}

	public void clear() {
		//1 for()
		//...
		this.proyectos.clear();
	}
	@Override
	public String toString(){
		return this.empresaID +  " -> " + proyectos.toString();
	}

	@Override
	public boolean equals(Object o) {
		return this.compareTo((Empresa)o) == 0;
	}
	
	@Override
	public int compareTo(Empresa otra) {
		//Orden natural: empresaID (ascendente)
		return //...
	}

	@Override
	public Iterator<Proyecto> iterator() {
		return //...
	}
}