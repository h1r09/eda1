package org.eda1.practica02.parte01;

import java.util.Iterator;

import edaAuxiliar.AVLTree;

public class Empresa implements Comparable<Empresa>, Iterable<Proyecto> { //Falta alguna interfaz???
	
	private String empresaID;
	private AVLTree<Proyecto> proyectos;
	
	
	public Empresa(String empresaID) {
		//...
		this.empresaID = empresaID.trim().toLowerCase();
		this.proyectos = new AVLTree<Proyecto>();
	}

	public boolean add(String proyectoID, String...ciudades) {
		//proyectoAux es el objeto que utilizamos para buscar el proyecto en el Ã¡rbol (haciendo uso del mÃ©todo find()) (sin ciudades)
		//proyectoCurr puede tener dos posibles valores: (1) null si el proyecto no existe; (2) referencia al objeto insertado en el Ã¡rbol (con sus ciudades)
		//Si no existÃ­a el proyecto, habrÃ¡ que insertarlo; en caso contrario, habrÃ¡ que actualizar su conjunto de cuidades con las que contiene el parÃ¡metro ciudades
		Proyecto proyectoAux = new Proyecto(proyectoID);
		Proyecto proyectoCurr = proyectos.find(proyectoAux);
		if (proyectoCurr == null) {
			this.proyectos.add(proyectoAux);
		}else {
			proyectoAux = proyectoCurr;
		}
		for (String ciudad : ciudades) {
			proyectoAux.add(ciudad.trim().toLowerCase());
		}
		return proyectoCurr == null;				
	}

	public String getEmpresaID(){
		return this.empresaID;
	}
	
	public int size() {
		return proyectos.size();
	}
	
	public Proyecto findProyecto(Proyecto proyecto){
		return this.proyectos.find(proyecto);
	}

	public void clear() {
		//1 for()
		//...
		for (Proyecto proyecto : proyectos) {
			proyecto.clear();
		}
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
		return this.empresaID.compareTo(otra.empresaID);
	}
	
	@Override
	public Iterator<Proyecto> iterator() {
		return this.proyectos.iterator();
	}
}