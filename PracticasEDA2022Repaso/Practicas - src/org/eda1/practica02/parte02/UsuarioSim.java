package org.eda1.practica02.parte02;

import edaAuxiliar.Format;

public class UsuarioSim implements Comparable<UsuarioSim>{
	private String usuarioID;
	private double distancia;
	
	public UsuarioSim(String usuarioID, double distance) {
		this.usuarioID = usuarioID;
		this.distancia = distance;
	}

	@Override
	public String toString() {
		return this.usuarioID + " <" + Format.formatDouble(this.distancia) + ">";
	}
	
	@Override
	public int compareTo(UsuarioSim o) {
		//Orden natural: Criterio 1: distancia (ascendente); Criterio 2: nombre (ascendente)
		int cmp = Double.compare(this.distancia, o.distancia);		
		return cmp == 0 ? this.usuarioID.compareTo(o.usuarioID) : cmp;
	}
}
