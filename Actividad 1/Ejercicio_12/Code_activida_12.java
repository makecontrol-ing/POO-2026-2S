/**
 * Ejercicio propuesto No 12: salario bruto, retención en la fuente y salario
 * neto de un empleado que trabaja 48 horas a $5.000 la hora (retención 12,5%).
 *
 * @author Andrés David Galeano Quintero
 */
public class Code_activida_12 {
    public static void main(String[] args) {
        Empleado empleado = new Empleado(48, 5000, 12.5);

        System.out.println("Horas trabajadas: " + empleado.getHorasTrabajadas());
        System.out.printf("Valor hora: $%.0f%n", empleado.getValorHora());
        System.out.println("Porcentaje de retencion: " + empleado.getPorcentajeRetencion() + "%");
        System.out.printf("El salario bruto es: $%.0f%n", empleado.calcularSalarioBruto());
        System.out.printf("La retencion en la fuente es: $%.0f%n", empleado.calcularRetencion());
        System.out.printf("El salario neto es: $%.0f%n", empleado.calcularSalarioNeto());
    }
}

class Empleado {
    private int horasTrabajadas;
    private double valorHora;
    private double porcentajeRetencion;

    public Empleado(int horasTrabajadas, double valorHora, double porcentajeRetencion) {
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
        this.porcentajeRetencion = porcentajeRetencion;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public double getPorcentajeRetencion() {
        return porcentajeRetencion;
    }

    public double calcularSalarioBruto() {
        return horasTrabajadas * valorHora;
    }

    public double calcularRetencion() {
        return calcularSalarioBruto() * porcentajeRetencion / 100;
    }

    public double calcularSalarioNeto() {
        return calcularSalarioBruto() - calcularRetencion();
    }
}
