package ual.hmis.sesion06.ejercicio3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class Ejercicio3Test {

    private final Ejercicio3 ejercicio3 = new Ejercicio3();

    @ParameterizedTest
    @CsvFileSource(resources = "/ual/hmis/sesion06/ejercicio3/passwords.csv", numLinesToSkip = 1, delimiter = ';')
    void enmascararPasswordDevuelveLaSalidaEsperada(String password, String esperado) {
        assertEquals(esperado, ejercicio3.enmascararPassword(password));
    }

    @Test
    void enmascararPasswordNullSeConsideraDemasiadoCorto() {
        assertEquals("password demasiado corto", ejercicio3.enmascararPassword(null));
    }
}
