package org.eda1.practica02.parte02;

public class PalabraFrecuencia implements Comparable<PalabraFrecuencia>{
	private String palabra;
	private int frecuencia;
	
	public PalabraFrecuencia(String palabra) {
		//Cuando creamos una palabra, su frecuencia es 1
		//...
		this.palabra = palabra.trim().toLowerCase();
		this.frecuencia = 1;
		
	}
	
	public void incFrecuencia() {
		this.frecuencia++;
	}
	
	public String getPalabra() {
		return this.palabra;
	}
	
	public int getFrecuencia() {
		return this.frecuencia;
	}

	@Override
	public boolean equals(Object o) {
		return this.compareTo((PalabraFrecuencia)o) == 0;
	}
	
	@Override
	public int compareTo(PalabraFrecuencia o) {
		//Orden natural: palabra (ascendente)
		return this.palabra.compareTo(o.palabra);
	}

	@Override
	public String toString() {
		return palabra +" <" + frecuencia + ">";
	}
}
