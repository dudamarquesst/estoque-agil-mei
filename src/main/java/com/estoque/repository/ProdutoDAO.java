package com.estoque.repository;

import com.estoque.model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Caminho onde o banco de dados será salvo no seu computador
    private static final String URL = "jdbc:h2:./data/estoque_db";
    private static final String USER = "sa";
    private static final String PASS = "";

    public ProdutoDAO() {
        criarTabela();
    }

    // Cria a tabela automaticamente se ela não existir
    private void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(255), " +
                "quantidadeAtual INT, " +
                "quantidadeMinima INT)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Salva um produto no banco
    public void salvar(Produto produto) {
        String sql = "INSERT INTO produtos (nome, quantidadeAtual, quantidadeMinima) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getQuantidadeAtual());
            pstmt.setInt(3, produto.getQuantidadeMinima());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista todos os produtos
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQuantidadeAtual(rs.getInt("quantidadeAtual"));
                p.setQuantidadeMinima(rs.getInt("quantidadeMinima"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
