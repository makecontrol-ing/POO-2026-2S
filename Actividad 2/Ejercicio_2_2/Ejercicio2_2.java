import java.util.Locale;

/**
 * Ejercicio 2.2
 */
public class Ejercicio2_2 {
    public static void main(String[] args) {
        Planeta tierra = new Planeta("Tierra", 1, 5.9722E24, 1.08321E12, 12756, 150,
                TipoPlaneta.TERRESTRE, true);
        Planeta jupiter = new Planeta("Jupiter", 95, 1.89813E27, 1.43128E15, 142984, 778,
                TipoPlaneta.GASEOSO, true);

        tierra.imprimir();
        System.out.printf(Locale.US, "Densidad: %.4e kg/km3%n", tierra.calcularDensidad());
        System.out.println("Es un planeta exterior: " + (tierra.esExterior() ? "Si" : "No"));
        System.out.println();

        jupiter.imprimir();
        System.out.printf(Locale.US, "Densidad: %.4e kg/km3%n", jupiter.calcularDensidad());
        System.out.println("Es un planeta exterior: " + (jupiter.esExterior() ? "Si" : "No"));
    }
}

class Planeta {
    private String nombre = null;
    private int cantidadSatelites = 0;
    private double masa = 0.0;
    private double volumen = 0.0;
    private int diametro = 0;
    private int distanciaMediaSol = 0;
    private TipoPlaneta tipo;
    private boolean observable = false;

    public Planeta(String nombre, int cantidadSatelites, double masa, double volumen,
            int diametro, int distanciaMediaSol, TipoPlaneta tipo, boolean observable) {
        this.nombre = nombre;
        this.cantidadSatelites = cantidadSatelites;
        this.masa = masa;
        this.volumen = volumen;
        this.diametro = diametro;
        this.distanciaMediaSol = distanciaMediaSol;
        this.tipo = tipo;
        this.observable = observable;
    }

    public void imprimir() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Cantidad de satelites: " + cantidadSatelites);
        System.out.printf(Locale.US, "Masa: %.4e kg%n", masa);
        System.out.printf(Locale.US, "Volumen: %.4e km3%n", volumen);
        System.out.println("Diametro: " + diametro + " km");
        System.out.println("Distancia media al Sol: " + distanciaMediaSol + " millones de km");
        System.out.println("Tipo de planeta: " + tipo);
        System.out.println("Observable a simple vista: " + (observable ? "Si" : "No"));
    }

    public double calcularDensidad() {
        return masa / volumen;
    }

    public boolean esExterior() {
        double distanciaUA = distanciaMediaSol * 1000000.0 / 149597870;
        return distanciaUA > 3.4;
    }
}

enum TipoPlaneta {
    GASEOSO, TERRESTRE, ENANO
}
