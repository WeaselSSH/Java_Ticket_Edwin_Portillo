package java_ticket_edwin_portillo;

import java.awt.*;
import javax.swing.*;

public class FrmEventos extends BaseFrame {

    public FrmEventos() {
        super("Administración de Eventos", 420, 400);
    }

    @Override
    protected void initComponents() {
        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        //otros paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de ui
        JLabel lblTitulo = new JLabel("ADMINISTRACIÓN DE EVENTOS");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        panelNorte.add(lblTitulo);

        JButton btnCrear = new JButton("Crear Evento");
        btnCrear.setBounds(82, 0, 245, 40);
        panelCentro.add(btnCrear);

        JButton btnEditar = new JButton("Editar Evento");
        btnEditar.setBounds(82, 60, 245, 40);
        panelCentro.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Evento");
        btnEliminar.setBounds(82, 120, 245, 40);
        panelCentro.add(btnEliminar);

        JButton btnVer = new JButton("Ver Evento");
        btnVer.setBounds(82, 180, 245, 40);
        panelCentro.add(btnVer);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(82, 240, 245, 40);
        panelCentro.add(btnRegresar);

        // Acciones
        btnCrear.addActionListener(e -> {
        });

        btnEditar.addActionListener(e -> {
        });

        btnEliminar.addActionListener(e -> {
        });

        btnVer.addActionListener(e -> {
        });

        btnRegresar.addActionListener(e -> {
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmEventos().setVisible(true);
    }
}
