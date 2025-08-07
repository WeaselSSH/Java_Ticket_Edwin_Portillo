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
        JLabel lblTitulo = crearLabel("ADMINISTRACIÓN DE EVENTOS", 0, 0, 0, 0, Font.BOLD, 18f);
        panelNorte.add(lblTitulo);

        JButton btnCrear = crearBoton("Crear Evento", 82, 0, 245, 40);
        panelCentro.add(btnCrear);

        JButton btnEditar = crearBoton("Editar Evento", 82, 60, 245, 40);
        panelCentro.add(btnEditar);

        JButton btnEliminar = crearBoton("Eliminar Evento", 82, 120, 245, 40);
        panelCentro.add(btnEliminar);

        JButton btnVer = crearBoton("Ver Evento", 82, 180, 245, 40);
        panelCentro.add(btnVer);

        JButton btnRegresar = crearBoton("Regresar", 82, 240, 245, 40);
        panelCentro.add(btnRegresar);

        // Acciones
        btnCrear.addActionListener(e -> {
            FrmCrearEvento ce = new FrmCrearEvento();
            ce.setVisible(true);
            this.dispose();
        });

        btnEditar.addActionListener(e -> {
        });

        btnEliminar.addActionListener(e -> {
            new FrmEliminarEvento().setVisible(true);
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
