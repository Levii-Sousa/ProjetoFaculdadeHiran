package view;

import javax.swing.*;
import java.awt.*;

// Tela principal do sistema — exibida após login bem-sucedido
public class FormPrincipal extends JFrame {

    public FormPrincipal() {
        configurarJanela();
        inicializarComponentes();
    }

    // Configurações básicas da janela principal
    private void configurarJanela() {
        setTitle("Sistema - Levi Software");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
    }

    // Criação dos componentes da tela principal
    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Mensagem de boas-vindas centralizada
        JLabel lblBemVindo = new JLabel("Bem-vindo ao Sistema!", SwingConstants.CENTER);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 22));
        lblBemVindo.setForeground(new Color(150, 0, 255));
        add(lblBemVindo, BorderLayout.CENTER);

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(30, 0, 50));

        JMenu menuUsuarios = new JMenu("Usuários");
        menuUsuarios.setForeground(Color.WHITE);
        JMenuItem itemGerenciar = new JMenuItem("Gerenciar Usuários");
        itemGerenciar.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Em breve: Sprint 2!")
        );
        menuUsuarios.add(itemGerenciar);
        menuBar.add(menuUsuarios);

        JMenu menuSistema = new JMenu("Sistema");
        menuSistema.setForeground(Color.WHITE);
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> {
            dispose();
            new FormLogin().setVisible(true);
        });
        menuSistema.add(itemSair);
        menuBar.add(menuSistema);

        setJMenuBar(menuBar);
    }
}