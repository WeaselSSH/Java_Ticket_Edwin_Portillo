package java_ticket_edwin_portillo;

import Usuarios.Usuario;
import Tipos.TipoMusica;
import Tipos.TipoDeporte;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class FrmCrearEvento extends BaseFrame {

    private final ManejoEventos manejoEventos = ManejoEventos.getInstancia();
    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmCrearEvento() {
        super("Crear Evento", 440, 560);
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

        JLabel lblCodigo = crearLabel("ID Evento:", 35, 0, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblCodigo);

        JTextField txtCodigo = crearTextField(170, 0, 200, 25);
        txtCodigo.setEditable(false);
        txtCodigo.setFocusable(false);
        txtCodigo.setText(manejoEventos.codigoSiguiente());
        panelCentro.add(txtCodigo);

        JLabel lblTituloEvento = crearLabel("T�tulo:", 35, 35, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblTituloEvento);

        JTextField txtTituloEvento = crearTextField(170, 35, 200, 25);
        panelCentro.add(txtTituloEvento);

        JLabel lblDescripcion = crearLabel("Descripci�n:", 35, 75, 210, 25, Font.BOLD, 14f);
        panelCentro.add(lblDescripcion);

        JTextArea txtDescripcion = crearTextArea(170, 75, 200, 55);
        panelCentro.add(txtDescripcion);

        JLabel lblFecha = crearLabel("Fecha del Evento:", 35, 150, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblFecha);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(170, 150, 200, 25);
        panelCentro.add(dateChooser);

        JLabel lblMontoRenta = crearLabel("Monto de Renta:", 35, 195, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblMontoRenta);

        JTextField txtMontoRenta = crearTextField(170, 195, 200, 25);
        panelCentro.add(txtMontoRenta);

        JLabel lblTipo = crearLabel("Tipo de Evento:", 35, 250, 200, 25, Font.BOLD, 14f);
        panelCentro.add(lblTipo);

        String tipoEvento[] = {"DEPORTIVO", "MUSICAL", "RELIGIOSO"};
        JComboBox<String> cboTipo = crearComboBox(tipoEvento, 170, 250, 200, 25);
        panelCentro.add(cboTipo);

        //PANEL DEPORTIVO
        JPanel panelDeportivo = new JPanel(null);
        panelDeportivo.setBounds(35, 290, 400, 100);
        panelCentro.add(panelDeportivo);

        JLabel lblEquipo1 = crearLabel("Equipo 1:", 0, 0, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblEquipo1);

        JTextField txtEquipo1 = crearTextField(100, 0, 200, 25);
        panelDeportivo.add(txtEquipo1);

        JLabel lblEquipo2 = crearLabel("Equipo 2:", 0, 35, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblEquipo2);

        JTextField txtEquipo2 = crearTextField(100, 35, 200, 25);
        panelDeportivo.add(txtEquipo2);

        JLabel lblTipoDeporte = crearLabel("Tipo de Deporte:", 0, 70, 120, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblTipoDeporte);

        JComboBox<TipoDeporte> cboDeporte = crearComboBox(TipoDeporte.values(), 130, 70, 170, 25);
        panelDeportivo.add(cboDeporte);

        //PANEL MUSICAL
        JPanel panelMusical = new JPanel(null);
        panelMusical.setBounds(35, 290, 400, 50);
        panelCentro.add(panelMusical);

        JLabel lblTipoMusica = crearLabel("Tipo de M�sica:", 0, 0, 120, 25, Font.BOLD, 12f);
        panelMusical.add(lblTipoMusica);

        JComboBox<TipoMusica> cboMusica = crearComboBox(TipoMusica.values(), 130, 0, 170, 25);
        panelMusical.add(cboMusica);

        //acci�n del combobox
        cboTipo.addActionListener(e -> {
            String seleccionado = (String) cboTipo.getSelectedItem();
            panelDeportivo.setVisible("DEPORTIVO".equals(seleccionado));
            panelMusical.setVisible("MUSICAL".equals(seleccionado));
        });

        //bot�n salir
        JButton btnRegresar = crearBoton("Regresar", 220, 410, 140, 35);
        panelCentro.add(btnRegresar);

        btnRegresar.addActionListener(e -> {
            new FrmEventos().setVisible(true);
        });

        //bot�n crear
        JButton btnCrear = crearBoton("Crear Evento", 60, 410, 140, 35);
        panelCentro.add(btnCrear);

        btnCrear.addActionListener(e -> {
            Usuario usuarioLogeado = manejoUsuarios.getUsuarioLogeado();

            String titulo = txtTituloEvento.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            Calendar fechaRealizar = dateChooser.getCalendar();
            String tipo = (String) cboTipo.getSelectedItem();
            String montoTexto = txtMontoRenta.getText().trim();

            if (titulo.isEmpty() || fechaRealizar == null || montoTexto.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: todos los campos generales deben estar llenos.");
                return;
            }

            double montoRenta;
            try {
                montoRenta = Double.parseDouble(montoTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: monto de renta inv�lido.");
                return;
            }
            if (montoRenta < 0) {
                JOptionPane.showMessageDialog(this, "Error: monto de renta no puede ser negativo.");
                return;
            }

            String codigo = manejoEventos.codigoSiguiente();

            switch (tipo) {
                case "RELIGIOSO":
                    manejoEventos.crearEventoReligioso(usuarioLogeado, codigo, titulo, descripcion, fechaRealizar, montoRenta);
                    JOptionPane.showMessageDialog(this, "Evento religioso creado correctamente.");
                    break;

                case "MUSICAL":
                    TipoMusica tipoMusica = (TipoMusica) cboMusica.getSelectedItem();
                    if (tipoMusica == null) {
                        JOptionPane.showMessageDialog(this, "Seleccione un tipo de m�sica.");
                        return;
                    }
                    manejoEventos.crearEventoMusical(usuarioLogeado, codigo, titulo, descripcion, fechaRealizar, montoRenta, tipoMusica);
                    JOptionPane.showMessageDialog(this, "Evento musical creado correctamente.");
                    break;

                case "DEPORTIVO":
                    String equipo1 = txtEquipo1.getText().trim();
                    String equipo2 = txtEquipo2.getText().trim();
                    if (equipo1.isEmpty() || equipo2.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Error: debe ingresar ambos equipos.");
                        return;
                    }
                    if (equipo1.equalsIgnoreCase(equipo2)) {
                        JOptionPane.showMessageDialog(this, "Error: ambos equipos son iguales.");
                        return;
                    }
                    TipoDeporte tipoDeporte = (TipoDeporte) cboDeporte.getSelectedItem();
                    if (tipoDeporte == null) {
                        JOptionPane.showMessageDialog(this, "Seleccione un tipo de deporte.");
                        return;
                    }
                    manejoEventos.crearEventoDeportivo(usuarioLogeado, codigo, titulo, descripcion, fechaRealizar, montoRenta, equipo1, equipo2, tipoDeporte);
                    JOptionPane.showMessageDialog(this, "Evento deportivo creado correctamente.");
                    break;
            }

            //vaciar campos y refrescar c�digo
            txtTituloEvento.setText("");
            txtDescripcion.setText("");
            txtMontoRenta.setText("");
            txtEquipo1.setText("");
            txtEquipo2.setText("");
            dateChooser.setCalendar(null);
            cboTipo.setSelectedIndex(0);
            panelDeportivo.setVisible(true);
            panelMusical.setVisible(false);

            txtCodigo.setText(manejoEventos.codigoSiguiente());
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmCrearEvento().setVisible(true);
    }
}
