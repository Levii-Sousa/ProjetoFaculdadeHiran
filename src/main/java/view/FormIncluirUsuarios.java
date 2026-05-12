package view;

import dao.ConexaoDAO;
import model.UsuarioDTO;
import javax.swing.*;
import java.awt.*;

public class FormIncluirUsuarios extends JFrame {
    private JTextField txtNovoUsuario;
    private JPasswordField txtNovaSenha;
    private FormListarUsuarios telaPai;

    public FormIncluirUsuarios(FormListarUsuarios telaPai) {
        this.telaPai = telaPai;
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Novo Registro - Levi Software");
        setSize(380, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);
    }

    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label e Campo Usuário
        JLabel lblUser = new JLabel("Login:");
        lblUser.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblUser, gbc);

        txtNovoUsuario = new JTextField(15);
        txtNovoUsuario.setBackground(new Color(30, 0, 50));
        txtNovoUsuario.setForeground(Color.WHITE);
        txtNovoUsuario.setCaretColor(Color.WHITE);
        txtNovoUsuario.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        add(txtNovoUsuario, gbc);

        // Label e Campo Senha
        JLabel lblPass = new JLabel("Senha:");
        lblPass.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblPass, gbc);

        txtNovaSenha = new JPasswordField(15);
        txtNovaSenha.setBackground(new Color(30, 0, 50));
        txtNovaSenha.setForeground(Color.WHITE);
        txtNovaSenha.setCaretColor(Color.WHITE);
        txtNovaSenha.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        add(txtNovaSenha, gbc);

        // Botão Salvar
        JButton btnSalvar = new JButton("Confirmar Cadastro");
        btnSalvar.setBackground(new Color(150, 0, 255));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 13));

        btnSalvar.addActionListener(e -> executarSalvamento());

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnSalvar, gbc);
    }

    private void executarSalvamento() {
        String user = txtNovoUsuario.getText().trim();
        String pass = new String(txtNovaSenha.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Atenção: Informe todos os dados.");
            return;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNome_usuario(user);
        dto.setSenha_usuario(pass);

        ConexaoDAO dao = new ConexaoDAO();
        dao.cadastrarUsuario(dto);

        // Refresh na tabela e fecha
        if (telaPai != null) {
            telaPai.carregarUsuarios();
        }

        dispose();
    }
}
