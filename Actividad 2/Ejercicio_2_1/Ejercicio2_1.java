/**
 * Ejercicio 2.1
 */
public class Ejercicio2_1 {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Juan Camilo", "Restrepo Gomez", "1037612458", 1998);
        Persona persona2 = new Persona("Luz Marina", "Zapata Ospina", "43875210", 1972);

        System.out.println("Datos de la persona 1:");
        persona1.imprimir();
        System.out.println();
        System.out.println("Datos de la persona 2:");
        persona2.imprimir();
    }
}

class Persona {
    private String nombre;
    private String apellidos;
    private String numeroDocumento;
    private int anioNacimiento;

    public Persona(String nombre, String apellidos, String numeroDocumento, int anioNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.anioNacimiento = anioNacimiento;
    }

    public void imprimir() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Numero de documento: " + numeroDocumento);
        System.out.println("Nacio en: " + anioNacimiento);
    }
}
