package java_ticket_edwin_portillo;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class FrmCrearEvento extends BaseFrame {

    public FrmCrearEvento() {
        super("Crear Evento", 440, 640);
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

        //UI GENERAL
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
        lblTipo.setBounds(35, 300, 200, 25);
        panelCentro.add(lblTipo);

        String tipoEvento[] = {"DEPORTIVO", "MUSICAL", "RELIGIOSO"};
        JComboBox<String> cboTipo = new JComboBox<>(tipoEvento);
        cboTipo.setBounds(170, 300, 200, 25);
        panelCentro.add(cboTipo);

        //PANEL PARA EVENTOS DEPORTIVOS
        JPanel panelDeportivo = new JPanel(null);
        panelDeportivo.setBounds(35, 350, 400, 100);
        panelCentro.add(panelDeportivo);

        JLabel lblEquipo1 = new JLabel("Equipo 1:");
        lblEquipo1.setBounds(0, 0, 100, 25);
        panelDeportivo.add(lblEquipo1);

        JTextField txtEquipo1 = new JTextField();
        txtEquipo1.setBounds(100, 0, 200, 25);
        panelDeportivo.add(txtEquipo1);

        JLabel lblEquipo2 = new JLabel("Equipo 2:");
        lblEquipo2.setBounds(0, 35, 100, 25);
        panelDeportivo.add(lblEquipo2);

        JTextField txtEquipo2 = new JTextField();
        txtEquipo2.setBounds(100, 35, 200, 25);
        panelDeportivo.add(txtEquipo2);

        JLabel lblTipoDeporte = new JLabel("Tipo Deporte:");
        lblTipoDeporte.setBounds(0, 70, 100, 25);
        panelDeportivo.add(lblTipoDeporte);

        JComboBox<TipoDeporte> cboDeporte = new JComboBox<>(TipoDeporte.values());
        cboDeporte.setBounds(100, 70, 200, 25);
        panelDeportivo.add(cboDeporte);
        
        //PANEL PARA EVENTOS MUSICALES
        

        // Muestro dinámico de los paneles por tipo de evento
        
        
        
        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmCrearEvento().setVisible(true);
    }
}
