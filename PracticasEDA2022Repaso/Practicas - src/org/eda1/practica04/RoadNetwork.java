package org.eda1.practica04;

import java.io.File;
import java.util.Scanner;

public class RoadNetwork extends Network<String> {

	private enum Secciones {
		Type, Vertex, Edge;

		/**
		 * Compruebo de que tipo es la linea. 
		 * Si es type compruebo si es Directed o not Directed.
		 * Si es vertex añado la línea.
		 * Si es edge, spliteo por espacios en blanco, en posicion 0 tengo el origen, 
		 * en 1 el destino y en 2 el peso.
		 * @param net
		 * @param linea
		 */
		void load(RoadNetwork net, String linea) {
			switch (this) {
			case Type:
				net.setDirected(linea.toLowerCase().equals("not directed") ? false : true);
				break;
			case Vertex:
				net.addVertex(linea);
				break;
			case Edge:
				String[] words = linea.split("[ ]+");
				net.addEdge(words[0], words[1], Double.parseDouble(words[2]));
				break;
			}

		}
	}

	/**
	 * Metodo para cargar el el archivo.
	 * me salto las lineas en balnco y las que empiezan por "%".
	 * 3 if para ver si es tipo, vertex o edge.
	 * si no es ninguna de estás debe cargar la línea ya que es informacion.
	 * @param filename
	 */
	public void load(String filename) {
		String line = "";
		Scanner scan = null;
		Secciones seccion = null;

		try {
			scan = new Scanner(new File(filename));
		} catch (Exception e) {
			System.out.println("Error al cargar el archivo --> " + e.getMessage());
			System.exit(-1);
		}

		while (scan.hasNextLine()) {
			line = scan.nextLine().trim();
			if (line.isEmpty())
				continue;
			if (line.startsWith("%"))
				continue;

			if (line.toLowerCase().contains("@type")) {
				seccion = Secciones.Type;
				continue;
			}
			if (line.toLowerCase().contains("@vertex")) {
				seccion = Secciones.Vertex;
				continue;
			}
			if (line.toLowerCase().contains("@edges")) {
				seccion = Secciones.Edge;
				continue;
			}
			
			seccion.load(this, line);

		}
	}
}
