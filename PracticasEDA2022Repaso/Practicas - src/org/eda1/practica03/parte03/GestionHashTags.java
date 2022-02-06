package org.eda1.practica03.parte03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.soap.Text;

import edaAuxiliar.Greater;
import edaAuxiliar.Less;

public class GestionHashTags {
	private HashMap<HashTag, ArrayList<String>> datos;

	public GestionHashTags() {
		this.datos = new HashMap<HashTag, ArrayList<String>>();
	}

	/**
	 * Si hastag no existe lo creo, y añado text pasado a minúsculas.
	 * 
	 * @param hashTagID
	 * @param paisID
	 * @param text
	 */
	public void add(String hashTagID, String paisID, String text) {
		HashTag hashTag = new HashTag(hashTagID, paisID);
		ArrayList<String> current = this.datos.get(hashTag);
		if (current == null) {
			current = new ArrayList<String>();
			this.datos.put(hashTag, current);
		}
		current.add(text.toLowerCase());
	}

	/**
	 * Llamamos al metodo add() con hashTagID, paisID y text aleatorios (clase
	 * Auxiliar)
	 * 
	 * @param numRepeticiones
	 */
	public void generaDatosAleatorios(int numRepeticiones) {
		for (int i = 0; i < numRepeticiones; i++) {
			add(Auxiliar.getHashTagRandom(), Auxiliar.getPaisRandom(), Auxiliar.getMensajeRandom());
		}
	}

	/**
	 * recorro los mensajes y a sum le sumo el tamaño de cada uno.
	 * 
	 * @return sum, suma de frecuencias
	 */
	public int getSumFrecuencias() {
		int sum = 0;
		for (ArrayList<String> mensaje : this.datos.values()) {
			sum += mensaje.size();
		}
		return sum;
	}

	/**
	 * recorro los mensajes y los limpio. limpio la estructura datos.
	 */
	public void clear() {
		for (ArrayList<String> mensaje : this.datos.values()) {
			mensaje.clear();
		}
		this.datos.clear();
	}

	public int size() {
		return this.datos.size();
	}

	/**
	 * En este caso, no nos interesa saber el contenido de los mensajes Unicamente
	 * nos interesa conocer el numero de mensajes que se han enviado asociados al
	 * par (hashTagID, pais) Contruyo el toString para que coincida con la salida
	 * esperada.
	 */
	@Override
	public String toString() {
		ArrayList<String> result = new ArrayList<String>();
		for (Entry<HashTag, ArrayList<String>> dato : this.datos.entrySet()) {
			result.add(dato.getKey().toString() + "[" + dato.getValue().size() + "]");
		}
		return result.toString();
	}

	/**
	 * pais (orden descendente) 
	 * numMensajes (segun comparator comp) 
	 * hashTagsID (orden descendente)
	 * 
	 * @param comp
	 * @return aux.toString()
	 */
	public String toStringOrdered(Comparator<Integer> comp) {
		TreeMap<String, TreeMap<Integer, TreeSet<String>>> aux = new TreeMap<String, TreeMap<Integer, TreeSet<String>>>(
				new Greater<String>());
		for (Entry<HashTag, ArrayList<String>> dato : this.datos.entrySet()) {
			TreeMap<Integer, TreeSet<String>> aux2 = aux.get(dato.getKey().getPaisID());

			if (aux2 == null) {
				aux2 = new TreeMap<Integer, TreeSet<String>>(comp);
				aux.put(dato.getKey().getPaisID(), aux2);
			}
			TreeSet<String> aux3 = aux2.get(dato.getValue().size());
			if (aux3 == null) {
				aux3 = new TreeSet<String>(new Greater<String>());
				aux2.put(dato.getValue().size(), aux3);
			}
			aux3.add(dato.getKey().getHashTagID());
		}
		return aux.toString();
	}

	public static void main(String[] args) {
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

		assertEquals(
				"[savetheworld <es>[2], picoftheday <en>[1], happy <en>[1], happy <es>[2], savetheworld <it>[2], happy <it>[1]]",
				gestion.toString());

		assertTrue(gestion.getSumFrecuencias() == 9);

		assertEquals("{it={2=[savetheworld], 1=[happy]}, es={2=[savetheworld, happy]}, en={1=[picoftheday, happy]}}",
				gestion.toStringOrdered(new Greater<Integer>()));

		gestion.add("#happy", "es", "me reafirmo");

		assertEquals(
				"{it={2=[savetheworld], 1=[happy]}, es={3=[happy], 2=[savetheworld]}, en={1=[picoftheday, happy]}}",
				gestion.toStringOrdered(new Greater<Integer>()));

		assertEquals(
				"{it={1=[happy], 2=[savetheworld]}, es={2=[savetheworld], 3=[happy]}, en={1=[picoftheday, happy]}}",
				gestion.toStringOrdered(new Less<Integer>()));

		gestion.add("#savetheworld", "es", "idem");
		gestion.add("#happy", "it", "idem");

		assertTrue(gestion.getSumFrecuencias() == 12);

		assertEquals("{it={2=[savetheworld, happy]}, es={3=[savetheworld, happy]}, en={1=[picoftheday, happy]}}",
				gestion.toStringOrdered(new Greater<Integer>()));

		gestion.clear();
		int numRepeticiones = 100000000;

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
