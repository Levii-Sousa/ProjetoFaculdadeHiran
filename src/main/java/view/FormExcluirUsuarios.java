package view;

import dao.ConexaoDAO;
import javax.swing.*;
import java.awt.*;

// Tela de confirmação de exclusão de usuário
public class FormExcluirUsuarios extends JFrame {

    private JButton btnConfirmar;
    private JButton btnCancelar;
    private int idUsuario;
    private String nomeUsuario;

    public FormExcluirUsuarios(int id, String nome) {
        this.idUsuario   = id;
        this.nomeUsuario = nome;
        configurarJanela();
        inicializarComponentes();
    }

    // Configurações básicas da janela
    private void configurarJanela() {
        setTitle("Excluir Usuário - Levi Software");
        setSize(380, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }

    // Criação dos componentes da tela
    private void inicializarComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("EXCLUIR USUÁRIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(180, 0, 0));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        // Mensagem de confirmação
        JLabel lblMensagem = new JLabel(
                "<html><center>Deseja excluir o usuário<br><b>" + nomeUsuario + "</b>?</center></html>",
                SwingConstants.CENTER
        );
        lblMensagem.setForeground(Color.WHITE);
        lblMensagem.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridy = 1;
        add(lblMensagem, gbc);

        // Botão confirmar
        btnConfirmar = new JButton("Confirmar Exclusão");
        btnConfirmar.setBackground(new Color(180, 0, 0));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        add(btnConfirmar, gbc);
        btnConfirmar.addActionListener(e -> confirmarExclusao());

        // Botão cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(50, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 1;
        add(btnCancelar, gbc);
        btnCancelar.addActionListener(e -> dispose());
    }

    // Confirma e executa a exclusão
    private void confirmarExclusao() {
        ConexaoDAO objConexaoDAO = new ConexaoDAO();
        objConexaoDAO.excluirUsuario(idUsuario);
        dispose();
    }
}
