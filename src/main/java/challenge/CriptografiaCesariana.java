package challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.stream.Collectors;

public class CriptografiaCesariana implements Criptografia {

	public static final Map<Integer, String> alfabeto;
	static {
		alfabeto = new HashMap<>();
		alfabeto.put(1, "a");
		alfabeto.put(2, "b");
		alfabeto.put(3, "c");
		alfabeto.put(4, "d");
		alfabeto.put(5, "e");
		alfabeto.put(6, "f");
		alfabeto.put(7, "g");
		alfabeto.put(8, "h");
		alfabeto.put(9, "i");
		alfabeto.put(10, "j");
		alfabeto.put(11, "k");
		alfabeto.put(12, "l");
		alfabeto.put(13, "m");
		alfabeto.put(14, "n");
		alfabeto.put(15, "o");
		alfabeto.put(16, "p");
		alfabeto.put(17, "q");
		alfabeto.put(18, "r");
		alfabeto.put(19, "s");
		alfabeto.put(20, "t");
		alfabeto.put(21, "u");
		alfabeto.put(22, "v");
		alfabeto.put(23, "w");
		alfabeto.put(24, "x");
		alfabeto.put(25, "y");
		alfabeto.put(26, "z");
	}
	
	//private String encriptar(String texto, Function<Integer, Integer> cifra){

	@Override
	public String criptografar(String texto) {
		if(texto.equals(null)) {
			throw new NullPointerException();
		}
		if(texto.isEmpty()) {
			throw new IllegalArgumentException();
		}
		// converte string em lista de chars
		List<Character> listCharsBefore = texto.chars().mapToObj(i -> (char) i).collect(Collectors.toList());

		// criptografa cada char da lista
		String textCrypted = listCharsBefore.stream().map(c -> {
			if (c.isLetter(c)) {
				return getCryptedValue(alfabeto, c.toString());
			}
			return c.toString();
		}).collect(Collectors.joining(""));

		return textCrypted;
	}

	@Override
	public String descriptografar(String texto) {
		if(texto.equals(null)) {
			throw new NullPointerException();
		}
		if(texto.isEmpty()) {
			throw new IllegalArgumentException();
		}
		// converte string em lista de chars
		List<Character> listCharsBefore = texto.chars().mapToObj(i -> (char) i).collect(Collectors.toList());

		// descriptografa cada char da lista
		String textDecrypted = listCharsBefore.stream().map(c -> {
			if (c.isLetter(c)) {
				return getDecryptedValue(alfabeto, c.toString());
			}
			return c.toString();
		}).collect(Collectors.joining(""));

		return textDecrypted;
	}

	// Obtem novo valor para o char que deseja ser criptografado
	public String getCryptedValue(Map<Integer, String> map, String value) {
		Integer newKey = Integer.valueOf(getKeyByValue(map, value).toArray()[0].toString()) + 3;
		if (newKey > 26) {
			newKey = newKey - 26;
		}
		return alfabeto.get(newKey);
	}

	// Obtem novo valor para o char que deseja ser descriptografado
	public String getDecryptedValue(Map<Integer, String> map, String value) {
		Integer newKey = Integer.valueOf(getKeyByValue(map, value).toArray()[0].toString()) - 3;
		if (newKey < 1) {
			newKey = 26 - newKey;
		}
		return alfabeto.get(newKey);
	}

	
	
	// Obtem key do mapa alfabeto atraves do valor/letra
	public <K, V> Set<Integer> getKeyByValue(Map<Integer, String> map, String value) {
		return map.entrySet().stream().filter(e -> e.getValue().equalsIgnoreCase(value)).map(Map.Entry::getKey)
				.collect(Collectors.toSet());
	}

}
