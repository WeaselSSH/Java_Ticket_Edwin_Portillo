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
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

public class FrmEditarEvento extends BaseFrame {

    private final ManejoEventos manejoEventos = ManejoEventos.getInstancia();
    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    private JPanel panelJugadores;
    private JTable tablaJugadores;
    private DefaultTableModel modeloJugador;

    public FrmEditarEvento() {
        super("Editar Evento", 920, 630);
    }

    @Override
    protected void initComponents() {
        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout()) {
            final Image bg = new ImageIcon(
                    getClass().getResource("/Imagenes/bg_dark_1920x1080.png")
            ).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //resto de paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 75));
        panelNorte.setOpaque(false);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de ui
        JLabel lblTitulo = crearLabel("Editar Evento", 0, 0, 0, 0, Font.BOLD, 24f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblCodigo = crearLabel("Código del evento:", 35, 0, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblCodigo);

        JTextField txtCodigo = crearTextField(175, 0, 170, 25);
        panelCentro.add(txtCodigo);

        JButton btnCargar = crearBoton("Cargar", 345, 0, 90, 25);
        panelCentro.add(btnCargar);

        JLabel lblTituloEvento = crearLabel("Título:", 35, 40, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblTituloEvento);

        JTextField txtTitulo = crearTextField(170, 40, 265, 25);
        txtTitulo.setEnabled(false);
        panelCentro.add(txtTitulo);

        JLabel lblDescripcion = crearLabel("Descripción:", 35, 80, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblDescripcion);

        JTextArea txtDescripcion = crearTextArea(170, 80, 265, 70);
        JScrollPane spDesc = new JScrollPane(txtDescripcion);
        spDesc.setBounds(170, 80, 265, 70);
        txtDescripcion.setEnabled(false);
        panelCentro.add(spDesc);

        JLabel lblFecha = crearLabel("Fecha del Evento:", 35, 160, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblFecha);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(170, 160, 200, 25);
        dateChooser.setEnabled(false);
        panelCentro.add(dateChooser);

        JLabel lblMontoRenta = crearLabel("Monto de Renta:", 35, 200, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblMontoRenta);

        JTextField txtMontoRenta = crearTextField(170, 200, 200, 25);
        txtMontoRenta.setEnabled(false);
        panelCentro.add(txtMontoRenta);

        JLabel lblTipo = crearLabel("Tipo de Evento:", 35, 240, 200, 25, Font.BOLD, 14f);
        panelCentro.add(lblTipo);

        JLabel lblTipoValor = crearLabel("-", 170, 240, 200, 25, Font.PLAIN, 14f);
        panelCentro.add(lblTipoValor);

        // PANEL DEPORTIVO
        JPanel panelDeportivo = new JPanel(null);
        panelDeportivo.setBounds(35, 280, 400, 150);
        panelDeportivo.setOpaque(false);
        panelCentro.add(panelDeportivo);

        JLabel lblEquipo1 = crearLabel("Equipo 1:", 0, 0, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblEquipo1);

        JTextField txtEquipo1 = crearTextField(100, 0, 235, 25);
        txtEquipo1.setEnabled(false);
        panelDeportivo.add(txtEquipo1);

        JLabel lblEquipo2 = crearLabel("Equipo 2:", 0, 40, 100, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblEquipo2);

        JTextField txtEquipo2 = crearTextField(100, 40, 235, 25);
        txtEquipo2.setEnabled(false);
        panelDeportivo.add(txtEquipo2);

        JLabel lblTipoDeporte = crearLabel("Tipo de Deporte:", 0, 80, 120, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblTipoDeporte);

        JLabel lbltipoDep = crearLabel("-", 130, 80, 170, 25, Font.BOLD, 12f);
        panelDeportivo.add(lbltipoDep);

        //panel jugadores
        panelJugadores = new JPanel(null);
        panelJugadores.setOpaque(false);
        panelJugadores.setBounds(470, 0, 400, 200);
        panelCentro.add(panelJugadores);

        modeloJugador = new DefaultTableModel(new Object[]{"#", "Equipo 1", "Equipo 2"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col > 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };
        tablaJugadores = new JTable(modeloJugador);
        tablaJugadores.setForeground(Color.decode("#1A2332"));
        JScrollPane spJugadores = new JScrollPane(tablaJugadores);
        spJugadores.setBounds(0, 0, 400, 200);
        panelJugadores.add(spJugadores);
        tablaJugadores.getColumnModel().getColumn(0).setMaxWidth(50);
        tablaJugadores.getColumnModel().getColumn(0).setMinWidth(40);
        tablaJugadores.setRowHeight(22);
        tablaJugadores.setFillsViewportHeight(true);

        // PANEL MUSICAL
        JPanel panelMusical = new JPanel(null);
        panelMusical.setBounds(35, 280, 400, 90);
        panelMusical.setOpaque(false);
        panelCentro.add(panelMusical);

        JLabel lblTipoMusica = crearLabel("Tipo de Música:", 0, 0, 120, 25, Font.BOLD, 12f);
        panelMusical.add(lblTipoMusica);

        JComboBox<TipoMusica> cboMusica = crearComboBox(TipoMusica.values(), 130, 0, 170, 25);
        cboMusica.setEnabled(false);
        panelMusical.add(cboMusica);

        //PANEL RELIGIOSO 
        JPanel panelReligioso = new JPanel(null);
        panelReligioso.setBounds(35, 280, 400, 90);
        panelReligioso.setOpaque(false);
        panelCentro.add(panelReligioso);

        JLabel lblConvertidos = crearLabel("Cantidad de convertidos:", 0, 0, 160, 25, Font.BOLD, 12f);
        panelReligioso.add(lblConvertidos);

        JTextField txtConvertidos = crearTextField(160, 0, 180, 25);
        panelReligioso.add(txtConvertidos);

        // BOTONES
        JButton btnGuardar = crearBoton("Guardar Cambios", 60, 465, 160, 35);
        panelCentro.add(btnGuardar);

        JButton btnRegresar = crearBoton("Regresar", 240, 465, 160, 35);
        panelCentro.add(btnRegresar);

        btnCargar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código de evento.");
                return;
            }

            Evento evt = manejoEventos.buscarEvento(codigo);
            if (evt == null) {
                JOptionPane.showMessageDialog(this, "No existe un evento con ese código.");
                return;
            }

            Usuario usuarioLogeado = manejoUsuarios.getUsuarioLogeado();

            if (!esCreador(usuarioLogeado, codigo)) {
                JOptionPane.showMessageDialog(this, "No puedes editar este evento: no eres el creador.");
                return;
            }

            txtTitulo.setText(evt.getTitulo());
            txtDescripcion.setText(evt.getDescripcion());
            dateChooser.setCalendar(evt.getFechaRealizar());
            txtMontoRenta.setText(Double.toString(evt.getMontoRenta()));

            panelDeportivo.setVisible(false);
            panelMusical.setVisible(false);
            panelReligioso.setVisible(false);
            panelJugadores.setVisible(false);

            if (evt instanceof EventoDeportivo) {
                EventoDeportivo ed = (EventoDeportivo) evt;

                lblTipoValor.setText("DEPORTIVO");
                lbltipoDep.setText(ed.getTipoDeporte().name());
                txtEquipo1.setText(ed.getEquipo1());
                txtEquipo2.setText(ed.getEquipo2());

                panelDeportivo.setVisible(true);
                panelJugadores.setVisible(true);

                ArrayList<String> lista1 = ed.getJugadoresEquipo1();
                ArrayList<String> lista2 = ed.getJugadoresEquipo2();

                int filasBase = jugadoresPorEquipo(ed.getTipoDeporte());
                int tam1 = (lista1 != null) ? lista1.size() : 0;
                int tam2 = (lista2 != null) ? lista2.size() : 0;
                int filas = filasBase;

                rellenarTablaJugadores(filas);

                for (int i = 0; i < tam1 && i < filas; i++) {
                    modeloJugador.setValueAt(lista1.get(i), i, 1);
                }
                for (int i = 0; i < tam2 && i < filas; i++) {
                    modeloJugador.setValueAt(lista2.get(i), i, 2);
                }
                tablaJugadores.getColumnModel().getColumn(1).setHeaderValue(txtEquipo1.getText().trim());
                tablaJugadores.getColumnModel().getColumn(2).setHeaderValue(txtEquipo2.getText().trim());
                tablaJugadores.getTableHeader().repaint();

            } else if (evt instanceof EventoMusical) {
                lblTipoValor.setText("MUSICAL");
                panelMusical.setVisible(true);

            } else if (evt instanceof EventoReligioso) {
                lblTipoValor.setText("RELIGIOSO");
                panelReligioso.setVisible(true);
                txtConvertidos.setText(String.valueOf(((EventoReligioso) evt).getCantidadConvertidos()));
            }

            Calendar hoy = Calendar.getInstance();

            if (hoy.before(evt.getFechaRealizar())) {
                dateChooser.setEnabled(true);
                txtTitulo.setEnabled(true);
                txtDescripcion.setEnabled(true);
                txtMontoRenta.setEnabled(true);
                txtConvertidos.setEnabled(false);
            }
        });

        btnGuardar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            String titulo = txtTitulo.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            Calendar fecha = dateChooser.getCalendar();
            String montoTexto = txtMontoRenta.getText().trim();

            if (codigo.isEmpty() || titulo.isEmpty() || descripcion.isEmpty()
                    || fecha == null || montoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: uno de los campos está vacío.");
                return;
            }

            Evento evt = manejoEventos.buscarEvento(codigo);
            if (evt == null) {
                JOptionPane.showMessageDialog(this, "Error: evento no existe.");
                return;
            }

            String tipoEvt = lblTipoValor.getText().trim();

            if ("DEPORTIVO".equalsIgnoreCase(tipoEvt)) {
                String equipo1 = txtEquipo1.getText().trim();
                String equipo2 = txtEquipo2.getText().trim();

                ArrayList<String> jugadores1 = new ArrayList<>();
                ArrayList<String> jugadores2 = new ArrayList<>();
                for (int r = 0; r < modeloJugador.getRowCount(); r++) {
                    Object v1 = modeloJugador.getValueAt(r, 1);
                    if (v1 instanceof String) {
                        String s = ((String) v1).trim();
                        if (!s.isEmpty()) {
                            jugadores1.add(s);
                        }
                    }
                    Object v2 = modeloJugador.getValueAt(r, 2);
                    if (v2 instanceof String) {
                        String s = ((String) v2).trim();
                        if (!s.isEmpty()) {
                            jugadores2.add(s);
                        }
                    }
                }
                String[] arr1 = jugadores1.toArray(new String[0]);
                String[] arr2 = jugadores2.toArray(new String[0]);

                Usuario usuarioLogeado = manejoUsuarios.getUsuarioLogeado();

                TipoDeporte deporte = ((EventoDeportivo) evt).getTipoDeporte();

                double monto;
                try {
                    monto = Double.parseDouble(montoTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error: monto de renta inválido.");
                    return;
                }

                boolean exitoso = manejoEventos.editarEventoDeportivo(
                        usuarioLogeado, codigo, titulo, descripcion, fecha,
                        monto, equipo1, equipo2, deporte, arr1, arr2
                );

                JOptionPane.showMessageDialog(this, exitoso ? "Cambios guardados correctamente."
                        : "Error: no se pudo guardar.");

            } else if ("RELIGIOSO".equalsIgnoreCase(tipoEvt)) {
                Usuario usuarioLogeado = manejoUsuarios.getUsuarioLogeado();
                double monto;
                try {
                    monto = Double.parseDouble(montoTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error: monto de renta inválido.");
                    return;
                }

                EventoReligioso er = (EventoReligioso) evt;
                Calendar hoy = Calendar.getInstance();
                int convertidos;

                if (hoy.after(fecha)) {
                    String convertidosTexto = txtConvertidos.getText().trim();
                    if (convertidosTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Error: ingrese la cantidad de convertidos.");
                        return;
                    }
                    try {
                        convertidos = Integer.parseInt(convertidosTexto);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Error: convertidos debe ser un entero válido.");
                        return;
                    }
                    if (convertidos < 0) {
                        JOptionPane.showMessageDialog(this, "Error: convertidos no puede ser negativo.");
                        return;
                    }
                } else {
                    convertidos = er.getCantidadConvertidos();
                }

                boolean exitoso = manejoEventos.editarEventoReligioso(
                        usuarioLogeado, codigo, titulo, descripcion, fecha, monto, convertidos
                );

                JOptionPane.showMessageDialog(this, exitoso ? "Cambios guardados correctamente."
                        : "Error: no se pudo guardar.");
            }
        });

        btnRegresar.addActionListener(e -> {
            new FrmEventos().setVisible(true);
            this.dispose();
        });

        panelDeportivo.setVisible(false);
        panelMusical.setVisible(false);
        panelReligioso.setVisible(false);
        panelJugadores.setVisible(false);
        setContentPane(panelPrincipal);
    }

    private void rellenarTablaJugadores(int filas) {
        modeloJugador.setRowCount(0);
        for (int i = 1; i <= filas; i++) {
            modeloJugador.addRow(new Object[]{i, "", ""});
        }
    }

    private int jugadoresPorEquipo(TipoDeporte d) {
        switch (d) {
            case FUTBOL:
                return 11;
            case RUGBY:
                return 15;
            case BASEBALL:
                return 9;
            default:
                return 1;
        }
    }

    private boolean esCreador(Usuario usuario, String codigo) {
        if (usuario == null || codigo == null) {
            return false;
        }

        if (usuario instanceof Administrador) {
            for (String c : ((Administrador) usuario).getEventosCreados()) {
                if (c != null && c.equalsIgnoreCase(codigo)) {
                    return true;
                }
            }
        } else if (usuario instanceof Contenido) {
            for (String c : ((Contenido) usuario).getEventosCreados()) {
                if (c != null && c.equalsIgnoreCase(codigo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new FrmEditarEvento().setVisible(true);
    }
}
//REPORTES
//EDITAR EVENTO (ACORDE A LO LóGICO)
//ver evento mostrar su creador?
//regresar al menú principal?
//fecha?
//final classes
//CLASE FINAL, MéTODO FINAL.
//si se puede cambiar color al estar disabled
//convertidos solo si es after hoy
