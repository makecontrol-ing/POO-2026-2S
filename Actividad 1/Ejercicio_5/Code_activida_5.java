/**
 * Ejercicio resuelto No 5: prueba de escritorio de un grupo de instrucciones.
 *
 * @author Andrés David Galeano Quintero
 */
public class Code_activida_5 {
    public static void main(String[] args) {
        PruebaEscritorio prueba = new PruebaEscritorio();

        prueba.ejecutar();

        System.out.println("EL VALOR DE LA SUMA ES: " + prueba.getSuma());
    }
}

class PruebaEscritorio {
    private double suma;
    private double x;
    private double y;

    // Ejecuta las instrucciones del libro y muestra cómo cambia cada variable
    public void ejecutar() {
        System.out.printf("%-22s %8s %8s %8s%n", "INSTRUCCION", "SUMA", "X", "Y");

        suma = 0;
        mostrarPaso("SUMA = 0");
        x = 20;
        mostrarPaso("X = 20");
        suma = suma + x;
        mostrarPaso("SUMA = SUMA + X");
        y = 40;
        mostrarPaso("Y = 40");
        x = x + Math.pow(y, 2);
        mostrarPaso("X = X + Y ** 2");
        suma = suma + x / y;
        mostrarPaso("SUMA = SUMA + X / Y");
    }

    private void mostrarPaso(String instruccion) {
        System.out.printf("%-22s %8s %8s %8s%n", instruccion, suma, x, y);
    }

    public double getSuma() {
        return suma;
    }
}
