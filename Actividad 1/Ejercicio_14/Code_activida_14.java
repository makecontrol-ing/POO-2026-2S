import java.util.Locale;
import java.util.Scanner;

/**
 * Ejercicio propuesto No 14: leer un número y obtener su cuadrado y su cubo.
 *
 * @author Andrés David Galeano Quinteroa
 */
public class Code_activida_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Ingrese un numero: ");
        Numero numero = new Numero(scanner.nextDouble());

        System.out.println("El numero es: " + numero.getValor());
        System.out.println("Su cuadrado es: " + numero.calcularCuadrado());
        System.out.println("Su cubo es: " + numero.calcularCubo());

        scanner.close();
    }
}

class Numero {
    private double valor;

    public Numero(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public double calcularCuadrado() {
        return valor * valor;
    }

    public double calcularCubo() {
        return valor * valor * valor;
    }
}
