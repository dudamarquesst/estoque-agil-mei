package com.estoque.model;

public class Produto {

    private int id;
    private String nome;
    private int quantidadeAtual;
    private int quantidadeMinima;

    // Construtor vazio
    public Produto() {
    }

    // Construtor completo para facilitar a criação do produto
    public Produto(int id, String nome, int quantidadeAtual, int quantidadeMinima) {
        this.id = id;
        this.nome = nome;
        this.quantidadeAtual = quantidadeAtual;
        this.quantidadeMinima = quantidadeMinima;
    }

    // REGRA DE NEGÓCIO: Verifica se o produto precisa de reposição
    public boolean isEstoqueBaixo() {
        return this.quantidadeAtual <= this.quantidadeMinima;
    }

    // --- Getters e Setters  ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }
}
