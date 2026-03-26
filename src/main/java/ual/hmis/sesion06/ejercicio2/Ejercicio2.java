package ual.hmis.sesion06.ejercicio2;

public class Ejercicio2 {

    public boolean login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return false;
        }

        if (username.length() >= 30 || password.length() >= 30) {
            return false;
        }

        if (!contieneMayuscula(password) || !contieneMinuscula(password) || !contieneDigito(password)) {
            return false;
        }

        return compruebaLoginEnBD(username, password);
    }

    public boolean compruebaLoginEnBD(String username, String password) {
        return "user".equals(username) && "Pass1".equals(password);
    }

    private boolean contieneMayuscula(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }

    private boolean contieneMinuscula(String password) {
        return password.chars().anyMatch(Character::isLowerCase);
    }

    private boolean contieneDigito(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }
}
