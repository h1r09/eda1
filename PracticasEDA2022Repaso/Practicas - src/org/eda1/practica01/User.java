	indepackage org.eda1.practica01;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
	private String name;
	private ArrayList<Device> devices;
	
	public User(String name) {
		//Si name == null se lanza excepcion de tipo RuntimeException()
		//3 lineas
		//...
		
	}
	
	public void clear() {
		//1 for()
		//...
		devices.clear();
	}
	
	public boolean addDevices(Device... devs) {
		if (devs == null) return false;
		//1 for()
		//...
		
		return true;
	}
	
	public int getNumDevices() {
		return this.devices.size();
	}

	public boolean loadMessages(Device dev, String fileName) {
		//Analizar la estructura de este tipico metodo de carga de datos a partir de un archivo de texto
		//Por que es necesario el bloque try{}catch(){}
	
		Scanner scan = null;
		String line; 
		int pos = this.devices.indexOf(dev);
		if (pos == -1) return false;
		try {
			scan = new Scanner(new File(fileName));
		}catch(Exception e) {
			return false;
		}
		while (scan.hasNextLine()) {
			//3 lineas
			//...
		}
		scan.close();
		return true;
	}
	
	public boolean sendMessage(Device dev, String msg) {
		int pos = this.devices.indexOf(dev);
		//2 lineas
		//...
		return true;
	}
	
	public void substitute(String word1, String word2) {
		//Sustituimos en todos los dispositivos...
		//1 for()
	}
	
	public boolean contains(String word) {
		//Buscamos en todos y cada uno de los dispositivos
		//1 for()
		//...
		return false;
	}
	
	public String getWords() {
		String result = "";
		//2 for() anidados
		//...
		return result;
	}
	
	public ArrayList<String> getOrderedWords() {
		ArrayList<String> result = new ArrayList<String>();
		//2 for() anidados
		//...
		result.sort(null); //que metodo de ordenacion estamos utilizando aqui...
		return result;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}