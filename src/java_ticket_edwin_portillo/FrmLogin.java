package java_ticket_edwin_portillo;

import Usuarios.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class FrmLogin extends BaseFrame {

    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmLogin() {
        super("Inicio de Sesión", 400, 260);
    }

    @Override
    protected void initComponents() {
        // Paneles
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

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 12));
        panelNorte.setPreferredSize(new Dimension(0, 50));
        panelNorte.setOpaque(false);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelSur = new JPanel(null);
        panelSur.setPreferredSize(new Dimension(0, 80));
        panelSur.setOpaque(false);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        JLabel lblTitulo = crearLabel("Inicio de Sesión", 0, 0, 0, 0, Font.BOLD, 24f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblUsuario = crearLabel("Nombre de Usuario:", 35, 25, 150, 25, Font.BOLD, 13f);
        panelCentro.add(lblUsuario);

        JTextField txtUsuario = crearTextField(200, 29, 130, 20);
        panelCentro.add(txtUsuario);

        JLabel lblContrasenia = crearLabel("Contraseña:", 35, 60, 150, 25, Font.BOLD, 13f);
        panelCentro.add(lblContrasenia);

        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(200, 65, 130, 20);
        txtContrasenia.setBackground(Color.decode("#1A2332"));
        txtContrasenia.setForeground(Color.decode("#E6EDF7"));
        txtContrasenia.setCaretColor(Color.decode("#E6EDF7"));
        txtContrasenia.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        panelCentro.add(txtContrasenia);

        JButton btnInicio = crearBoton("Iniciar Sesión", 40, 20, 140, 35);
        panelSur.add(btnInicio);

        JButton btnSalir = crearBoton("Salir", 200, 20, 140, 35);
        btnSalir.setBackground(Color.decode("#2F3846"));
        btnSalir.setForeground(Color.decode("#CDD6E3"));
        btnSalir.setBorder(BorderFactory.createLineBorder(Color.decode("#3C4758")));
        panelSur.add(btnSalir);

        btnInicio.addActionListener(e -> {
            String pass = new String(txtContrasenia.getPassword());
            String user = txtUsuario.getText();

            if (user.trim().isEmpty() || pass.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: uno de los campos se encuentra vacío.");
                return;
            }

            if (manejoUsuarios.iniciarSesion(user, pass)) {
                Usuario u = manejoUsuarios.getUsuarioLogeado();
                switch (u.getRol().toLowerCase()) {
                    case "administrador":
                        new FrmMenuAdmin().setVisible(true);
                        break;
                    case "contenido":
                        new FrmMenuContenido().setVisible(true);
                        break;
                    case "limitado":
                        new FrmMenuLimitado().setVisible(true);
                        break;
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Usuario o contraseña incorrectos.");
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmLogin().setVisible(true);
    }
}
