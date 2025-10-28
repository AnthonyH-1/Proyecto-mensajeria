package proyecto2;

public class Usuario {

    private String nombre;
    private String carpeta;

    public Usuario() {}
    public Usuario(String nombre, String carpeta) {
        this.nombre = nombre;
        this.carpeta = carpeta;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCarpeta() { return carpeta; }
    public void setCarpeta(String carpeta) { this.carpeta = carpeta; }
}
