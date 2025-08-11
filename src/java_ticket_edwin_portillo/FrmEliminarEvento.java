package java_ticket_edwin_portillo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Eventos.Evento;
import Usuarios.Administrador;
import Usuarios.Contenido;
import Usuarios.Usuario;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class FrmEliminarEvento extends BaseFrame {

    private final ManejoEventos manejoEventos = ManejoEventos.getInstancia();
    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmEliminarEvento() {
        super("Eliminar Evento", 400, 250);
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

        //resto de ui
        JLabel lblTitulo = crearLabel("Eliminar Evento", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        JLabel lblEvento = crearLabel("Código del evento:", 40, 25, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblEvento);

        JTextField txtEvento = crearTextField(190, 29, 150, 20);
        panelCentro.add(txtEvento);

        JButton btnEliminar = crearBoton("Eliminar evento", 60, 75, 130, 35);
        panelCentro.add(btnEliminar);

        JButton btnRegresar = crearBoton("Regresar", 200, 75, 130, 35);
        panelCentro.add(btnRegresar);

        // Acción de eliminar
        btnEliminar.addActionListener(e -> {
            Usuario usuarioLogeado = manejoUsuarios.getUsuarioLogeado();

            String codigoEvento = txtEvento.getText().trim().toUpperCase();

            if (codigoEvento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: el campo de código está vacío.");
                return;
            }

            Evento evento = manejoEventos.buscarEvento(codigoEvento);

            if (evento == null) {
                JOptionPane.showMessageDialog(this, "Error: evento no encontrado.");
                return;
            }

            if (evento.getCancelado()) {
                JOptionPane.showMessageDialog(this, "Error: este evento ya está cancelado.");
                return;
            }
            if (evento.getRealizado()) {
                JOptionPane.showMessageDialog(this, "Error: no se puede cancelar un evento ya realizado.");
                return;
            }

            boolean creador = false;
            switch (usuarioLogeado.getRol().toLowerCase()) {
                case "administrador":
                    creador = ((Administrador) usuarioLogeado).getEventosCreados().contains(codigoEvento);
                    break;
                case "contenido":
                    creador = ((Contenido) usuarioLogeado).getEventosCreados().contains(codigoEvento);
                    break;
            }

            if (!creador) {
                JOptionPane.showMessageDialog(this, "Error: solo el creador puede cancelar este evento.");
                return;
            }

            if (!manejoEventos.cancelarEvento(usuarioLogeado, codigoEvento)) {
                JOptionPane.showMessageDialog(this, "Error: no se pudo cancelar el evento.");
                return;
            }

            double multa = evento.getMulta();
            if (multa > 0) {
                JOptionPane.showMessageDialog(this,
                        "Evento cancelado.\nMulta aplicada: L." + String.format("%.2f", multa));
            } else {
                JOptionPane.showMessageDialog(this, "Evento cancelado correctamente.");
            }

            txtEvento.setText("");
        });

        btnRegresar.addActionListener(e -> {
            new FrmEventos().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmEliminarEvento().setVisible(true);
    }
}
