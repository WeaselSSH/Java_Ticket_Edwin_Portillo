package java_ticket_edwin_portillo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FrmReportes extends BaseFrame {

    public FrmReportes() {
        super("Reportes", 700, 300);
    }

    @Override
    protected void initComponents() {
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

        //resto de UI
        JLabel lblTitulo = crearLabel("Reportes", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JButton btnRealizados = crearBoton("Eventos realizados", 60, 0, 245, 40);
        panelCentro.add(btnRealizados);

        JButton btnFuturos = crearBoton("Eventos futuros", 365, 0, 245, 40);
        panelCentro.add(btnFuturos);

        JButton btnCancelados = crearBoton("Eventos cancelados", 60, 60, 245, 40);
        panelCentro.add(btnCancelados);

        JButton btnIngresoFechas = crearBoton("Ingreso generado por fecha", 365, 60, 245, 40);
        panelCentro.add(btnIngresoFechas);

        JButton btnPerfil = crearBoton("Perfil del usuario", 60, 120, 245, 40);
        panelCentro.add(btnPerfil);

        JButton btnRegresar = crearBoton("Regresar", 365, 120, 245, 40);
        panelCentro.add(btnRegresar);

        //Acciones
        btnRealizados.addActionListener(e -> {
        });
        btnFuturos.addActionListener(e -> {
        });
        btnCancelados.addActionListener(e -> {
        });
        btnIngresoFechas.addActionListener(e -> {
        });
        btnPerfil.addActionListener(e -> {
        });

        btnRegresar.addActionListener(e -> {
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmReportes().setVisible(true);
    }
}
