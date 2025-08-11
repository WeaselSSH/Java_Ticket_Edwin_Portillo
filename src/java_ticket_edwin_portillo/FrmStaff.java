package java_ticket_edwin_portillo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.ArrayList;

public class FrmStaff extends BaseFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private boolean guardado = false;
    private String[] staffFinal;

    public FrmStaff() {
        super("Staff Técnico", 680, 280);
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
        JLabel lblTitulo = crearLabel("Crear Evento", 0, 0, 0, 0, Font.BOLD, 21f);
        lblTitulo.setForeground(java.awt.Color.decode("#5FA4F8"));
        panelNorte.add(lblTitulo);

        modelo = new DefaultTableModel(new Object[]{"Número", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int col) {
                return col == 1;
            }
        };

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(20, 20, 630, 90);
        panelCentro.add(sp);

        JButton btnAgregar = crearBoton("Agregar fila", 20, 130, 130, 30);
        panelCentro.add(btnAgregar);

        JButton btnEliminar = crearBoton("Eliminar fila", 170, 130, 130, 30);
        panelCentro.add(btnEliminar);

        JButton btnGuardar = crearBoton("Guardar", 370, 130, 130, 30);
        panelCentro.add(btnGuardar);

        JButton btnCancelar = crearBoton("Cancelar", 520, 130, 130, 30);
        panelCentro.add(btnCancelar);

        btnAgregar.addActionListener(e -> {
            modelo.addRow(new Object[]{modelo.getRowCount() + 1, ""});
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fila.");
                return;
            }
            modelo.removeRow(fila);
            renumerar();
        });

        btnGuardar.addActionListener(e -> {
            if (tabla.isEditing()) {
                TableCellEditor ed = tabla.getCellEditor();
                if (ed != null) {
                    ed.stopCellEditing();
                }
            }

            ArrayList<String> lista = new ArrayList<>();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object v = modelo.getValueAt(i, 1);
                String nombre = (v == null) ? "" : v.toString().trim();
                if (!nombre.isEmpty()) {
                    lista.add(nombre);
                }
            }

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: ingrese al menos un integrante del staff.");
                return;
            }

            staffFinal = lista.toArray(new String[0]);
            guardado = true;
            this.dispose();
        });

        btnCancelar.addActionListener(e -> {
            guardado = false;
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    private void renumerar() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(i + 1, i, 0);
        }
    }

    public void configurar(String[] staffInicial) {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        if (staffInicial != null && staffInicial.length > 0) {
            int idx = 1;
            for (String s : staffInicial) {
                String nombre = (s == null) ? "" : s.trim();
                modelo.addRow(new Object[]{idx++, nombre});
            }
        } else {
            modelo.addRow(new Object[]{1, ""});
        }
    }

    public boolean guardado() {
        return guardado;
    }

    public String[] getResultadoStaff() {
        return staffFinal;
    }

    public static void main(String[] args) {
        FrmStaff f = new FrmStaff();
        f.setVisible(true);
        f.configurar(new String[]{"Lucia", "Robero", "Juan"});
    }
}
