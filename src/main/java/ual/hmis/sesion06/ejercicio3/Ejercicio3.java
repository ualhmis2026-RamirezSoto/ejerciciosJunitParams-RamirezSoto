package ual.hmis.sesion06.ejercicio3;

public class Ejercicio3 {

    public String enmascararPassword(String password) {
        if (password == null || password.length() < 5) {
            return "password demasiado corto";
        }

        if (password.length() <= 8) {
            return "*".repeat(8);
        }

        if (password.length() <= 11) {
            return "*".repeat(password.length());
        }

        if (password.length() <= 40) {
            return "*".repeat(12);
        }

        return "password demasiado largo";
    }
}
