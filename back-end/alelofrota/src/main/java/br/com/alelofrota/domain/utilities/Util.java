package br.com.alelofrota.domain.utilities;

public class Util {

	public static String removeSpecialCharacters(String str) {
		return str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
	}
}
