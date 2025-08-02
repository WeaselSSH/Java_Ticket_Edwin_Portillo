package java_ticket_edwin_portillo;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class FrmCrearEvento extends BaseFrame {

    public FrmCrearEvento() {
        super("Crear Evento", 480, 600);
    }

    @Override
    protected void initComponents() {
        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        //resto de paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de ui
        JLabel lblTitulo = new JLabel("CREAR EVENTO");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 20f));
        panelNorte.add(lblTitulo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(lblCodigo.getFont().deriveFont(Font.BOLD, 14f));
        lblCodigo.setBounds(35, 10, 140, 25);
        panelCentro.add(lblCodigo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(170, 10, 200, 25);
        txtCodigo.setEditable(false);
        txtCodigo.setFocusable(false);
        panelCentro.add(txtCodigo);

        JLabel lblTituloEvento = new JLabel("Título:");
        lblTituloEvento.setFont(lblTituloEvento.getFont().deriveFont(Font.BOLD, 14f));
        lblTituloEvento.setBounds(35, 55, 140, 25);
        panelCentro.add(lblTituloEvento);

        JTextField txtTituloEvento = new JTextField();
        txtTituloEvento.setBounds(170, 55, 200, 25);
        panelCentro.add(txtTituloEvento);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(lblTituloEvento.getFont().deriveFont(Font.BOLD, 14f));
        lblDescripcion.setBounds(35, 100, 210, 25);
        panelCentro.add(lblDescripcion);

        JTextArea txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(170, 100, 200, 55);
        txtDescripcion.setLineWrap(true); //sirve para que haga un salto de línea
        txtDescripcion.setWrapStyleWord(true); //sirve para saltar de línea solo en palabras completas
        panelCentro.add(txtDescripcion);

        JLabel lblFecha = new JLabel("Fecha del Evento:");
        lblFecha.setFont(lblFecha.getFont().deriveFont(Font.BOLD, 14f));
        lblFecha.setBounds(35, 190, 150, 25);
        panelCentro.add(lblFecha);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(170, 190, 200, 25);
        panelCentro.add(dateChooser);

        JLabel lblMontoRenta = new JLabel("Monto de Renta:");
        lblMontoRenta.setFont(lblMontoRenta.getFont().deriveFont(Font.BOLD, 14f));
        lblMontoRenta.setBounds(35, 235, 150, 25);
        panelCentro.add(lblMontoRenta);

        JTextField txtMontoRenta = new JTextField();
        txtMontoRenta.setBounds(170, 235, 200, 25);
        panelCentro.add(txtMontoRenta);

        JLabel lblTipo = new JLabel("Tipo de Evento:");
        lblTipo.setFont(lblTipo.getFont().deriveFont(Font.BOLD, 14f));
        lblTipo.setBounds(35, 280, 200, 25);
        panelCentro.add(lblTipo);

        String tipoEvento[] = {"Deportivo", "Musical", "Religioso"};
        JComboBox<String> cboTipo = new JComboBox<>(tipoEvento);
        cboTipo.setBounds(170, 280, 200, 25);
        panelCentro.add(cboTipo);


        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmCrearEvento().setVisible(true);
    }
}
