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
    private JButton btnEditar;
    private JButton btnExcluir;

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

        // Tabela
        String[] colunas = {"ID", "Usuário", "Senha"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setBackground(new Color(30, 0, 50));
        tabelaUsuarios.setForeground(Color.WHITE);
        tabelaUsuarios.setGridColor(new Color(150, 0, 255));
        tabelaUsuarios.setSelectionBackground(new Color(150, 0, 255));
        tabelaUsuarios.setRowHeight(25);
        tabelaUsuarios.getTableHeader().setBackground(new Color(150, 0, 255));
        tabelaUsuarios.getTableHeader().setForeground(Color.WHITE);
        tabelaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Habilita botões ao selecionar linha
        tabelaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            boolean selecionado = tabelaUsuarios.getSelectedRow() >= 0;
            btnEditar.setEnabled(selecionado);
            btnExcluir.setEnabled(selecionado);
        });

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.getViewport().setBackground(new Color(30, 0, 50));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 255)));
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior com botões
        JPanel painelRodape = new JPanel();
        painelRodape.setBackground(Color.BLACK);

        JButton btnNovo = new JButton("Adicionar Usuário");
        btnNovo.setBackground(new Color(150, 0, 255));
        btnNovo.setForeground(Color.WHITE);
        btnNovo.setFocusPainted(false);
        btnNovo.setFont(new Font("Arial", Font.BOLD, 13));
        btnNovo.addActionListener(e -> new FormIncluirUsuarios(this).setVisible(true));

        btnEditar = new JButton("Editar Usuário");
        btnEditar.setBackground(new Color(80, 0, 150));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);
        btnEditar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(e -> abrirEditar());

        btnExcluir = new JButton("Excluir Usuário");
        btnExcluir.setBackground(new Color(180, 0, 0));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFocusPainted(false);
        btnExcluir.setFont(new Font("Arial", Font.BOLD, 13));
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(e -> abrirExcluir());

        painelRodape.add(btnNovo);
        painelRodape.add(btnEditar);
        painelRodape.add(btnExcluir);
        add(painelRodape, BorderLayout.SOUTH);
    }

    // Abre o FormEditarUsuario com os dados da linha selecionada
    private void abrirEditar() {
        int linha = tabelaUsuarios.getSelectedRow();
        if (linha < 0) return;

        int id       = (int)    modeloTabela.getValueAt(linha, 0);
        String login = (String) modeloTabela.getValueAt(linha, 1);
        String senha = (String) modeloTabela.getValueAt(linha, 2);

        FormEditarUsuarios formEditar = new FormEditarUsuarios(id, login, senha);
        formEditar.setVisible(true);
        formEditar.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                carregarUsuarios();
            }
        });
    }

    // Abre o FormExcluirUsuario com os dados da linha selecionada
    private void abrirExcluir() {
        int linha = tabelaUsuarios.getSelectedRow();
        if (linha < 0) return;

        int id       = (int)    modeloTabela.getValueAt(linha, 0);
        String login = (String) modeloTabela.getValueAt(linha, 1);

        FormExcluirUsuarios formExcluir = new FormExcluirUsuarios(id, login);
        formExcluir.setVisible(true);
        formExcluir.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                carregarUsuarios();
            }
        });
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
