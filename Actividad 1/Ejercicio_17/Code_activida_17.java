import java.util.Locale;
import java.util.Scanner;

/**
 * Ejercicio propuesto No 17: área del círculo y longitud de la circunferencia.
 *
 * @author Andrés David Galeano Quintero
 */
public class Code_activida_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Ingrese el radio del circulo: ");
        Circulo circulo = new Circulo(scanner.nextDouble());

        System.out.printf(Locale.US, "El radio es: %.2f%n", circulo.getRadio());
        System.out.printf(Locale.US, "El area del circulo es: %.2f%n", circulo.calcularArea());
        System.out.printf(Locale.US, "La longitud de la circunferencia es: %.2f%n", circulo.calcularLongitud());

        scanner.close();
    }
}

class Circulo {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    // Área = PI * radio^2
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    // Longitud = 2 * PI * radio
    public double calcularLongitud() {
        return 2 * Math.PI * radio;
    }
}
