package org.eda1.practica02.parte02;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edaAuxiliar.Format;
import edaAuxiliar.Greater;
import edaAuxiliar.Less;


public class Practica02_02JUnit5 {
	String directorioEntrada = System.getProperty("user.dir") + File.separator +
			   "Practicas - src" + File.separator + 
			   "org" + File.separator +
			   "eda1" + File.separator + 
			   "practica02" + File.separator + 
			   "parte02" + File.separator;

	
	@Test
	public void testFrecuenciaPalabra() {
		PalabraFrecuencia wF1 = new PalabraFrecuencia("   Hola");
		PalabraFrecuencia wF2 = new PalabraFrecuencia("hola    ");
		PalabraFrecuencia wF3 = new PalabraFrecuencia("adios  ");
		PalabraFrecuencia wF4 = new PalabraFrecuencia("bye");
		
		assertEquals("hola <1>", wF1.toString());
		assertEquals("hola <1>", wF2.toString());
		assertEquals("adios <1>", wF3.toString());
		assertEquals("bye <1>", wF4.toString());
		
		//incFrecuencia()
		for (int i = 0; i<100; i++) {
			wF1.incFrecuencia();
			wF3.incFrecuencia();
		}
		
		//toString()
		assertEquals("hola <101>", wF1.toString());
		assertEquals("hola <1>", wF2.toString());
		assertEquals("adios <101>", wF3.toString());
		assertEquals("bye <1>", wF4.toString());
		
		//getFrecuencia() y getPalabra()
		assertTrue(wF1.getFrecuencia() == wF3.getFrecuencia());
		assertTrue(wF1.getPalabra().equals(wF2.getPalabra()));
		
		//equals()
		assertEquals(wF1, wF2);
 		assertNotEquals(wF1, wF3);

		//compareTo()
		assertTrue(wF1.compareTo(wF2) == 0);
		assertTrue(wF4.compareTo(wF3) > 0);
		assertTrue(wF2.compareTo(wF3) > 0);
		assertTrue(wF1.compareTo(wF3) > 0);
		
		wF1 = wF2 = wF3 = wF4 = null;
	}
	
	@Test
	public void testLibro() {
		Libro libro1 = new Libro("Libro01");
		Libro libro2 = new Libro("Libro02");
		
		//toString() con arbol de palabra vacio
		assertEquals("libro01 -> []", libro1.toString());
		assertEquals("libro02 -> []", libro2.toString());
		
		//Insertamos palabas en book1
		libro1.add("adios");
		libro1.add("mundo");
		libro1.add("cruel");
		libro1.add("adios");
	
		//toString()
		assertEquals("libro01 -> [adios <2>, cruel <1>, mundo <1>]", libro1.toString());

		//Insertamos palabras en book2
		libro2.add("hola");
		libro2.add("mundo");
		libro2.add("maravilloso");
		libro2.add("ADIOS");
		libro2.add("MUNDO");
		
		//toString()
		assertEquals("libro02 -> [adios <1>, hola <1>, maravilloso <1>, mundo <2>]", libro2.toString());
	
		//equals() y compareTo()
		assertNotEquals(libro1, libro2);
		assertEquals(libro2, new Libro("   libRO02     "));
		assertTrue(libro1.compareTo(libro2) < 0);
		assertTrue(libro1.compareTo(new Libro("libro01"))==0);
		
		//Iterar sobre un libro equivale a iterar sobre los objetos FrecuenciaPalabra que contiene
		int cont = 0;
		ArrayList<String> salidaEsperada = new ArrayList<String>();
		
		salidaEsperada.add("adios <2>");
		salidaEsperada.add("cruel <1>");
		salidaEsperada.add("mundo <1>");
		
		for (PalabraFrecuencia wF : libro1) {
			assertEquals(salidaEsperada.get(cont++), wF.toString());
		}
		
		salidaEsperada.clear();
		salidaEsperada.add("adios <1>");
		salidaEsperada.add("hola <1>");
		salidaEsperada.add("maravilloso <1>");
		salidaEsperada.add("mundo <2>");
	
		cont = 0;
		for (PalabraFrecuencia wF : libro2) {
			assertEquals(salidaEsperada.get(cont++), wF.toString());
		}
		
		salidaEsperada.clear();
		libro1.clear();
		libro2.clear();
		salidaEsperada = null;
		libro1 = libro2 = null;
	}
	
	@Test
	public void testUsuario() {
		Usuario usuario1 = new Usuario("Pedra");
		Usuario usuario2 = new Usuario("Martina");
		Usuario usuario3 = new Usuario("Juan");
		Usuario usuario4 = new Usuario("Juan");
		
		//toString()
		assertEquals("pedra", usuario1.toString());
		assertEquals("martina", usuario2.toString());
		assertEquals("juan", usuario3.toString());
		
		for (int i=0; i<5; i++) {
			usuario1.registrarPrestamo(new Libro("libro" + Format.formatInt(i)));
			usuario2.registrarPrestamo(new Libro("libro" + Format.formatInt(2*i)));
			usuario3.registrarPrestamo(new Libro("libro" + Format.formatInt(3*i)));
		}
		
		//getRegistroPrestamos()
		assertEquals("[libro000 -> [], libro001 -> [], libro002 -> [], libro003 -> [], libro004 -> []]", usuario1.getRegistroPrestamos());
		assertEquals("[libro000 -> [], libro002 -> [], libro004 -> [], libro006 -> [], libro008 -> []]", usuario2.getRegistroPrestamos());
		assertEquals("[libro000 -> [], libro003 -> [], libro006 -> [], libro009 -> [], libro012 -> []]", usuario3.getRegistroPrestamos());
		assertEquals("[]", usuario4.getRegistroPrestamos());
	
		//equals() y compareTo()
		assertEquals(usuario3, usuario4);
		assertTrue(usuario1.compareTo(usuario3) > 0);
		assertTrue(usuario2.compareTo(usuario3) > 0);
		assertTrue(usuario3.compareTo(usuario4) == 0);
		
		//Iterar sobre un lector equivale a iterar sobre los libros que ha leido
		int cont = 0;
		ArrayList<String> salidaEsperada = new ArrayList<String>();
		
		salidaEsperada.add("libro000 -> []");
		salidaEsperada.add("libro001 -> []");
		salidaEsperada.add("libro002 -> []");
		salidaEsperada.add("libro003 -> []");
		salidaEsperada.add("libro004 -> []");
		
		for (Libro libro : usuario1) {
			assertEquals(salidaEsperada.get(cont++),libro.toString());
		}
		
		//toString(): libros con palabras...
		Libro libro1 = new Libro("libro001");
		Libro libro2 = new Libro("libro002");
		libro1.add("hola");
		libro1.add("mundo");
		libro2.add("adios");
		libro2.add("mundo");
		usuario4.registrarPrestamo(libro1);
		usuario4.registrarPrestamo(libro2);
		
		assertEquals("[libro001 -> [hola <1>, mundo <1>], libro002 -> [adios <1>, mundo <1>]]", usuario4.getRegistroPrestamos());
		
		libro1.add("hola");
		libro2.add("mundo");
		assertEquals("[libro001 -> [hola <2>, mundo <1>], libro002 -> [adios <1>, mundo <2>]]", usuario4.getRegistroPrestamos());
		
		usuario1.clear();
		usuario2.clear();
		usuario3.clear();
		usuario4.clear();
		usuario1 = usuario2 = usuario3 = usuario4 = null;
	}
	
	@Test
	public void testSimilitudLibros() {
		Libro libro1 = new Libro("Libro01");
		Libro libro2 = new Libro("Libro02");
		
		libro1.add("adios");
		libro1.add("mundo");
		libro1.add("cruel");
		libro1.add("adios");
	
		libro2.add("hola");
		libro2.add("mundo");
		libro2.add("maravilloso");
		libro2.add("ADIOS");
		libro2.add("MUNDO");
		
		//Metodo getPalabrasComunes()
		assertEquals("[adios, mundo]",libro1.getPalabrasComunes(libro2).toString());
		
		libro2.add("cruel");
		assertEquals("[adios, cruel, mundo]",libro1.getPalabrasComunes(libro2).toString());
		
		libro1.add("maravilloso");
		assertEquals("[adios, cruel, maravilloso, mundo]",libro1.getPalabrasComunes(libro2).toString());
		
		
		assertTrue(libro1.getPalabrasComunes(libro2).equals(libro2.getPalabrasComunes(libro1)));
	
		//getGradoSimilitud()
		assertTrue(libro1.getDistancia(libro1) == .0);
		assertTrue(libro1.getDistancia(libro2) == Math.sqrt(2));
		
		libro1.add("cruel");
		libro2.add("hola");

		assertTrue(libro1.getDistancia(libro2) == Math.sqrt(3));
		
		for (int i=0; i<10; i++) {
			libro1.add("hola");
			libro2.add("mundo");
		}
		
		assertEquals(Format.formatDouble(libro1.getDistancia(libro2)), "13.67");

		assertTrue(libro1.getDistancia(libro2) == libro2.getDistancia(libro1));		
		
		libro1.clear();
		libro2.clear();
		libro1 = libro2 = null;
	}
	
	@Test
	public void testBiblioteca() {
		Biblioteca biblioteca = new Biblioteca("Biblioteca UAL");
		final int NUM_LIBROS = 5;
		final int NUM_USUARIOS = 5;
		
		for (int i=0; i<NUM_LIBROS; i++) {
			biblioteca.addLibro(new Libro("libro" + Format.formatInt(i)));
		}
	
		for (int i=0; i<NUM_USUARIOS; i++) {
			biblioteca.addUsuario(new Usuario("usuario" + Format.formatInt(i)));
		}
		
		//toString()
		assertEquals("Biblioteca UAL (" + NUM_LIBROS + " libros y " + NUM_USUARIOS + " usuarios)", biblioteca.toString());
		
		//prestarLibro() y devolverLibro()
		assertTrue(biblioteca.prestarLibro("usuario001", "libro001"));
		assertFalse(biblioteca.prestarLibro("usuario002", "libro001")); //El libro ya esta prestado
		assertFalse(biblioteca.prestarLibro("usuario01", "libro001"));  //No existe usuario01
		assertTrue(biblioteca.prestarLibro("usuario001", "libro002"));
		assertTrue(biblioteca.devolverLibro("usuario001", "libro001"));
		assertFalse(biblioteca.devolverLibro("usuario001", "libro001")); //el libro no esta prestado
		assertFalse(biblioteca.devolverLibro("usuario002", "libro001")); //el libro no esta prestado
		assertFalse(biblioteca.devolverLibro("usuario001", "libro001")); //no existe usuario01
		assertTrue(biblioteca.devolverLibro("usuario001", "libro002"));
		assertFalse(biblioteca.prestarLibro("usuario002", "libro3"));	//No existe libro3
		assertTrue(biblioteca.prestarLibro("usuario002", "libro002"));
		assertTrue(biblioteca.prestarLibro("usuario002", "libro003"));
		assertTrue(biblioteca.prestarLibro("usuario002", "libro004"));
		
		//getUsuariosPrestamo()
		assertEquals("[usuario001]",biblioteca.getUsuariosPrestamo("libro001").toString());
		assertEquals("[usuario001, usuario002]", biblioteca.getUsuariosPrestamo("libro002").toString());
		assertEquals("[]",biblioteca.getUsuariosPrestamo("libro000").toString());
		assertTrue(biblioteca.getUsuariosPrestamo("libro02") == null);
		
		//getLibrosPrestados()
		assertEquals("[libro001, libro002]", biblioteca.getLibrosPrestados("usuario001").toString());
		assertEquals("[libro002, libro003, libro004]", biblioteca.getLibrosPrestados("usuario002").toString());
		assertTrue(biblioteca.getLibrosPrestados("user03") == null);
		
		//getLibrosPrestados
		assertEquals("[libro002: usuario002, libro003: usuario002, libro004: usuario002]", biblioteca.getLibrosPrestados().toString());
		
		//Devolvemos todos los libros para hacer una nueva prueba
		biblioteca.devolverLibro("usuario002", "libro002");
		biblioteca.devolverLibro("usuario002", "libro003");
		biblioteca.devolverLibro("usuario002", "libro004");
		
		assertTrue(biblioteca.getLibrosPrestados().isEmpty());
		
		for (int i=0; i < NUM_USUARIOS; i++) {
			for (int j=0; j < NUM_LIBROS; j++) {
				biblioteca.prestarLibro("usuario" + Format.formatInt(i), "libro" + Format.formatInt(j));
				biblioteca.devolverLibro("usuario" + Format.formatInt(i), "libro" + Format.formatInt(j));	
			}
		}
		for (int i=0; i < NUM_LIBROS; i++) {
			assertTrue(biblioteca.getUsuariosPrestamo("libro" + Format.formatInt(i)).size() == NUM_USUARIOS);
	
		}
		for (int i=0; i < NUM_USUARIOS; i++) {
			assertTrue(biblioteca.getLibrosPrestados("usuario" + Format.formatInt(i)).size() == NUM_LIBROS);	
		}
	
		assertTrue(biblioteca.getLibrosPrestados().isEmpty());
		
		biblioteca.clear();
		biblioteca = null;
	}
	
	@Test
	public void testUsuarioSim() {		
		ArrayList<UsuarioSim> lista = new ArrayList<UsuarioSim>();
		lista.add(new UsuarioSim("pedra", 10));
		lista.add(new UsuarioSim("juan", 10));
		lista.add(new UsuarioSim("martina", 4));
		lista.add(new UsuarioSim("amelia", 4));

		//toString()
		assertEquals("[pedra <10.00>, juan <10.00>, martina <4.00>, amelia <4.00>]",lista.toString());
		
		//Ordenamos la lista: criterio = orden natural
		lista.sort(null);
		assertEquals("[amelia <4.00>, martina <4.00>, juan <10.00>, pedra <10.00>]", lista.toString());

		//Ordenamos la lista: criterio = orden natural (equivalente al anterior) 
		lista.sort(new Less<UsuarioSim>());
		assertEquals("[amelia <4.00>, martina <4.00>, juan <10.00>, pedra <10.00>]", lista.toString());
		
		//Ordenamos la lista: criterio = orden natural inverso
		lista.sort(new Greater<UsuarioSim>());
		assertEquals("[pedra <10.00>, juan <10.00>, martina <4.00>, amelia <4.00>]",lista.toString());
		
		lista.clear();
		lista = null;
	}
	
	@Test
	public void testSimilares() {
		Biblioteca biblioteca = new Biblioteca("Picasso");
		Usuario usuario1 = new Usuario("Luisa");
		Usuario usuario2 = new Usuario("Carmen");
		Usuario usuario3 = new Usuario("Juan");
		Usuario usuario4 = new Usuario("Perico");
		Libro libro1 = new Libro("El bosque de los cuatro vientos");
		Libro libro2 = new Libro("Y Julia reto a los dioses");
		Libro libro3 = new Libro("Las alas de Sophie");
		Libro libro4 = new Libro("La madre de Frankestein");
		
		libro1.load(directorioEntrada + "archivo1");
		libro2.load(directorioEntrada + "archivo2");
		libro3.load(directorioEntrada + "archivo3");
		libro4.load(directorioEntrada + "archivo4");

		biblioteca.addLibro(libro1);
		biblioteca.addLibro(libro2);
		biblioteca.addLibro(libro3);
		biblioteca.addLibro(libro4);
		biblioteca.addUsuario(usuario1);
		biblioteca.addUsuario(usuario2);
		biblioteca.addUsuario(usuario3);
		biblioteca.addUsuario(usuario4);
		
		assertEquals("Picasso (4 libros y 4 usuarios)", biblioteca.toString());
		
		assertTrue(biblioteca.prestarLibro("Luisa", "La madre de Frankestein"));
		assertTrue(biblioteca.prestarLibro("Juan",  "El bosque de los cuatro vientos"));
		assertTrue(biblioteca.prestarLibro("Perico", "Las alas de Sophie"));
		assertTrue(biblioteca.prestarLibro("Carmen", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.devolverLibro("Luisa", "La madre de Frankestein"));
		assertTrue(biblioteca.prestarLibro("Perico", "La madre de Frankestein"));
		assertTrue(biblioteca.devolverLibro("Carmen", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.prestarLibro("Juan", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.devolverLibro("Juan", "Y Julia reto a los dioses"));
		assertTrue(biblioteca.prestarLibro("Perico", "Y Julia reto a los dioses"));
		
		assertTrue(biblioteca.getLibrosPrestados().size() == 4);
		
		//similarity()
		assertTrue(biblioteca.getDistancia("Pepe", "Juan") == -1); //Pepe no existe
		assertTrue(biblioteca.getDistancia("Juan", "Pepe") == -1);

		assertTrue(biblioteca.getDistancia("Perico", "Luisa") == libro4.getDistancia(libro4) + 
				                                            libro4.getDistancia(libro3) +
				                                            libro4.getDistancia(libro2));
		assertTrue(biblioteca.getDistancia("Carmen", "Luisa") == libro4.getDistancia(libro2));
		
	
		assertEquals(Format.formatDouble(biblioteca.getDistancia("Carmen",  "Perico")), "15.86");
		assertEquals(Format.formatDouble(biblioteca.getDistancia("Carmen",  "Luisa")), "9.38");
		assertEquals(Format.formatDouble(biblioteca.getDistancia("JUAN",  "Luisa")), "17.13");
		
		
		assertEquals("[juan <5.92>, luisa <9.38>, perico <15.86>]", biblioteca.getSimilares("Carmen").toString());
		assertEquals("[carmen <15.86>, luisa <18.60>, juan <36.59>]", biblioteca.getSimilares("Perico").toString());
		assertEquals("[carmen <5.92>, luisa <17.13>, perico <36.59>]", biblioteca.getSimilares("Juan").toString());
		assertEquals("[carmen <9.38>, juan <17.13>, perico <18.60>]", biblioteca.getSimilares("Luisa").toString());
		assertTrue(biblioteca.getSimilares("Pedra")==null);
		assertTrue(biblioteca.getSimilares("Carmela")==null);
		
		biblioteca.clear();
		usuario1.clear();
		usuario2.clear();
		usuario3.clear();
		usuario4.clear();
		libro1.clear();
		libro2.clear();
		libro3.clear();
		libro4.clear();
		usuario1 = usuario2 = usuario3 = usuario4 = null;
		libro1 = libro2 = libro3 = libro4 = null;
	}
}