package java_ticket_edwin_portillo;

import java.awt.*;
import javax.swing.*;

public class FrmMenuLimitado extends BaseFrame {

    public FrmMenuLimitado() {
        super("Menú Limitado", 420, 300);
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
        JLabel lblTitulo = crearLabel("MENU USUARIO LIMITADO", 0, 0, 0, 0, Font.BOLD, 18f);
        panelNorte.add(lblTitulo);

        JButton btnVerEventos = crearBoton("Ver Eventos", 82, 0, 245, 40);
        panelCentro.add(btnVerEventos);

        JButton btnReportes = crearBoton("Ver Reportes", 82, 60, 245, 40);
        panelCentro.add(btnReportes);

        JButton btnSalir = crearBoton("Salir", 82, 120, 245, 40);
        panelCentro.add(btnSalir);

        // Acciones
        btnVerEventos.addActionListener(e -> {
        });

        btnReportes.addActionListener(e -> {
        });

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmMenuLimitado().setVisible(true);
    }
}
