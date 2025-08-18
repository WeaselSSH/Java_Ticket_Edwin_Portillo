package java_ticket_edwin_portillo;

import Eventos.Evento;
import Usuarios.Usuario;
import Usuarios.Administrador;
import Usuarios.Contenido;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmPerfil extends BaseFrame {

    private JTextField txtUsuario;
    private JTextField txtNombre;
    private JTextField txtRol;
    private JTextField txtEdad;

    private JTable tablaEventos;
    private DefaultTableModel modeloEventos;

    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();
    private final ManejoEventos manejoEventos = ManejoEventos.getInstancia();

    public FrmPerfil() {
        super("Perfil del Usuario", 920, 600);
        cargarDatos();
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

        //resto de UI
        JLabel lblTitulo = crearLabel("Perfil del Usuario", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblUsuario = crearLabel("Usuario:", 35, 30, 120, 25, Font.BOLD, 14f);
        panelCentro.add(lblUsuario);
        txtUsuario = crearTextField(120, 30, 240, 25);
        txtUsuario.setEditable(false);
        txtUsuario.setFocusable(false);
        panelCentro.add(txtUsuario);

        JLabel lblNombre = crearLabel("Nombre:", 35, 70, 120, 25, Font.BOLD, 14f);
        panelCentro.add(lblNombre);
        txtNombre = crearTextField(120, 70, 240, 25);
        txtNombre.setEditable(false);
        txtNombre.setFocusable(false);
        panelCentro.add(txtNombre);

        JLabel lblRol = crearLabel("Rol:", 35, 110, 120, 25, Font.BOLD, 14f);
        panelCentro.add(lblRol);
        txtRol = crearTextField(120, 110, 240, 25);
        txtRol.setEditable(false);
        txtRol.setFocusable(false);
        panelCentro.add(txtRol);

        JLabel lblEdad = crearLabel("Edad:", 35, 150, 120, 25, Font.BOLD, 14f);
        panelCentro.add(lblEdad);
        txtEdad = crearTextField(120, 150, 240, 25);
        txtEdad.setEditable(false);
        txtEdad.setFocusable(false);
        panelCentro.add(txtEdad);

        JLabel lblEventosCreados = crearLabel("Eventos creados", 420, 0, 300, 25, Font.BOLD, 16f);
        panelCentro.add(lblEventosCreados);

        modeloEventos = new DefaultTableModel(
                new Object[]{"Código", "Tipo", "Título", "Monto"}, 0
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
        spEventos.setBounds(420, 30, 455, 400);
        panelCentro.add(spEventos);

        // Botón regresar
        JButton btnRegresar = crearBoton("Regresar", 715, 455, 160, 35);
        panelCentro.add(btnRegresar);

        btnRegresar.addActionListener(e -> {
            new FrmReportes().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    private void cargarDatos() {
        Usuario u = manejoUsuarios.getUsuarioLogeado();

        txtUsuario.setText(u.getUsuario());
        txtNombre.setText(u.getNombre());
        txtRol.setText(u.getRol());
        txtEdad.setText(Integer.toString(u.getEdad()));

        modeloEventos.setRowCount(0);

        ArrayList<String> codigos = new ArrayList<>();
        if (u instanceof Administrador) {
            codigos = ((Administrador) u).getEventosCreados();
        } else if (u instanceof Contenido) {
            codigos = ((Contenido) u).getEventosCreados();
        }

        for (int i = 0; i < codigos.size(); i++) {
            String codigo = codigos.get(i);
            if (codigo == null) {
                continue;
            }
            Evento evt = manejoEventos.buscarEvento(codigo);
            if (evt == null) {
                continue;
            }

            String tipo = evt.getTipo();
            String titulo = evt.getTitulo();
            String monto = String.format("L.%.2f", evt.getMontoRenta());

            modeloEventos.addRow(new Object[]{codigo, tipo, titulo, monto});
        }
    }

    public static void main(String[] args) {
        new FrmPerfil().setVisible(true);
    }
}
