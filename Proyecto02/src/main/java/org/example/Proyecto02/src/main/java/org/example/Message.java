package org.example;

/**
 * Representa un mensaje enviado entre usuarios.
 */
public class Message {
    private String from;   // remitente
    private String to;     // destinatario
    private String text;   // contenido original

    public Message(String from, String to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public String getFrom() { return from; }

    public String getTo() { return to; }

    public String getText() { return text; }
}
