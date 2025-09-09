import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnCancelar, btnCadastrar;

    public TelaLogin() {
        setTitle("Login do Sistema");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        painel.add(txtUsuario);

        painel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        painel.add(txtSenha);

        btnEntrar = new JButton("Entrar");
        btnCancelar = new JButton("Cancelar");
        btnCadastrar = new JButton("Cadastrar");

        painel.add(btnEntrar);
        painel.add(btnCancelar);
        painel.add(btnCadastrar);

        // botão entrar
        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (TelaCadastroLogin.validarLogin(usuario, senha)) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                // abre a tela principal
                TelaPrincipal principal = new TelaPrincipal(usuario);
                principal.setVisible(true);
                this.dispose(); // fecha a tela de login
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!");
            }
        });


        // botão cancelar
        btnCancelar.addActionListener(e -> System.exit(0));

        // botão cadastrar
        btnCadastrar.addActionListener(e -> {
            new TelaCadastroLogin().setVisible(true);
        });

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
