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

    private static final String URL = System.getenv("DB_URL") != null 
        ? System.getenv("DB_URL") 
        : "jdbc:postgresql://db.mhfdfvcvigvigpawpxso.supabase.co:5432/postgres";
    private static final String USER = System.getenv("DB_USER") != null 
        ? System.getenv("DB_USER") 
        : "postgres";
    private static final String PASS = System.getenv("DB_PASS") != null 
        ? System.getenv("DB_PASS") 
        : "";

    public ProdutoDAO() {
        criarTabela();
    }

    private void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                "id SERIAL PRIMARY KEY, " +
                "nome VARCHAR(255), " +
                "quantidade_atual INT, " +
                "quantidade_minima INT)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produtos (nome, quantidade_atual, quantidade_minima) VALUES (?, ?, ?)";

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
                p.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                p.setQuantidadeMinima(rs.getInt("quantidade_minima"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}