package java_ticket_edwin_portillo;

import java.awt.*;
import javax.swing.*;

public class FrmUsuarios extends BaseFrame {

    public FrmUsuarios() {
        super("Administración de Usuarios", 420, 350);
    }

    @Override
    protected void initComponents() {
        //paneles
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        
        //resto de UI
        JLabel lblTitulo = crearLabel("ADMINISTRACIÓN DE USUARIOS", 0, 0, 0, 0, Font.BOLD, 18f);
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
        });

        btnEliminar.addActionListener(e -> {
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
