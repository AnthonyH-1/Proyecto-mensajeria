package org.example;

import java.math.BigInteger;

/**
 * Contiene métodos estáticos para cifrar y descifrar usando RSA.
 */
public class CifradorMensajes {

    /**
     * Cifra un mensaje usando clave pública y módulo.
     */
    public static String cifrar(String mensaje, BigInteger clavePublica, BigInteger modulo) {
        BigInteger m = new BigInteger(mensaje.getBytes());
        BigInteger c = m.modPow(clavePublica, modulo);
        return c.toString();
    }

    /**
     * Descifra texto cifrado usando clave privada y módulo.
     */
    public static String descifrar(String textoCifrado, BigInteger clavePrivada, BigInteger modulo) {
        BigInteger c = new BigInteger(textoCifrado);
        BigInteger m = c.modPow(clavePrivada, modulo);
        return new String(m.toByteArray());
    }
}
