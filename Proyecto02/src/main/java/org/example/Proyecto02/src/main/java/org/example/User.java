package org.example;

/**
 * Clase Usuario. Normaliza y almacena el nombre.
 */
public class User {
    private String username;

    public User(String username) {
        this.username = formatear(username);
    }

    public String getUsername() {
        return username;
    }

    /**
     * Normaliza nombre: minúsculas, sin espacios extra.
     */
    private String formatear(String s) {
        return s.trim().toLowerCase().replaceAll("\\s+", " ");
    }
}
