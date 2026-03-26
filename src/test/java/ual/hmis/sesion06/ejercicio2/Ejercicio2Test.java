package ual.hmis.sesion06.ejercicio2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Ejercicio2Test {

    private final Ejercicio2 ejercicio2 = new Ejercicio2();

    @ParameterizedTest
    @MethodSource("credencialesInvalidas")
    void loginRechazaCredencialesInvalidas(String username, String password) {
        assertFalse(ejercicio2.login(username, password));
    }

    @ParameterizedTest
    @MethodSource("credencialesConLongitudValidaPeroNoRegistradas")
    void loginAceptaLongitudesLimiteAunqueLaBDAcabeRechazandolas(String username, String password) {
        assertFalse(ejercicio2.login(username, password));
    }

    @Test
    void loginAceptaCredencialesCorrectas() {
        assertTrue(ejercicio2.login("user", "Pass1"));
    }

    private static Stream<Arguments> credencialesInvalidas() {
        String longitudTreinta = "u".repeat(30);
        String passwordSinMayusculas = "password1";
        String passwordSinMinusculas = "PASSWORD1";
        String passwordSinDigitos = "Password";
        String passwordTreinta = "A".repeat(15) + "a".repeat(14) + "1";

        return Stream.of(
                Arguments.of(null, "Pass1"),
                Arguments.of("user", null),
                Arguments.of("", "Pass1"),
                Arguments.of("user", ""),
                Arguments.of(longitudTreinta, "Pass1"),
                Arguments.of("user", passwordTreinta),
                Arguments.of("user", passwordSinMayusculas),
                Arguments.of("user", passwordSinMinusculas),
                Arguments.of("user", passwordSinDigitos)
        );
    }

    private static Stream<Arguments> credencialesConLongitudValidaPeroNoRegistradas() {
        String usernameVeintinueve = "u".repeat(29);
        String passwordVeintinueve = "Aa1" + "x".repeat(26);
        return Stream.of(
                Arguments.of(usernameVeintinueve, "Pass1"),
                Arguments.of("user", passwordVeintinueve),
                Arguments.of("otro", "Pass1")
        );
    }
}
