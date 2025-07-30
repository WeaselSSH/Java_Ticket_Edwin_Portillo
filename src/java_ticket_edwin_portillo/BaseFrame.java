package java_ticket_edwin_portillo;
import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame {
    public BaseFrame(String titulo, int width, int height) {
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    abstract void initComponents();
}
