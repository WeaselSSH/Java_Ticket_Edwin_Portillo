package java_ticket_edwin_portillo;

import java.awt.*;
import javax.swing.*;

public class FrmMenuContenido extends BaseFrame {

    public FrmMenuContenido() {
        super("Menú Contenido", 420, 300);
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

        //resto de ui
        JLabel lblTitulo = new JLabel("MENU USUARIO CONTENIDO");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        panelNorte.add(lblTitulo);

        JButton btnEventos = new JButton("Administrar Eventos");
        btnEventos.setBounds(82, 0, 245, 40);
        panelCentro.add(btnEventos);

        JButton btnReportes = new JButton("Ver Reportes");
        btnReportes.setBounds(82, 60, 245, 40);
        panelCentro.add(btnReportes);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(82, 120, 245, 40);
        panelCentro.add(btnSalir);

        // Acciones
        btnEventos.addActionListener(e -> {
        });

        btnReportes.addActionListener(e -> {
        });

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmMenuContenido().setVisible(true);
    }
}
