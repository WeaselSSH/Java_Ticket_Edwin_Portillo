package java_ticket_edwin_portillo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Usuarios.Usuario;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class FrmEliminarUsuario extends BaseFrame {

    ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmEliminarUsuario() {
        super("Eliminar Usuario", 400, 250);
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
        JLabel lblTitulo = crearLabel("Eliminar Usuario", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblUsuario = crearLabel("Usuario a eliminar:", 40, 25, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblUsuario);

        JTextField txtUsuario = crearTextField(190, 29, 150, 20);
        panelCentro.add(txtUsuario);

        JButton btnEliminar = crearBoton("Eliminar usuario", 60, 75, 130, 35);
        panelCentro.add(btnEliminar);

        JButton btnRegresar = crearBoton("Regresar", 200, 75, 130, 35);
        panelCentro.add(btnRegresar);

        btnEliminar.addActionListener(e -> {
            Usuario usuarioLogeado = manejoUsuarios.getUsuarioLogeado();
            String usuarioEliminar = txtUsuario.getText().trim();

            if (usuarioEliminar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: el campo de usuario está vacío.");
                return;
            }

            if (usuarioEliminar.equalsIgnoreCase("admin")) {
                JOptionPane.showMessageDialog(this, "Error: el usuario 'admin' no puede eliminarse.");
                return;
            }

            if (manejoUsuarios.buscarUsuario(usuarioEliminar) == null) {
                JOptionPane.showMessageDialog(this, "Error: el usuario no existe.");
                return;
            }

            if (manejoUsuarios.eliminarUsuario(usuarioEliminar)) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");

                if (usuarioLogeado.getUsuario().equalsIgnoreCase(usuarioEliminar)) {
                    new FrmLogin().setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error: no se pudo eliminar el usuario.");
            }
        });

        btnRegresar.addActionListener(e -> {
            new FrmUsuarios().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmEliminarUsuario().setVisible(true);
    }
}
