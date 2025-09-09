import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(String usuario) {
        setTitle("Sistema - Tela Principal");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblBemVindo = new JLabel("Bem-vindo, " + usuario + "!", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 18));

        add(lblBemVindo);
    }
}
