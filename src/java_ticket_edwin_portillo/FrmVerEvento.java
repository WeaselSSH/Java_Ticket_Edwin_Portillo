package java_ticket_edwin_portillo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Eventos.Evento;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmVerEvento extends BaseFrame {

    ManejoEventos manejoEventos = new ManejoEventos();

    public FrmVerEvento() {
        super("Ver evento", 380, 480);
    }

    @Override
    public void initComponents() {
        //PANELES
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 40));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //UI GENERAL
        JLabel lblTitulo = crearLabel("VER EVENTO", 0, 0, 300, 40, Font.BOLD, 20f);
        panelNorte.add(lblTitulo);

        JLabel lblEvento = crearLabel("Código del evento:", 40, 25, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblEvento);

        JTextField txtEvento = crearTextField(190, 29, 150, 20);
        panelCentro.add(txtEvento);

        JButton btnVer = crearBoton("Ver evento", 60, 345, 120, 30);
        panelCentro.add(btnVer);

        JButton btnRegresar = crearBoton("Regresar", 200, 345, 120, 30);
        panelCentro.add(btnRegresar);

        JTextArea txtResultado;
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 75, 320, 250);
        panelCentro.add(scroll);

        //Acciones
        btnVer.addActionListener(e -> {
            String eventoVerTexto = txtEvento.getText().trim();

            if (eventoVerTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: El campo está vacío.");
                return;
            }

            Evento eventoVer = manejoEventos.buscarEvento(eventoVerTexto);

            if (eventoVer == null) {
                JOptionPane.showMessageDialog(this, "Error: este evento no existe.");
                return;
            }

            txtResultado.setText(eventoVer.toString());
        });

        btnRegresar.addActionListener(e -> {
            new FrmEventos().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmVerEvento().setVisible(true);
    }
}
