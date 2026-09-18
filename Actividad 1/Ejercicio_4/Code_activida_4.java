import java.util.Locale;
import java.util.Scanner;

/**
 * Ejercicio resuelto No 4: edades de Juan, Alberto, Ana y la mamá.
 *
 * @author Andrés David Galeano Quintero
 */
public class Code_activida_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Ingrese la edad de Juan: ");
        Edades edades = new Edades(scanner.nextDouble());

        System.out.println("LAS EDADES SON:");
        System.out.printf(Locale.US, "Alberto: %.1f%n", edades.calcularEdadAlberto());
        System.out.printf(Locale.US, "Juan: %.1f%n", edades.getEdadJuan());
        System.out.printf(Locale.US, "Ana: %.1f%n", edades.calcularEdadAna());
        System.out.printf(Locale.US, "Mama: %.1f%n", edades.calcularEdadMama());

        scanner.close();
    }
}

class Edades {
    private double edadJuan;

    public Edades(double edadJuan) {
        this.edadJuan = edadJuan;
    }

    public double getEdadJuan() {
        return edadJuan;
    }

    // Alberto tiene 2/3 de la edad de Juan
    public double calcularEdadAlberto() {
        return 2 * edadJuan / 3;
    }

    // Ana tiene 4/3 de la edad de Juan
    public double calcularEdadAna() {
        return 4 * edadJuan / 3;
    }

    // La edad de la mamá es la suma de las edades de sus tres hijos
    public double calcularEdadMama() {
        return edadJuan + calcularEdadAlberto() + calcularEdadAna();
    }
}
