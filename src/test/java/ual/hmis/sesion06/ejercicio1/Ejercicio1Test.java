package ual.hmis.sesion06.ejercicio1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

class Ejercicio1Test {

    private final Ejercicio1 ejercicio1 = new Ejercicio1();

    @ParameterizedTest
    @CsvSource({
            "10, 1",
            "9, 1",
            "25, 1",
            "7, 7"
    })
    void transformarDevuelveElValorEsperado(int entrada, int esperado) {
        assertEquals(esperado, ejercicio1.transformar(entrada));
    }

    @Test
    void transformarConCeroProvocaStackOverflow() {
        assertThrows(StackOverflowError.class, () -> ejercicio1.transformar(0));
    }
}
