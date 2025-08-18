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

public class FrmEventosCancelados extends BaseFrame {

    private JTable tablaEventos;
    private DefaultTableModel modeloEventos;

    private JTextField txtDeportivos;
    private JTextField txtReligiosos;
    private JTextField txtMusicales;
    private JTextField txtTotalMulta;

    private final ManejoEventos manejoEventos = ManejoEventos.getInstancia();

    public FrmEventosCancelados() {
        super("Eventos Cancelados", 920, 600);
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

        //titulo
        JLabel lblTitulo = crearLabel("Eventos Cancelados", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        modeloEventos = new DefaultTableModel(
                new Object[]{"Código", "Tipo", "Título", "Fecha", "Multa"}, 0
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

        JLabel lblDeportivos = crearLabel("Deportivos:", 0, 35, 100, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblDeportivos);
        txtDeportivos = crearTextField(90, 35, 70, 25);
        txtDeportivos.setEditable(false);
        txtDeportivos.setFocusable(false);
        panelEstadisticas.add(txtDeportivos);

        JLabel lblReligiosos = crearLabel("Religiosos:", 180, 35, 100, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblReligiosos);
        txtReligiosos = crearTextField(260, 35, 70, 25);
        txtReligiosos.setEditable(false);
        txtReligiosos.setFocusable(false);
        panelEstadisticas.add(txtReligiosos);

        JLabel lblMusicales = crearLabel("Musicales:", 350, 35, 100, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblMusicales);
        txtMusicales = crearTextField(430, 35, 70, 25);
        txtMusicales.setEditable(false);
        txtMusicales.setFocusable(false);
        panelEstadisticas.add(txtMusicales);

        JLabel lblTotalMulta = crearLabel("Total Multa L.", 540, 35, 110, 25, Font.BOLD, 12f);
        panelEstadisticas.add(lblTotalMulta);
        txtTotalMulta = crearTextField(650, 35, 150, 25);
        txtTotalMulta.setEditable(false);
        txtTotalMulta.setFocusable(false);
        panelEstadisticas.add(txtTotalMulta);

        // botones
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

        ArrayList<Evento> eventosCancelados = manejoEventos.listarEventosCancelados();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int cantidadDeportivos = 0;
        int cantidadReligiosos = 0;
        int cantidadMusicales = 0;

        double totalMulta = 0.0;

        for (int i = 0; i < eventosCancelados.size(); i++) {
            Evento evento = eventosCancelados.get(i);

            String codigo = evento.getCodigo();
            String tipo = evento.getTipo();
            String titulo = evento.getTitulo();
            String fechaFormateada = sdf.format(evento.getFechaRealizar().getTime());
            String multaFormateada = String.format("L.%.2f", evento.getMulta());

            modeloEventos.addRow(new Object[]{codigo, tipo, titulo, fechaFormateada, multaFormateada});

            if ("Deportivo".equalsIgnoreCase(tipo)) {
                cantidadDeportivos++;
            } else if ("Religioso".equalsIgnoreCase(tipo)) {
                cantidadReligiosos++;
            } else if ("Musical".equalsIgnoreCase(tipo)) {
                cantidadMusicales++;
            }

            totalMulta += evento.getMulta();
        }

        if (txtDeportivos != null) {
            txtDeportivos.setText(String.valueOf(cantidadDeportivos));
        }
        if (txtReligiosos != null) {
            txtReligiosos.setText(String.valueOf(cantidadReligiosos));
        }
        if (txtMusicales != null) {
            txtMusicales.setText(String.valueOf(cantidadMusicales));
        }
        if (txtTotalMulta != null) {
            txtTotalMulta.setText(String.format("L.%.2f", totalMulta));
        }
    }

    public static void main(String[] args) {
        new FrmEventosCancelados().setVisible(true);
    }
}
