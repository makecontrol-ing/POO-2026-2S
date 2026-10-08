import java.util.Locale;

/**
 * Ejercicio 2.4
 */
public class Ejercicio2_4 {
    public static void main(String[] args) {
        double radio = 2.5;
        double baseRectangulo = 6;
        double alturaRectangulo = 3.5;
        double lado = 4.5;
        double baseTriangulo = 5;
        double alturaTriangulo = 5;

        Circulo circulo = new Circulo(radio);
        Rectangulo rectangulo = new Rectangulo(baseRectangulo, alturaRectangulo);
        Cuadrado cuadrado = new Cuadrado(lado);
        TrianguloRectangulo triangulo = new TrianguloRectangulo(baseTriangulo, alturaTriangulo);

        System.out.printf(Locale.US, "Circulo (radio = %.2f cm)%n", radio);
        System.out.printf(Locale.US, "Area: %.2f cm^2%n", circulo.calcularArea());
        System.out.printf(Locale.US, "Perimetro: %.2f cm%n", circulo.calcularPerimetro());
        System.out.println();

        System.out.printf(Locale.US, "Rectangulo (base = %.2f cm, altura = %.2f cm)%n", baseRectangulo, alturaRectangulo);
        System.out.printf(Locale.US, "Area: %.2f cm^2%n", rectangulo.calcularArea());
        System.out.printf(Locale.US, "Perimetro: %.2f cm%n", rectangulo.calcularPerimetro());
        System.out.println();

        System.out.printf(Locale.US, "Cuadrado (lado = %.2f cm)%n", lado);
        System.out.printf(Locale.US, "Area: %.2f cm^2%n", cuadrado.calcularArea());
        System.out.printf(Locale.US, "Perimetro: %.2f cm%n", cuadrado.calcularPerimetro());
        System.out.println();

        System.out.printf(Locale.US, "Triangulo rectangulo (base = %.2f cm, altura = %.2f cm)%n", baseTriangulo, alturaTriangulo);
        System.out.printf(Locale.US, "Area: %.2f cm^2%n", triangulo.calcularArea());
        System.out.printf(Locale.US, "Perimetro: %.2f cm%n", triangulo.calcularPerimetro());
        System.out.printf(Locale.US, "Hipotenusa: %.2f cm%n", triangulo.calcularHipotenusa());
        System.out.println("Tipo de triangulo: " + triangulo.determinarTipo());
    }
}

class Circulo {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
}

class Rectangulo {
    private double base;
    private double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcularArea() {
        return base * altura;
    }

    public double calcularPerimetro() {
        return 2 * (base + altura);
    }
}

class Cuadrado {
    private double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    public double calcularArea() {
        return Math.pow(lado, 2);
    }

    public double calcularPerimetro() {
        return 4 * lado;
    }
}

class TrianguloRectangulo {
    private double base;
    private double altura;

    public TrianguloRectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double calcularArea() {
        return base * altura / 2;
    }

    public double calcularPerimetro() {
        return base + altura + calcularHipotenusa();
    }

    public double calcularHipotenusa() {
        return Math.sqrt(Math.pow(base, 2) + Math.pow(altura, 2));
    }

    public String determinarTipo() {
        double hipotenusa = calcularHipotenusa();
        double tolerancia = 1e-9;
        boolean baseIgualAltura = Math.abs(base - altura) < tolerancia;
        boolean baseIgualHipotenusa = Math.abs(base - hipotenusa) < tolerancia;
        boolean alturaIgualHipotenusa = Math.abs(altura - hipotenusa) < tolerancia;

        if (baseIgualAltura && baseIgualHipotenusa) {
            return "Equilatero";
        } else if (baseIgualAltura || baseIgualHipotenusa || alturaIgualHipotenusa) {
            return "Isosceles";
        } else {
            return "Escaleno";
        }
    }
}
