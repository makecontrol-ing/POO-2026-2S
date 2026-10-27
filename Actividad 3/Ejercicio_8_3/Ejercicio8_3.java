import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Ejercicio 8.3
 */
public class Ejercicio8_3 {
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
    private Container contenedor;
    private JButton[] botonesFiguras;
    private VentanaFigura[] ventanasFiguras;

    public VentanaPrincipal() {
        inicio();
        setTitle("Figuras geométricas");
        setSize(320, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        JLabel instruccion = new JLabel("Seleccione una figura geométrica:");
        instruccion.setBounds(30, 15, 260, 25);
        contenedor.add(instruccion);

        TipoFigura[] tipos = TipoFigura.values();
        botonesFiguras = new JButton[tipos.length];
        ventanasFiguras = new VentanaFigura[tipos.length];
        for (int i = 0; i < tipos.length; i++) {
            botonesFiguras[i] = new JButton(tipos[i].getNombre());
            botonesFiguras[i].setBounds(60, 50 + i * 45, 180, 32);
            botonesFiguras[i].addActionListener(this);
            contenedor.add(botonesFiguras[i]);
        }
    }

    public void actionPerformed(ActionEvent evento) {
        for (int i = 0; i < botonesFiguras.length; i++) {
            if (evento.getSource() == botonesFiguras[i]) {
                if (ventanasFiguras[i] == null) {
                    ventanasFiguras[i] = new VentanaFigura(TipoFigura.values()[i]);
                }
                ventanasFiguras[i].setVisible(true);
            }
        }
    }
}

enum TipoFigura {
    CILINDRO("Cilindro", "Radio (cm):", "Altura (cm):"),
    ESFERA("Esfera", "Radio (cm):"),
    PIRAMIDE("Pirámide", "Base (cm):", "Altura (cm):", "Apotema (cm):"),
    CUBO("Cubo", "Arista (cm):"),
    PRISMA("Prisma rectangular", "Largo (cm):", "Ancho (cm):", "Altura (cm):");

    private String nombre;
    private String[] datos;

    TipoFigura(String nombre, String... datos) {
        this.nombre = nombre;
        this.datos = datos;
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getDatos() {
        return datos;
    }

    public FiguraGeometrica crearFigura(double[] valores) {
        switch (this) {
            case CILINDRO:
                return new Cilindro(valores[0], valores[1]);
            case ESFERA:
                return new Esfera(valores[0]);
            case PIRAMIDE:
                return new Piramide(valores[0], valores[1], valores[2]);
            case CUBO:
                return new Cubo(valores[0]);
            default:
                return new Prisma(valores[0], valores[1], valores[2]);
        }
    }
}

@SuppressWarnings("serial")
class VentanaFigura extends JFrame implements ActionListener {
    private TipoFigura tipo;
    private Container contenedor;
    private PanelFigura imagen;
    private JLabel[] etiquetasDatos;
    private JTextField[] camposDatos;
    private JButton botonCalcular;
    private JLabel volumen;
    private JLabel superficie;

    public VentanaFigura(TipoFigura tipo) {
        this.tipo = tipo;
        inicio();
        setTitle(tipo.getNombre());
        setSize(470, 290);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        imagen = new PanelFigura(tipo);
        imagen.setBounds(20, 20, 170, 170);
        contenedor.add(imagen);

        String[] datos = tipo.getDatos();
        etiquetasDatos = new JLabel[datos.length];
        camposDatos = new JTextField[datos.length];
        for (int i = 0; i < datos.length; i++) {
            etiquetasDatos[i] = new JLabel(datos[i]);
            etiquetasDatos[i].setBounds(215, 20 + i * 35, 110, 25);
            contenedor.add(etiquetasDatos[i]);

            camposDatos[i] = new JTextField();
            camposDatos[i].setBounds(325, 20 + i * 35, 110, 25);
            contenedor.add(camposDatos[i]);
        }

        botonCalcular = new JButton("Calcular");
        botonCalcular.setBounds(215, 135, 220, 30);
        botonCalcular.addActionListener(this);
        contenedor.add(botonCalcular);

        volumen = new JLabel("Volumen:");
        volumen.setBounds(20, 205, 420, 25);
        volumen.setFont(volumen.getFont().deriveFont(Font.BOLD));
        contenedor.add(volumen);

        superficie = new JLabel("Superficie:");
        superficie.setBounds(20, 230, 420, 25);
        superficie.setFont(superficie.getFont().deriveFont(Font.BOLD));
        contenedor.add(superficie);
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botonCalcular) {
            double[] valores = leerDatos();
            if (valores != null) {
                FiguraGeometrica figura = tipo.crearFigura(valores);
                volumen.setText("Volumen: " + String.format(Locale.US, "%.2f", figura.getVolumen()) + " cm³");
                superficie.setText("Superficie: " + String.format(Locale.US, "%.2f", figura.getSuperficie()) + " cm²");
            }
        }
    }

    private double[] leerDatos() {
        double[] valores = new double[camposDatos.length];
        for (int i = 0; i < camposDatos.length; i++) {
            String nombreDato = tipo.getDatos()[i].replace(" (cm):", "");
            String texto = camposDatos[i].getText().trim();
            try {
                valores[i] = Double.parseDouble(texto.replace(',', '.'));
            } catch (NumberFormatException e) {
                mostrarError("Ingrese un valor numérico para: " + nombreDato + ".");
                return null;
            }
            if (valores[i] <= 0) {
                mostrarError("El valor de " + nombreDato + " debe ser mayor que cero.");
                return null;
            }
        }
        return valores;
    }

    private void mostrarError(String mensaje) {
        volumen.setText("Volumen:");
        superficie.setText("Superficie:");
        JOptionPane.showMessageDialog(this, mensaje, "Dato inválido", JOptionPane.ERROR_MESSAGE);
    }
}

@SuppressWarnings("serial")
class PanelFigura extends JPanel {
    private TipoFigura tipo;

    public PanelFigura(TipoFigura tipo) {
        this.tipo = tipo;
        setPreferredSize(new Dimension(170, 170));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(new Color(60, 60, 60));
        switch (tipo) {
            case CILINDRO:
                g2.draw(new Ellipse2D.Double(45, 25, 80, 26));
                g2.drawLine(45, 38, 45, 132);
                g2.drawLine(125, 38, 125, 132);
                g2.drawArc(45, 119, 80, 26, 180, 180);
                g2.setStroke(punteada());
                g2.drawArc(45, 119, 80, 26, 0, 180);
                break;
            case ESFERA:
                g2.draw(new Ellipse2D.Double(30, 30, 110, 110));
                g2.drawArc(30, 72, 110, 26, 180, 180);
                g2.setStroke(punteada());
                g2.drawArc(30, 72, 110, 26, 0, 180);
                break;
            case PIRAMIDE:
                g2.drawLine(30, 125, 100, 145);
                g2.drawLine(100, 145, 140, 115);
                g2.drawLine(85, 25, 30, 125);
                g2.drawLine(85, 25, 100, 145);
                g2.drawLine(85, 25, 140, 115);
                g2.setStroke(punteada());
                g2.drawLine(30, 125, 70, 97);
                g2.drawLine(70, 97, 140, 115);
                g2.drawLine(85, 25, 70, 97);
                break;
            case CUBO:
                g2.drawRect(35, 60, 75, 75);
                g2.drawLine(35, 60, 65, 35);
                g2.drawLine(110, 60, 140, 35);
                g2.drawLine(110, 135, 140, 110);
                g2.drawLine(65, 35, 140, 35);
                g2.drawLine(140, 35, 140, 110);
                g2.setStroke(punteada());
                g2.drawLine(35, 135, 65, 110);
                g2.drawLine(65, 110, 140, 110);
                g2.drawLine(65, 35, 65, 110);
                break;
            default:
                g2.drawRect(25, 70, 100, 65);
                g2.drawLine(25, 70, 50, 45);
                g2.drawLine(125, 70, 150, 45);
                g2.drawLine(125, 135, 150, 110);
                g2.drawLine(50, 45, 150, 45);
                g2.drawLine(150, 45, 150, 110);
                g2.setStroke(punteada());
                g2.drawLine(25, 135, 50, 110);
                g2.drawLine(50, 110, 150, 110);
                g2.drawLine(50, 45, 50, 110);
                break;
        }
    }

    private BasicStroke punteada() {
        return new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, new float[] {5f, 4f}, 0f);
    }
}

abstract class FiguraGeometrica {
    private double volumen;
    private double superficie;

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public abstract double calcularVolumen();

    public abstract double calcularSuperficie();

    protected void calcular() {
        setVolumen(calcularVolumen());
        setSuperficie(calcularSuperficie());
    }
}

class Cilindro extends FiguraGeometrica {
    private double radio;
    private double altura;

    public Cilindro(double radio, double altura) {
        this.radio = radio;
        this.altura = altura;
        calcular();
    }

    public double calcularVolumen() {
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    public double calcularSuperficie() {
        return 2 * Math.PI * radio * (radio + altura);
    }
}

class Esfera extends FiguraGeometrica {
    private double radio;

    public Esfera(double radio) {
        this.radio = radio;
        calcular();
    }

    public double calcularVolumen() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radio, 3);
    }

    public double calcularSuperficie() {
        return 4 * Math.PI * Math.pow(radio, 2);
    }
}

class Piramide extends FiguraGeometrica {
    private double base;
    private double altura;
    private double apotema;

    public Piramide(double base, double altura, double apotema) {
        this.base = base;
        this.altura = altura;
        this.apotema = apotema;
        calcular();
    }

    public double calcularVolumen() {
        return Math.pow(base, 2) * altura / 3;
    }

    public double calcularSuperficie() {
        return Math.pow(base, 2) + 2 * base * apotema;
    }
}

class Cubo extends FiguraGeometrica {
    private double arista;

    public Cubo(double arista) {
        this.arista = arista;
        calcular();
    }

    public double calcularVolumen() {
        return Math.pow(arista, 3);
    }

    public double calcularSuperficie() {
        return 6 * Math.pow(arista, 2);
    }
}

class Prisma extends FiguraGeometrica {
    private double largo;
    private double ancho;
    private double altura;

    public Prisma(double largo, double ancho, double altura) {
        this.largo = largo;
        this.ancho = ancho;
        this.altura = altura;
        calcular();
    }

    public double calcularVolumen() {
        return largo * ancho * altura;
    }

    public double calcularSuperficie() {
        return 2 * (largo * ancho + largo * altura + ancho * altura);
    }
}
