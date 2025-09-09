import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class TelaCadastroLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;

    // Arquivo de usuários
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    public TelaCadastroLogin() {
        setTitle("Cadastro de Usuário");
        setSize(350, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Novo Usuário:"));
        txtUsuario = new JTextField();
        painel.add(txtUsuario);

        painel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        painel.add(txtSenha);

        btnCadastrar = new JButton("Cadastrar");
        painel.add(btnCadastrar);

        // ação do botão Cadastrar
        btnCadastrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (usuario.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            if (usuarioExiste(usuario)) {
                JOptionPane.showMessageDialog(this, "Usuário já existe!");
            } else {
                salvarUsuario(usuario, senha);
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                dispose(); // fecha a tela
            }
        });

        add(painel);
    }

    // Verifica se já existe usuário no arquivo
    private boolean usuarioExiste(String usuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(usuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // se o arquivo não existir ainda, ignora
        }
        return false;
    }

    // Salva novo usuário no arquivo
    private void salvarUsuario(String usuario, String senha) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
            bw.write(usuario + ";" + senha);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar usuário!");
        }
    }

    // Valida login consultando o arquivo
    public static boolean validarLogin(String usuario, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(usuario) && partes[1].equals(senha)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // se não conseguir ler o arquivo, não valida
        }
        return false;
    }
}
