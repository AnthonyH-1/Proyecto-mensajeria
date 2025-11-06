package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Escanea la carpeta mensajes y obtiene los archivos
 * destinados a un usuario. Luego los borra, simulando un buzón real.
 */
public class Reader {

    private static final String RUTA = "mensajes";

    public Reader() {
        File carpeta = new File(RUTA);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    /**
     * Obtiene y elimina los mensajes destinados al usuario.
     */
    public List<String> obtenerMensajesPara(String usuario) {

        List<String> mensajes = new ArrayList<>();

        File carpeta = new File(RUTA);
        File[] archivos = carpeta.listFiles();

        if (archivos == null)
            return mensajes;

        for (File f : archivos) {

            // Buscar archivos cuyo nombre indique destinatario
            if (f.getName().toLowerCase().contains("_to_" + usuario.toLowerCase())) {
                try {
                    // Leer texto cifrado
                    String texto = Files.readString(f.toPath());
                    mensajes.add(texto);

                    // Eliminar archivo tras consumo
                    f.delete();

                } catch (IOException ex) {
                    System.out.println("Error leyendo archivo: " + f.getName());
                }
            }
        }

        return mensajes;
    }
}
