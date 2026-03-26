package ual.hmis.sesion06.ejercicio6;

import java.util.Locale;

public class ConversionTemperatura {

    public double convertTemperature(double temperature, String fromUnit, String toUnit) {
        if (fromUnit == null || toUnit == null) {
            return Double.NaN;
        }

        String origen = fromUnit.toLowerCase(Locale.ROOT);
        String destino = toUnit.toLowerCase(Locale.ROOT);

        if (!unidadValida(origen) || !unidadValida(destino)) {
            return Double.NaN;
        }

        if (origen.equals(destino)) {
            return temperature;
        }

        if ("celsius".equals(origen)) {
            if ("fahrenheit".equals(destino)) {
                return (temperature * 9 / 5) + 32;
            }
            return temperature + 273.15;
        }

        if ("fahrenheit".equals(origen)) {
            if ("celsius".equals(destino)) {
                return (temperature - 32) * 5 / 9;
            }
            return ((temperature - 32) * 5 / 9) + 273.15;
        }

        if ("celsius".equals(destino)) {
            return temperature - 273.15;
        }

        return ((temperature - 273.15) * 9 / 5) + 32;
    }

    private boolean unidadValida(String unidad) {
        return "celsius".equals(unidad) || "fahrenheit".equals(unidad) || "kelvin".equals(unidad);
    }
}
