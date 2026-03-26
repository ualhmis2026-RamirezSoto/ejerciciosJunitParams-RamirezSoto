package ual.hmis.sesion06.ejercicio5;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContadorDePalabras {

    static final Path INPUT_FILE = Path.of(
            "src", "main", "resources", "ual", "hmis", "sesion06", "ejercicio5", "entrada.txt");

    private static final Pattern WORD_PATTERN = Pattern.compile("[\\p{L}\\p{Nd}]+");

    public List<String> obtenerPalabrasOrdenadasAlfabeticamente() {
        List<String> palabras = new ArrayList<>(leerPalabras());
        palabras.sort(String::compareTo);
        return palabras;
    }

    public List<String> obtenerPalabrasOrdenadasPorFrecuencia() {
        Map<String, Integer> frecuencias = new LinkedHashMap<>();
        for (String palabra : leerPalabras()) {
            frecuencias.merge(palabra, 1, Integer::sum);
        }

        return frecuencias.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .toList();
    }

    private List<String> leerPalabras() {
        try {
            String contenido = Files.readString(INPUT_FILE, StandardCharsets.UTF_8).toLowerCase(Locale.ROOT);
            Matcher matcher = WORD_PATTERN.matcher(contenido);
            List<String> palabras = new ArrayList<>();
            while (matcher.find()) {
                palabras.add(matcher.group());
            }
            return palabras;
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer el archivo de entrada", e);
        }
    }
}
