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

        setContentPane(panelPrincipal);

        JButton btnEliminar = crearBoton("Eliminar evento", 60, 75, 130, 35);
        panelCentro.add(btnEliminar);

        JButton btnRegresar = crearBoton("Regresar", 200, 75, 130, 35);
        panelCentro.add(btnRegresar);
        
        btnEliminar.addActionListener(e ->{
            String idEvento = txtEvento.getText();
            
            if (manejoEventos.eliminarEvento(idEvento) == true){
                JOptionPane.showMessageDialog(this, "Evento eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error: evento no encontrado. Verifique que el código ingresado"
                        + "sea correcto.");
            }
        });
    }
}
