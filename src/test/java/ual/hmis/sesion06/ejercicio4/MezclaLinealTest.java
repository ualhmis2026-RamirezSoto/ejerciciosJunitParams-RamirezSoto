package ual.hmis.sesion06.ejercicio4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class MezclaLinealTest {

    private final MezclaLineal mezclaLineal = new MezclaLineal();

    @ParameterizedTest
    @CsvFileSource(resources = "/ual/hmis/sesion06/ejercicio4/mezclas.csv", numLinesToSkip = 1, delimiter = ';')
    void mezclarDevuelveLaListaEsperada(String listaA, String listaB, String esperado) {
        assertEquals(parsearLista(esperado), mezclaLineal.mezclar(parsearLista(listaA), parsearLista(listaB)));
    }

    @Test
    void mezclarLanzaExcepcionSiListaANull() {
        assertThrows(NullPointerException.class, () -> mezclaLineal.<Integer>mezclar(null, List.of()));
    }

    @Test
    void mezclarLanzaExcepcionSiListaBNull() {
        assertThrows(NullPointerException.class, () -> mezclaLineal.<Integer>mezclar(List.of(), null));
    }

    private static List<Integer> parsearLista(String texto) {
        if (texto == null || texto.isBlank()) {
            return List.of();
        }

        return java.util.Arrays.stream(texto.split("\\|"))
                .map(Integer::parseInt)
                .toList();
    }
}
