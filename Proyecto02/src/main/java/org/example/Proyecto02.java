package org.example;

import java.util.Scanner;

/**
 * Clase principal del sistema de mensajería.
 * Controla el flujo de interacción del usuario y muestra el menú principal.
 * Parte A - Interfaz y flujo base del sistema.
 */
public class Proyecto02 {

    private static final Scanner teclado = new Scanner(System.in);
    private static User currentUser;
    private static Reader reader = new Reader(); // ← aquí usamos la nueva clase Reader

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ MENSAJERÍA =====");
            System.out.println("1. Ingresar (usuario)");
            System.out.println("2. Ver conectados (placeholder)");
            System.out.println("3. Enviar mensaje");
            System.out.println("4. Revisar buzón");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    verConectados();
                    break;
                case 3:
                    enviarMensaje();
                    break;
                case 4:
                    revisarBuzon();
                    break;
                case 5:
                    System.out.println(" Saliendo del sistema...");
                    break;
                default:
                    System.out.println(" Opción inválida. Intenta nuevamente.");
            }

        } while (opcion != 5);
    }

    private static void iniciarSesion() {
        System.out.print("Ingresa tu nombre de usuario: ");
        String username = teclado.nextLine();
        currentUser = new User(username);
        System.out.println("Sesión iniciada como: " + username);
    }

    private static void verConectados() {
        System.out.println("Usuarios conectados (simulado):");
        System.out.println("- ana_user");
        System.out.println("- adonis_user");
    }

    private static void enviarMensaje() {
        if (currentUser == null) {
            System.out.println(" Debes iniciar sesión antes de enviar un mensaje.");
            return;
        }

        System.out.print("Destinatario: ");
        String to = teclado.nextLine();
        System.out.print("Mensaje: ");
        String text = teclado.nextLine();

        Message mensaje = new Message(currentUser.getUsername(), to, text);
        System.out.println(" Enviando mensaje (sin cifrado)...");

        // En la Parte B se integrará el cifrado y el guardado de archivo.
        System.out.println("Mensaje enviado a " + mensaje.getTo() + ": " + mensaje.getText());
    }

    private static void revisarBuzon() {
        if (currentUser == null) {
            System.out.println(" Debes iniciar sesión primero.");
            return;
        }

        reader.checkNow(currentUser); // ← aquí llamamos a Reader
    }
}
