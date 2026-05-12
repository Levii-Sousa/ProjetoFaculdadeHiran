package view;

import dao.ConexaoDAO;
import model.UsuarioDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormUsuarios extends JFrame {

    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    private JTextField txtNovoUsuario;
    private JPasswordField txtNovaSenha;
    private JButton btnIncluir;
    private JButton btnAtualizar;

    public FormUsuarios() {
        configurarJanela();
        inicializarComponentes();
        carregarUsuarios();
    }

    // Configurações básicas da janela
    private void configurarJanela() {
        setTitle("Gerenciar Usuários - Levi Software");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }

    // Criação dos componentes da tela
    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));


        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBackground(Color.BLACK);
        painelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 0, 255)),
                "Incluir Novo Usuário",
                0, 0,
                new Font("Arial", Font.BOLD, 12),
                new Color(150, 0, 255)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo usuário
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setForeground(Color.WHITE);
        painelFormulario.add(lblUsuario, gbc);
        txtNovoUsuario = new JTextField(15);
        txtNovoUsuario.setBackground(new Color(30, 0, 50));
        txtNovoUsuario.setForeground(Color.WHITE);
        txtNovoUsuario.setCaretColor(Color.WHITE);
        txtNovoUsuario.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 1;
        painelFormulario.add(txtNovoUsuario, gbc);

        // Campo senha
        gbc.gridx = 2; gbc.gridy = 0;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        painelFormulario.add(lblSenha, gbc);
        txtNovaSenha = new JPasswordField(15);
        txtNovaSenha.setBackground(new Color(30, 0, 50));
        txtNovaSenha.setForeground(Color.WHITE);
        txtNovaSenha.setCaretColor(Color.WHITE);
        txtNovaSenha.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        gbc.gridx = 3;
        painelFormulario.add(txtNovaSenha, gbc);

        // Botão incluir
        btnIncluir = new JButton("Incluir");
        btnIncluir.setBackground(new Color(150, 0, 255));
        btnIncluir.setForeground(Color.WHITE);
        btnIncluir.setFocusPainted(false);
        btnIncluir.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4;
        painelFormulario.add(btnIncluir, gbc);
        btnIncluir.addActionListener(e -> incluirUsuario());

        add(painelFormulario, BorderLayout.NORTH);

        // --- TABELA DE USUÁRIOS (centro) ---
        String[] colunas = {"ID", "Usuário", "Senha"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            // Impede edição direta na tabela
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setBackground(new Color(30, 0, 50));
        tabelaUsuarios.setForeground(Color.WHITE);
        tabelaUsuarios.setGridColor(new Color(150, 0, 255));
        tabelaUsuarios.setSelectionBackground(new Color(150, 0, 255));
        tabelaUsuarios.setSelectionForeground(Color.WHITE);
        tabelaUsuarios.setRowHeight(25);
        tabelaUsuarios.getTableHeader().setBackground(new Color(150, 0, 255));
        tabelaUsuarios.getTableHeader().setForeground(Color.WHITE);
        tabelaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.getViewport().setBackground(new Color(30, 0, 50));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        add(scrollPane, BorderLayout.CENTER);

        // --- BOTÃO ATUALIZAR (rodapé) ---
        btnAtualizar = new JButton("Atualizar Lista");
        btnAtualizar.setBackground(new Color(30, 0, 50));
        btnAtualizar.setForeground(new Color(150, 0, 255));
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAtualizar.addActionListener(e -> carregarUsuarios());

        JPanel painelRodape = new JPanel();
        painelRodape.setBackground(Color.BLACK);
        painelRodape.add(btnAtualizar);
        add(painelRodape, BorderLayout.SOUTH);
    }

    // Carrega todos os usuários do banco e preenche a tabela
    private void carregarUsuarios() {
        modeloTabela.setRowCount(0); // Limpa a tabela antes de recarregar
        ConexaoDAO objConexaoDAO = new ConexaoDAO();
        List<UsuarioDTO> lista = objConexaoDAO.listarUsuarios();
        for (UsuarioDTO usuario : lista) {
            modeloTabela.addRow(new Object[]{
                    usuario.getId_usuario(),
                    usuario.getNome_usuario(),
                    usuario.getSenha_usuario()
            });
        }
    }

    // Inclui um novo usuário com validações
    private void incluirUsuario() {
        String usuario = txtNovoUsuario.getText().trim();
        String senha   = new String(txtNovaSenha.getPassword()).trim();

        // 1. Campos vazios
        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        // 2. Usuário com espaços em branco
        if (usuario.contains(" ")) {
            JOptionPane.showMessageDialog(this, "O usuário não pode conter espaços.");
            return;
        }

        // 3. Tamanho mínimo do usuário
        if (usuario.length() < 3) {
            JOptionPane.showMessageDialog(this, "Usuário deve ter pelo menos 3 caracteres.");
            return;
        }

        // 4. Tamanho máximo
        if (usuario.length() > 50 || senha.length() > 50) {
            JOptionPane.showMessageDialog(this, "Usuário ou senha muito longos (máx. 50 caracteres).");
            return;
        }

        // 5. Senha forte: mínimo 6 caracteres, 1 letra e 1 número
        if (!senhaValida(senha)) {
            JOptionPane.showMessageDialog(this,
                    "Senha fraca! Use pelo menos 6 caracteres,\num número e uma letra.");
            return;
        }

        // 6. Caracteres especiais perigosos no usuário (SQL injection básico)
        if (usuario.matches(".*[';\"\\-].*")) {
            JOptionPane.showMessageDialog(this, "O usuário contém caracteres inválidos.");
            return;
        }

        UsuarioDTO objUsuarioDTO = new UsuarioDTO();
        objUsuarioDTO.setNome_usuario(usuario);
        objUsuarioDTO.setSenha_usuario(senha);

        ConexaoDAO objConexaoDAO = new ConexaoDAO();
        objConexaoDAO.cadastrarUsuario(objUsuarioDTO);

        txtNovoUsuario.setText("");
        txtNovaSenha.setText("");
        carregarUsuarios();
    }
    private boolean senhaValida(String senha) {
        if (senha.length() < 6) return false;
        boolean temLetra  = senha.matches(".*[a-zA-Z].*");
        boolean temNumero = senha.matches(".*[0-9].*");
        return temLetra && temNumero;
    }
}