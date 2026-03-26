package ual.hmis.sesion06.ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MezclaLineal {

    public <T extends Comparable<? super T>> List<T> mezclar(List<T> listaA, List<T> listaB) {
        Objects.requireNonNull(listaA, "listaA no puede ser null");
        Objects.requireNonNull(listaB, "listaB no puede ser null");

        List<T> resultado = new ArrayList<>();
        int indiceA = 0;
        int indiceB = 0;

        while (indiceA < listaA.size() && indiceB < listaB.size()) {
            T valorA = listaA.get(indiceA);
            T valorB = listaB.get(indiceB);
            int comparacion = valorA.compareTo(valorB);

            if (comparacion < 0) {
                agregarSinDuplicados(resultado, valorA);
                indiceA++;
            } else if (comparacion > 0) {
                agregarSinDuplicados(resultado, valorB);
                indiceB++;
            } else {
                agregarSinDuplicados(resultado, valorA);
                indiceA++;
                indiceB++;
            }
        }

        while (indiceA < listaA.size()) {
            agregarSinDuplicados(resultado, listaA.get(indiceA));
            indiceA++;
        }

        while (indiceB < listaB.size()) {
            agregarSinDuplicados(resultado, listaB.get(indiceB));
            indiceB++;
        }

        return resultado;
    }

    private <T> void agregarSinDuplicados(List<T> resultado, T valor) {
        if (resultado.isEmpty() || !resultado.get(resultado.size() - 1).equals(valor)) {
            resultado.add(valor);
        }
    }
}
