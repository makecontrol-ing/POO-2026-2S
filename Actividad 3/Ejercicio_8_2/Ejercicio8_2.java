import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Ejercicio 8.2
 */
public class Ejercicio8_2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            }
        });
    }
}

@SuppressWarnings("serial")
class VentanaPrincipal extends JFrame implements ActionListener {
    private static final int CANTIDAD_NOTAS = 5;

    private Container contenedor;
    private JLabel[] etiquetasNotas;
    private JTextField[] camposNotas;
    private JButton botonCalcular;
    private JButton botonBorrar;
    private JLabel promedio;
    private JLabel desviacion;
    private JLabel mayor;
    private JLabel menor;

    public VentanaPrincipal() {
        inicio();
        setTitle("Notas del estudiante");
        setSize(360, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        JLabel instruccion = new JLabel("Ingrese las cinco notas del estudiante:");
        instruccion.setBounds(20, 15, 320, 25);
        contenedor.add(instruccion);

        etiquetasNotas = new JLabel[CANTIDAD_NOTAS];
        camposNotas = new JTextField[CANTIDAD_NOTAS];
        for (int i = 0; i < CANTIDAD_NOTAS; i++) {
            etiquetasNotas[i] = new JLabel("Nota " + (i + 1) + ":");
            etiquetasNotas[i].setBounds(40, 50 + i * 35, 80, 25);
            contenedor.add(etiquetasNotas[i]);

            camposNotas[i] = new JTextField();
            camposNotas[i].setBounds(120, 50 + i * 35, 100, 25);
            contenedor.add(camposNotas[i]);
        }

        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(40, 235, 120, 30);
        botonCalcular.addActionListener(this);
        contenedor.add(botonCalcular);

        botonBorrar = new JButton("Borrar");
        botonBorrar.setBounds(180, 235, 120, 30);
        botonBorrar.addActionListener(this);
        contenedor.add(botonBorrar);

        promedio = crearResultado(285);
        desviacion = crearResultado(310);
        mayor = crearResultado(335);
        menor = crearResultado(360);
        limpiarResultados();
    }

    private JLabel crearResultado(int y) {
        JLabel etiqueta = new JLabel();
        etiqueta.setBounds(40, y, 280, 25);
        etiqueta.setFont(etiqueta.getFont().deriveFont(Font.BOLD));
        contenedor.add(etiqueta);
        return etiqueta;
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botonCalcular) {
            double[] valores = leerNotas();
            if (valores != null) {
                Notas notas = new Notas(valores);
                promedio.setText("Promedio: " + formatear(notas.calcularPromedio()));
                desviacion.setText("Desviación estándar: " + formatear(notas.calcularDesviacion()));
                mayor.setText("Nota mayor: " + formatear(notas.calcularMayor()));
                menor.setText("Nota menor: " + formatear(notas.calcularMenor()));
            }
        } else if (evento.getSource() == botonBorrar) {
            for (JTextField campo : camposNotas) {
                campo.setText("");
            }
            limpiarResultados();
        }
    }

    private double[] leerNotas() {
        double[] valores = new double[CANTIDAD_NOTAS];
        for (int i = 0; i < CANTIDAD_NOTAS; i++) {
            String texto = camposNotas[i].getText().trim();
            if (texto.isEmpty()) {
                mostrarAlerta("Falta ingresar la nota " + (i + 1) + ". Las cinco notas son obligatorias.", i);
                return null;
            }
            try {
                valores[i] = Double.parseDouble(texto.replace(',', '.'));
            } catch (NumberFormatException e) {
                mostrarAlerta("La nota " + (i + 1) + " (\"" + texto + "\") no es un valor numérico.", i);
                return null;
            }
        }
        return valores;
    }

    private void mostrarAlerta(String mensaje, int indiceCampo) {
        limpiarResultados();
        JOptionPane.showMessageDialog(this, mensaje, "Dato inválido", JOptionPane.WARNING_MESSAGE);
        camposNotas[indiceCampo].requestFocus();
    }

    private void limpiarResultados() {
        promedio.setText("Promedio:");
        desviacion.setText("Desviación estándar:");
        mayor.setText("Nota mayor:");
        menor.setText("Nota menor:");
    }

    private String formatear(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }
}

class Notas {
    private double[] notas;

    public Notas(double[] notas) {
        this.notas = notas;
    }

    public double calcularPromedio() {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.length;
    }

    public double calcularDesviacion() {
        double promedio = calcularPromedio();
        double suma = 0;
        for (double nota : notas) {
            suma += Math.pow(nota - promedio, 2);
        }
        return Math.sqrt(suma / notas.length);
    }

    public double calcularMayor() {
        double mayor = notas[0];
        for (double nota : notas) {
            mayor = Math.max(mayor, nota);
        }
        return mayor;
    }

    public double calcularMenor() {
        double menor = notas[0];
        for (double nota : notas) {
            menor = Math.min(menor, nota);
        }
        return menor;
    }
}
