package org.eda1.practica03.parte02;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class GestionArticulos {
	private TreeMap<String, TreeMap<Articulo, TreeSet<String>>> datos;
	
	public GestionArticulos() {
		this.datos = new TreeMap<String, TreeMap<Articulo, TreeSet<String>>>();
	}
	
	public void load(String directorioEntrada, String file) {
		Scanner scan = null;
		String lineIn;
		String[] items = null;
		String repositorioID = "";
		this.datos.clear();
		
		try {
			scan = new Scanner(new File(directorioEntrada + file));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		while(scan.hasNextLine()){
			lineIn = scan.nextLine().trim().toLowerCase();
			if (lineIn.isEmpty()) continue;
			if (lineIn.startsWith("%")) continue;
			//Cuidado con la estructura del archivo de entrada (datos.txt)
			//Si la linea comienza con el simbolo @ quiere decir que, a continuacion, encontramos el identificador de repositorio
			//...
			//Vamos a intentar no hacer uso de ningun bucle de forma explicita--> Arrays.copyOfRange()
			//Llamamos al metodo add()
			add(//...);
		}
		scan.close();
	}

	public boolean add(String repositorioID, String articuloID, String file, String ...autoresID) {
		Articulo articulo = new Articulo(articuloID);
		//Seguimos la linea de actuacion que ya conocemos
		//Si el repositorio con clave repositorioID no esta --> lo creamos
		//Si el articulo ya existia en el repositorio --> return false
		//En caso contrario, se cargan los datos del archivo de texto especificado y se almacena junto con el conjunto de autores
		//...
		//1 for()
		//...
		return true;
	}
	
	public TreeSet<String> getArticulosID(String autorID) {
		TreeSet<String> result = new TreeSet<String>();
		autorID = autorID.trim().toLowerCase();
		//2 for() anidados
		//...
		
		return result;
	}
	
	public TreeSet<String> getCoAutores(String autorID) {
		TreeSet<String> result = new TreeSet<String>();
		autorID = autorID.trim().toLowerCase();
		//2 for() anidados ??
		//...
		return result;
	}
	
	public boolean sustituir(String autorID, String palabra1, String palabra2) {
		autorID = autorID.trim().toLowerCase();
		//Sustituimos palabra1 por palabra2 en todos y cada uno de los articulos firmados por autor con clave autorID
		//2 for() anidados
		//...
		return true;
	}
	
	public TreeSet<String> getPalabrasClave(String autorID, int minFrec) {
		Articulo macroArticulo = new Articulo("");
		TreeSet<String> resultado = new TreeSet<String>();
		autorID = autorID.trim().toLowerCase();
		
		//Quiza tenga sentido fusionar todos los articulos que ha escrito autorID y, a continuacion, recorrer macroArticulo y tener
		//en cuenta aquellas palabras cuya frecuencia >= minFrec
		//2 for() anidados + 1 for()
		//...
		return resultado;
	}
	
	public int size() {
		return this.datos.size();
	}
	
	public void clear() {
		this.datos.clear();
	}
	
	@Override
	public String toString() {
		return this.datos.toString();
	}
}