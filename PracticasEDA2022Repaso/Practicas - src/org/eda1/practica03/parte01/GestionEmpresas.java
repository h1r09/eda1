package org.eda1.practica03.parte01;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import java.io.File;
import java.io.IOException;

public class GestionEmpresas {

	private TreeMap<String, TreeMap<String, TreeSet<String>>> datos = new TreeMap<String, TreeMap<String, TreeSet<String>>>();

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

	public boolean add(String empresaID, String proyectoID, String ciudad) {
		//if() + if() + return
		//...
		return //...
	}

	public int size() {
		return this.datos.size();
	}

	public int numeroProyectosEmpresa(String empresaID) {
		TreeMap<String, TreeSet<String>> proyectoCurr = //...
		return //...
	}

	public int numeroCiudadesProyecto(String proyectoID) {
		//2 for()
		//...
		return -1;
	}

	public int numeroCiudadesEmpresa(String empresaID) {
		TreeSet<String> ciudades = null;
		TreeMap<String, TreeSet<String>> proyectos = //...
		//...		
		//1 for()
		//...

		return ciudades.size();
	}

	@Override
	public String toString() {
		String aux = "";
		//2 for() anidados
		//...
		return aux;
	}

	public String devolverEmpresasCiudad(String ciudad) {
		TreeSet<String> empresasIDaux = new TreeSet<String>();
		//2 for() anidados
		//...
		return empresasIDaux.toString();
	}

	public String devolverProyectosCiudad(String ciudad) {
		ArrayList<String> proyectosIDaux = new ArrayList<String>();
		//2 for() anidados
		//...
		
		return proyectosIDaux.toString();
	}
	
	 public String devolverCiudadesEmpresa(String empresaID) {
		 TreeSet<String> ciudades = null;
		 //...
		 //1 for()
		 //...
		 
		 return ciudades.toString();
	}
}