package challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CriptografiaCesariana implements Criptografia {

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
