package java_ticket_edwin_portillo;

import Tipos.TipoDeporte;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrmJugadores extends BaseFrame {

    private TipoDeporte deporte;
    private JTable tabla;
    private DefaultTableModel modelo;

    private boolean guardado = false;
    private String[] equipo1Res;
    private String[] equipo2Res;

    public FrmJugadores() {
        super("Jugadores", 680, 250);
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

        //resto de ui
        JLabel lblTitulo = crearLabel("Crear Evento", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        modelo = new DefaultTableModel(new Object[]{"Equipo", "J1"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int col) {
                return col > 0;
            }
        };

        tabla = new JTable(modelo);
        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(20, 20, 630, 54);
        panelCentro.add(sp);

        JButton btnGuardar = crearBoton("Guardar", 350, 100, 130, 30);
        panelCentro.add(btnGuardar);

        JButton btnCancelar = crearBoton("Cancelar", 500, 100, 130, 30);
        panelCentro.add(btnCancelar);

        btnGuardar.addActionListener(e -> {

            int cantidad = cantJugadores(deporte);

            String e1[] = new String[cantidad];
            String e2[] = new String[cantidad];

            for (int col = 1; col <= cantidad; col++) {
                Object c1 = modelo.getValueAt(0, col);
                Object c2 = modelo.getValueAt(1, col);

                String jug1 = (c1 == null) ? "" : c1.toString().trim();
                String jug2 = (c2 == null) ? "" : c2.toString().trim();

                if (jug1.isEmpty() || jug2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Error: cada equipo debe tener " + cantidad + " jugadores.");
                    return;
                }
                e1[col - 1] = jug1;
                e2[col - 1] = jug2;
            }

            equipo1Res = e1;
            equipo2Res = e2;
            guardado = true;
            this.dispose();
        });

        btnCancelar.addActionListener(e -> {
            guardado = false;
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public void configurar(TipoDeporte deporte, String nombreEquipo1, String nombreEquipo2,
            String jugadores1[], String jugadores2[]) {

        this.deporte = deporte;
        int cantidad = cantJugadores(deporte);

        String columnas[] = new String[cantidad + 1];
        columnas[0] = "Equipo";
        for (int i = 1; i <= cantidad; i++) {
            columnas[i] = "J" + i;
        }

        modelo.setDataVector(new Object[0][0], columnas);

        Object[] fila1 = new Object[cantidad + 1];
        Object[] fila2 = new Object[cantidad + 1];

        fila1[0] = (nombreEquipo1 == null || nombreEquipo1.isBlank()) ? "Equipo 1" : nombreEquipo1;
        fila2[0] = (nombreEquipo2 == null || nombreEquipo2.isBlank()) ? "Equipo 2" : nombreEquipo2;

        for (int i = 1; i <= cantidad; i++) {
            String v1 = (jugadores1 != null && jugadores1.length >= i && jugadores1[i - 1] != null)
                    ? jugadores1[i - 1] : "";
            String v2 = (jugadores2 != null && jugadores2.length >= i && jugadores2[i - 1] != null)
                    ? jugadores2[i - 1] : "";
            fila1[i] = v1;
            fila2[i] = v2;
        }

        modelo.addRow(fila1);
        modelo.addRow(fila2);
    }

    public boolean guardado() {
        return guardado;
    }

    public String[] getResultadoEquipo1() {
        return equipo1Res;
    }

    public String[] getResultadoEquipo2() {
        return equipo2Res;
    }

    private int cantJugadores(TipoDeporte d) {
        if (d == null) {
            return 1;
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
                return 1;
        }
    }

    //para testear solamente
    public static void main(String[] args) {
        FrmJugadores j = new FrmJugadores();
        j.setVisible(true);
        j.configurar(Tipos.TipoDeporte.FUTBOL, "Rojo", "Azul", null, null);
    }
}
