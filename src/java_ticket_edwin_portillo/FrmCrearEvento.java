package java_ticket_edwin_portillo;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class FrmCrearEvento extends BaseFrame {

    public FrmCrearEvento() {
        super("Crear Evento", 500, 600);
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
        JLabel lblTitulo = new JLabel("CREAR EVENTO");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 20f));
        panelNorte.add(lblTitulo);

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmCrearEvento().setVisible(true);
    }
}
