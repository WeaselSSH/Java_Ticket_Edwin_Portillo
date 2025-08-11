package java_ticket_edwin_portillo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrmEventos extends BaseFrame {

    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmEventos() {
        super("Administración de Eventos", 420, 400);
    }

    @Override
    protected void initComponents() {
        // Paneles + fondo
        JPanel panelPrincipal = new JPanel(new BorderLayout()) {
            final Image bg = new ImageIcon(
                    getClass().getResource("/Imagenes/bg_dark_1920x1080.png")
            ).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // escalado al tamaño
            }
        };

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setOpaque(false);
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de ui
        JLabel lblTitulo = crearLabel("Administración de Eventos", 0, 0, 0, 0, Font.BOLD, 18f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JButton btnCrear = crearBoton("Crear Evento", 82, 0, 245, 40);
        panelCentro.add(btnCrear);

        JButton btnEditar = crearBoton("Editar Evento", 82, 60, 245, 40);
        panelCentro.add(btnEditar);

        JButton btnEliminar = crearBoton("Eliminar Evento", 82, 120, 245, 40);
        panelCentro.add(btnEliminar);

        JButton btnVer = crearBoton("Ver Evento", 82, 180, 245, 40);
        panelCentro.add(btnVer);

        JButton btnRegresar = crearBoton("Regresar", 82, 240, 245, 40);
        panelCentro.add(btnRegresar);

        // Acciones
        btnCrear.addActionListener(e -> {
            new FrmCrearEvento().setVisible(true);
            this.dispose();
        });

        btnEditar.addActionListener(e -> {
            new FrmEditarEvento().setVisible(true);
            this.dispose();
        });

        btnEliminar.addActionListener(e -> {
            new FrmEliminarEvento().setVisible(true);
            this.dispose();
        });

        btnVer.addActionListener(e -> {
            new FrmVerEvento().setVisible(true);
            this.dispose();
        });

        btnRegresar.addActionListener(e -> {
            switch (manejoUsuarios.getUsuarioLogeado().getRol().toLowerCase()) {
                case "administrador":
                    new FrmMenuAdmin().setVisible(true);
                    this.dispose();
                    break;
                case "contenido":
                    new FrmMenuContenido().setVisible(true);
                    this.dispose();
                    break;
                default:
                    new FrmMenuLimitado().setVisible(true);
                    this.dispose();
                    break;
            }
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmEventos().setVisible(true);
    }
}
