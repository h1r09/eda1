package org.eda1.practica03.parte03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import edaAuxiliar.Greater;
import edaAuxiliar.Less;

public class GestionHashTags {
	private HashMap<HashTag, ArrayList<String>> datos;

	public GestionHashTags() {
		this.datos = new HashMap<HashTag, ArrayList<String>>();
	}
	
	public void add(String hashTagID, String paisID, String text) {
		HashTag hashTag = //...
		ArrayList<String> current = //...
		//...
		current.add(text.toLowerCase());
	}
	
	public void generaDatosAleatorios(int numRepeticiones) {
		for (int i=0; i<numRepeticiones; i++) {
			//Llamamos al metodo add() con hashTagID, paisID y text aleatorios (clase Auxiliar)
		}
	}
	
	public int getSumFrecuencias() {
		int sum = 0;
		//1 simple for()
		//...
		return sum;
	}
	
	public void clear(){
		//1 for()
		//...
		this.datos.clear();
	}
	
	public int size() {
		return this.datos.size();
	}
	
	@Override
	public String toString() {
		ArrayList<String> result = new ArrayList<String>();
		//En este caso, no nos interesa saber el contenido de los mensajes...
		//Unicamente nos interesa conocer el numero de mensajes que se han enviado asociados al par (hashTagID, pais)
		//1 for()
		//...
		return result.toString();
	}
	
	public String toStringOrdered(Comparator<Integer> comp) {
		TreeMap<String, TreeMap<Integer, TreeSet<String>>> aux = new TreeMap<String, TreeMap<Integer, TreeSet<String>>>(new Greater<String>());
		// pais (orden descendente) --> numMensajes (segun comparator comp) --> hashTagsIDs (orden descendente)
		//1 for()
		//...
		return aux.toString();
	}
	
	public static void main(String[]args) {
		GestionHashTags gestion = new GestionHashTags();
		
		gestion.add("#saveTheWorld", "ES", "Es hora de salvar a nuestro planeta");
		gestion.add("#saveTheWorld", "es", "No podemos esperar ni un minuto mas");
		gestion.add("#saveTheWorld", "it", "e ora di salvare il nostro pianeta");
		gestion.add("#saveTheWorld", "it", "per favore");
		gestion.add("#happy", "es", "hoy me siento feliz");
		gestion.add("#happy", "es", "incluso mas que ayer");
		gestion.add("#happy", "en", "today I am feeling so happy");
		gestion.add("#happy", "it", "mi sento felice");
		gestion.add("#picOfTheDay", "en", "blah blah blah");
		
		
		assertEquals("[savetheworld <es>[2], picoftheday <en>[1], happy <en>[1], happy <es>[2], savetheworld <it>[2], happy <it>[1]]", gestion.toString());
		
		assertTrue(gestion.getSumFrecuencias() == 9);
		
		assertEquals("{it={2=[savetheworld], 1=[happy]}, es={2=[savetheworld, happy]}, en={1=[picoftheday, happy]}}", gestion.toStringOrdered(new Greater<Integer>()));
		
		gestion.add("#happy", "es", "me reafirmo");

		assertEquals("{it={2=[savetheworld], 1=[happy]}, es={3=[happy], 2=[savetheworld]}, en={1=[picoftheday, happy]}}", gestion.toStringOrdered(new Greater<Integer>()));

		assertEquals("{it={1=[happy], 2=[savetheworld]}, es={2=[savetheworld], 3=[happy]}, en={1=[picoftheday, happy]}}", gestion.toStringOrdered(new Less<Integer>()));

		gestion.add("#savetheworld", "es", "idem");
		gestion.add("#happy", "it", "idem");

		assertTrue(gestion.getSumFrecuencias() == 12);
		
		assertEquals("{it={2=[savetheworld, happy]}, es={3=[savetheworld, happy]}, en={1=[picoftheday, happy]}}", gestion.toStringOrdered(new Greater<Integer>()));
		
		gestion.clear();
		int numRepeticiones= 100000000; 
		
		for (int i = 1000; i <= numRepeticiones; i *= 10) {
			gestion.generaDatosAleatorios(i);
			assertTrue(gestion.getSumFrecuencias() == i);
			assertTrue(gestion.size() == 70); // por que setenta...
			gestion.clear();
		}
		
		System.out.println("TODO OK!!!!!");
		
		gestion = null;
	}
}
