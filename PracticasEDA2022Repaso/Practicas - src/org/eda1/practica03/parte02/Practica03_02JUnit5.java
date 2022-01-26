package org.eda1.practica03.parte02;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;


public class Practica03_02JUnit5 {

	String directorioEntrada = System.getProperty("user.dir") + File.separator +
								"Practicas - src" + File.separator +
								"org" + File.separator +
								"eda1" + File.separator +
								"practica03" + File.separator +
								"parte02" + File.separator;
	
	@Test
	public void testArticuloBasico() {
		Articulo articulo01 = new Articulo("titulo01");
		Articulo articulo02 = new Articulo("titulo02");
		Articulo articulo03 = null;
		
		//Metodo add()
		articulo01.add("Este es un ejemplo de articulo", "compuesto por lineas", "compuesto por palabras", "compuesto por letras");
		articulo01.add("articulo", "lineas", "palabras", "letras");
		articulo02.add("Este es otro ejemplo de articulo", "compuesto por lineas", "compuesto por palabras", "compuesto por letras");
		articulo01.add("articulo", "lineas");
		
		assertEquals("titulo01: {articulo=3, compuesto=3, ejemplo=1, letras=2, lineas=3, palabras=2}", articulo01.toString());
		assertEquals("titulo02: {articulo=1, compuesto=3, ejemplo=1, letras=1, lineas=1, palabras=1}", articulo02.toString()); 
		
		//Metodos getFrecuencia y getSumaFrecuencias
		assertTrue(articulo01.getFrecuencia("lineas") == 3);
		assertTrue(articulo01.getFrecuencia("linea") == -1);
		assertTrue(articulo02.getFrecuencia("compuESto  ") == 3);
		assertTrue(articulo01.getFrecuencia(" ejemplo ") == articulo02.getFrecuencia(" EJEMPlo "));
		assertTrue(articulo01.getSumaFrecuencias() == 14);
		assertTrue(articulo02.getSumaFrecuencias() == 8);
	
		//Metodo combinar()
		articulo03 = articulo01.fusionar(articulo02);
		assertEquals("titulo01+titulo02: {articulo=4, compuesto=6, ejemplo=2, letras=3, lineas=4, palabras=3}", articulo03.toString());
		articulo02.add("hola", "hola", "HOLA");
		
		articulo03 = articulo01.fusionar(articulo02);
		assertEquals("titulo01+titulo02: {articulo=4, compuesto=6, ejemplo=2, hola=3, letras=3, lineas=4, palabras=3}", articulo03.toString());
		
		assertTrue(articulo01.getSumaFrecuencias() == 14);
		assertTrue(articulo02.getSumaFrecuencias() == 11);
		assertTrue(articulo03.getSumaFrecuencias() == articulo01.getSumaFrecuencias() + articulo02.getSumaFrecuencias());
		
		//Metodo sustituir()
		articulo03.sustituir("hola",  "articulo");
		articulo02.sustituir("hola",  null);
		articulo01.sustituir("compuesto",  "compuesta");
		
		assertTrue(articulo01.getSumaFrecuencias() == 14);
		assertEquals("titulo01: {articulo=3, compuesta=3, ejemplo=1, letras=2, lineas=3, palabras=2}", articulo01.toString());
		assertTrue(articulo02.getSumaFrecuencias() == 8);
		assertEquals("titulo02: {articulo=1, compuesto=3, ejemplo=1, letras=1, lineas=1, palabras=1}", articulo02.toString());
		assertEquals("titulo01+titulo02: {articulo=7, compuesto=6, ejemplo=2, letras=3, lineas=4, palabras=3}", articulo03.toString());
		assertTrue(articulo03.getSumaFrecuencias() == 25);
		
		articulo01.clear();
		articulo02.clear();
		articulo03.clear();
		articulo01 = articulo02= articulo03 = null;
	}

	@Test
	public void testArticuloLoad() {
		Articulo articulo01 = new Articulo("articuloID01");
		Articulo articulo02 = new Articulo("articuloID02");
		
		articulo01.load(this.directorioEntrada + "articulo01");
		articulo02.load(this.directorioEntrada + "articulo02");
		
		assertTrue(articulo01.getSumaFrecuencias() == 295);
		assertTrue(articulo02.getSumaFrecuencias() == 133);
		
		//El metodo loadFile inicializa la estructura text
		articulo01.load(this.directorioEntrada + "articulo01");
		articulo02.load(this.directorioEntrada + "articulo02");
		assertTrue(articulo01.getSumaFrecuencias() == 295);
		assertTrue(articulo02.getSumaFrecuencias() == 133);
		
		assertTrue(articulo01.getFrecuencia("caracteristicas") == 2);
		assertTrue(articulo02.getFrecuencia("algoritmos") == 2);
		
		//Si no existe la palabra, devuelve -1
		assertTrue(articulo01.getFrecuencia("eda") == -1);
		assertTrue(articulo02.getFrecuencia("eda") == -1);
		
		//Sustituimos, en articulo1, todas las palabras por la palabra eda
		ArrayList<String> aux = new ArrayList<String>();
		for(Entry<String, Integer> par: articulo01) {
			aux.add(par.getKey());
		}
		for(String word: aux) {
			articulo01.sustituir(word, "eda");
		}
		assertTrue(articulo01.getFrecuencia("eda") == 295);

		//Sustituimos, en articulo2, todas las palabras por la palabra eda
		aux.clear();
		for(Entry<String, Integer> par: articulo02) {
			aux.add(par.getKey());
		}
		for(String word: aux) {
			articulo02.sustituir(word, "eda");
		}
		
		assertTrue(articulo02.getFrecuencia("eda") == 133);
		
		//Fusionamos articulo01 y articulo02
		Articulo articulo03 = articulo01.fusionar(articulo02);
		
		assertTrue(articulo03.size() == 1); //Solo tenemos la palabra eda
		assertTrue(articulo01.size() == 1);
		assertTrue(articulo02.size() == 1);
		assertTrue(articulo03.getSumaFrecuencias() == 428);
		assertTrue(articulo01.getFrecuencia("decir") == -1);
		assertTrue(articulo03.getFrecuencia("eda") == 428);
		
		aux.clear();
		articulo01.clear();
		articulo02.clear();
		articulo03.clear();
		articulo01 = articulo02= articulo03 = null;
		aux = null;
	}
	
	@Test
	public void testGestionArticulosBasico() {
		GestionArticulos gestion = new GestionArticulos();
		
		gestion.add("repositorio01", "articulo01", directorioEntrada + "articuloPrueba", "Pedra", "Martin", "Manuel");
		gestion.add("repositorio01", "articulo02", directorioEntrada + "articuloPrueba", "Maria", "Pepe", "Martin", "Jose");
		gestion.add("repositorio01", "articulo03", directorioEntrada + "articuloPrueba", "Martin", "Pedra", "Jose");
		
		assertTrue(gestion.size() == 1); //1 unico repositorio
		
		assertEquals("[articulo01, articulo03]", gestion.getArticulosID("pedra").toString());
		assertEquals("[]", gestion.getArticulosID("pedro").toString());
		assertEquals("[articulo02, articulo03]", gestion.getArticulosID("Jose").toString());
		
		assertEquals("[jose, manuel, maria, pedra, pepe]", gestion.getCoAutores("martin").toString());
		assertEquals("[martin, pedra]", gestion.getCoAutores("manuel").toString());
		assertEquals("[]", gestion.getCoAutores("martina").toString());
		
		assertEquals("[palabras=9]", gestion.getPalabrasClave("martin", 9).toString());
		
		assertEquals("[articulo=3, palabras=9, pocas=3, prueba=3]", gestion.getPalabrasClave("martin", 3).toString());
		
		assertTrue(gestion.getPalabrasClave("martin", 2).equals(gestion.getPalabrasClave("martin", 1)));
		assertTrue(gestion.getPalabrasClave("martin", 3).equals(gestion.getPalabrasClave("martin", 3)));
		assertTrue(gestion.getPalabrasClave("martin", 1).equals(gestion.getPalabrasClave("martin", 3)));
		
		assertEquals("[palabras=6]", gestion.getPalabrasClave("jose", 3).toString());
		assertEquals("[articulo=2, palabras=6, pocas=2, prueba=2]", gestion.getPalabrasClave("jose", 2).toString());
	}
	
	@Test
	public void testGestionArticulosLoad() {
		GestionArticulos gestion = new GestionArticulos();
		gestion.load(directorioEntrada, "datos");
		assertTrue(gestion.size() == 3);
		gestion.load(directorioEntrada, "datos");
		assertTrue(gestion.size() == 3);
				
		assertEquals("[titulo01_01, titulo01_04, titulo03_01, titulo03_04]", gestion.getArticulosID("Juan").toString());
		assertEquals("[titulo01_02, titulo01_03, titulo02_02, titulo02_03, titulo03_02, titulo03_03]", gestion.getArticulosID("mANUELA").toString());
		assertEquals("[]", gestion.getArticulosID("teresa").toString());

		assertEquals("[alberto, alejandro]", gestion.getCoAutores("Manuela").toString());
		assertEquals("[alberto, alejandro, juan, macarena]", gestion.getCoAutores("Pedra").toString());
		assertEquals("[]", gestion.getCoAutores("Manuel").toString());
		
		assertEquals("[algoritmo=7, algoritmos=8, base=7, clasificacion=9, como=7, datos=24, mineria=9, oa=16, tecnicas=9]", 
				      gestion.getPalabrasClave("Pedra", 7).toString());
		
		assertTrue(gestion.getPalabrasClave("Pedra", 6).size() == 15);
		assertTrue(gestion.getPalabrasClave("Pedra", 1).size() == 365);
		
		assertTrue(gestion.sustituir("pedra", "oa", "objetos aprendizaje"));
		assertEquals("[algoritmo=7, algoritmos=8, base=7, clasificacion=9, como=7, datos=24, mineria=9, objetos aprendizaje=16, tecnicas=9]", 
			      gestion.getPalabrasClave("Pedra",7).toString());
	
		assertEquals("[datos=15]",gestion.getPalabrasClave("Alberto", 15).toString());
		assertTrue(gestion.getPalabrasClave("Alberto", 1).size() == 142);
		
		gestion.clear();
		gestion = null;
	}
}