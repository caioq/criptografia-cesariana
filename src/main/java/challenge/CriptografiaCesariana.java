package challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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

	// private String encriptar(String texto, Function<Integer, Integer> cifra){

	@Override
	public String criptografar(String texto) {
		if (texto.equals(null)) {
			throw new NullPointerException();
		}
		if (texto.isEmpty()) {
			throw new IllegalArgumentException();
		}
		// converte string em lista de chars
		List<Character> listCharsBefore = texto.chars().mapToObj(i -> (char) i).collect(Collectors.toList());

		// calcula valor da letra criptografada
		Function<Character, Character> encriptar = letra -> {
			Character novaLetra = (char) (letra + 3);
			if (novaLetra > 122) {
				novaLetra = (char) (novaLetra - 26);
			}
			return novaLetra;
		};
		// converte letra para minusculo
		Function<Character, Character> toLowerCase = Character::toLowerCase;
		// Une as duas funcoes de tratamento na criptografia
		Function<Character, Character> transformacao = toLowerCase.andThen(encriptar);

		// criptografa cada char da lista
		String textCrypted = listCharsBefore.stream().map(c -> {
			if (c.isLetter(c)) {
				return transformacao.apply(c).toString();
			}
			return c.toString();
		}).collect(Collectors.joining(""));

		return textCrypted;
	}

	@Override
	public String descriptografar(String texto) {
		if (texto.equals(null)) {
			throw new NullPointerException();
		}
		if (texto.isEmpty()) {
			throw new IllegalArgumentException();
		}
		// converte string em lista de chars
		List<Character> listCharsBefore = texto.chars().mapToObj(i -> (char) i).collect(Collectors.toList());

		// calcula valor da letra criptografada
		Function<Character, Character> desencriptar = letra -> {
			Character novaLetra = (char) (letra - 3);
			if (novaLetra < 97) {
				novaLetra = (char) (122 - (96 - novaLetra));
			}
			return novaLetra;
		};
		// converte letra para minusculo
		Function<Character, Character> toLowerCase = Character::toLowerCase;
		// Une as duas funcoes de tratamento na criptografia
		Function<Character, Character> transformacao = toLowerCase.andThen(desencriptar);

		// descriptografa cada char da lista
		String textDecrypted = listCharsBefore.stream().map(c -> {
			if (c.isLetter(c)) {
				return transformacao.apply(c).toString();
			}
			return c.toString();
		}).collect(Collectors.joining(""));

		return textDecrypted;
	}

}
