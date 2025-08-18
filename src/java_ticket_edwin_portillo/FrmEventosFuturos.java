package java_ticket_edwin_portillo;

import Eventos.Evento;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmEventosFuturos extends BaseFrame {

    private JTable tablaEventos;
    private DefaultTableModel modeloEventos;

    private JTextField txtDeportivos;
    private JTextField txtTotalDeportivos;
    private JTextField txtReligiosos;
    private JTextField txtTotalReligiosos;
    private JTextField txtMusicales;
    private JTextField txtTotalMusicales;

    private final ManejoEventos manejoEventos = ManejoEventos.getInstancia();

    public FrmEventosFuturos() {
        super("Eventos Futuros", 920, 600);
        cargarDatos();
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
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelNorte.setOpaque(false);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de UI
        JLabel lblTitulo = crearLabel("Eventos Futuros", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        modeloEventos = new DefaultTableModel(
                new Object[]{"Código", "Tipo", "Título", "Fecha", "Monto"}, 0
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
        spEventos.setBounds(35, 0, 840, 320);
        panelCentro.add(spEventos);

        // panel estadísticas
        JPanel panelEstadisticas = new JPanel(null);
        panelEstadisticas.setOpaque(false);
        panelEstadisticas.setBounds(35, 350, 840, 115);
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

        JButton btnRegresar = crearBoton("Regresar", 720, 455, 160, 35);
        panelCentro.add(btnRegresar);

        btnRegresar.addActionListener(e -> {
            new FrmReportes().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    private void cargarDatos() {
        modeloEventos.setRowCount(0);

        ArrayList<Evento> eventosFuturos = manejoEventos.listarEventosFuturos();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        int cantidadDeportivos = 0;
        int cantidadReligiosos = 0;
        int cantidadMusicales = 0;

        double montoTotalDeportivos = 0.0;
        double montoTotalReligiosos = 0.0;
        double montoTotalMusicales = 0.0;

        for (int i = 0; i < eventosFuturos.size(); i++) {
            Evento evento = eventosFuturos.get(i);

            String codigo = evento.getCodigo();
            String tipo = evento.getTipo();
            String titulo = evento.getTitulo();
            String fechaFormateada = formatoFecha.format(evento.getFechaRealizar().getTime());
            String montoFormateado = String.format("L.%.2f", evento.getMontoRenta());

            modeloEventos.addRow(new Object[]{codigo, tipo, titulo, fechaFormateada, montoFormateado});

            if ("Deportivo".equalsIgnoreCase(tipo)) {
                cantidadDeportivos++;
                montoTotalDeportivos += evento.getMontoRenta();
            } else if ("Religioso".equalsIgnoreCase(tipo)) {
                cantidadReligiosos++;
                montoTotalReligiosos += evento.getMontoRenta();
            } else if ("Musical".equalsIgnoreCase(tipo)) {
                cantidadMusicales++;
                montoTotalMusicales += evento.getMontoRenta();
            }
        }

        txtDeportivos.setText(String.valueOf(cantidadDeportivos));
        txtReligiosos.setText(String.valueOf(cantidadReligiosos));
        txtMusicales.setText(String.valueOf(cantidadMusicales));

        txtTotalDeportivos.setText(String.format("L.%.2f", montoTotalDeportivos));
        txtTotalReligiosos.setText(String.format("L.%.2f", montoTotalReligiosos));
        txtTotalMusicales.setText(String.format("L.%.2f", montoTotalMusicales));
    }

    public static void main(String[] args) {
        new FrmEventosFuturos().setVisible(true);
    }
}
