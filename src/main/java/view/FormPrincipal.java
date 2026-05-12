package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormPrincipal extends JFrame {

    public FormPrincipal() {
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Dashboard - Levi Software");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(10, 10, 10));
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(25, 25, 25));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(150, 0, 255)));

        // Menu Usuários
        JMenu menuUsuarios = new JMenu("Usuários");
        menuUsuarios.setForeground(Color.WHITE);
        menuUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JMenuItem itemGerenciar = new JMenuItem("Gerenciar Usuários");

        // --- ALTERAÇÃO AQUI: Apontando para a nova tela de listagem ---
        itemGerenciar.addActionListener(e -> {
            FormListarUsuarios telaLista = new FormListarUsuarios();
            telaLista.setVisible(true);
        });

        menuUsuarios.add(itemGerenciar);
        menuBar.add(menuUsuarios);

        // Menu Configurações
        JMenu menuConfig = new JMenu("Configurações");
        menuConfig.setForeground(Color.WHITE);
        menuConfig.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JMenuItem itemPreferencias = new JMenuItem("Preferências");
        itemPreferencias.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Em breve!")
        );
        menuConfig.add(itemPreferencias);
        menuBar.add(menuConfig);

        menuBar.add(Box.createHorizontalGlue());

        // Menu Sair
        JMenu menuSair = new JMenu("Sair");
        menuSair.setForeground(Color.WHITE);
        menuSair.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JMenuItem itemLogoff = new JMenuItem("Sair do Sistema");
        itemLogoff.addActionListener(e -> {
            dispose();
            new FormLogin().setVisible(true);
        });
        menuSair.add(itemLogoff);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        // Painel central
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setOpaque(false);

        JLabel lblBoasVindas = new JLabel("LEVI SOFTWARE");
        lblBoasVindas.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblBoasVindas.setForeground(new Color(150, 0, 255));

        JLabel lblSubtitulo = new JLabel("Painel de Controle v1.0");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        lblSubtitulo.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        painelCentral.add(lblBoasVindas, gbc);
        gbc.gridy = 1;
        painelCentral.add(lblSubtitulo, gbc);

        add(painelCentral, BorderLayout.CENTER);

        // Rodapé
        JPanel barraStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        barraStatus.setBackground(new Color(20, 20, 20));
        barraStatus.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel lblStatus = new JLabel("Desenvolvido por Levi Sousa | Status: Sistema Online");
        lblStatus.setForeground(new Color(120, 120, 120));
        lblStatus.setFont(new Font("Monospaced", Font.PLAIN, 12));
        barraStatus.add(lblStatus);
        add(barraStatus, BorderLayout.SOUTH);
    }
}
