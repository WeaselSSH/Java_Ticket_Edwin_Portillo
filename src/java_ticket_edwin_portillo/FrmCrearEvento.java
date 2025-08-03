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

        //PANELES
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //UI GENERAL
        JLabel lblTitulo = crearLabel("CREAR EVENTO", 0, 0, 300, 40, Font.BOLD, 20f);
        panelNorte.add(lblTitulo);

        JLabel lblCodigo = crearLabel("Código:", 35, 10, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblCodigo);

        JTextField txtCodigo = crearTextField(170, 10, 200, 25);
        txtCodigo.setEditable(false);
        txtCodigo.setFocusable(false);
        txtCodigo.setText(ManejoEventos.codigoActual());
        panelCentro.add(txtCodigo);

        JLabel lblTituloEvento = crearLabel("Título:", 35, 55, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblTituloEvento);

        JTextField txtTituloEvento = crearTextField(170, 55, 200, 25);
        panelCentro.add(txtTituloEvento);

        JLabel lblDescripcion = crearLabel("Descripción:", 35, 100, 210, 25, Font.BOLD, 14f);
        panelCentro.add(lblDescripcion);

        JTextArea txtDescripcion = crearTextArea(170, 100, 200, 55);
        panelCentro.add(txtDescripcion);

        JLabel lblFecha = crearLabel("Fecha del Evento:", 35, 190, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblFecha);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(170, 190, 200, 25);
        panelCentro.add(dateChooser);

        JLabel lblMontoRenta = crearLabel("Monto de Renta:", 35, 235, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblMontoRenta);

        JTextField txtMontoRenta = crearTextField(170, 235, 200, 25);
        panelCentro.add(txtMontoRenta);

        JLabel lblTipo = crearLabel("Tipo de Evento:", 35, 300, 200, 25, Font.BOLD, 14f);
        panelCentro.add(lblTipo);

        String tipoEvento[] = {"DEPORTIVO", "MUSICAL", "RELIGIOSO"}; //elementos que se pasan al combobox
        JComboBox<String> cboTipo = crearComboBox(tipoEvento, 170, 300, 200, 25);
        panelCentro.add(cboTipo);

        //PANEL DEPORTIVO
        JPanel panelDeportivo = new JPanel(null);
        panelDeportivo.setBounds(35, 350, 400, 100);
        panelCentro.add(panelDeportivo);

        JLabel lblEquipo1 = crearLabel("Equipo 1:", 0, 0, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblEquipo1);

        JTextField txtEquipo1 = crearTextField(100, 0, 200, 25);
        panelDeportivo.add(txtEquipo1);

        JLabel lblEquipo2 = crearLabel("Equipo 2:", 0, 35, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblEquipo2);

        JTextField txtEquipo2 = crearTextField(100, 35, 200, 25);
        panelDeportivo.add(txtEquipo2);

        JLabel lblTipoDeporte = crearLabel("Tipo de Deporte:", 0, 70, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblTipoDeporte);

        JComboBox<TipoDeporte> cboDeporte = crearComboBox(TipoDeporte.values(), 110, 70, 190, 25);
        panelDeportivo.add(cboDeporte);

        //PANEL MUSICAL
        JPanel panelMusical = new JPanel(null);
        panelMusical.setBounds(35, 350, 400, 100);
        panelCentro.add(panelMusical);

        JLabel lblTipoMusica = crearLabel("Tipo de Música:", 0, 0, 100, 25, Font.BOLD, 12f);
        panelMusical.add(lblTipoMusica);

        JComboBox<TipoMusica> cboMusica = crearComboBox(TipoMusica.values(), 100, 0, 200, 25);
        panelMusical.add(cboMusica);

        //acción del combobox
        cboTipo.addActionListener(e -> {
            String seleccionado = (String) cboTipo.getSelectedItem();
            panelDeportivo.setVisible("DEPORTIVO".equals(seleccionado));
            panelMusical.setVisible("MUSICAL".equals(seleccionado));
        });
        
        //botón salir
        JButton btnSalir = crearBoton("Regresar", 220, 470, 140, 35);
        panelCentro.add(btnSalir);
        btnSalir.addActionListener(e -> {
           FrmEventos evt = new FrmEventos();
           evt.setVisible(true);
           this.dispose();
        });

        //botón crear y su acción
        JButton btnCrear = crearBoton("Crear Evento", 60, 470, 140, 35);
        panelCentro.add(btnCrear);

        btnCrear.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            String titulo = txtTituloEvento.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            Calendar fechaRealizar;
            String tipo = (String) cboTipo.getSelectedItem();
            String montoTexto = txtMontoRenta.getText().trim();
            fechaRealizar = dateChooser.getCalendar();
            double montoRenta;

            if (codigo.isEmpty() || titulo.isEmpty() || descripcion.isEmpty()
                    || fechaRealizar == null || montoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: todos los campos generales deben estar llenos.");
                return;
            }

            //en caso que ingrese algo que no sea números
            try {
                montoRenta = Double.parseDouble(montoTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: monto de renta inválido.");
                return;
            }

            //creación de eventos
            switch (tipo) {
                case "RELIGIOSO":
                    manejoEventos.crearEventoReligioso(ManejoUsuarios.usuarioLogeado, codigo, titulo, descripcion,
                            fechaRealizar, montoRenta);

                    JOptionPane.showMessageDialog(this, "Evento religioso creado correctamente.");
                    txtCodigo.setText(ManejoEventos.codigoActual());
                    break;

                case "MUSICAL":
                    TipoMusica tipoMusica = (TipoMusica) cboMusica.getSelectedItem();

                    manejoEventos.crearEventoMusical(ManejoUsuarios.usuarioLogeado, codigo, titulo, descripcion,
                            fechaRealizar, montoRenta, tipoMusica);

                    JOptionPane.showMessageDialog(this, "Evento musical creado correctamente.");
                    txtCodigo.setText(ManejoEventos.codigoActual());
                    break;

                case "DEPORTIVO":
                    String equipo1 = txtEquipo1.getText().trim();
                    String equipo2 = txtEquipo2.getText().trim();
                    TipoDeporte tipoDeporte = (TipoDeporte) cboDeporte.getSelectedItem();

                    if (equipo1.isEmpty() || equipo2.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Error: debe ingresar ambos equipos.");
                        return;
                    }

                    manejoEventos.crearEventoDeportivo(ManejoUsuarios.usuarioLogeado, codigo, titulo, descripcion,
                            fechaRealizar, montoRenta, equipo1, equipo2, tipoDeporte);

                    JOptionPane.showMessageDialog(this, "Evento deportivo creado correctamente.");
                    txtCodigo.setText(ManejoEventos.codigoActual());
                    break;
            }

        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmCrearEvento().setVisible(true);
    }
}
