package java_ticket_edwin_portillo;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class FrmEliminarEvento extends BaseFrame {

    public FrmEliminarEvento() {
        super("Eliminar Evento", 400, 240);
    }

    @Override
    protected void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelNorte = new JPanel();
        panelNorte.setPreferredSize(new Dimension(0, 50));
        panelNorte.add(crearLabel("ELIMINAR EVENTO", 0, 0, 0, 0, Font.BOLD, 18f));

        JPanel panelCentro = new JPanel(null);

        JLabel lblCodigo = crearLabel("Código del evento:", 30, 30, 140, 25, Font.PLAIN, 14f);
        panelCentro.add(lblCodigo);

        JTextField txtCodigo = crearTextField(180, 30, 160, 25);
        panelCentro.add(txtCodigo);

        JButton btnEliminar = crearBoton("Eliminar", 60, 90, 120, 35);
        JButton btnCancelar = crearBoton("Regresar", 200, 90, 120, 35);
        panelCentro.add(btnEliminar);
        panelCentro.add(btnCancelar);
    }

    public static void main(String[] args) {
        new FrmEliminarEvento().setVisible(true);
    }
}
