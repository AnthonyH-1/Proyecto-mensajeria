package proyecto2;
import java.util.Scanner;
public class Proyecto2 {

public class Main {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do{
            System.out.println("Menu Mensajeria");
            System.out.println("1) Ingresar (usuario + carpeta)");
            System.out.println("2) Ver conectados");
            System.out.println("3) Enviar mensaje");
            System.out.println("4) Revisar buzón");
            System.out.println("5) Salir");
            System.out.print("Elige una opcion: ");
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1: ingresar();       
                break;
                case 2: verConectados();     
                break;
                case 3: enviarMensaje();         
                break;
                case 4: verBuzon();          
                break;
                case 5: System.out.println("Saliendo del programa.");
                break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        }while(opcion !=5);
    }
}
}
