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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmEditarUsuario extends BaseFrame {

    ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmEditarUsuario() {
        super("Editar Usuario", 465, 340);
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
        JLabel lblTitulo = crearLabel("Editar Usuario", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblUsuario = crearLabel("Nombre de usuario:", 25, 0, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblUsuario);

        JTextField txtUsuario = crearTextField(180, 0, 140, 25);
        panelCentro.add(txtUsuario);

        JButton btnCargar = crearBoton("Cargar", 330, 0, 90, 25);
        panelCentro.add(btnCargar);

        JLabel lblNombre = crearLabel("Nombre completo:", 25, 45, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblNombre);

        JTextField txtNombre = crearTextField(180, 45, 240, 25);
        panelCentro.add(txtNombre);

        JLabel lblContrasenia = crearLabel("Contraseña:", 25, 95, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblContrasenia);

        JLabel lblEdad = crearLabel("Edad:", 25, 135, 140, 25, Font.BOLD, 14f);
        panelCentro.add(lblEdad);

        JTextField txtEdad = crearTextField(180, 135, 80, 25);
        panelCentro.add(txtEdad);

        JButton btnGuardar = crearBoton("Guardar", 95, 185, 120, 35);
        panelCentro.add(btnGuardar);

        JButton btnRegresar = crearBoton("Regresar", 245, 185, 120, 35);
        panelCentro.add(btnRegresar);

        JTextField txtContrasenia = new JTextField();
        txtContrasenia.setBounds(180, 95, 240, 25);
        txtContrasenia.setBackground(Color.decode("#1A2332"));
        txtContrasenia.setForeground(Color.decode("#E6EDF7"));
        txtContrasenia.setCaretColor(Color.decode("#E6EDF7"));
        txtContrasenia.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        panelCentro.add(txtContrasenia);

        btnRegresar.addActionListener(e -> {
            new FrmUsuarios().setVisible(true);
            this.dispose();
        });

        btnCargar.addActionListener(e -> {
            String usuario = txtUsuario.getText();

            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: uno de los campos está vacío.");
                return;
            }

            Usuario usuarioEditar = manejoUsuarios.buscarUsuario(usuario);

            if (usuarioEditar == null) {
                JOptionPane.showMessageDialog(this, "Error: este usuario no existe.");
                return;
            }

            if (usuarioEditar.getUsuario().equals("admin")) {
                JOptionPane.showMessageDialog(this, "Error: este usuario no se puede editar.");
                return;
            }

            txtNombre.setText(usuarioEditar.getNombre());
            txtContrasenia.setText(usuarioEditar.getContrasenia());
            txtEdad.setText(String.valueOf(usuarioEditar.getEdad()));
        });

        btnGuardar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String nombre = txtNombre.getText().trim();
            String contrasenia = txtContrasenia.getText().trim();
            String edadTexto = txtEdad.getText().trim();

            if (usuario.isEmpty() || nombre.isEmpty() || edadTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: uno de los campos está vacío.");
                return;
            }

            int edad;
            try {
                edad = Integer.parseInt(edadTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: ingrese un valor entero para la edad.");
                return;
            }

            boolean exitoso = manejoUsuarios.actualizarUsuario(usuario, nombre, contrasenia, edad);
            if (!exitoso) {
                JOptionPane.showMessageDialog(this, "Error: no se pudo actualizar "
                        + "(datos inválidos o usuario no editable).");
                return;
            }

            txtUsuario.setText("");
            txtContrasenia.setText("");
            txtEdad.setText("");
            txtNombre.setText("");
            JOptionPane.showMessageDialog(this, "Usuario editado exitosamente.");
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmEditarUsuario().setVisible(true);
    }
}
