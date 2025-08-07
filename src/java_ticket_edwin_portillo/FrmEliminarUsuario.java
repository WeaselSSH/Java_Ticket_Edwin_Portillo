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

public class FrmEliminarUsuario extends BaseFrame {

    ManejoUsuarios manejoUsuarios = new ManejoUsuarios();

    public FrmEliminarUsuario() {
        super("Eliminar Usuario", 400, 250);
    }

    @Override
    protected void initComponents() {

        //PANELES
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 40));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //UI GENERAL
        JLabel lblTitulo = crearLabel("ELIMINAR USUARIO", 0, 0, 300, 40, Font.BOLD, 20f);
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

            boolean usuarioLogeado = (usuarioEliminar.equalsIgnoreCase(ManejoUsuarios.usuarioLogeado.getUsuario()));

            if (manejoUsuarios.eliminarUsuario(usuarioEliminar)) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");

                if (usuarioLogeado) {
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
