package org.example;

import java.util.function.Consumer;

public class Reader {

    public void startPolling(User user, Consumer<Message> onMessage) {
        System.out.println(" Monitoreo de carpeta activado (modo simulado).");
        // En Parte B se agregará WatchService o lectura de archivos reales.
    }

    public void checkNow(User user) {
        System.out.println(" (Simulado) No hay mensajes nuevos para " + user.getUsername() + ".");
    }
}
