package org.eda1.practica02.parte02;

import java.util.Iterator;

import edaAuxiliar.AVLTree;

public class Usuario implements Comparable<Usuario>, Iterable<Libro> {
	private String usuarioID; //Identificador de usuario (su nombre)
	private AVLTree<Libro> registroPrestamos; //histórico de libros prestados

	public Usuario(String usuarioID) {
		//...
	}
	
	public String getUsuarioID() {
		return this.usuarioID;
	}
	
	public boolean registrarPrestamo(Libro book) {
		return  //...
	}
	
	public String getRegistroPrestamos() {
		 return this.registroPrestamos.toString();
	}
	
	public void clear() {
		this.registroPrestamos.clear();
	}
	
	@Override
	public String toString() {
		return this.usuarioID;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.compareTo((Usuario)o) == 0;
	}
	@Override
	public int compareTo(Usuario o) {
		//Orden natural: criterio único usuarioID (ascendente)
		return //...
	}
	
	@Override
	public Iterator<Libro> iterator() {
		//Iterar sobre un usuario equivale a iterar sobre su historico de libros prestados (registroPrestamos)
		return //...
	}
}