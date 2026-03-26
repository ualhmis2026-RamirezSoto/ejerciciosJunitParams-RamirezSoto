package ual.hmis.sesion06.ejercicio7;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AleatorizadorArrays {

    public int[] aleatorizarConFisherYates(int[] array) {
        int[] copia = validarYCopiar(array);
        aleatorizarArrayFY(copia);
        return copia;
    }

    public int[] aleatorizarConIntercambios(int[] array) {
        int[] copia = validarYCopiar(array);
        aleatorizarArray(copia);
        return copia;
    }

    private static void aleatorizarArrayFY(int[] array) {
        aleatorizarArrayFY(array, ThreadLocalRandom.current());
    }

    private static void aleatorizarArray(int[] array) {
        aleatorizarArray(array, ThreadLocalRandom.current());
    }

    static void aleatorizarArrayFY(int[] array, Random random) {
        Objects.requireNonNull(random, "random no puede ser null");
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            intercambiar(array, i, j);
        }
    }

    static void aleatorizarArray(int[] array, Random random) {
        Objects.requireNonNull(random, "random no puede ser null");
        for (int i = 0; i < array.length; i++) {
            int j = random.nextInt(array.length);
            intercambiar(array, i, j);
        }
    }

    private static int[] validarYCopiar(int[] array) {
        Objects.requireNonNull(array, "array no puede ser null");
        if (array.length > 100) {
            throw new IllegalArgumentException("El array no puede tener mas de 100 elementos");
        }

        for (int valor : array) {
            if (valor < 0 || valor > 100) {
                throw new IllegalArgumentException("Todos los valores deben estar entre 0 y 100");
            }
        }

        return Arrays.copyOf(array, array.length);
    }

    private static void intercambiar(int[] array, int origen, int destino) {
        if (origen == destino) {
            return;
        }

        int temporal = array[origen];
        array[origen] = array[destino];
        array[destino] = temporal;
    }
}
