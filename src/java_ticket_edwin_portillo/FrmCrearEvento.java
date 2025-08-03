package java_ticket_edwin_portillo;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class FrmCrearEvento extends BaseFrame {
    ManejoEventos manejoEventos = new ManejoEventos();

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

        JLabel lblTipoDeporte = new JLabel("Tipo de Deporte:");
        lblTipoDeporte.setBounds(0, 70, 100, 25);
        panelDeportivo.add(lblTipoDeporte);

        JComboBox<TipoDeporte> cboDeporte = new JComboBox<>(TipoDeporte.values());
        cboDeporte.setBounds(110, 70, 190, 25);
        panelDeportivo.add(cboDeporte);

        //PANEL PARA EVENTOS MUSICALES
        JPanel panelMusical = new JPanel(null);
        panelMusical.setBounds(35, 350, 400, 100);
        panelCentro.add(panelMusical);

        JLabel lblTipoMusica = new JLabel("Tipo de Música:");
        lblTipoMusica.setBounds(0, 0, 100, 25);
        panelMusical.add(lblTipoMusica);

        JComboBox<TipoMusica> cboMusica = new JComboBox<>(TipoMusica.values());
        cboMusica.setBounds(100, 0, 200, 25);
        panelMusical.add(cboMusica);

        //PANEL PARA EVENTOS RELIGIOSOS (no tiene nada xd)
        JPanel panelReligioso = new JPanel(null);
        panelReligioso.setBounds(35, 350, 400, 1);
        panelCentro.add(panelReligioso);

        //muestreo dinámico de los paneles de evento
        cboTipo.addActionListener(e -> {
            String seleccionado = (String) cboTipo.getSelectedItem();

            panelDeportivo.setVisible("DEPORTIVO".equals(seleccionado));
            panelMusical.setVisible("MUSICAL".equals(seleccionado));
            panelReligioso.setVisible("RELIGIOSO".equals(seleccionado));
        });

        //botón de crear evento
        JButton btnCrear = new JButton("Crear Evento");
        btnCrear.setBounds(140, 470, 160, 35);
        panelCentro.add(btnCrear);

        //acción de botón crear evento
        btnCrear.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            String titulo = txtTituloEvento.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            Calendar fechaRealizar = dateChooser.getCalendar();
            String tipo = (String) cboTipo.getSelectedItem();
            String montoTexto = txtMontoRenta.getText().trim();

            if (codigo.isEmpty() || titulo.isEmpty() || descripcion.isEmpty()
                    || fechaRealizar == null || montoTexto.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Error: todos los campos generales deben estar llenos.");
                return;
            }

            double montoRenta;

            try {
                montoRenta = Double.parseDouble(montoTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Monto de renta inválido.");
                return;
            }

            switch (tipo) {
                case "RELIGIOSO":
                    manejoEventos.crearEventoReligioso(ManejoUsuarios.usuarioLogeado, codigo, titulo, descripcion,
                            fechaRealizar, montoRenta);
                    
                    JOptionPane.showMessageDialog(this, "Evento religioso creado correctamente.");
                    break;

                case "MUSICAL":
                    JOptionPane.showMessageDialog(this, "La lógica para evento MUSICAL aún no está implementada.");
                    break;

                case "DEPORTIVO":
                    JOptionPane.showMessageDialog(this, "La lógica para evento DEPORTIVO aún no está implementada.");
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Tipo de evento inválido.");
            }

        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmCrearEvento().setVisible(true);
    }
}
