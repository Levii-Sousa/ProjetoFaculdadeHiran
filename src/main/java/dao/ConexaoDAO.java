package dao;

import model.UsuarioDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

// Classe responsável pela conexão com o banco e operações de usuário
public class ConexaoDAO {

    // Dados de conexão com o banco de dados
    private static final String URL  = "jdbc:mysql://bd_savir.mysql.dbaas.com.br:3306/bd_savir?useSSL=false&serverTimezone=America/Fortaleza";
    private static final String USER = "bd_savir";
    private static final String PASS = "B@nc0D@d0s";

    // Método privado que retorna uma conexão ativa com o banco
    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Método para testar se a conexão está funcionando
    public static boolean testarConexao() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            return false;
        }
    }

    // Cadastra um novo usuário no banco de dados
    public void cadastrarUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "INSERT INTO tb_usuarios (login, senha) VALUES (?, ?)";
        try {
            Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuarioDTO.getNome_usuario());
            pstm.setString(2, objUsuarioDTO.getSenha_usuario());
            pstm.execute();
            pstm.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro.getMessage());
        }
    }

    // Autentica o usuário — retorna true se login e senha existirem no banco
    public boolean autenticarUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "SELECT * FROM tb_usuarios WHERE login = ? AND senha = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuarioDTO.getNome_usuario());
            pstm.setString(2, objUsuarioDTO.getSenha_usuario());
            ResultSet rs = pstm.executeQuery();
            boolean autenticado = rs.next();
            rs.close();
            pstm.close();
            conn.close();
            return autenticado;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro na autenticação: " + erro.getMessage());
            return false;
        }
    }
}