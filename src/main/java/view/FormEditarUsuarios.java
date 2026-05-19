package view;

import dao.ConexaoDAO;
import model.UsuarioDTO;
import javax.swing.*;
import java.awt.*;

// Tela de edição de usuário
public class FormEditarUsuarios extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private int idUsuario;

    public FormEditarUsuarios(int id, String login, String senha) {
        this.idUsuario = id;
        configurarJanela();
        inicializarComponentes(login, senha);
    }

    // Configurações básicas da janela
    private void configurarJanela() {
        setTitle("Editar Usuário - Levi Software");
        setSize(380, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }

    // Criação dos componentes da tela
    private void inicializarComponentes(String login, String senha) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("EDITAR USUÁRIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(150, 0, 255));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        // Campo usuário
        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setForeground(Color.WHITE);
        add(lblUsuario, gbc);
        txtUsuario = new JTextField(15);
        txtUsuario.setText(login);
        txtUsuario.setBackground(new Color(30, 0, 50));
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(Color.WHITE);
        txtUsuario.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        add(txtUsuario, gbc);

        // Campo senha
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha, gbc);
        txtSenha = new JPasswordField(15);
        txtSenha.setText(senha);
        txtSenha.setBackground(new Color(30, 0, 50));
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        add(txtSenha, gbc);

        // Botão salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(150, 0, 255));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        add(btnSalvar, gbc);
        btnSalvar.addActionListener(e -> salvarEdicao());

        // Botão cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(50, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 1; gbc.gridy = 3;
        add(btnCancelar, gbc);
        btnCancelar.addActionListener(e -> dispose());
    }

    // Salva a edição com validações
    private void salvarEdicao() {
        String usuario = txtUsuario.getText().trim();
        String senha   = new String(txtSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }
        if (usuario.length() < 3) {
            JOptionPane.showMessageDialog(this, "Usuário deve ter pelo menos 3 caracteres.");
            return;
        }
        if (senha.length() < 6) {
            JOptionPane.showMessageDialog(this, "Senha deve ter pelo menos 6 caracteres.");
            return;
        }
        if (usuario.contains(" ")) {
            JOptionPane.showMessageDialog(this, "O usuário não pode conter espaços.");
            return;
        }
        if (usuario.length() > 50 || senha.length() > 50) {
            JOptionPane.showMessageDialog(this, "Usuário ou senha muito longos (máx. 50 caracteres).");
            return;
        }

        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
        objUsuarioDTO.setId_usuario(idUsuario);
        objUsuarioDTO.setNome_usuario(usuario);
        objUsuarioDTO.setSenha_usuario(senha);

        ConexaoDAO objConexaoDAO = new ConexaoDAO();
        objConexaoDAO.editarUsuario(objUsuarioDTO);
        dispose();
    }
}
