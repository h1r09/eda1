package org.eda1.practica01;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class User {
	private String name;
	private ArrayList<Device> devices;
	
	public User(String name) {
		//Si name == null se lanza excepcion de tipo RuntimeException()
		//3 lineas
		//...
		if (name == null) throw new RuntimeException("El atributo name no puede ser nulo");
		this.name = name.trim();
		this.devices = new ArrayList<Device>();
	}
	
	public void clear() {
		//1 for()
		//...
		for (Device device : devices) {
			device.clear();
		}
		devices.clear();
	}
	
	public boolean addDevices(Device... devs) {
		if (devs == null) return false;
		//1 for()
		//...
		for (Device device : devs) {
			if (this.devices.contains(device)) 
				continue;		
			this.devices.add(device);
		}
		
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
			line = scan.nextLine();
			if (line.isEmpty()) {
				continue;
			}else {
				dev.sendMessage(line);
			}
			
		}
		scan.close();
		return true;
	}
	
	public boolean sendMessage(Device dev, String msg) {
		int pos = this.devices.indexOf(dev);
		if (pos == -1) return false;
		this.devices.get(pos).sendMessage(msg);
		//2 lineas
		//...
		return true;
	}
	
	public void substitute(String word1, String word2) {
		//Sustituimos en todos los dispositivos...
		//1 for()
		for (Device device : devices) {
			device.substitute(word1, word2);
		}
	}
	
	public boolean contains(String word) {
		//Buscamos en todos y cada uno de los dispositivos
		//1 for()
		//...
		for (Device device : devices) {
			if (device.contains(word)) return true;
		}
		return false;
	}
	
	public String getWords() {
		String result = "";
		//2 for() anidados
		//...
		for (Device device : devices) {
			result += device.toString() + ": ";
			for (String string : device) {
				result += string + " "; 
			}
			result += "\n";
		}
		return result;
	}
	
	public ArrayList<String> getOrderedWords() {
		ArrayList<String> result = new ArrayList<String>();
		//2 for() anidados
		//...
		for (Device device : devices) {
			for (String string : device) {
				if (!result.contains(string)) result.add(string);
			}
			
		}
		result.sort(null); //que metodo de ordenacion estamos utilizando aqui...
		return result;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}