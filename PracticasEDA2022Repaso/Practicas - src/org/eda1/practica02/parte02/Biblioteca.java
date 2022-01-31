package org.eda1.practica02.parte02;

import java.util.ArrayList;
import java.util.PriorityQueue;

import edaAuxiliar.AVLTree;

public class Biblioteca {
	
	/**
	 * Variable bibliotecaID de tipo String
	 * AVriable libros de tipo AVLTree<Libro>
	 * VAriable usuarios de tipo AVLTree<Usuario>
	 */
	private String bibliotecaID; 
 	protected AVLTree<Libro> libros; 
	protected AVLTree<Usuario> usuarios; 
	
	/**
	 * Instancia una nueva biblioteca
	 * Inicializo las AVLTree libros y usuarios
	 * @param bibliotecaID
	 */
	public Biblioteca(String bibliotecaID) {
		this.bibliotecaID = bibliotecaID;
		this.libros = new AVLTree<Libro>();
		this.usuarios = new AVLTree<Usuario>();
	}
	
	/**
	 * Añado el libro al alrbol de libros
	 * @param libro
	 * @return true si se ha añadido, false si no
	 */
	public boolean addLibro(Libro libro) {
		return this.libros.add(libro);
	}
	
	/**
	 * añado el usuario al arbol de usuarios
	 * @param usuario
	 * @return true si se ha añadido
	 */
	public boolean addUsuario(Usuario usuario) {
		return this.usuarios.add(usuario);
	}
	
	/**
	 * Borra los 2 arboles, libros y usuarios.
	 */
	public void clear() {
		this.libros.clear();
		this.usuarios.clear();
	}
	
	/**
	 * Creo un usuario
	 * si el usuario no existe me ahorro buscar los libros
	 * creo el libro
	 * compruebo que el libro no esté prestado ni sea nulo.
	 * asocio el usuario al libro con setUsuarioPrestamo(usuario)
	 * registro el prestamo en el usuario con registrarPrestamo(libro)
	 * 
	 * @param usuarioID
	 * @param libroID
	 * @return true, if sucessful
	 */
	public boolean prestarLibro(String usuarioID, String libroID) {
		
		Usuario usuario = this.usuarios.find(new Usuario(usuarioID)); 
		if (usuario == null) return false; 
		Libro libro = this.libros.find(new Libro(libroID));
		if (libro == null || libro.getPrestado()) return false;
		libro.setUsuarioPrestamo(usuario); 
		usuario.registrarPrestamo(libro);
		return true;
	}
	
	/**
	 * Creo un usuario
	 * si el usuario no existe me ahorro buscar los libros
	 * creo el libro
	 * compruebo que el libro existe y está prestado
	 * compruebo que el libro está prestado al usuario cuyo id nos pasan por parámetro.
	 * Modifico el atributo usuarioPrestamo con setUsuarioPrestamo(null)
	 * @param usuarioID
	 * @param libroID
	 * @return
	 */
	public boolean devolverLibro(String usuarioID, String libroID) {

		Usuario usuario = this.usuarios.find(new Usuario(usuarioID));
		if (usuario == null) return false; 
		Libro libro = this.libros.find(new Libro(libroID));
		if (libro == null || !libro.getPrestado() ) return false;
		if(!libro.getUsuarioPrestamo().equals(usuario)) return false; 
		libro.setUsuarioPrestamo(null); 
		return true;
	}
	
	/**
	 * Recorro la estructura libros y añado a result los que están prestados
	 * @return result con salida esperada en el test.
	 */
	public ArrayList<String> getLibrosPrestados(){
		ArrayList<String> result = new ArrayList<String>();
		for (Libro libro : libros) {
			if(libro.getPrestado()) {
				result.add(libro.getLibroID() + ": " + libro.getUsuarioPrestamo().getUsuarioID());
			}
		}
		return result;
	}
	
	/**
	 * creo el libro y si no existe devuelvo null.
	 * recorro los usuarios y miro si contienen el libro .
	 * Si lo contiene lo agrego a result y paro ese bucle con break
	 * @param libroID
	 * @return result
	 */
	public ArrayList<String> getUsuariosPrestamo(String libroID){
		ArrayList<String> result = new ArrayList<String>();
		Libro libro = this.libros.find(new Libro(libroID));
		if (libro == null) return null;
		for (Usuario usuario : usuarios) {
			for (Libro Libro : usuario) {
				if (Libro.equals(libro)) { 
					result.add(usuario.getUsuarioID());
					break; 
				}
			}
		}
		return result;
	}
	
	/**
	 * obtengo los libros prestados del usuario cuyo suarioID me pasan por parametro
	 * @param usuarioID
	 * @return libros prestado usuario con usuarioID
	 */
	public ArrayList<String> getLibrosPrestados(String usuarioID){
		return getLibrosPrestados(new Usuario(usuarioID));
	}
	
	/**
	 * recorro cada libro y agrego el id de cada libro.
	 * @param usuario
	 * @return result
	 */
	public ArrayList<String> getLibrosPrestados(Usuario usuario){
		ArrayList<String> result = new ArrayList<String>();
		Usuario usu = this.usuarios.find(usuario);
		if (usu == null) return null;
		for (Libro libro : usu) {
			result.add(libro.getLibroID());
		}
		return result;
	}
	
	/**
	 * La distancia entre dos usuarios equivale a la suma de las distancias de los libros que han prestado
	 * No se realiza el computo de distancia en el caso de que ambos libros sean iguales (distancia 0)
	 * Compruebo que los usuarios no son nulos.
	 * Recorro los libors de cada usuario y si no son iguales añado su distancia al atributo distancia.
	 * @param usuarioID1
	 * @param usuarioID2
	 * @return distancia
	 */
	public double getDistancia(String usuarioID1, String usuarioID2) {
		double distancia = .0;
		Usuario usuario1, usuario2;
		usuario1 = this.usuarios.find(new Usuario(usuarioID1));
		if (usuario1 == null) return -1;
		usuario2 = this.usuarios.find(new Usuario(usuarioID2));
		if (usuario2 == null) return -1;
		
		for (Libro libro : usuario1) {
			for (Libro libro2 : usuario2) {
				if (!libro.equals(libro2)) {
					distancia += libro.getDistancia(libro2);
				}
			}
		}
		return distancia;
	}
	
	/**
	 * obtener el conjunto de usuarios ordenados por distancia, de manera que los primeros seran los mas similares a usuarioID
	 * Calculamos las distancias entre pares de usuarios no iguales y vamos insertando pares (usuario, distancia) en la cola de prioridad
	 * Volcamos el contenido de la cola en la estructura result con poll()
	 * @param usuarioID
	 * @return result
	 */
	public ArrayList<String> getSimilares(String usuarioID) {
		ArrayList<String> result = new ArrayList<String>();
		PriorityQueue<UsuarioSim> pQ = new PriorityQueue<UsuarioSim>();
		Usuario usuario1 = this.usuarios.find(new Usuario(usuarioID));
		if (usuario1 == null) return null;
		
		for (Usuario usuario : usuarios) {
			if (!usuario.equals(usuario1)) {
				UsuarioSim usuarioSim = new UsuarioSim(usuario.getUsuarioID(), getDistancia(usuario.getUsuarioID(), usuario1.getUsuarioID()));
				pQ.add(usuarioSim);
			}
		}		
		while (!pQ.isEmpty()) {
			result.add(pQ.poll().toString());
		}
		return result;
	}
	
	/**
	 * toString con la salida esperada en el test.
	 *
	 * @return El string con la salida esperada 
	 */
	@Override
	public String toString() {
		return this.bibliotecaID + " (" + this.libros.size() + " libros y " + this.usuarios.size() + " usuarios)";
	}
}