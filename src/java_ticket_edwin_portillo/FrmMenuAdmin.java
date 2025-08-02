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
        JLabel lblTitulo = new JLabel("MENU PRINCIPAL ADMINISTRADOR");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        panelNorte.add(lblTitulo);

        JButton btnEventos = new JButton("Administrar Eventos");
        btnEventos.setBounds(82, 0, 245, 40);
        panelCentro.add(btnEventos);

        JButton btnUsuarios = new JButton("Administrar Usuarios");
        btnUsuarios.setBounds(82, 60, 245, 40);
        panelCentro.add(btnUsuarios);

        JButton btnReportes = new JButton("Ver Reportes");
        btnReportes.setBounds(82, 120, 245, 40);
        panelCentro.add(btnReportes);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(82, 180, 245, 40);
        panelCentro.add(btnSalir);
        
        //Acciones
        btnEventos.addActionListener(e -> {
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
