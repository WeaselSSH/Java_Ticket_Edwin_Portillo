package java_ticket_edwin_portillo;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class BaseFrame extends JFrame {

    public BaseFrame(String titulo, int width, int height) {
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    abstract void initComponents();

    protected JLabel crearLabel(String texto, int x, int y, int ancho, int alto, int estilo, float tamaño) {
        JLabel label = new JLabel(texto);
        label.setFont(label.getFont().deriveFont(estilo, tamaño));
        label.setBounds(x, y, ancho, alto);
        return label;
    }

    protected JTextField crearTextField(int x, int y, int ancho, int alto) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, ancho, alto);
        return textField;
    }

    protected JTextArea crearTextArea(int x, int y, int ancho, int alto) {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(x, y, ancho, alto);
        textArea.setLineWrap(true); //sirve para que haga un salto de línea
        textArea.setWrapStyleWord(true); //sirve para saltar de línea solo en palabras completas
        return textArea;
    }

    protected JButton crearBoton(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        return boton;
    }

    protected <T> JComboBox<T> crearComboBox(T[] items, int x, int y, int ancho, int alto) {
        JComboBox<T> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x, y, ancho, alto);
        return comboBox;
    }
}
