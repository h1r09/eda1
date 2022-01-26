package org.eda1.practica03.parte03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

public class HashTag {
	private String hashTagID;
	private String paisID;
	private boolean isTrendingTopic; //No lo vamos a utilizar...por ahora...

	public HashTag(String hashTagID, String pais) {
		//...
	}
	
	public void setIsTrendingTopic(boolean flag) {
		this.isTrendingTopic = flag;
	}
	
	public boolean getIsTrendingTopic() {
		return this.isTrendingTopic;
	}

	public String getPaisID() {
		return this.paisID;
	}
	
	public String getHashTagID() {
		return this.hashTagID;
	}
	
	@Override
	public int hashCode() {
		return //1 sola linea --> investigad el uso del metodo estatico Objects.hash(Objects...args);
	}

	@Override
	public boolean equals(Object o) {
		//Atencion a este metodo --> equals debe ser coherente con hashCode()
		return this.hashCode() == ((HashTag) o).hashCode();
	}

	@Override
	public String toString() {
		return //...
	}

	public static void main(String[] args) {
		HashTag hashTag01 = new HashTag("#SaveTheWorld", "es");
		HashTag hashTag02 = new HashTag("SaveTheWorld", "es");
		HashTag hashTag03 = new HashTag("#MacBookPro", "it");
		HashTag hashTag04 = new HashTag("MacBookPro", "fr");
		
		assertTrue(hashTag01.equals(hashTag02));
		assertEquals("savetheworld <es>", hashTag01.toString());
		assertEquals("macbookpro <it>", hashTag03.toString());
		assertFalse(hashTag01.equals(hashTag04));
		assertFalse(hashTag03.equals(hashTag04));
		assertTrue(hashTag03.equals(new HashTag("macbookpro", "it")));
		assertEquals("macbookpro <fr>", hashTag04.toString());

		hashTag04.setIsTrendingTopic(true);
		assertEquals("****macbookpro <fr>", hashTag04.toString());
		
		System.out.println("TODO OK!!!!!");

		hashTag01 = hashTag02 = hashTag03 = hashTag04 = null;
	}
}