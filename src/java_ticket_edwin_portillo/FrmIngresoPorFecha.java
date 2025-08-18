package java_ticket_edwin_portillo;

import Eventos.Evento;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmIngresoPorFecha extends BaseFrame {

    private JTable tablaEventos;
    private DefaultTableModel modeloEventos;

    private JDateChooser dateDesde;
    private JDateChooser dateHasta;

    private JTextField txtDeportivos;
    private JTextField txtTotalDeportivos;
    private JTextField txtReligiosos;
    private JTextField txtTotalReligiosos;
    private JTextField txtMusicales;
    private JTextField txtTotalMusicales;
    private JTextField txtTotalGeneral;

    public FrmIngresoPorFecha() {
        super("Ingreso por Fechas", 920, 660);
    }

    @Override
    protected void initComponents() {
        // panel principal
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
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelNorte.setOpaque(false);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        JLabel lblTitulo = crearLabel("Ingreso generado por fechas", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblDesde = crearLabel("Fecha inicial:", 35, 0, 120, 25, Font.BOLD, 14f);
        panelCentro.add(lblDesde);

        dateDesde = new JDateChooser();
        dateDesde.setBounds(150, 0, 180, 25);
        panelCentro.add(dateDesde);

        JLabel lblHasta = crearLabel("Fecha final:", 350, 0, 120, 25, Font.BOLD, 14f);
        panelCentro.add(lblHasta);

        dateHasta = new JDateChooser();
        dateHasta.setBounds(460, 0, 180, 25);
        panelCentro.add(dateHasta);

        JButton btnCalcular = crearBoton("Calcular", 660, 0, 120, 25);
        panelCentro.add(btnCalcular);

        modeloEventos = new DefaultTableModel(
                new Object[]{"Código", "Tipo", "Título", "Fecha", "Importe"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tablaEventos = new JTable(modeloEventos);
        tablaEventos.setRowHeight(22);
        tablaEventos.setFillsViewportHeight(true);

        JScrollPane spEventos = new JScrollPane(tablaEventos);
        spEventos.setBounds(35, 40, 840, 300);
        panelCentro.add(spEventos);

        // panel estadísticas
        JPanel panelEstadisticas = new JPanel(null);
        panelEstadisticas.setOpaque(false);
        panelEstadisticas.setBounds(35, 360, 840, 145);
        panelCentro.add(panelEstadisticas);

        JLabel lblEstadisticas = crearLabel("Estadísticas", 0, 0, 200, 25, Font.BOLD, 16f);
        panelEstadisticas.add(lblEstadisticas);

        // Deportivos
        JLabel lblDeportivos = crearLabel("Deportivos:", 0, 35, 100, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblDeportivos);
        txtDeportivos = crearTextField(90, 35, 70, 25);
        txtDeportivos.setEditable(false);
        txtDeportivos.setFocusable(false);
        panelEstadisticas.add(txtDeportivos);

        JLabel lblTotalDeportivos = crearLabel("Total L.", 170, 35, 60, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblTotalDeportivos);
        txtTotalDeportivos = crearTextField(230, 35, 120, 25);
        txtTotalDeportivos.setEditable(false);
        txtTotalDeportivos.setFocusable(false);
        panelEstadisticas.add(txtTotalDeportivos);

        // Religiosos
        JLabel lblReligiosos = crearLabel("Religiosos:", 380, 35, 100, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblReligiosos);
        txtReligiosos = crearTextField(460, 35, 70, 25);
        txtReligiosos.setEditable(false);
        txtReligiosos.setFocusable(false);
        panelEstadisticas.add(txtReligiosos);

        JLabel lblTotalReligiosos = crearLabel("Total L.", 540, 35, 60, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblTotalReligiosos);
        txtTotalReligiosos = crearTextField(600, 35, 120, 25);
        txtTotalReligiosos.setEditable(false);
        txtTotalReligiosos.setFocusable(false);
        panelEstadisticas.add(txtTotalReligiosos);

        // Musicales
        JLabel lblMusicales = crearLabel("Musicales:", 0, 80, 100, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblMusicales);
        txtMusicales = crearTextField(90, 80, 70, 25);
        txtMusicales.setEditable(false);
        txtMusicales.setFocusable(false);
        panelEstadisticas.add(txtMusicales);

        JLabel lblTotalMusicales = crearLabel("Total L.", 170, 80, 60, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblTotalMusicales);
        txtTotalMusicales = crearTextField(230, 80, 120, 25);
        txtTotalMusicales.setEditable(false);
        txtTotalMusicales.setFocusable(false);
        panelEstadisticas.add(txtTotalMusicales);

        JLabel lblTotalGeneral = crearLabel("Total General L.", 380, 80, 130, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblTotalGeneral);
        txtTotalGeneral = crearTextField(510, 80, 210, 25);
        txtTotalGeneral.setEditable(false);
        txtTotalGeneral.setFocusable(false);
        panelEstadisticas.add(txtTotalGeneral);

        JButton btnRegresar = crearBoton("Regresar", 720, 515, 160, 35);
        panelCentro.add(btnRegresar);

        btnCalcular.addActionListener(e -> calcularIngresos());
        btnRegresar.addActionListener(e -> {
            new FrmReportes().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    private void calcularIngresos() {
        Calendar desde = dateDesde.getCalendar();
        Calendar hasta = dateHasta.getCalendar();

        if (desde == null || hasta == null) {
            JOptionPane.showMessageDialog(this, "Favor seleccione ambas fechas.");
            return;
        }

        //normalización de fechas como siempre
        Calendar d = Calendar.getInstance();
        d.setTime(desde.getTime());
        d.set(Calendar.HOUR_OF_DAY, 0);
        d.set(Calendar.MINUTE, 0);
        d.set(Calendar.SECOND, 0);
        d.set(Calendar.MILLISECOND, 0);

        Calendar h = Calendar.getInstance();
        h.setTime(hasta.getTime());
        h.set(Calendar.HOUR_OF_DAY, 23);
        h.set(Calendar.MINUTE, 59);
        h.set(Calendar.SECOND, 59);
        h.set(Calendar.MILLISECOND, 999);

        if (h.before(d)) {
            JOptionPane.showMessageDialog(this, "Error: la fecha final no puede ser menor a la inicial.");
            return;
        }

        modeloEventos.setRowCount(0);

        ArrayList<Evento> eventos = ManejoEventos.getInstancia().listarEventosEntreFechas(d, h);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int cantidadDeportivos = 0;
        int cantidadReligiosos = 0;
        int cantidadMusicales = 0;

        double totalDeportivos = 0.0;
        double totalReligiosos = 0.0;
        double totalMusicales = 0.0;

        for (int i = 0; i < eventos.size(); i++) {
            Evento evt = eventos.get(i);

            String codigo = evt.getCodigo();
            String tipo = evt.getTipo();
            String titulo = evt.getTitulo();
            String fecha = sdf.format(evt.getFechaRealizar().getTime());

            double importe = evt.getCancelado() ? evt.getMulta() : evt.getMontoRenta();
            String importeTexto = String.format("L.%.2f", importe);

            modeloEventos.addRow(new Object[]{codigo, tipo, titulo, fecha, importeTexto});

            if ("Deportivo".equalsIgnoreCase(tipo)) {
                cantidadDeportivos++;
                totalDeportivos += importe;
            } else if ("Religioso".equalsIgnoreCase(tipo)) {
                cantidadReligiosos++;
                totalReligiosos += importe;
            } else if ("Musical".equalsIgnoreCase(tipo)) {
                cantidadMusicales++;
                totalMusicales += importe;
            }
        }

        txtDeportivos.setText(String.valueOf(cantidadDeportivos));
        txtReligiosos.setText(String.valueOf(cantidadReligiosos));
        txtMusicales.setText(String.valueOf(cantidadMusicales));

        txtTotalDeportivos.setText(String.format("L.%.2f", totalDeportivos));
        txtTotalReligiosos.setText(String.format("L.%.2f", totalReligiosos));
        txtTotalMusicales.setText(String.format("L.%.2f", totalMusicales));

        double totalGeneral = totalDeportivos + totalReligiosos + totalMusicales;
        txtTotalGeneral.setText(String.format("L.%.2f", totalGeneral));
    }

    public static void main(String[] args) {
        new FrmIngresoPorFecha().setVisible(true);
    }
}
