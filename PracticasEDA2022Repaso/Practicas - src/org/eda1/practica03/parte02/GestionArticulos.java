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
	
	/**
	 * Me salto la linea si está vacia o empieza por %
	 * para el repoID le quito el primer caracter con substring comenzando desde el carácter 1.
	 * Spliteo la linea en items, para recoger el idArticulo, el file y los autores, que en el add los espera en un array.
	 * Para guardar los autores en el array, creo uno auxiliar, de tamaño length()-2 para excluir los 2 primeros items que serán articuloID y file
	 * Mediante Arrays.copyOfRange() paso el array solo con los autores.
	 * @param directorioEntrada
	 * @param file
	 */
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
			
			if (lineIn.startsWith("@")) {
				repositorioID = lineIn.substring(1);
				continue;
			}
			items = lineIn.split(" ");
			add(repositorioID, items[0], directorioEntrada + items[1], Arrays.copyOfRange(items, 2, items.length));
		}
		scan.close();
	}

	/**
	 * Si el repositorio con clave repositorioID no esta --> lo creamos
	 * Si el articulo ya existia en el repositorio --> return false
	 * En caso contrario, se cargan los datos del archivo de texto especificado y se almacena junto con el conjunto de autores
	 * 
	 * @param repositorioID
	 * @param articuloID
	 * @param file
	 * @param autoresID
	 * @return True if successful
	 */
	public boolean add(String repositorioID, String articuloID, String file, String ...autoresID) {
		Articulo articulo = new Articulo(articuloID);
	
		TreeMap<Articulo, TreeSet<String>> aux = this.datos.get(repositorioID);
		if (aux == null ) {
			aux = new TreeMap<Articulo, TreeSet<String>>();
			this.datos.put(repositorioID, aux);
		}
		if (aux.containsKey(articulo)) return false;
		articulo.load(file);
		TreeSet<String> autores = new TreeSet<String>();
		for (String autor : autoresID) {
			autores.add(autor.toLowerCase());
		}
		aux.put(articulo, autores);
			
		
		return true;
	}
	
	/**
	 * Recorro los articulos, recorro el entrySet ya que necesito tanto la clave como los valores.
	 * si el articulo contiene el AutorID, añado el ArticuloID a result.
	 * @param autorID
	 * @return result, treeSet de articuloIDs
	 */
	public TreeSet<String> getArticulosID(String autorID) {
		TreeSet<String> result = new TreeSet<String>();
		autorID = autorID.trim().toLowerCase();
		for (TreeMap<Articulo, TreeSet<String>> articulo : this.datos.values()) {
			for (Entry<Articulo, TreeSet<String>> contenido : articulo.entrySet()) {
				if (contenido.getValue().contains(autorID)) {
					result.add(contenido.getKey().getArticuloID());
				}
			}
		}
		return result;
	}
	
	/**
	 * Del primer mapa solo necesito los valores, y del segundo mapa tambien. Recorro los values.
	 * Si en autores tenemos a autorID inserto todos los autores.
	 * Debo borrar autorID del treeSet porque espera solo los coautores.
	 * @param autorID
	 * @return treeSet de coautores.
	 */
	public TreeSet<String> getCoAutores(String autorID) {
		TreeSet<String> result = new TreeSet<String>();
		autorID = autorID.trim().toLowerCase();
		for (TreeMap<Articulo, TreeSet<String>> articulo : this.datos.values()) {
			for (TreeSet<String> autor : articulo.values()) {
				if (autor.contains(autorID)) {
					result.addAll(autor);
				}
			}
		}
		result.remove(autorID);
		return result;
	}
	
	/**
	 * Sustituimos palabra1 por palabra2 en todos y cada uno de 
	 * los articulos firmados por autor con clave autorID
	 * Recorro los articulos, recorro el entrySet. Si autores contiene al autor con clave autorID
	 * sustituyo palabra1 por palabra 2.
	 * @param autorID
	 * @param palabra1
	 * @param palabra2
	 * @return True is successful
	 */
	public boolean sustituir(String autorID, String palabra1, String palabra2) {
		autorID = autorID.trim().toLowerCase();
		for (TreeMap<Articulo, TreeSet<String>> articulo : this.datos.values()) {
			for (Entry<Articulo, TreeSet<String>> contenido : articulo.entrySet()) {
				if (contenido.getValue().contains(autorID)) {
					contenido.getKey().sustituir(palabra1, palabra2);
				}
			}
		}
		return true;
	}
	
	/**
	 * recorro todos los repositorio
	 * Recorro los articulos de cada repo y veo si contienen al autorID, 
	 * si lo contienen añado el articulo a macroArticulo
	 * Recoro macroArticulo, si la frecuencia es mayor o iguala a minFrec lo añado a resultado.
	 * @param autorID
	 * @param minFrec
	 * @return resultado
	 */
	public TreeSet<String> getPalabrasClave(String autorID, int minFrec) {
		Articulo macroArticulo = new Articulo("");
		TreeSet<String> resultado = new TreeSet<String>();
		autorID = autorID.trim().toLowerCase();
		for (TreeMap<Articulo, TreeSet<String>> articulo : this.datos.values()) {
			for (Entry<Articulo, TreeSet<String>> contenido : articulo.entrySet()) {
				if (contenido.getValue().contains(autorID)) {
					macroArticulo = macroArticulo.fusionar(contenido.getKey());
				}
			}
		}
		for (Entry<String,Integer> entry : macroArticulo) {
			if (entry.getValue() >= minFrec) {
				resultado.add(entry.getKey() + "=" + entry.getValue());
			}
		}
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