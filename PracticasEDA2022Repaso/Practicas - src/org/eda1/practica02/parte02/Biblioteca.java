package org.eda1.practica02.parte02;

import java.util.ArrayList;
import java.util.PriorityQueue;

import edaAuxiliar.AVLTree;

public class Biblioteca {
	private String bibliotecaID; //nombre de la biblioteca
 	protected AVLTree<Libro> libros; //libros registrados en la biblioteca
	protected AVLTree<Usuario> usuarios; //registro de usuarios de la biblioteca
	
	public Biblioteca(String bibliotecaID) {
		//...
	}
	
	public boolean addLibro(Libro libro) {
		return //...
	}
	
	public boolean addUsuario(Usuario usuario) {
		return //...
	}
	
	public void clear() {
		this.libros.clear();
		this.usuarios.clear();
	}
	
	public boolean prestarLibro(String usuarioID, String libroID) {
		//Condiciones para prestar un libro:
		//1. Que el usuario con identificador usuarioID este registrado
		//2. Que el libro con identificador libroID este registrado
		//3. Que el libro no este prestado (getPrestado())
		//Una vez que esta todo correcto habra que hacer dos acciones:
		//1. modificar el atributo usuarioPrestamo de libro
		//2. registrar el prestamo en uauario para actualizar el historio de libros prestados
		Usuario usuario = //...
		Libro libro = //...
		//...
		return true;
	}
	
	public boolean devolverLibro(String usuarioID, String libroID) {
		//Para devolver un libro:
		//1. El usuario con identificador usuarioID debe estar registrado
		//2. El usuario con identificador libroID debe estar registrado
		//3. El libro tiene que estar prestado por el usuario con identificador usuarioID
		//Una vez que esta todo correcto habra que hacer una unica accion: modificar el atributo usuarioPrestamo de Libro
		return true;
	}
	
	public ArrayList<String> getLibrosPrestados(){
		ArrayList<String> result = new ArrayList<String>();
		//Recorriendo la estructura libros nos interesaria unicamente los libros que estan prestados...
		//1 for()
		//...
		return result;
	}
	
	public ArrayList<String> getUsuariosPrestamo(String libroID){
		ArrayList<String> result = new ArrayList<String>();
		//Una vez comprobado que el libro existe, tendremos que recorrer todos los usuarios y, para cada usuario, su historico de libros prestados...
		
		//2 for() anidados
		//...
		return result;
	}
	
	public ArrayList<String> getLibrosPrestados(String usuarioID){
		//Simplemente, hacemos uso de la version de getLibrosPrestados a la que le pasamos una referencia de tipo Usuario
		return //...
	}
	
	public ArrayList<String> getLibrosPrestados(Usuario usuario){
		ArrayList<String> result = new ArrayList<String>();
		//1 for()
		//...
		return result;
	}
	
	public double getDistancia(String usuarioID1, String usuarioID2) {
		double distancia = .0;
		Usuario usuario1, usuario2;
		//La distancia entre dos usuarios equivale a la suma de las distancias de los libros que han prestado
		//Evitar el computo de distancia en el caso de que ambos libros sean iguales (distancia 0)
		//2 for() anidados
		//...
		
		return distancia;
	}
	
	public ArrayList<String> getSimilares(String usuarioID) {
		ArrayList<String> result = new ArrayList<String>();
		PriorityQueue<UsuarioSim> pQ = new PriorityQueue<UsuarioSim>();
		Usuario usuario1 = //...
				
		//Vamos a obtener el conjunto de usuarios ordenados por distancia, de manera que los primeros seran los mas similares a usuarioID
		//Aunque podemos resolver este problema de multiples formas, vamos a hacer uso de una cola de prioridad (pQ).
		//Calculamos las distancias entre pares de usuarios no iguales y vamos insertando pares (usuario, distancia) en la cola
		//Volvamos el contenido de la cola en la estructura result...
				
		//1 for() + 1 while()
		//...
		return result;
	}
	
	@Override
	public String toString() {
		return //... 
	}
}