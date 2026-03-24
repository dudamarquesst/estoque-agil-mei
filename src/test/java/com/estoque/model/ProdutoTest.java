package com.estoque.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    public void deveDetectarEstoqueBaixo() {
        // Cenário: Quantidade Atual é menor que o Mínimo
        Produto p = new Produto(1, "Bolo de Pote", 5, 10);

        // Verificação: O metodo deve retornar TRUE
        assertTrue(p.isEstoqueBaixo(), "O alerta deveria estar ativado!");
    }

    @Test
    public void naoDeveDetectarEstoqueBaixoQuandoOk() {
        // Cenário: Qtd Atual é maior que o Mínimo
        Produto p = new Produto(2, "Bolo de Pote", 15, 10);

        // Verificação: O metodo deve retornar FALSE
        assertFalse(p.isEstoqueBaixo(), "O alerta NÃO deveria estar ativado!");
    }

    @Test
    public void deveRetornarVerdadeiroQuandoEstoqueIgualAoMinimo() {
        // Caso Limite: Qtd Atual exatamente igual ao mínimo
        Produto p = new Produto(3, "Papel", 10, 10);
        assertTrue(p.isEstoqueBaixo(), "No limite também deve alertar!");
    }

    @Test
    public void naoDeveAceitarQuantidadeNegativa() {
        // Caso Inválido: Testando se a lógica de criação faz sentido
        Produto p = new Produto(4, "Erro", -5, 10);
        assertTrue(p.getQuantidadeAtual() < 0, "O sistema permitiu quantidade negativa (verificar validação)");
    }
}
