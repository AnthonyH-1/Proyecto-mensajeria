package org.example;

import java.io.*;

/**
 * Guarda mensajes cifrados en archivos dentro de la carpeta "mensajes".
 */
public class AlmacenadorMensajes {

    private static final String RUTA = "mensajes";

    /**
     * Crea carpeta si no existe.
     */
    public AlmacenadorMensajes() {
        File carpeta = new File(RUTA);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    /**
     * Guarda un mensaje cifrado en un archivo .enc
     */
    public void guardarMensaje(Message msg, String textoCifrado) {

        String fileName = msg.getFrom() + "_to_" + msg.getTo() + ".enc";
        File f = new File(RUTA + "/" + fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write(textoCifrado);
            System.out.println("Archivo guardado correctamente: " + f.getName());
        } catch (IOException ex) {
            System.out.println("Error al guardar archivo");
        }
    }
}
