package com.estoque.ui;

import com.estoque.model.Produto;
import com.estoque.repository.ProdutoDAO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

public class EstoqueGUI extends JFrame {
    private ProdutoDAO dao;
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public EstoqueGUI() {
        dao = new ProdutoDAO();
        setTitle("Estoque Ágil MEI - Controle Profissional");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout());

        // --- Parte Superior: Tabela ---
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Qtd Atual", "Qtd Mínima", "Status"}, 0);
        tabela = new JTable(modeloTabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // --- Parte Inferior: Botões ---
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Novo Produto");
        JButton btnAtualizar = new JButton("Atualizar Lista");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ação do botão Atualizar
        btnAtualizar.addActionListener(e -> carregarDados());

        // Ação do botão Adicionar
        btnAdicionar.addActionListener(e -> mostrarFormularioCadastro());

        carregarDados(); // Carrega ao abrir
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0); // Limpa a tabela
        List<Produto> produtos = dao.listarTodos();
        for (Produto p : produtos) {
            String status = p.isEstoqueBaixo() ? "⚠️ REPOR" : "✅ OK";
            modeloTabela.addRow(new Object[]{p.getId(), p.getNome(), p.getQuantidadeAtual(), p.getQuantidadeMinima(), status});
        }
    }

    private void mostrarFormularioCadastro() {
        JTextField nomeField = new JTextField();
        JTextField qtdField = new JTextField();
        JTextField minField = new JTextField();

        Object[] mensagem = {
                "Nome do Produto:", nomeField,
                "Quantidade Atual:", qtdField,
                "Quantidade Mínima:", minField
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Cadastrar Produto", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText();
                int qtd = Integer.parseInt(qtdField.getText());
                int min = Integer.parseInt(minField.getText());

                dao.salvar(new Produto(0, nome, qtd, min));
                carregarDados();
                JOptionPane.showMessageDialog(this, "Produto salvo!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Erro: Digite apenas números nas quantidades.");
            }
        }
    }
}
