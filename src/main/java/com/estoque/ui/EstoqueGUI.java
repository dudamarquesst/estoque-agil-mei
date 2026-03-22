package com.estoque.ui;

import com.estoque.model.Produto;
import com.estoque.repository.ProdutoDAO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

public class EstoqueGUI extends JFrame {
    private ProdutoDAO dao;
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public EstoqueGUI() {
        dao = new ProdutoDAO();
        configurarJanela();
        inicializarComponentes();
        carregarDados();
    }

    private void configurarJanela() {
        setTitle("Estoque Ágil MEI");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Controle de Insumos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Configuração da Tabela
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Produto", "Qtd Atual", "Mínimo", "Status"}, 0);
        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(35);

        // --- MELHORIA: Ativando as linhas de separação ---
        tabela.setShowGrid(true);
        tabela.setGridColor(new Color(230, 230, 230));

        // --- MELHORIA: Centralizando nomes e números ---
        DefaultTableCellRenderer centralizador = new DefaultTableCellRenderer();
        centralizador.setHorizontalAlignment(JLabel.CENTER);

        // Centralizador em todas as colunas
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centralizador);
        }

        // Renderizador específico para a coluna de STATUS (Centralizado + Cor)
        tabela.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER); // Garante a centralização

                if ("⚠️ REPOR".equals(value)) {
                    c.setForeground(Color.RED);
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                } else {
                    c.setForeground(new Color(0, 150, 0));
                    c.setFont(c.getFont().deriveFont(Font.PLAIN));
                }
                return c;
            }
        });

        painelPrincipal.add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Botão Adicionar
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdicionar = new JButton("Adicionar Novo Produto");
        btnAdicionar.setPreferredSize(new Dimension(200, 40));
        btnAdicionar.setBackground(new Color(63, 81, 181));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        painelBotoes.add(btnAdicionar);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> mostrarFormularioCadastro());

        add(painelPrincipal);
    }

    private void carregarDados() {
        modeloTabela.setRowCount(0);
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

        Object[] mensagem = { "Nome do Insumo:", nomeField, "Quantidade em Estoque:", qtdField, "Quantidade Mínima:", minField };

        int opcao = JOptionPane.showConfirmDialog(this, mensagem, "Cadastrar Produto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcao == JOptionPane.OK_OPTION) {
            try {
                if(nomeField.getText().isEmpty()) throw new Exception();

                dao.salvar(new Produto(0, nomeField.getText(), Integer.parseInt(qtdField.getText()), Integer.parseInt(minField.getText())));
                carregarDados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: Preencha todos os campos corretamente.");
            }
        }
    }
}
