package java_ticket_edwin_portillo;

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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmCrearUsuario extends BaseFrame {

    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmCrearUsuario() {
        super("Crear Usuario", 450, 380);
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

        JPanel panelSur = new JPanel(null);
        panelSur.setPreferredSize(new Dimension(0, 80));
        panelSur.setOpaque(false);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        //resto de ui
        JLabel lblTitulo = crearLabel("Crear Usuario", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblRol = crearLabel("Rol:", 40, 10, 100, 25, Font.BOLD, 14f);
        panelCentro.add(lblRol);

        String roles[] = {"administrador", "contenido", "limitado"};
        JComboBox<String> cboRol = crearComboBox(roles, 150, 12, 200, 25);
        panelCentro.add(cboRol);

        JLabel lblNombre = crearLabel("Nombre Completo:", 40, 50, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblNombre);

        JTextField txtNombre = crearTextField(190, 52, 200, 22);
        panelCentro.add(txtNombre);

        JLabel lblUsuario = crearLabel("Username:", 40, 90, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblUsuario);

        JTextField txtUsuario = crearTextField(190, 92, 200, 22);
        panelCentro.add(txtUsuario);

        JLabel lblContrasenia = crearLabel("Contraseña:", 40, 130, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblContrasenia);

        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(190, 132, 200, 22);
        txtContrasenia.setBackground(Color.decode("#1A2332"));
        txtContrasenia.setForeground(Color.decode("#E6EDF7"));
        txtContrasenia.setCaretColor(Color.decode("#E6EDF7"));
        txtContrasenia.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        panelCentro.add(txtContrasenia);

        JLabel lblEdad = crearLabel("Edad:", 40, 170, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblEdad);

        JTextField txtEdad = crearTextField(190, 172, 50, 22);
        panelCentro.add(txtEdad);

        JButton btnCrear = crearBoton("Registrar", 40, 20, 160, 35);
        panelSur.add(btnCrear);

        JButton btnRegresar = crearBoton("Regresar", 230, 20, 160, 35);
        panelSur.add(btnRegresar);

        // Acciones
        btnCrear.addActionListener(e -> {
            String rol = (String) cboRol.getSelectedItem();
            String nombre = txtNombre.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String contrasenia = new String(txtContrasenia.getPassword()).trim();
            String edadTexto = txtEdad.getText().trim();
            int edad;

            if (nombre.isEmpty() || usuario.isEmpty() || contrasenia.isEmpty() || edadTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: uno de los campos está vacío.");
                return;
            }

            try {
                edad = Integer.parseInt(edadTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: ingrese un valor entero para el campo edad.");
                return;
            }

            if (edad <= 0) {
                JOptionPane.showMessageDialog(this, "Error: favor ingrese una edad válida");
                return;
            }

            if (!manejoUsuarios.contraseniaValida(contrasenia)) {
                JOptionPane.showMessageDialog(this, "Error: Contraseña inválida. Debe tener mínimo 8 caracteres"
                        + ", una letra, un número y un símbolo.");
                return;
            }

            if (manejoUsuarios.buscarUsuario(usuario) != null) {
                JOptionPane.showMessageDialog(this, "Error: nombre de usuario ya existente.");
                return;
            }

            boolean registroExitoso = manejoUsuarios.registrarUsuario(rol, nombre, usuario, contrasenia, edad);
            if (!registroExitoso) {
                JOptionPane.showMessageDialog(this, "No se pudo registrar (rol inválido o datos incorrectos).");
                return;
            }

            JOptionPane.showMessageDialog(this, "Usuario creado exitosamente.");

            txtNombre.setText("");
            txtUsuario.setText("");
            txtContrasenia.setText("");
            txtEdad.setText("");
        });

        btnRegresar.addActionListener(e -> {
            FrmUsuarios u = new FrmUsuarios();
            u.setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmCrearUsuario().setVisible(true);
    }
}
