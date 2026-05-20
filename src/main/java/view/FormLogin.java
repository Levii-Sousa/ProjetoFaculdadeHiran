 package view;

import dao.ConexaoDAO;
import model.UsuarioDTO;
import javax.swing.*;
import java.awt.*;

// Tela principal do sistema — contém abas de Login e Cadastro
public class FormLogin extends JFrame {

    // Componentes da aba Login
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    // Componentes da aba Cadastro
    private JTextField txtNovoUsuario;
    private JPasswordField txtNovaSenha;
    private JButton btnSalvar;

    public FormLogin() {
        configurarJanela();
        inicializarComponentes();
    }

    // Configurações básicas da janela
    private void configurarJanela() {
        setTitle("Acesso ao Sistema - Levi Software");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }

    // Criação das abas e componentes
    private void inicializarComponentes() {
        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(new Color(30, 0, 50));
        abas.setForeground(Color.WHITE);
        abas.setFont(new Font("Arial", Font.BOLD, 13));

        abas.addTab("Login", criarPainelLogin());
        abas.addTab("Cadastro", criarPainelCadastro());

        add(abas);
    }

    // Monta o painel da aba Login
    private JPanel criarPainelLogin() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("LOGIN", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(150, 0, 255));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        painel.add(lblTitulo, gbc);

        // Campo usuário
        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setForeground(Color.WHITE);
        painel.add(lblUsuario, gbc);
        txtUsuario = new JTextField(15);
        txtUsuario.setBackground(new Color(30, 0, 50));
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(Color.WHITE);
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        painel.add(txtUsuario, gbc);

        // Campo senha
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        painel.add(lblSenha, gbc);
        txtSenha = new JPasswordField(15);
        txtSenha.setBackground(new Color(30, 0, 50));
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        painel.add(txtSenha, gbc);

        // Botão entrar
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(150, 0, 255));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        painel.add(btnEntrar, gbc);
        btnEntrar.addActionListener(e -> acaoLogin());

        return painel;
    }

    // Monta o painel da aba Cadastro
    private JPanel criarPainelCadastro() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("CADASTRO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(150, 0, 255));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        painel.add(lblTitulo, gbc);

        // Campo novo usuário
        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        JLabel lblUsuario = new JLabel("Novo Usuário:");
        lblUsuario.setForeground(Color.WHITE);
        painel.add(lblUsuario, gbc);
        txtNovoUsuario = new JTextField(15);
        txtNovoUsuario.setBackground(new Color(30, 0, 50));
        txtNovoUsuario.setForeground(Color.WHITE);
        txtNovoUsuario.setCaretColor(Color.WHITE);
        txtNovoUsuario.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        painel.add(txtNovoUsuario, gbc);

        // Campo nova senha
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblSenha = new JLabel("Nova Senha:");
        lblSenha.setForeground(Color.WHITE);
        painel.add(lblSenha, gbc);
        txtNovaSenha = new JPasswordField(15);
        txtNovaSenha.setBackground(new Color(30, 0, 50));
        txtNovaSenha.setForeground(Color.WHITE);
        txtNovaSenha.setCaretColor(Color.WHITE);
        txtNovaSenha.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        painel.add(txtNovaSenha, gbc);

        // Botão salvar
        btnSalvar = new JButton("Salvar Cadastro");
        btnSalvar.setBackground(new Color(150, 0, 255));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        painel.add(btnSalvar, gbc);
        btnSalvar.addActionListener(e -> salvarDados());

        return painel;
    }

    // Lógica de autenticação
    private void acaoLogin() {
        String usuario = txtUsuario.getText().trim();
        String senha   = new String(txtSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
        objUsuarioDTO.setNome_usuario(usuario);
        objUsuarioDTO.setSenha_usuario(senha);

        ConexaoDAO objConexaoDAO = new ConexaoDAO();
        boolean autenticado = objConexaoDAO.autenticarUsuario(objUsuarioDTO);

        if (autenticado) {
            JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario + "!");
            new FormPrincipal().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos.",
                    "Acesso negado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lógica de cadastro com validações
    private void salvarDados() {
        String usuario = txtNovoUsuario.getText().trim();
        String senha   = new String(txtNovaSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        if (usuario.length() < 3) {
            JOptionPane.showMessageDialog(this, "Usuário deve ter pelo menos 3 caracteres.");
            return;
        }

        if (!senhaValida(senha)) {
            JOptionPane.showMessageDialog(this,
                    "Senha fraca! Use pelo menos 6 caracteres,\num número e uma letra.");
            return;
        }

        if (usuario.length() > 50 || senha.length() > 50) {
            JOptionPane.showMessageDialog(this, "Usuário ou senha muito longos (máx. 50 caracteres).");
            return;
        }

        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
        objUsuarioDTO.setNome_usuario(usuario);
        objUsuarioDTO.setSenha_usuario(senha);

        ConexaoDAO objConexaoDAO = new ConexaoDAO();
        objConexaoDAO.cadastrarUsuario(objUsuarioDTO);

        txtNovoUsuario.setText("");
        txtNovaSenha.setText("");
    }

    // Valida se a senha tem pelo menos 6 caracteres, uma letra e um número
    private boolean senhaValida(String senha) {
        if (senha.length() < 6) return false;
        boolean temLetra  = senha.matches(".*[a-zA-Z].*");
        boolean temNumero = senha.matches(".*[0-9].*");
        return temLetra && temNumero;
    }

    // Ponto de entrada da aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLogin().setVisible(true));
    }
}
