package java_ticket_edwin_portillo;

import java.awt.*;
import javax.swing.*;

public class FrmMenuAdmin extends BaseFrame {

    public FrmMenuAdmin() {
        super("Menú Administrador", 440, 350);
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
        JLabel lblTitulo = crearLabel("Menú Principal - Administrador", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JButton btnEventos = crearBoton("Administrar Eventos", 95, 0, 245, 40);
        panelCentro.add(btnEventos);

        JButton btnUsuarios = crearBoton("Administrar Usuarios", 95, 60, 245, 40);
        panelCentro.add(btnUsuarios);

        JButton btnReportes = crearBoton("Ver Reportes", 95, 120, 245, 40);
        panelCentro.add(btnReportes);

        JButton btnSalir = crearBoton("Salir", 95, 180, 245, 40);
        panelCentro.add(btnSalir);

        //Acciones
        btnEventos.addActionListener(e -> {
            new FrmEventos().setVisible(true);
            this.dispose();
        });

        btnUsuarios.addActionListener(e -> {
            new FrmUsuarios().setVisible(true);
            this.dispose();
        });

        btnReportes.addActionListener(e -> {
        });

        btnSalir.addActionListener(e -> {
            new FrmLogin().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmMenuAdmin().setVisible(true);
    }
}
