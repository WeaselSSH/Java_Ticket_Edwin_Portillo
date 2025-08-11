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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmVerEvento extends BaseFrame {

    ManejoEventos manejoEventos = ManejoEventos.getInstancia();

    public FrmVerEvento() {
        super("Ver evento", 380, 500);
    }

    @Override
    public void initComponents() {
        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout()) {
            final Image bg = new ImageIcon(
                    getClass().getResource("/Imagenes/bg_dark_1920x1080.png")
            ).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //resto de paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelNorte.setOpaque(false);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelCentro.setOpaque(false);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de ui
        JLabel lblTitulo = crearLabel("Ver Evento", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblEvento = crearLabel("Código del evento:", 40, 25, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblEvento);

        JTextField txtEvento = crearTextField(190, 29, 150, 20);
        panelCentro.add(txtEvento);

        JButton btnVer = crearBoton("Ver evento", 60, 350, 120, 30);
        panelCentro.add(btnVer);

        JButton btnRegresar = crearBoton("Regresar", 200, 350, 120, 30);
        panelCentro.add(btnRegresar);

        JTextArea txtResultado;
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtResultado.setBackground(Color.decode("#1A2332"));
        txtResultado.setForeground(Color.decode("#E6EDF7"));
        txtResultado.setCaretColor(Color.decode("#E6EDF7"));
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
