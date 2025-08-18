package java_ticket_edwin_portillo;

import Eventos.Evento;
import Eventos.EventoDeportivo;
import Eventos.EventoMusical;
import Eventos.EventoReligioso;
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

    private JPanel panelStaff;
    private JTable tablaStaff;
    private DefaultTableModel modeloStaff;

    public FrmEditarEvento() {
        super("Editar Evento", 920, 590);
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

        JLabel lblPersonas = crearLabel("Personas:", 35, 275, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblPersonas);

        JTextField txtPersonas = crearTextField(170, 275, 200, 25);
        panelCentro.add(txtPersonas);

        JLabel lblTipo = crearLabel("Tipo de Evento:", 35, 240, 200, 25, Font.BOLD, 14f);
        panelCentro.add(lblTipo);

        JLabel lblTipoValor = crearLabel("-", 170, 240, 200, 25, Font.PLAIN, 14f);
        panelCentro.add(lblTipoValor);

        // PANEL DEPORTIVO
        JPanel panelDeportivo = new JPanel(null);
        panelDeportivo.setBounds(35, 310, 400, 150);
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

        JLabel lblTipoDeporte = crearLabel("Tipo de Deporte:", 0, 75, 120, 25, Font.BOLD, 12f);
        panelDeportivo.add(lblTipoDeporte);

        JLabel lbltipoDep = crearLabel("-", 130, 75, 170, 25, Font.BOLD, 12f);
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
        panelMusical.setBounds(35, 310, 400, 90);
        panelMusical.setOpaque(false);
        panelCentro.add(panelMusical);

        JLabel lblTipoMusica = crearLabel("Tipo de Música:", 0, 0, 120, 25, Font.BOLD, 12f);
        panelMusical.add(lblTipoMusica);

        JLabel lblValorMusica = crearLabel("-", 130, 0, 70, 25, Font.BOLD, 12F);
        panelMusical.add(lblValorMusica);

        //panel staff musical
        panelStaff = new JPanel(null);
        panelStaff.setOpaque(false);
        panelStaff.setBounds(470, 0, 400, 230);
        panelCentro.add(panelStaff);

        modeloStaff = new DefaultTableModel(new Object[]{"#", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col > 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };

        tablaStaff = new JTable(modeloStaff);
        tablaStaff.setForeground(Color.decode("#1A2332"));

        JScrollPane spStaff = new JScrollPane(tablaStaff);
        spStaff.setBounds(0, 0, 400, 170);
        panelStaff.add(spStaff);

        JButton btnAgregarStaff = crearBoton("Agregar", 80, 180, 120, 25);
        JButton btnEliminarStaff = crearBoton("Eliminar", 210, 180, 120, 25);
        panelStaff.add(btnAgregarStaff);
        panelStaff.add(btnEliminarStaff);

        tablaStaff.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        btnAgregarStaff.addActionListener(ev -> {
            if (tablaStaff.isEditing()) {
                tablaStaff.getCellEditor().stopCellEditing();
            }
            int next = modeloStaff.getRowCount() + 1;
            modeloStaff.addRow(new Object[]{next, ""});
        });

        btnEliminarStaff.addActionListener(ev -> {
            if (tablaStaff.isEditing()) {
                tablaStaff.getCellEditor().stopCellEditing();
            }
            int[] sel = tablaStaff.getSelectedRows();
            if (sel == null || sel.length == 0) {
                JOptionPane.showMessageDialog(this, "Selecciona una o más filas del staff para eliminar.");
                return;
            }
            for (int i = sel.length - 1; i >= 0; i--) {
                modeloStaff.removeRow(sel[i]);
            }
            if (modeloStaff.getRowCount() == 0) {
                modeloStaff.addRow(new Object[]{1, ""});
            }
            renumerarPrimeraColumna(modeloStaff);
        });

        //PANEL RELIGIOSO 
        JPanel panelReligioso = new JPanel(null);
        panelReligioso.setBounds(35, 310, 400, 90);
        panelReligioso.setOpaque(false);
        panelCentro.add(panelReligioso);

        JLabel lblConvertidos = crearLabel("Cantidad de convertidos:", 0, 0, 160, 25, Font.BOLD, 12f);
        panelReligioso.add(lblConvertidos);

        JTextField txtConvertidos = crearTextField(160, 0, 180, 25);
        panelReligioso.add(txtConvertidos);

        // BOTONES
        JButton btnGuardar = crearBoton("Guardar Cambios", 60, 425, 160, 35);
        panelCentro.add(btnGuardar);

        JButton btnRegresar = crearBoton("Regresar", 240, 425, 160, 35);
        panelCentro.add(btnRegresar);

        btnCargar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código de evento.");
                return;
            }

            Evento evt = manejoEventos.buscarEvento(codigo);
            if (evt == null || evt.getCancelado()) {
                JOptionPane.showMessageDialog(this, "Error: No existe un evento con ese código o fue cancelado.");
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
            txtPersonas.setText(String.valueOf(evt.getPersonas()));

            panelDeportivo.setVisible(false);
            panelMusical.setVisible(false);
            panelReligioso.setVisible(false);
            panelJugadores.setVisible(false);
            panelStaff.setVisible(false);

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
                EventoMusical em = (EventoMusical) evt;

                lblTipoValor.setText("MUSICAL");
                lblValorMusica.setText(em.getTipoMusica().name());

                cargarStaff(em);
                panelMusical.setVisible(true);
                panelStaff.setVisible(true);

            } else if (evt instanceof EventoReligioso) {
                lblTipoValor.setText("RELIGIOSO");
                panelReligioso.setVisible(true);
                txtConvertidos.setText(String.valueOf(((EventoReligioso) evt).getCantidadConvertidos()));
            }

            Calendar hoy = Calendar.getInstance();
            boolean eventoFuturo = hoy.before(evt.getFechaRealizar());

            txtTitulo.setEnabled(eventoFuturo);
            txtDescripcion.setEnabled(eventoFuturo);
            txtMontoRenta.setEnabled(eventoFuturo);
            dateChooser.setEnabled(eventoFuturo);

            txtConvertidos.setEnabled(!eventoFuturo && (evt instanceof EventoReligioso));
        });

        btnGuardar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            String titulo = txtTitulo.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            Calendar fecha = dateChooser.getCalendar();
            String montoTexto = txtMontoRenta.getText().trim();
            String personasTexto = txtPersonas.getText().trim();

            int personas;
            try {
                personas = Integer.parseInt(personasTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Capacidad máxima inválida.");
                return;
            }

            if (personas < 0) {
                JOptionPane.showMessageDialog(this, "Error: personas no puede ser menor a 0.");
                return;
            }

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

            Calendar hoy = Calendar.getInstance();

            //se setea todo en 0 para que tome en cuenta el inicio del día
            hoy.set(Calendar.HOUR_OF_DAY, 0);
            hoy.set(Calendar.MINUTE, 0);
            hoy.set(Calendar.SECOND, 0);
            hoy.set(Calendar.MILLISECOND, 0);

            //fecha seleccionada en el dateChooser
            Calendar fechaSel = Calendar.getInstance();
            fechaSel.setTime(fecha.getTime());
            fechaSel.set(Calendar.HOUR_OF_DAY, 0);
            fechaSel.set(Calendar.MINUTE, 0);
            fechaSel.set(Calendar.SECOND, 0);
            fechaSel.set(Calendar.MILLISECOND, 0);

            boolean eraFuturo = hoy.before(evt.getFechaRealizar());
            boolean esFuturoNuevo = hoy.before(fechaSel);

            if (eraFuturo && !esFuturoNuevo) {
                JOptionPane.showMessageDialog(this,
                        "No puedes mover un evento futuro a una fecha pasada.");
                return;
            }

            if (!eraFuturo) {
                boolean mismaFecha = evt.getFechaRealizar().get(Calendar.YEAR) == fechaSel.get(Calendar.YEAR)
                        && evt.getFechaRealizar().get(Calendar.DAY_OF_YEAR) == fechaSel.get(Calendar.DAY_OF_YEAR);
                if (!mismaFecha) {
                    JOptionPane.showMessageDialog(this,
                            "No puedes cambiar la fecha de un evento pasado.");
                    return;
                }
            }

            Evento choque = manejoEventos.choqueFecha(fechaSel);
            if (choque != null && !choque.getCodigo().equalsIgnoreCase(codigo)) {
                JOptionPane.showMessageDialog(this,
                        "Error: ya existe un evento para ese día: " + choque.getCodigo()
                        + " - " + choque.getTitulo());
                return;
            }

            String tipoEvt = lblTipoValor.getText().trim();

            if ("DEPORTIVO".equalsIgnoreCase(tipoEvt)) {
                String equipo1 = txtEquipo1.getText().trim();
                String equipo2 = txtEquipo2.getText().trim();

                if (tablaJugadores != null && tablaJugadores.isEditing()) {
                    tablaJugadores.getCellEditor().stopCellEditing();
                }

                for (int r = 0; r < modeloJugador.getRowCount(); r++) {
                    Object o1 = modeloJugador.getValueAt(r, 1);
                    Object o2 = modeloJugador.getValueAt(r, 2);
                    String s1 = (o1 == null) ? "" : o1.toString().trim();
                    String s2 = (o2 == null) ? "" : o2.toString().trim();
                    if (s1.isEmpty() || s2.isEmpty()) {
                        JOptionPane.showMessageDialog(this,
                                "Error: completa ambos equipos en la fila " + (r + 1) + ".");
                        return;
                    }
                }

                ArrayList<String> jugadores1 = new ArrayList<>();
                ArrayList<String> jugadores2 = new ArrayList<>();
                for (int r = 0; r < modeloJugador.getRowCount(); r++) {
                    Object o1 = modeloJugador.getValueAt(r, 1);
                    Object o2 = modeloJugador.getValueAt(r, 2);
                    jugadores1.add((o1 == null) ? "" : o1.toString().trim());
                    jugadores2.add((o2 == null) ? "" : o2.toString().trim());
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

                if (personas > 20000) {
                    JOptionPane.showMessageDialog(this, "Error: capacidad máxima excedida (20,000).");
                    return;
                }

                boolean exitoso = manejoEventos.editarEventoDeportivo(
                        usuarioLogeado, codigo, titulo, descripcion, fecha,
                        monto, equipo1, equipo2, deporte, arr1, arr2
                );

                if (exitoso) {
                    evt.setPersonas(personas);
                }

                JOptionPane.showMessageDialog(this, exitoso ? "Cambios guardados correctamente."
                        : "Error: no se pudo guardar.");

            } else if ("MUSICAL".equalsIgnoreCase(tipoEvt)) {
                Usuario usuarioLogeado = manejoUsuarios.getUsuarioLogeado();

                double monto;
                try {
                    monto = Double.parseDouble(montoTexto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error: monto de renta inválido.");
                    return;
                }

                if (personas > 25000) {
                    JOptionPane.showMessageDialog(this, "Error: capacidad máxima excedida (25,000).");
                    return;
                }

                EventoMusical em = (EventoMusical) evt;
                ArrayList<String> staff;
                try {
                    staff = leerStaffTabla();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                    return;
                }

                boolean exitoso = manejoEventos.editarEventoMusical(
                        usuarioLogeado,
                        codigo, titulo, descripcion, fecha, monto,
                        em.getTipoMusica(),
                        staff
                );

                if (exitoso) {
                    evt.setPersonas(personas);
                }

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

                if (personas > 30000) {
                    JOptionPane.showMessageDialog(this, "Error: capacidad máxima excedida (30,000).");
                    return;
                }

                EventoReligioso er = (EventoReligioso) evt;
                int convertidos;

                if (hoy.after(fechaSel)) {
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

                if (exitoso) {
                    evt.setPersonas(personas);
                }

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
        panelStaff.setVisible(false);
        setContentPane(panelPrincipal);
    }

    //métodos para equipos
    private void rellenarTablaJugadores(int filas) {
        modeloJugador.setRowCount(0);
        for (int i = 0; i < filas; i++) {
            modeloJugador.addRow(new Object[]{i + 1, "", ""});
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

    //métodos para staff
    private void cargarStaff(EventoMusical em) {
        int n = (em.getStaffTecnico() != null) ? em.getStaffTecnico().size() : 0;
        int filas = Math.max(n, 1);
        rellenarTablaStaff(filas);
        for (int i = 0; i < n; i++) {
            String nombre = em.getStaffTecnico().get(i);
            modeloStaff.setValueAt(nombre == null ? "" : nombre.trim(), i, 1);
        }
    }

    private void rellenarTablaStaff(int filas) {
        modeloStaff.setRowCount(0);
        for (int i = 0; i < filas; i++) {
            modeloStaff.addRow(new Object[]{i + 1, ""});
        }
    }

    private ArrayList<String> leerStaffTabla() {
        if (tablaStaff != null && tablaStaff.isEditing()) {
            tablaStaff.getCellEditor().stopCellEditing();
        }

        ArrayList<String> out = new ArrayList<>();

        for (int r = 0; r < modeloStaff.getRowCount(); r++) {
            Object v = modeloStaff.getValueAt(r, 1);
            String s = (v == null) ? "" : v.toString().trim();

            if (s.isEmpty()) {
                throw new IllegalArgumentException(
                        "Fila " + (r + 1) + " del staff está vacía."
                );
            }
            out.add(s);
        }
        return out;
    }

    //verificar que sea el creador realmente
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

    private void renumerarPrimeraColumna(DefaultTableModel m) {
        for (int i = 0; i < m.getRowCount(); i++) {
            m.setValueAt(i + 1, i, 0);
        }
    }

    public static void main(String[] args) {
        new FrmEditarEvento().setVisible(true);
    }
}
