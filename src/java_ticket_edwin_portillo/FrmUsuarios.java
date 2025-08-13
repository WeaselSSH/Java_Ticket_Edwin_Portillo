package java_ticket_edwin_portillo;

import java.awt.*;
import javax.swing.*;

public class FrmUsuarios extends BaseFrame {

    public FrmUsuarios() {
        super("Administración de Usuarios", 420, 350);
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
        JLabel lblTitulo = crearLabel("Administración de Usuarios", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JButton btnCrear = crearBoton("Crear Usuario", 82, 0, 245, 40);
        panelCentro.add(btnCrear);

        JButton btnEditar = crearBoton("Editar Usuario", 82, 60, 245, 40);
        panelCentro.add(btnEditar);

        JButton btnEliminar = crearBoton("Eliminar Usuario", 82, 120, 245, 40);
        panelCentro.add(btnEliminar);

        JButton btnRegresar = crearBoton("Regresar", 82, 180, 245, 40);
        panelCentro.add(btnRegresar);

        //acciones
        btnCrear.addActionListener(e -> {
            FrmCrearUsuario cu = new FrmCrearUsuario();
            cu.setVisible(true);
            this.dispose();
        });

        btnEditar.addActionListener(e -> {
            new FrmEditarUsuario().setVisible(true);
            this.dispose();
        });

        btnEliminar.addActionListener(e -> {
            new FrmEliminarUsuario().setVisible(true);
            this.dispose();
        });

        btnRegresar.addActionListener(e -> {
            FrmMenuAdmin ma = new FrmMenuAdmin();
            ma.setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmUsuarios().setVisible(true);
    }
}
