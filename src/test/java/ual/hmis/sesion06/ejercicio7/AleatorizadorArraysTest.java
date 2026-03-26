package ual.hmis.sesion06.ejercicio7;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AleatorizadorArraysTest {

    private final AleatorizadorArrays aleatorizador = new AleatorizadorArrays();

    @ParameterizedTest
    @MethodSource("casosValidos")
    void losMetodosPublicosDevuelvenUnaPermutacionDelArray(String metodo, int[] original) {
        int[] resultado = ejecutarMetodo(metodo, original);
        assertNotSame(original, resultado);
        assertArrayEquals(ordenado(original), ordenado(resultado));
    }

    @ParameterizedTest
    @MethodSource("casosInvalidos")
    void losMetodosPublicosValidanLaEntrada(String metodo, int[] original, Class<? extends Throwable> excepcion) {
        assertThrows(excepcion, () -> ejecutarMetodo(metodo, original));
    }

    @Test
    void fisherYatesConRandomFijoProduceElResultadoEsperado() {
        int[] array = {0, 1, 2, 3};
        AleatorizadorArrays.aleatorizarArrayFY(array, new FixedRandom(1, 1, 0));
        assertArrayEquals(new int[]{2, 0, 3, 1}, array);
    }

    @Test
    void intercambiosConRandomFijoProduceElResultadoEsperado() {
        int[] array = {0, 1, 2, 3};
        AleatorizadorArrays.aleatorizarArray(array, new FixedRandom(0, 3, 1, 1));
        assertArrayEquals(new int[]{0, 1, 3, 2}, array);
    }

    private int[] ejecutarMetodo(String metodo, int[] original) {
        return switch (metodo) {
            case "FY" -> aleatorizador.aleatorizarConFisherYates(original);
            case "SWAP" -> aleatorizador.aleatorizarConIntercambios(original);
            default -> throw new IllegalArgumentException("Metodo no soportado");
        };
    }

    private static Stream<Arguments> casosValidos() {
        int[] arrayCien = new int[100];
        for (int i = 0; i < arrayCien.length; i++) {
            arrayCien[i] = i;
        }

        return Stream.of(
                Arguments.of("FY", new int[]{}),
                Arguments.of("FY", new int[]{42}),
                Arguments.of("FY", new int[]{0, 1, 2, 3, 4}),
                Arguments.of("FY", arrayCien),
                Arguments.of("SWAP", new int[]{}),
                Arguments.of("SWAP", new int[]{42}),
                Arguments.of("SWAP", new int[]{0, 1, 2, 3, 4}),
                Arguments.of("SWAP", arrayCien)
        );
    }

    private static Stream<Arguments> casosInvalidos() {
        int[] arrayLargo = new int[101];
        int[] arrayValorNegativo = {0, -1, 2};
        int[] arrayValorGrande = {0, 101, 2};

        return Stream.of(
                Arguments.of("FY", null, NullPointerException.class),
                Arguments.of("FY", arrayLargo, IllegalArgumentException.class),
                Arguments.of("FY", arrayValorNegativo, IllegalArgumentException.class),
                Arguments.of("FY", arrayValorGrande, IllegalArgumentException.class),
                Arguments.of("SWAP", null, NullPointerException.class),
                Arguments.of("SWAP", arrayLargo, IllegalArgumentException.class),
                Arguments.of("SWAP", arrayValorNegativo, IllegalArgumentException.class),
                Arguments.of("SWAP", arrayValorGrande, IllegalArgumentException.class)
        );
    }

    private static int[] ordenado(int[] array) {
        int[] copia = Arrays.copyOf(array, array.length);
        Arrays.sort(copia);
        return copia;
    }

    private static final class FixedRandom extends Random {
        private final int[] values;
        private int index;

        private FixedRandom(int... values) {
            this.values = values;
        }

        @Override
        public int nextInt(int bound) {
            int value = values[index++];
            if (value >= bound) {
                throw new IllegalArgumentException("Valor de prueba fuera del limite");
            }
            return value;
        }
    }
}
