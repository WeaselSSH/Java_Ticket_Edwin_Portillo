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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
        panelDeportivo.setOpaque(false);
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

        cboDeporte.addActionListener(e -> {
            if (!panelJugadores.isVisible()) {
                return;
            }
            if (tablaJugadores.isEditing()) {
                tablaJugadores.getCellEditor().stopCellEditing();
            }

            int jugRequerido = cupoDeporte((TipoDeporte) cboDeporte.getSelectedItem());
            reconstruirTablaJugadores(jugRequerido, txtEquipo1.getText(), txtEquipo2.getText());
        });

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

        //combobox
        cboTipo.addActionListener(e -> {
            String tipo = (String) cboTipo.getSelectedItem();
            boolean esDeportivo = "DEPORTIVO".equals(tipo);
            panelDeportivo.setVisible("DEPORTIVO".equals(tipo));
            panelMusical.setVisible("MUSICAL".equals(tipo));
            panelReligioso.setVisible("RELIGIOSO".equals(tipo));
            panelJugadores.setVisible(esDeportivo);
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
            boolean esDeportivo = "DEPORTIVO".equals(tipo);
            cboTipo.setSelectedItem(tipo);
            panelDeportivo.setVisible(false);
            panelMusical.setVisible(false);
            panelReligioso.setVisible(false);
            panelJugadores.setVisible(esDeportivo);

            switch (tipo) {
                case "DEPORTIVO":
                    panelDeportivo.setVisible(true);
                    EventoDeportivo dep = (EventoDeportivo) evt;

                    txtEquipo1.setText(dep.getEquipo1());
                    txtEquipo2.setText(dep.getEquipo2());

                    cboDeporte.setSelectedItem(dep.getTipoDeporte());
                    cargarJugadores(dep);
                    panelJugadores.setVisible(true);

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

                    TipoDeporte tipoDeporte = (TipoDeporte) cboDeporte.getSelectedItem();
                    if (tipoDeporte == null) {
                        JOptionPane.showMessageDialog(this, "Seleccione tipo de deporte.");
                        return;
                    }

                    String jugadores[][];
                    try {
                        jugadores = leerJugadoresTabla();
                    } catch (IllegalArgumentException | IllegalStateException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                        return;
                    }

                    exitoso = manejoEventos.editarEventoDeportivo(
                            manejoUsuarios.getUsuarioLogeado(),
                            codigo, titulo, desc, fecha, renta,
                            eq1, eq2, tipoDeporte,
                            jugadores[0], jugadores[1]
                    );
                    break;
                }

                case "MUSICAL": {
                    TipoMusica tm = (TipoMusica) cboMusica.getSelectedItem();
                    if (tm == null) {
                        JOptionPane.showMessageDialog(this, "Seleccione tipo de música.");
                        return;
                    }

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

        panelDeportivo.setVisible(false);
        panelMusical.setVisible(false);
        panelReligioso.setVisible(false);
        panelJugadores.setVisible(false);
        setContentPane(panelPrincipal);
    }

    private void reconstruirTablaJugadores(int req, String eq1, String eq2) {
        String tituloEquipo1 = eq1.trim();
        String tituloEquipo2 = eq2.trim();

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"#", tituloEquipo1, tituloEquipo2}, 0) {
            @Override
            public boolean isCellEditable(int f, int c) {
                return c > 0;
            }

            @Override
            public Class<?> getColumnClass(int c) {
                return c == 0 ? Integer.class : String.class;
            }
        };
        for (int i = 0; i < req; i++) {
            modelo.addRow(new Object[]{i + 1, "", ""});
        }

        tablaJugadores.setModel(modelo);
        modeloJugador = modelo;
        TableColumn col0 = tablaJugadores.getColumnModel().getColumn(0);
        col0.setMaxWidth(50);
        col0.setMinWidth(40);
        tablaJugadores.setRowHeight(22);
        tablaJugadores.setFillsViewportHeight(true);
    }

    private void cargarJugadores(EventoDeportivo dep) {
        int jugadoresRequeridos = cupoDeporte(dep.getTipoDeporte());
        reconstruirTablaJugadores(jugadoresRequeridos, dep.getEquipo1(), dep.getEquipo2());

        int n1 = (dep.getJugadoresEquipo1() != null) ? dep.getJugadoresEquipo1().size() : 0;
        int n2 = (dep.getJugadoresEquipo2() != null) ? dep.getJugadoresEquipo2().size() : 0;
        for (int i = 0; i < jugadoresRequeridos; i++) {
            String j1 = (i < n1 && dep.getJugadoresEquipo1().get(i) != null) ? dep.getJugadoresEquipo1().get(i).trim() : "";
            String j2 = (i < n2 && dep.getJugadoresEquipo2().get(i) != null) ? dep.getJugadoresEquipo2().get(i).trim() : "";
            modeloJugador.setValueAt(j1, i, 1);
            modeloJugador.setValueAt(j2, i, 2);
        }
    }

    private String[][] leerJugadoresTabla() {
        if (tablaJugadores != null && tablaJugadores.isEditing()) {
            tablaJugadores.getCellEditor().stopCellEditing();
        }
        int n = (modeloJugador != null) ? modeloJugador.getRowCount() : 0;

        String j1[] = new String[n];
        String j2[] = new String[n];

        for (int i = 0; i < n; i++) {
            String s1 = modeloJugador.getValueAt(i, 1).toString().trim();
            String s2 = modeloJugador.getValueAt(i, 2).toString().trim();

            if (s1.isEmpty() || s2.isEmpty()) {
                throw new IllegalArgumentException("Fila " + (i + 1) + " Incompleta: ambos jugadores son obligatorios.");
            }
            j1[i] = s1;
            j2[i] = s2;
        }
        return new String[][]{j1, j2};
    }

    private int cupoDeporte(TipoDeporte d) {
        if (d == null) {
            return 0;
        }
        switch (d) {
            case FUTBOL:
                return 11;
            case TENIS:
                return 1;
            case RUGBY:
                return 15;
            case BASEBALL:
                return 9;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        new FrmEditarEvento().setVisible(true);
    }
}
//VER EVENTO LE FALTAN CAMPOS QQUE MOSTRAR
