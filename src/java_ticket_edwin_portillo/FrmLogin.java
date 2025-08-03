package java_ticket_edwin_portillo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class FrmLogin extends BaseFrame {

    ManejoUsuarios manejoUsuarios = new ManejoUsuarios();

    public FrmLogin() {
        super("Inicio de Sesión", 400, 260);
    }

    @Override
    protected void initComponents() {
        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        //resto de paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 40));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelSur = new JPanel(null);
        panelSur.setPreferredSize(new Dimension(0, 90));
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de UI
        JLabel lblTitulo = new JLabel("INICIO DE SESION");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));
        panelNorte.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setFont(lblUsuario.getFont().deriveFont(14f));
        lblUsuario.setBounds(40, 25, 150, 25);  // x, y, ancho, alto
        panelCentro.add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(190, 29, 150, 20);
        panelCentro.add(txtUsuario);

        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setFont(lblUsuario.getFont().deriveFont(14f));
        lblContrasenia.setBounds(40, 60, 150, 25);
        panelCentro.add(lblContrasenia);

        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(140, 65, 200, 20);
        panelCentro.add(txtContrasenia);

        JButton btnInicio = new JButton("Iniciar Sesión");
        btnInicio.setBounds(40, 20, 140, 35);
        panelSur.add(btnInicio);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 20, 140, 35);
        panelSur.add(btnSalir);

        //Acciones
        btnInicio.addActionListener(e -> {
            String contrasenia = new String(txtContrasenia.getPassword());
            String NombreUsuario = txtUsuario.getText();

            if (manejoUsuarios.iniciarSesion(NombreUsuario, contrasenia)) {
                Usuario usuario = manejoUsuarios.buscarUsuario(NombreUsuario);

                switch (usuario.getRol().toLowerCase()) {
                    case "administrador":
                        FrmMenuAdmin ma = new FrmMenuAdmin();
                        ma.setVisible(true);
                        this.dispose();
                        break;
                    case "contenido":
                        FrmMenuContenido mc = new FrmMenuContenido();
                        mc.setVisible(true);
                        this.dispose();
                        break;
                    case "limitado":
                        FrmMenuLimitado ml = new FrmMenuLimitado();
                        ml.setVisible(true);
                        this.dispose();
                        break;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos.");
            }
        });

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        FrmLogin login = new FrmLogin();
        login.setVisible(true);
    }
}
