package ual.hmis.sesion06.ejercicio5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.UncheckedIOException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ContadorDePalabrasTest {

    private final ContadorDePalabras contador = new ContadorDePalabras();
    private String contenidoOriginal;

    @BeforeEach
    void guardarContenidoOriginal() throws IOException {
        contenidoOriginal = Files.readString(ContadorDePalabras.INPUT_FILE, StandardCharsets.UTF_8);
    }

    @AfterEach
    void restaurarContenidoOriginal() throws IOException {
        Files.writeString(ContadorDePalabras.INPUT_FILE, contenidoOriginal, StandardCharsets.UTF_8);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ual/hmis/sesion06/ejercicio5/casos.csv", numLinesToSkip = 1, delimiter = ';')
    void contadorDevuelveLasListasEsperadas(String fixture, String esperadoAlfabetico, String esperadoFrecuencia)
            throws IOException {
        Path fixturePath = Path.of(
                "src", "test", "resources", "ual", "hmis", "sesion06", "ejercicio5", "fixtures", fixture);
        String contenidoFixture = Files.readString(fixturePath, StandardCharsets.UTF_8);
        Files.writeString(ContadorDePalabras.INPUT_FILE, contenidoFixture, StandardCharsets.UTF_8);

        assertEquals(parsearLista(esperadoAlfabetico), contador.obtenerPalabrasOrdenadasAlfabeticamente());
        assertEquals(parsearLista(esperadoFrecuencia), contador.obtenerPalabrasOrdenadasPorFrecuencia());
    }

    @org.junit.jupiter.api.Test
    void lanzaExcepcionSiElArchivoHardcodeadoNoExiste() throws IOException {
        Path backup = ContadorDePalabras.INPUT_FILE.resolveSibling("entrada-backup.txt");
        Files.move(ContadorDePalabras.INPUT_FILE, backup);
        try {
            assertThrows(UncheckedIOException.class, contador::obtenerPalabrasOrdenadasAlfabeticamente);
        } finally {
            Files.move(backup, ContadorDePalabras.INPUT_FILE);
        }
    }

    private static List<String> parsearLista(String texto) {
        if (texto == null || texto.isBlank()) {
            return List.of();
        }

        return java.util.Arrays.stream(texto.split("\\|"))
                .toList();
    }
}
