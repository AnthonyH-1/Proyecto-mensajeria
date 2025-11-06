package org.example;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Se encarga de generar llaves RSA para cifrar y descifrar mensajes.
 */
public class GeneradorClaves {

    private BigInteger n;   // Módulo
    private BigInteger e;   // Clave pública
    private BigInteger d;   // Clave privada

    /**
     * Constructor
     * @param bits Tamaño de la clave RSA
     */
    public GeneradorClaves(int bits) {
        generar(bits);
    }

    /**
     * Genera las claves RSA usando dos primos grandes.
     */
    private void generar(int bits) {
        SecureRandom rnd = new SecureRandom();

        // Crear dos primos grandes
        BigInteger p = BigInteger.probablePrime(bits / 2, rnd);
        BigInteger q = BigInteger.probablePrime(bits / 2, rnd);

        // Calcular módulo
        n = p.multiply(q);

        // Calcular phi
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Exponente público estándar
        e = BigInteger.valueOf(65537);

        // Asegurar coprimos
        if (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = BigInteger.valueOf(3);
        }

        // Exponente privado
        d = e.modInverse(phi);
    }

    public BigInteger obtenerModulo() {
        return n;
    }

    public BigInteger obtenerClavePublica() {
        return e;
    }

    public BigInteger obtenerClavePrivada() {
        return d;
    }
}
