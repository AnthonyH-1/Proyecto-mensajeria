package org.example;

import java.util.*;

/**
 * Controla el flujo del programa, mostrando el menú e interactuando con los módulos de usuarios, cifrado y archivo.
 */
public class Proyecto02 {

    // Scanner global para leer entrada de usuario
    private static final Scanner teclado = new Scanner(System.in);

    // Usuario actualmente logueado
    private static User usuarioActual;

    // Registro de todos los usuarios que han iniciado sesión
    private static Set<String> usuariosConectados = new HashSet<>();

    // Contenedor donde se asocian usuarios con sus llaves RSA
    private static Map<String, GeneradorClaves> llavesPorUsuario = new HashMap<>();

    // Administra almacenamiento de mensajes
    private static AlmacenadorMensajes almacenador = new AlmacenadorMensajes();

    // Revisa y consume mensajes desde la carpeta
    private static Reader lector = new Reader();

    public static void main(String[] args) {

        int opcion;

        // Ciclo principal del menú
        do {
            System.out.println("\n---------------- MENÚ MENSAJERÍA ----------------");
            System.out.println("1. Ingresar usuario");
            System.out.println("2. Ver usuarios conectados");
            System.out.println("3. Enviar mensaje");
            System.out.println("4. Revisar buzón");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> ingresarUsuario();
                case 2 -> verConectados();
                case 3 -> enviarMensaje();
                case 4 -> revisarBuzon();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 5);
    }

    /**
     * Permite registrar un usuario o seleccionar uno previamente ingresado.
     * También asigna o recupera las llaves RSA del usuario.
     */
    private static void ingresarUsuario() {

        System.out.print("Ingresa tu nombre de usuario: ");
        String nombre = formatear(teclado.nextLine());

        // Si el usuario ya existía, solo lo seleccionamos
        if (usuariosConectados.contains(nombre)) {
            System.out.println("Ya habías iniciado sesión como: " + nombre);
            usuarioActual = new User(nombre);
        } else {
            // Nuevo usuario
            usuariosConectados.add(nombre);
            usuarioActual = new User(nombre);
        }

        // Obtener o generar llaves RSA
        GeneradorClaves clavesUser = llavesPorUsuario.get(nombre);

        // Si no tiene llaves, se crean
        if (clavesUser == null) {
            clavesUser = new GeneradorClaves(1024);
            llavesPorUsuario.put(nombre, clavesUser);
            System.out.println("Nuevas llaves generadas para " + nombre);
        } else {
            System.out.println("Llaves cargadas para " + nombre);
        }

        System.out.println("Sesión iniciada como: " + nombre);
    }

    /**
     * Muestra los usuarios que han ingresado al sistema.
     */
    private static void verConectados() {
        System.out.println("Usuarios conectados:");
        usuariosConectados.forEach(u -> System.out.println("- " + u));
    }

    /*
     * Permite al usuario actual enviar un mensaje a otro usuario.
     * El mensaje se cifra con la clave pública del receptor.
     */
    private static void enviarMensaje() {

        // Validar que haya usuario con sesión activa
        if (usuarioActual == null) {
            System.out.println("Debes iniciar sesión primero");
            return;
        }

        System.out.print("Destinatario: ");
        String destino = formatear(teclado.nextLine());

        System.out.print("Mensaje: ");
        String texto = teclado.nextLine();

        // Crear objeto mensaje
        Message msj = new Message(usuarioActual.getUsername(), destino, texto);

        // Obtener llaves del destinatario
        GeneradorClaves clavesDestino = llavesPorUsuario.get(destino);

        if (clavesDestino == null) {
            System.out.println("ERROR: Destinatario nunca inició sesión, no tiene llaves.");
            return;
        }

        // Cifrar con llave pública del destinatario
        String cifrado = CifradorMensajes.cifrar(
                texto,
                clavesDestino.obtenerClavePublica(),
                clavesDestino.obtenerModulo()
        );

        // Guardar mensaje en archivo
        almacenador.guardarMensaje(msj, cifrado);

        System.out.println("Mensaje enviado a " + destino);
    }

    /*
     * Revisar los mensajes destinados al usuario logueado.
     * Se descifran usando su clave privada.
     */
    private static void revisarBuzon() {

        if (usuarioActual == null) {
            System.out.println("Debes iniciar sesión primero");
            return;
        }

        String nombre = usuarioActual.getUsername();

        // Obtener llaves del usuario
        GeneradorClaves claves = llavesPorUsuario.get(nombre);

        if (claves == null) {
            System.out.println("ERROR: No hay llaves asociadas a " + nombre);
            return;
        }

        // Obtener mensajes cifrados
        List<String> mensajesCifrados = lector.obtenerMensajesPara(nombre);

        if (mensajesCifrados.isEmpty()) {
            System.out.println("No hay mensajes nuevos para " + nombre);
            return;
        }

        // Descifrar mensajes
        System.out.println("Mensajes para " + nombre + ":");
        for (String cifrado : mensajesCifrados) {
            String texto = CifradorMensajes.descifrar(
                    cifrado,
                    claves.obtenerClavePrivada(),
                    claves.obtenerModulo()
            );

            System.out.println("Mensaje: " + texto);
        }
    }

    /**
     * Normaliza entrada: sin espacios dobles, solo minúsculas.
     */
    private static String formatear(String s) {
        return s.trim().toLowerCase().replaceAll("\\s+", " ");
    }

}
