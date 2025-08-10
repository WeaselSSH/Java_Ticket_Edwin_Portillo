package java_ticket_edwin_portillo;

import Eventos.Evento;
import Eventos.EventoDeportivo;
import Eventos.EventoMusical;
import Eventos.EventoReligioso;
import Tipos.TipoMusica;
import Tipos.TipoDeporte;
import Usuarios.Administrador;
import Usuarios.Contenido;
import Usuarios.Usuario;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class FrmEditarEvento extends BaseFrame {

    private final ManejoEventos manejoEventos = ManejoEventos.getInstancia();
    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmEditarEvento() {
        super("Editar Evento", 480, 630);
    }

    @Override
    protected void initComponents() {
        //paneles
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de ui
        JLabel lblTitulo = crearLabel("EDITAR EVENTO", 0, 0, 300, 40, Font.BOLD, 20f);
        panelNorte.add(lblTitulo);

        JLabel lblCodigo = crearLabel("Código del evento:", 35, 0, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblCodigo);

        JTextField txtCodigo = crearTextField(170, 0, 170, 25);
        panelCentro.add(txtCodigo);

        JButton btnCargar = crearBoton("Cargar", 345, 0, 90, 25);
        panelCentro.add(btnCargar);

        JLabel lblTituloEvento = crearLabel("Título:", 35, 40, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblTituloEvento);

        JTextField txtTitulo = crearTextField(170, 40, 265, 25);
        panelCentro.add(txtTitulo);

        JLabel lblDescripcion = crearLabel("Descripción:", 35, 80, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblDescripcion);

        JTextArea txtDescripcion = crearTextArea(170, 80, 265, 70);
        panelCentro.add(txtDescripcion);

        JLabel lblFecha = crearLabel("Fecha del Evento:", 35, 160, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblFecha);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(170, 160, 200, 25);
        panelCentro.add(dateChooser);

        JLabel lblMontoRenta = crearLabel("Monto de Renta:", 35, 200, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblMontoRenta);

        JTextField txtMontoRenta = crearTextField(170, 200, 200, 25);
        panelCentro.add(txtMontoRenta);

        JLabel lblTipo = crearLabel("Tipo de Evento:", 35, 240, 200, 25, Font.BOLD, 14f);
        panelCentro.add(lblTipo);

        String[] tipos = {"DEPORTIVO", "MUSICAL", "RELIGIOSO"};
        JComboBox<String> cboTipo = crearComboBox(tipos, 170, 240, 200, 25);
        panelCentro.add(cboTipo);

        // PANEL DEPORTIVO
        JPanel panelDeportivo = new JPanel(null);
        panelDeportivo.setBounds(35, 280, 400, 150);
        panelCentro.add(panelDeportivo);

        JLabel lblEquipo1 = crearLabel("Equipo 1:", 0, 0, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblEquipo1);

        JTextField txtEquipo1 = crearTextField(100, 0, 235, 25);
        panelDeportivo.add(txtEquipo1);

        JLabel lblEquipo2 = crearLabel("Equipo 2:", 0, 40, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblEquipo2);

        JTextField txtEquipo2 = crearTextField(100, 40, 235, 25);
        panelDeportivo.add(txtEquipo2);

        JLabel lblTipoDeporte = crearLabel("Tipo de Deporte:", 0, 80, 120, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblTipoDeporte);

        JComboBox<TipoDeporte> cboDeporte = crearComboBox(TipoDeporte.values(), 130, 80, 170, 25);
        panelDeportivo.add(cboDeporte);

        JButton btnJugadores = crearBoton("Ingresar Jugadores", 110, 125, 190, 25);
        panelDeportivo.add(btnJugadores);

        // PANEL MUSICAL
        JPanel panelMusical = new JPanel(null);
        panelMusical.setBounds(35, 280, 400, 90);
        panelCentro.add(panelMusical);

        JLabel lblTipoMusica = crearLabel("Tipo de Música:", 0, 0, 120, 25, Font.BOLD, 12f);
        panelMusical.add(lblTipoMusica);

        JComboBox<TipoMusica> cboMusica = crearComboBox(TipoMusica.values(), 130, 0, 170, 25);
        panelMusical.add(cboMusica);

        JButton btnStaff = crearBoton("Ingresar Staff", 110, 40, 190, 25);
        panelMusical.add(btnStaff);

        //PANEL RELIGIOSO 
        JPanel panelReligioso = new JPanel(null);
        panelReligioso.setBounds(35, 280, 400, 90);
        panelCentro.add(panelReligioso);

        JLabel lblConvertidos = crearLabel("Cantidad de convertidos:", 0, 0, 160, 25, Font.BOLD, 12f);
        panelReligioso.add(lblConvertidos);

        JTextField txtConvertidos = crearTextField(160, 0, 180, 25);
        panelReligioso.add(txtConvertidos);

        //combobox
        cboTipo.addActionListener(e -> {
            String tipo = (String) cboTipo.getSelectedItem();
            panelDeportivo.setVisible("DEPORTIVO".equals(tipo));
            panelMusical.setVisible("MUSICAL".equals(tipo));
            panelReligioso.setVisible("RELIGIOSO".equals(tipo));
        });

        // BOTONES
        JButton btnGuardar = crearBoton("Guardar Cambios", 60, 465, 160, 35);
        panelCentro.add(btnGuardar);

        JButton btnRegresar = crearBoton("Regresar", 240, 465, 160, 35);
        panelCentro.add(btnRegresar);

        btnCargar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código.");
                return;
            }

            Usuario usuarioLogeado = manejoUsuarios.getUsuarioLogeado();
            boolean creador = false;

            if (usuarioLogeado.getRol().equalsIgnoreCase("administrador")) {
                for (String c : ((Administrador) usuarioLogeado).getEventosCreados()) {
                    if (c != null && c.equalsIgnoreCase(codigo.toUpperCase())) {
                        creador = true;
                        break;
                    }
                }
            } else if (usuarioLogeado.getRol().equalsIgnoreCase("contenido")) {
                for (String c : ((Contenido) usuarioLogeado).getEventosCreados()) {
                    if (c != null && c.equalsIgnoreCase(codigo.toUpperCase())) {
                        creador = true;
                        break;
                    }
                }
            } else {
                creador = false;
            }

            if (!creador) {
                JOptionPane.showMessageDialog(this, "Error: solo el creador puede editar el evento");
                return;

            }

            Evento evt = manejoEventos.buscarEvento(codigo);
            if (evt == null) {
                JOptionPane.showMessageDialog(this, "Error: este evento no existe.");
                return;
            }

            txtTitulo.setText(evt.getTitulo());
            txtDescripcion.setText(evt.getDescripcion());
            dateChooser.setCalendar(evt.getFechaRealizar());
            txtMontoRenta.setText(String.valueOf(evt.getMontoRenta()));

            String tipo = evt.getTipo().toUpperCase();
            cboTipo.setSelectedItem(tipo);
            panelDeportivo.setVisible(false);
            panelMusical.setVisible(false);
            panelReligioso.setVisible(false);

            switch (tipo) {
                case "DEPORTIVO":
                    panelDeportivo.setVisible(true);
                    EventoDeportivo dep = (EventoDeportivo) evt;

                    txtEquipo1.setText(dep.getEquipo1());
                    txtEquipo2.setText(dep.getEquipo2());

                    cboDeporte.setSelectedItem(dep.getTipoDeporte());
                    break;

                case "MUSICAL":
                    panelMusical.setVisible(true);
                    EventoMusical mus = (EventoMusical) evt;

                    cboMusica.setSelectedItem(mus.getTipoMusica());
                    break;

                case "RELIGIOSO":
                    panelReligioso.setVisible(true);
                    EventoReligioso reli = (EventoReligioso) evt;
                    txtConvertidos.setText(String.valueOf(reli.getCantidadConvertidos()));
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Tipo desconocido.");
            }
        });

        btnJugadores.addActionListener(e -> {

            String codigo = txtCodigo.getText().trim();

            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: ingrese el código del evento primero.");
                return;
            }

            Evento evt = manejoEventos.buscarEvento(codigo);
            if (evt == null || !(evt instanceof EventoDeportivo)) {
                JOptionPane.showMessageDialog(this, "Error: evento no existe.");
                return;
            }

            TipoDeporte dep = (TipoDeporte) cboDeporte.getSelectedItem();
            String eq1 = txtEquipo1.getText().trim();
            String eq2 = txtEquipo2.getText().trim();

            if (eq1.isEmpty() || eq2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese ambos equipos.");
                return;
            }
            if (eq1.equalsIgnoreCase(eq2)) {
                JOptionPane.showMessageDialog(this, "Error: Los equipos no pueden ser iguales.");
                return;
            }

            EventoDeportivo d = (EventoDeportivo) evt;

            FrmJugadores fj = new FrmJugadores();

            fj.configurar(dep, eq1, eq2,
                    d.getJugadoresEquipo1().toArray(new String[0]),
                    d.getJugadoresEquipo2().toArray(new String[0]));

            fj.setVisible(true);

            if (!fj.guardado()) {
                return;
            }

            boolean exitoso = manejoEventos.actualizarJugadoresDeportivo(
                    manejoUsuarios.getUsuarioLogeado(),
                    codigo,
                    fj.getResultadoEquipo1(),
                    fj.getResultadoEquipo2()
            );

            if (exitoso) {
                JOptionPane.showMessageDialog(this, "Jugadores actualizados.");
            } else {
                JOptionPane.showMessageDialog(this, "Error: no se pudo actualizar los jugadores.");
            }
        });

        btnStaff.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: ingrese el código del evento primero.");
                return;
            }

            Evento evt = manejoEventos.buscarEvento(codigo);
            if (evt == null || !(evt instanceof EventoMusical) ) {
                JOptionPane.showMessageDialog(this, "Error: evento no existe.");
                return;
            }

            EventoMusical m = (EventoMusical) evt;

            FrmStaff fs = new FrmStaff();
            fs.configurar(m.getStaffTecnico().toArray(new String[0]));
            fs.setVisible(true);

            if (!fs.guardado()) {
                return;
            }

            boolean exitoso = manejoEventos.actualizarStaffMusical(
                    manejoUsuarios.getUsuarioLogeado(),
                    codigo,
                    fs.getResultadoStaff()
            );

            JOptionPane.showMessageDialog(this, exitoso ? "Staff actualizado."
                    : "Error: no se pudo actualizar el staff.");
        });

        btnGuardar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código.");
                return;
            }
            
            Evento evt = manejoEventos.buscarEvento(codigo);
            if (evt == null) {
                JOptionPane.showMessageDialog(this, "Error: este evento no existe.");
                return;
            }

            String titulo = txtTitulo.getText().trim();
            String desc = txtDescripcion.getText().trim();
            Calendar fecha = dateChooser.getCalendar();
            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese título.");
                return;
            }
            if (fecha == null) {
                JOptionPane.showMessageDialog(this, "Seleccione fecha.");
                return;
            }

            double renta;
            try {
                renta = Double.parseDouble(txtMontoRenta.getText().trim());
                if (renta < 0) {
                    JOptionPane.showMessageDialog(this, "Monto inválido.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Monto inválido.");
                return;
            }

            boolean exitoso = false;
            String tipoReal = evt.getTipo().toUpperCase();

            switch (tipoReal) {
                case "DEPORTIVO": {
                    String eq1 = txtEquipo1.getText().trim();
                    String eq2 = txtEquipo2.getText().trim();
                    if (eq1.isEmpty() || eq2.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Ingrese ambos equipos.");
                        return;
                    }
                    if (eq1.equalsIgnoreCase(eq2)) {
                        JOptionPane.showMessageDialog(this, "Los equipos no pueden ser iguales.");
                        return;
                    }

                    TipoDeporte td = (TipoDeporte) cboDeporte.getSelectedItem();
                    if (td == null) {
                        JOptionPane.showMessageDialog(this, "Seleccione tipo de deporte.");
                        return;
                    }

                    EventoDeportivo d = (EventoDeportivo) evt;
                    String j1[] = d.getJugadoresEquipo1().toArray(new String[0]);
                    String j2[] = d.getJugadoresEquipo2().toArray(new String[0]);

                    exitoso = manejoEventos.editarEventoDeportivo(
                            manejoUsuarios.getUsuarioLogeado(),
                            codigo, titulo, desc, fecha, renta,
                            eq1, eq2, td, j1, j2
                    );
                    break;
                }
                case "MUSICAL": {
                    TipoMusica tm = (TipoMusica) cboMusica.getSelectedItem();
                    if (tm == null) {
                        JOptionPane.showMessageDialog(this, "Seleccione tipo de música.");
                        return;
                    }

                    EventoMusical m = (EventoMusical) evt;
                    java.util.ArrayList<String> staff = new java.util.ArrayList<>(m.getStaffTecnico());

                    exitoso = manejoEventos.editarEventoMusical(
                            manejoUsuarios.getUsuarioLogeado(),
                            codigo, titulo, desc, fecha, renta, tm, staff
                    );
                    break;
                }
                case "RELIGIOSO": {
                    int convertidos;
                    try {
                        convertidos = Integer.parseInt(txtConvertidos.getText().trim());
                        if (convertidos < 0) {
                            JOptionPane.showMessageDialog(this, "Convertidos inválido.");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Convertidos inválido.");
                        return;
                    }

                    exitoso = manejoEventos.editarEventoReligioso(
                            manejoUsuarios.getUsuarioLogeado(),
                            codigo, titulo, desc, fecha, renta, convertidos
                    );
                    break;
                }
                default:
                    JOptionPane.showMessageDialog(this, "Tipo desconocido.");
                    return;
            }

            JOptionPane.showMessageDialog(this, exitoso ? "Cambios guardados." : "No se pudo guardar.");
        });

        btnRegresar.addActionListener(e -> {
            new FrmEventos().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmEditarEvento().setVisible(true);
    }
}
