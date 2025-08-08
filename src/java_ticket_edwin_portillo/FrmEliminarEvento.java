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
import Usuarios.Contenido;
import Usuarios.Administrador;
import Usuarios.Usuario;
import java.util.Calendar;

public class FrmEliminarEvento extends BaseFrame {

    ManejoEventos manejoEventos = new ManejoEventos();

    public FrmEliminarEvento() {
        super("Eliminar Evento", 400, 250);
    }

    @Override
    protected void initComponents() {

        //PANELES
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 40));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //UI GENERAL
        JLabel lblTitulo = crearLabel("ELIMINAR EVENTO", 0, 0, 300, 40, Font.BOLD, 20f);
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
            String idEvento = txtEvento.getText().trim();

            if (idEvento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: el campo de código está vacío.");
                return;
            }

            Evento evento = manejoEventos.buscarEvento(idEvento);

            if (evento == null || evento.getEliminado()) {
                JOptionPane.showMessageDialog(this, "Error: evento no encontrado o ya eliminado.");
                return;
            }

            //Con polimorfismo se verifica el tipo de usuario para acceder a su arraylist con ideventos
            if (ManejoUsuarios.usuarioLogeado instanceof Administrador) {
                Administrador admin = (Administrador) ManejoUsuarios.usuarioLogeado;
                if (!admin.getEventosCreados().contains(idEvento)) {
                    JOptionPane.showMessageDialog(this, "Error: solo el creador puede eliminar este evento.");
                    return;
                }
            } else {
                Contenido contenido = (Contenido) ManejoUsuarios.usuarioLogeado;
                if (!contenido.getEventosCreados().contains(idEvento)) {
                    JOptionPane.showMessageDialog(this, "Error: solo el creador puede eliminar este evento.");
                    return;
                }
            }

            if (evento.Realizado()) {
                JOptionPane.showMessageDialog(this, "Error: no se puede eliminar un evento ya realizado.");
                return;
            }

            Calendar hoy = Calendar.getInstance();
            Calendar fechaEvento = evento.getFechaRealizar();
            Long hoyMilis = hoy.getTimeInMillis();
            Long fechaEventoMilis = fechaEvento.getTimeInMillis();
            Long diferenciaTiempo = fechaEventoMilis - hoyMilis;

            if (diferenciaTiempo <= 86400000) {
                if (!evento.getTipo().equalsIgnoreCase("religioso")) {
                    double multa = evento.getMontoRenta() * 0.5;
                    evento.setMulta(multa);

                    String multa2Decimas = String.format("%.2f", multa);
                    JOptionPane.showMessageDialog(this, "Multa de L." + multa2Decimas
                            + " aplicada por cancelar con 1 día de anticipación.");
                }
            }

            evento.setCancelado();
            evento.setEliminado();
            JOptionPane.showMessageDialog(this, "Evento eliminado correctamente.");
        });

        // Acción de regresar
        btnRegresar.addActionListener(e -> {
            FrmEventos f = new FrmEventos();
            f.setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmEliminarEvento().setVisible(true);
    }
}
