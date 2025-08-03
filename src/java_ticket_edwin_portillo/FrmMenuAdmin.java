package java_ticket_edwin_portillo;

import java.awt.*;
import javax.swing.*;

public class FrmMenuAdmin extends BaseFrame {

    public FrmMenuAdmin() {
        super("Menú Administrador", 420, 360);
    }

    @Override
    protected void initComponents() {
        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        //resto de paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de UI
        JLabel lblTitulo = crearLabel("MENU PRINCIPAL ADMINISTRADOR", 0, 0, 0, 0, Font.BOLD, 18f);
        panelNorte.add(lblTitulo);

        JButton btnEventos = crearBoton("Administrar Eventos", 82, 0, 245, 40);
        panelCentro.add(btnEventos);

        JButton btnUsuarios = crearBoton("Administrar Usuarios", 82, 60, 245, 40);
        panelCentro.add(btnUsuarios);

        JButton btnReportes = crearBoton("Ver Reportes", 82, 120, 245, 40);
        panelCentro.add(btnReportes);

        JButton btnSalir = crearBoton("Salir", 82, 180, 245, 40);
        panelCentro.add(btnSalir);

        //Acciones
        btnEventos.addActionListener(e -> {
            FrmEventos evt = new FrmEventos();
            evt.setVisible(true);
            this.dispose();
        });

        btnUsuarios.addActionListener(e -> {
        });

        btnReportes.addActionListener(e -> {
        });

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        FrmMenuAdmin mp = new FrmMenuAdmin();
        mp.setVisible(true);
    }
}
