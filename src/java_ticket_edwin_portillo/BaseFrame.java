package java_ticket_edwin_portillo;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;

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
        label.setForeground(Color.WHITE);
        return label;
    }

    protected JTextField crearTextField(int x, int y, int ancho, int alto) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, ancho, alto);
        textField.setBackground(Color.decode("#1A2332"));
        textField.setForeground(Color.decode("#E6EDF7"));
        textField.setCaretColor(Color.decode("#E6EDF7"));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        return textField;
    }

    protected JTextArea crearTextArea(int x, int y, int ancho, int alto) {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(x, y, ancho, alto);
        textArea.setLineWrap(true); //sirve para que haga un salto de línea
        textArea.setWrapStyleWord(true); //sirve para saltar de línea solo en palabras completas
        textArea.setBounds(x, y, ancho, alto);
        textArea.setBackground(Color.decode("#1A2332"));
        textArea.setForeground(Color.decode("#E6EDF7"));
        textArea.setCaretColor(Color.decode("#E6EDF7"));
        return textArea;
    }

    protected JButton crearBoton(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setBackground(Color.decode("#2F6BFF"));
        boton.setForeground(Color.decode("#FFFFFF"));
        boton.setBorder(BorderFactory.createLineBorder(Color.decode("#3A4C63")));
        boton.setFocusPainted(false);
        return boton;
    }

    protected <T> JComboBox<T> crearComboBox(T[] items, int x, int y, int ancho, int alto) {
        JComboBox<T> comboBox = new JComboBox<>(items);
        comboBox.setBounds(x, y, ancho, alto);
        comboBox.setBackground(Color.decode("#1A2332"));
        comboBox.setForeground(Color.decode("#E6EDF7"));
        return comboBox;
    }
}
