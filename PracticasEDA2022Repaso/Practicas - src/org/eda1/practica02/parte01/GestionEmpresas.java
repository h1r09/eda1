package org.eda1.practica02.parte01;

import java.util.ArrayList;
import java.util.Scanner;

import edaAuxiliar.AVLTree;

import java.io.File;
import java.io.IOException;

public class GestionEmpresas {

	private AVLTree<Empresa> empresas = new AVLTree<Empresa>();

	public void cargarArchivo(String file) {
		Scanner scan = null;
		String line;
		String[] items;
		this.empresas.clear(); //Cada vez que cargamos un archivo, se inicializa la estructura empresas
		try {
			scan = new Scanner(new File(file));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		while (scan.hasNextLine()) {
			line = scan.nextLine().trim();
			//Leer archivo de entrada
			//Si la linea empieza por @ o está vacía --> la ignoramos (usar continue)
			//...
			if (line.isEmpty()) continue;
			if (line.startsWith("@")) continue;
			items = line.split(" - ");		
			add(items[0], items[1], items[2]);
		}
		scan.close();
	}

	public boolean add(String empresaID, String proyectoID, String ciudad) {
		//Seguimos con la misma lógica: objeto auxiliar para buscar en la estructura
		//Objeto current que puede ser null o almacenar la referencia al objeto en caso de que exista
		Empresa empresaAux = new Empresa(empresaID);
		Empresa empresaCurr = this.empresas.find(empresaAux);
		if (empresaCurr == null ) {
			empresas.add(empresaAux);
		}else {
			empresaAux = empresaCurr;
		}
		empresaAux.add(proyectoID, ciudad);
		//...
		return empresaCurr == null;
	}

	public int size() {
		return this.empresas.size();
	}

	public int numeroProyectosEmpresa(String empresaID) {
		Empresa empresaCurr = this.empresas.find(new Empresa(empresaID));
		return (empresaCurr == null) ? -1 : empresaCurr.size();
	}

	public int numeroCiudadesProyecto(String proyectoID) {
		Proyecto proyectoCurr = null;
		//1 for()
		//...
		for (Empresa empresa : this.empresas) {
			proyectoCurr = empresa.findProyecto(new Proyecto(proyectoID));
			if (proyectoCurr == null) continue;
			return proyectoCurr.size();
		}
		return -1;
	}

	public int numeroCiudadesEmpresa(String empresaID) {
		ArrayList<String> ciudades = null;
		//Tendremos que comprobar que la empresa existe
		//2 for() anidados
		//...
		Empresa empresaCurr = this.empresas.find(new Empresa(empresaID));
		
		if (empresaCurr == null) return -1;
		ciudades = new ArrayList<String>();
		for (Proyecto proyecto : empresaCurr) {
			for (String ciudad : proyecto) {
				if (ciudades.contains(ciudad)) continue;
				ciudades.add(ciudad);
			}
		}
		return ciudades.size();
	}

	@Override
	public String toString() {
		String cadena = "";
		for (Empresa empresa : this.empresas) {
			cadena += empresa.toString() + "\n";
		}
		return cadena;
	}

	public String devolverEmpresasCiudad(String ciudad) {
		ArrayList<String> empresasIDaux = new ArrayList<String>();
		//2 for() anidados
		//...
		for (Empresa empresa : empresas) {
			for (Proyecto proyecto : empresa) {
				if (proyecto.contains(ciudad)) {
					empresasIDaux.add(empresa.getEmpresaID());
					break;
				}
			}
		}
		return empresasIDaux.toString();
	}

	public String devolverProyectosCiudad(String ciudad) {
		ArrayList<String> proyectosIDaux = new ArrayList<String>();
		//2 for() anidados
		//...
		for (Empresa empresa : empresas) {
			for (Proyecto proyecto : empresa) {
				if (proyecto.contains(ciudad)) {
					proyectosIDaux.add(proyecto.getProyectoID());
				}
			}
		}
		return proyectosIDaux.toString();
	}
	
	 public String devolverCiudadesEmpresa(String empresaID) {
		 ArrayList<String> ciudades = null;
		 //Tendremos que comprobar que la empresa existe
		 //2 for() anidados
		 //...
		 Empresa empresa = empresas.find(new Empresa(empresaID));
		 if (empresa == null) return "[]";
		 ciudades = new ArrayList<String>();
		 for (Proyecto proyecto : empresa) {
			for (String ciudad : proyecto) {
				if (ciudades.contains(ciudad)) continue;
				ciudades.add(ciudad);
			}
		}
		 return ciudades.toString();
	}
}