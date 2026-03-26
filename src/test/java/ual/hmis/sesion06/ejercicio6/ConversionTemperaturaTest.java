package ual.hmis.sesion06.ejercicio6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ConversionTemperaturaTest {

    private final ConversionTemperatura conversion = new ConversionTemperatura();

    @ParameterizedTest
    @MethodSource("casosDeConversion")
    void convierteTemperaturasCorrectamente(double temperatura, String origen, String destino, double esperado) {
        assertEquals(esperado, conversion.convertTemperature(temperatura, origen, destino), 0.000001);
    }

    @ParameterizedTest
    @MethodSource("casosInvalidos")
    void devuelveNaNConUnidadesInvalidas(double temperatura, String origen, String destino) {
        assertTrue(Double.isNaN(conversion.convertTemperature(temperatura, origen, destino)));
    }

    @Test
    void devuelveNaNSiAlgunaUnidadEsNull() {
        assertTrue(Double.isNaN(conversion.convertTemperature(10, null, "Celsius")));
        assertTrue(Double.isNaN(conversion.convertTemperature(10, "Celsius", null)));
    }

    private static Stream<Arguments> casosDeConversion() {
        return Stream.of(
                Arguments.of(0.0, "Celsius", "Fahrenheit", 32.0),
                Arguments.of(0.0, "Celsius", "Kelvin", 273.15),
                Arguments.of(32.0, "Fahrenheit", "Celsius", 0.0),
                Arguments.of(32.0, "Fahrenheit", "Kelvin", 273.15),
                Arguments.of(273.15, "Kelvin", "Celsius", 0.0),
                Arguments.of(273.15, "Kelvin", "Fahrenheit", 32.0),
                Arguments.of(25.5, "Celsius", "Celsius", 25.5)
        );
    }

    private static Stream<Arguments> casosInvalidos() {
        return Stream.of(
                Arguments.of(10.0, "Rankine", "Celsius"),
                Arguments.of(10.0, "Celsius", "Rankine")
        );
    }
}
