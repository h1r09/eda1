package org.eda1.practica03.parte01;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import java.io.File;
import java.io.IOException;


/**
 * @author Hunor Moriczi
 *
 */
public class GestionEmpresas {

	
	/**
	 * variable privada datos de tipo TreeMap<String, TreeMap<String, TreeSet<String>>>
	 */
	private TreeMap<String, TreeMap<String, TreeSet<String>>> datos = new TreeMap<String, TreeMap<String, TreeSet<String>>>();

	/**
	 * Load. Método para cargar un archivo.
	 * Se salta las lineas vacias y las que empiezan por "@"
	 * Separa los elementos por el separador "-"
	 *
	 * @param file the file
	 */
	public void load(String file) {
		Scanner scan = null;
		String line;
		String[] items;
		this.datos.clear(); 
		try {
			scan = new Scanner(new File(file));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		while (scan.hasNextLine()) {
			line = scan.nextLine().trim();
			if (line.isEmpty()) continue;
			if (line.startsWith("@")) continue;
			items = line.split(" - ");
			add(items[0], items[1], items[2]);
		}
		scan.close();
	}

	/**
	 * Hago 2 comprobaciones, sino existe la empresa la añado. Si no existe el proyecto se lo añado a la empresa.
	 * A continuacion, le añado la ciudad.
	 * @param empresaID the empresa ID
	 * @param proyectoID the proyecto ID
	 * @param ciudad the ciudad
	 * @return true si añade la ciudad y false si no lo añade.
	 */
	public boolean add(String empresaID, String proyectoID, String ciudad) {
		
		if (!datos.containsKey(empresaID)) {
			datos.put(empresaID, new TreeMap<String, TreeSet<String>>());	
		}
		if (!datos.get(empresaID).containsKey(proyectoID)) {
			datos.get(empresaID).put(proyectoID, new TreeSet<String>());
		}
		return datos.get(empresaID).get(proyectoID).add(ciudad);
	}

	/**
	 * Me devuelve el tamaño de datos.
	 *
	 * @return the int
	 */
	public int size() {
		return this.datos.size();
	}

	/**
	 * Numero proyectos empresa.
	 * si proyectoCurr es nulo no existe la empresa y devuelvo -1, sino me devuelve el tamaño.
	 *
	 * @param empresaID the empresa ID
	 * @return the -1 o tamaño de proyectoCurr
	 */
	public int numeroProyectosEmpresa(String empresaID) {
		TreeMap<String, TreeSet<String>> proyectoCurr = this.datos.get(empresaID);
		return proyectoCurr == null ? -1 : proyectoCurr.size();
	}

	/**
	 * Numero ciudades proyecto. 
	 * Recorro las empresas para ver si contienen el proyecto. Me vale con recorrer solo los valores y compruebo si los valores contienen el proyecto.
	 * Si lo contienen que me de su tamaño.
	 * Para cumplir con los 2 for, finalmente hago uso del entrySet()
	 * Con un solo for se podría hacer de la siguiente manera
	 * for (TreeMap<String, TreeSet<String>> iterable_element : datos.values()) {
	 *		if (iterable_element.containsKey(proyectoID)) {
	 *			return iterable_element.get(proyectoID).size();
	 *  	}	
	 * }
	 * return -1;
	 * 
	 * @param proyectoID the proyecto ID
	 * @return the int
	 */
	public int numeroCiudadesProyecto(String proyectoID) {
					
		for (Entry<String, TreeMap<String, TreeSet<String>>> empresa : this.datos.entrySet()) {
			for (Entry<String, TreeSet<String>> proyecto : empresa.getValue().entrySet()) {
				if (proyecto.getKey().contains(proyectoID)) {
//					System.out.println(iterable_element2.getKey().contains(proyectoID));
					return proyecto.getValue().size();
				}
			}
		}
		return -1;
	}

	/**
	 * Numero ciudades empresa.
	 * en proyectos guardo los proyectos de la empresa que me pasan.
	 * si no existe devuelvo -1. Si existe, recorro los valores de proyectos y lo añado a ciudades.
	 * @param empresaID the empresa ID
	 * @return the int
	 */
	public int numeroCiudadesEmpresa(String empresaID) {
		TreeSet<String> ciudades = null;
		TreeMap<String, TreeSet<String>> proyectos = this.datos.get(empresaID);
		if(proyectos == null) return -1;
		ciudades = new TreeSet<String>();

		for (TreeSet<String> ciudad : proyectos.values()) {
			ciudades.addAll(ciudad);
		}
		return ciudades.size();
	}

	/**
	 * To string. Recorro las estrucutras y creo el toString que espera el test.
	 * El string que espera el test "empresa06 -> [proyecto06_01: [ciudad03, ciudad04, ciudad10]]\n", 
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		String aux = "";
		for (Entry<String, TreeMap<String, TreeSet<String>>> empresa : this.datos.entrySet()) {
			aux += empresa.getKey() + " -> [";
			for (Entry<String, TreeSet<String>> proyecto : empresa.getValue().entrySet()) {
				aux +=proyecto.getKey() + ": " + proyecto.getValue();
				if (!proyecto.getKey().equals(empresa.getValue().lastKey())) {
					aux += ", ";
				}
			}
			aux += "]\n";
		}

		return aux;
	}

	/**
	 * Devolver empresas ciudad.
	 *
	 * @param ciudad the ciudad
	 * @return the string
	 */
	public String devolverEmpresasCiudad(String ciudad) {
		TreeSet<String> empresasIDaux = new TreeSet<String>();
		for (Entry<String, TreeMap<String, TreeSet<String>>> empresa : this.datos.entrySet()) {
			for (TreeSet<String> ciudades : empresa.getValue().values()) {
				if (ciudades.contains(ciudad)) {
					empresasIDaux.add(empresa.getKey());
					break;
				
				}
			}
		}
		return empresasIDaux.toString();
	}

	/**
	 * Devolver proyectos ciudad.
	 *
	 * @param ciudad the ciudad
	 * @return the string
	 */
	public String devolverProyectosCiudad(String ciudad) {
		ArrayList<String> proyectosIDaux = new ArrayList<String>();
		//2 for() anidados
		//...
		for (TreeMap<String, TreeSet<String>> proyecto : this.datos.values()) {
			for (Entry<String, TreeSet<String>> detProyecto : proyecto.entrySet()) {
				if (detProyecto.getValue().contains(ciudad)) {
					proyectosIDaux.add(detProyecto.getKey());
				}
			}
		}
		return proyectosIDaux.toString();
	}
	
	 /**
 	 * Devolver ciudades empresa.
 	 *
 	 * @param empresaID the empresa ID
 	 * @return the string
 	 */
 	public String devolverCiudadesEmpresa(String empresaID) {
		TreeSet<String> ciudades = null;
		TreeMap<String, TreeSet<String>> aux = this.datos.get(empresaID);
		if (aux == null) return "[]";
		ciudades = new TreeSet<String>();
		for (TreeSet<String> ciudad : aux.values()) {
			ciudades.addAll(ciudad);
		}
		 
		 
		 return ciudades.toString();
	}
}