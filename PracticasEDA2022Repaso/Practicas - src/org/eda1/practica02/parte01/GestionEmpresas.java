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
			add(items[0], items[1], items[2]);
		}
		scan.close();
	}

	public boolean add(String empresaID, String proyectoID, String ciudad) {
		//Seguimos con la misma lógica: objeto auxiliar para buscar en la estructura
		//Objeto current que puede ser null o almacenar la referencia al objeto en caso de que exista
		Empresa empresaAux = new Empresa(empresaID);
		Empresa empresaCurr = //...
		//...
		return empresaCurr == null;
	}

	public int size() {
		return this.empresas.size();
	}

	public int numeroProyectosEmpresa(String empresaID) {
		Empresa empresaCurr = //...
		return //... 1 linea...hacemos uso del "if compacto"
	}

	public int numeroCiudadesProyecto(String proyectoID) {
		Proyecto proyectoCurr = null;
		//1 for()
		//...
		return -1;
	}

	public int numeroCiudadesEmpresa(String empresaID) {
		ArrayList<String> ciudades = null;
		//Tendremos que comprobar que la empresa existe
		//2 for() anidados
		//...
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
		return empresasIDaux.toString();
	}

	public String devolverProyectosCiudad(String ciudad) {
		ArrayList<String> proyectosIDaux = new ArrayList<String>();
		//2 for() anidados
		//...
		return proyectosIDaux.toString();
	}
	
	 public String devolverCiudadesEmpresa(String empresaID) {
		 ArrayList<String> ciudades = null;
		 //Tendremos que comprobar que la empresa existe
		 //2 for() anidados
		 //...
		 return ciudades.toString();
	}
}