package view;

import dao.ConexaoDAO;
import model.UsuarioDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormListarUsuarios extends JFrame {

    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;

    public FormListarUsuarios() {
        configurarJanela();
        inicializarComponentes();
        carregarUsuarios();
    }

    private void configurarJanela() {
        setTitle("Consulta de Usuários - Levi Software");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));

        // Tabela com seu tema Roxo
        String[] colunas = {"ID", "Usuário", "Senha"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setBackground(new Color(30, 0, 50)); // Seu fundo roxo escuro
        tabelaUsuarios.setForeground(Color.WHITE);
        tabelaUsuarios.setGridColor(new Color(150, 0, 255)); // Seu roxo vibrante
        tabelaUsuarios.setSelectionBackground(new Color(150, 0, 255));
        tabelaUsuarios.setRowHeight(25);

        // Cabeçalho da Tabela
        tabelaUsuarios.getTableHeader().setBackground(new Color(150, 0, 255));
        tabelaUsuarios.getTableHeader().setForeground(Color.WHITE);
        tabelaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.getViewport().setBackground(new Color(30, 0, 50));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior
        JPanel painelRodape = new JPanel();
        painelRodape.setBackground(Color.BLACK);

        JButton btnNovo = new JButton("Adicionar Usuário");
        btnNovo.setBackground(new Color(150, 0, 255));
        btnNovo.setForeground(Color.WHITE);
        btnNovo.setFocusPainted(false);
        btnNovo.setFont(new Font("Arial", Font.BOLD, 13));

        btnNovo.addActionListener(e -> {
            new FormIncluirUsuarios(this).setVisible(true);
        });

        painelRodape.add(btnNovo);
        add(painelRodape, BorderLayout.SOUTH);
    }

    public void carregarUsuarios() {
        modeloTabela.setRowCount(0);
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
}
