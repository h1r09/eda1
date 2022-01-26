package org.eda1.practica03.parte02;

public class Auxiliar {
	private static String[] stopWords = {"a", "al", "con", "de", "del", "el", "en",
			                             "es", "si", "la", "las", "los", "las", "se",
			                             "y", "o", "por", "han", "ya", "lo", "para",
			                             "que", "un", "este", "esta", "aquel", "aquella", "otro", "otra"};
	public static boolean isStopWord(String word) {
		for (String stopWord: stopWords) {
			if (word.equals(stopWord)) return true;
		}
		return false;
	}
}
