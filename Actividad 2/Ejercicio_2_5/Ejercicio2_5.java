import java.util.Locale;

/**
 * Ejercicio 2.5
 */
public class Ejercicio2_5 {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria("Maria Fernanda", "Lopez Restrepo",
                "456-123789-01", TipoCuenta.AHORROS);

        System.out.println("DATOS DE LA CUENTA BANCARIA:");
        cuenta.imprimir();
        System.out.println();

        cuenta.consignar(1500000);
        System.out.printf(Locale.US, "Saldo consultado: $%,.0f%n", cuenta.consultarSaldo());
        cuenta.retirar(400000);
        cuenta.retirar(2000000);
        cuenta.consignar(-50000);
        System.out.println();

        System.out.println("ESTADO FINAL DE LA CUENTA:");
        cuenta.imprimir();
    }
}

enum TipoCuenta {
    AHORROS, CORRIENTE
}

class CuentaBancaria {
    private String nombresTitular;
    private String apellidosTitular;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;

    public CuentaBancaria(String nombresTitular, String apellidosTitular, String numeroCuenta,
            TipoCuenta tipoCuenta) {
        this.nombresTitular = nombresTitular;
        this.apellidosTitular = apellidosTitular;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = 0;
    }

    public void imprimir() {
        System.out.println("Nombres del titular: " + nombresTitular);
        System.out.println("Apellidos del titular: " + apellidosTitular);
        System.out.println("Numero de cuenta: " + numeroCuenta);
        System.out.println("Tipo de cuenta: " + tipoCuenta);
        System.out.printf(Locale.US, "Saldo: $%,.0f%n", saldo);
    }

    public double consultarSaldo() {
        return saldo;
    }

    public void consignar(double valor) {
        if (valor <= 0) {
            System.out.println("Consignacion rechazada: el valor debe ser mayor que cero");
        } else {
            saldo += valor;
            System.out.printf(Locale.US, "Consignacion de $%,.0f realizada. Nuevo saldo: $%,.0f%n",
                    valor, saldo);
        }
    }

    public void retirar(double valor) {
        if (valor <= 0) {
            System.out.println("Retiro rechazado: el valor debe ser mayor que cero");
        } else if (valor > saldo) {
            System.out.printf(Locale.US, "Retiro de $%,.0f rechazado: el valor supera el saldo actual de $%,.0f%n",
                    valor, saldo);
        } else {
            saldo -= valor;
            System.out.printf(Locale.US, "Retiro de $%,.0f realizado. Nuevo saldo: $%,.0f%n",
                    valor, saldo);
        }
    }
}
