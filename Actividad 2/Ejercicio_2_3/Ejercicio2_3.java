import java.util.Locale;

/**
 * Ejercicio 2.3
 */
public class Ejercicio2_3 {
    public static void main(String[] args) {
        Automovil automovil = new Automovil("Mazda", 2022, 2.0, TipoCombustible.GASOLINA,
                TipoAutomovil.COMPACTO, 4, 5, 200, Color.ROJO, 0);

        System.out.println("DATOS DEL AUTOMOVIL");
        automovil.mostrarAtributos();
        System.out.println();

        automovil.setVelocidadActual(100);
        System.out.println("Se coloca la velocidad en 100 km/h. Velocidad actual: "
                + automovil.getVelocidadActual() + " km/h");

        automovil.acelerar(20);
        System.out.println("Se acelera 20 km/h. Velocidad actual: "
                + automovil.getVelocidadActual() + " km/h");

        automovil.desacelerar(50);
        System.out.println("Se desacelera 50 km/h. Velocidad actual: "
                + automovil.getVelocidadActual() + " km/h");

        System.out.printf(Locale.US, "Tiempo estimado para recorrer 140 km: %.2f horas%n",
                automovil.calcularTiempoLlegada(140));

        automovil.frenar();
        System.out.println("Se frena. Velocidad actual: "
                + automovil.getVelocidadActual() + " km/h");
    }
}

enum TipoCombustible {
    GASOLINA, BIOETANOL, DIESEL, BIODIESEL, GAS_NATURAL
}

enum TipoAutomovil {
    CARRO_DE_CIUDAD, SUBCOMPACTO, COMPACTO, FAMILIAR, EJECUTIVO, SUV
}

enum Color {
    BLANCO, NEGRO, ROJO, NARANJA, AMARILLO, VERDE, AZUL, VIOLETA
}

class Automovil {
    private String marca;
    private int modelo;
    private double motor;
    private TipoCombustible tipoCombustible;
    private TipoAutomovil tipoAutomovil;
    private int numeroPuertas;
    private int cantidadAsientos;
    private int velocidadMaxima;
    private Color color;
    private int velocidadActual;

    public Automovil(String marca, int modelo, double motor, TipoCombustible tipoCombustible,
            TipoAutomovil tipoAutomovil, int numeroPuertas, int cantidadAsientos,
            int velocidadMaxima, Color color, int velocidadActual) {
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.tipoCombustible = tipoCombustible;
        this.tipoAutomovil = tipoAutomovil;
        this.numeroPuertas = numeroPuertas;
        this.cantidadAsientos = cantidadAsientos;
        this.velocidadMaxima = velocidadMaxima;
        this.color = color;
        this.velocidadActual = velocidadActual;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public double getMotor() {
        return motor;
    }

    public void setMotor(double motor) {
        this.motor = motor;
    }

    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(TipoCombustible tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public TipoAutomovil getTipoAutomovil() {
        return tipoAutomovil;
    }

    public void setTipoAutomovil(TipoAutomovil tipoAutomovil) {
        this.tipoAutomovil = tipoAutomovil;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getVelocidadActual() {
        return velocidadActual;
    }

    public void setVelocidadActual(int velocidadActual) {
        this.velocidadActual = velocidadActual;
    }

    public void acelerar(int incremento) {
        if (velocidadActual + incremento > velocidadMaxima) {
            System.out.println("No se puede acelerar " + incremento
                    + " km/h: se superaria la velocidad maxima de " + velocidadMaxima + " km/h");
        } else {
            velocidadActual += incremento;
        }
    }

    public void desacelerar(int decremento) {
        if (velocidadActual - decremento < 0) {
            System.out.println("No se puede desacelerar " + decremento
                    + " km/h: la velocidad quedaria negativa");
        } else {
            velocidadActual -= decremento;
        }
    }

    public void frenar() {
        velocidadActual = 0;
    }

    public double calcularTiempoLlegada(double distancia) {
        if (velocidadActual == 0) {
            System.out.println("El automovil esta detenido: no se puede estimar el tiempo de llegada");
            return -1;
        }
        return distancia / velocidadActual;
    }

    public void mostrarAtributos() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Motor: " + motor + " litros");
        System.out.println("Tipo de combustible: " + tipoCombustible);
        System.out.println("Tipo de automovil: " + tipoAutomovil);
        System.out.println("Numero de puertas: " + numeroPuertas);
        System.out.println("Cantidad de asientos: " + cantidadAsientos);
        System.out.println("Velocidad maxima: " + velocidadMaxima + " km/h");
        System.out.println("Color: " + color);
        System.out.println("Velocidad actual: " + velocidadActual + " km/h");
    }
}
