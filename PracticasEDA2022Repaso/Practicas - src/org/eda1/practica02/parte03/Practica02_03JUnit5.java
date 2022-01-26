package org.eda1.practica02.parte03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import edaAuxiliar.Format;
import edaAuxiliar.Pair;

public class Practica02_03JUnit5 {
		 			
	@Test
	public void testAVLTreePair() {
		AVLTreePair<String, ArrayList<Double>> arbol = new AVLTreePair<String, ArrayList<Double>>();
		ArrayList<Double> aux = null;
		
		assertTrue(arbol.put("Oswald", new ArrayList<Double>()));
		assertTrue(arbol.get("Oswald").size() == 0);
		
		assertFalse(arbol.put("Oswald", null));
		assertTrue(arbol.get("Oswald") == null);
		
		assertFalse(arbol.put("Oswald", new ArrayList<Double>()));
		
		assertTrue(arbol.get("Pepe") == null);
		
		arbol.get("Oswald").add(3.0);
		arbol.get("Oswald").add(2.0);
		arbol.get("Oswald").add(4.0);
		arbol.get("Oswald").add(7.0);
		
		assertTrue(arbol.get("Oswald").size() == 4);
		
		aux = new ArrayList<Double>();
		aux.add(3.0);
		aux.add(2.0);
		aux.add(8.0);
		aux.add(3.0);
		assertTrue(arbol.put("Edward", aux)); 
		assertFalse(arbol.put("Edward", aux)); 
		
		assertTrue(arbol.get("Edward").size() == 4);
		
		assertTrue(arbol.size() == 2);
				
		assertEquals("[Edward, Oswald]", arbol.keySet().toString());
		assertEquals("[[3.0, 2.0, 8.0, 3.0], [3.0, 2.0, 4.0, 7.0]]", arbol.values().toString());
		assertEquals("[<Edward, [3.0, 2.0, 8.0, 3.0]>, <Oswald, [3.0, 2.0, 4.0, 7.0]>]", arbol.entrySet().toString());	
		
		assertEquals(arbol.toString(), arbol.entrySet().toString());
		
		assertFalse(arbol.put("Edward", new ArrayList<Double>()));
		assertEquals("[<Edward, []>, <Oswald, [3.0, 2.0, 4.0, 7.0]>]", arbol.entrySet().toString());	
		
		assertTrue(arbol.put("Alfred", new ArrayList<Double>()));
		assertTrue(arbol.size() == 3);
		
		aux = arbol.get("Alfred");
		
		assertTrue(arbol.get("Alfred").size() == 0);
		
		aux.add(23.3);
		aux.add(34.3);
		
		assertTrue(arbol.get("Alfred").size() == 2);
		assertFalse(arbol.put("Oswald", aux)); //put() devuelve false pq la clave ya existe; ahora Oswald y Alfred comparten la misma estructura (aux)
		
		assertEquals("[23.3, 34.3]", arbol.get("Oswald").toString());
		assertEquals("[23.3, 34.3]", arbol.get("Alfred").toString());
		
		aux.add(.5);
		aux.add(.6);
		aux.add(.3); 
		
		assertEquals("[23.3, 34.3, 0.5, 0.6, 0.3]", arbol.get("Oswald").toString());
		
		assertEquals("[23.3, 34.3, 0.5, 0.6, 0.3]", arbol.get("Alfred").toString());
		
		assertNull(arbol.get("Bruce"));
		assertFalse(arbol.containsKey("Bruce"));

		assertEquals("[<Alfred, [23.3, 34.3, 0.5, 0.6, 0.3]>, <Edward, []>, <Oswald, [23.3, 34.3, 0.5, 0.6, 0.3]>]", arbol.toString());
		
		arbol.clear();
		aux.clear();
		
		assertEquals("[]", arbol.entrySet().toString());
		assertEquals("[]", arbol.keySet().toString());
		assertEquals("[]", arbol.values().toString());
		assertEquals(arbol.toString(), arbol.entrySet().toString());
		
		aux.clear();
		arbol = null;
		aux = null;
	}
	
	@Test
	public void testSujeto() {
		Sujeto sujeto1 = new Sujeto("@oswald");
		Sujeto sujeto2 = new Sujeto("@edwarD");
		Sujeto sujeto3 = new Sujeto("@Alfred");
		
		assertTrue(sujeto1.add("prueba01", 2.0, 5.0, 8.9, 7.2, 0., .0));
		assertFalse(sujeto1.add("prueba01", 6.0, 5.0, 0., 2.0, 2.0));
		assertTrue(sujeto2.add("prueba01", 3.0, 2.0, 5.0, 8.0, 5.0));
		assertTrue(sujeto2.add("prueba02", 8.0, 5.0, 7.5, 9.0, 12.0));
		assertTrue(sujeto3.add("prueba02", 4., 4., .4, .4));
		assertTrue(sujeto3.add("prueba03", 4., 4., 4.));
		
		assertTrue(sujeto1.getNumPuntuaciones() == 11);
		assertTrue(sujeto1.getNumPuntuaciones("pruebas0001") == -1);
		assertTrue(sujeto1.getNumPuntuaciones("prueba01") == 11);
		
		assertTrue(sujeto3.getNumPuntuaciones() == 7);
		assertTrue(sujeto3.getNumPuntuaciones("prueba02") == 4);
		
		assertTrue(sujeto1.getMaximaPuntuacion() == 8.9);
		assertEquals(Format.formatDouble(sujeto2.getMaximaPuntuacion()), "12.00");
		assertEquals(Format.formatDouble(sujeto3.getMaximaPuntuacion()), "4.00");
		
		assertTrue(sujeto2.getMaximaPuntuacion("prueba03") == -1);
		
		assertTrue(sujeto2.getMaximaPuntuacion() == sujeto2.getMaximaPuntuacion("prueba02"));
	
		assertEquals("@oswald=<1 prueba>", sujeto1.toString());
		assertEquals("@edwarD=<2 pruebas>", sujeto2.toString());
		assertEquals("@Alfred=<2 pruebas>", sujeto3.toString());
		
		assertTrue(sujeto1.add("prueba02"));
		assertTrue(sujeto1.add("prueba03"));
		assertTrue(sujeto1.add("prueba04"));
		assertFalse(sujeto1.add("prueba02", 15., 19.));
		assertFalse(sujeto1.add("prueba03", 1.5, 1.9, 2.));
		
		assertEquals("@oswald=<4 pruebas>", sujeto1.toString());

		String salidaEsperada = "<prueba01, [2.0, 5.0, 8.9, 7.2, 0.0, 0.0, 6.0, 5.0, 0.0, 2.0, 2.0]>\n" + 
								"<prueba02, [15.0, 19.0]>\n" + 
								"<prueba03, [1.5, 1.9, 2.0]>\n" + 
								"<prueba04, []>\n";
								
		String salidaReal = "";
		for (Pair<String, ArrayList<Double>> par : sujeto1) {
			salidaReal += par.toString() + "\n";
		}
		assertEquals(salidaEsperada, salidaReal);
		
		sujeto1.clear();
		sujeto2.clear();
		sujeto3.clear();
		salidaEsperada = salidaReal = null;
		sujeto1 = sujeto2 = sujeto3 = null;
	}
}