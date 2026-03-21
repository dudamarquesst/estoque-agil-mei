package com.estoque.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    public void deveDetectarEstoqueBaixo() {
        // Cenário: Quantidade (5) é menor que o Mínimo (10)
        Produto p = new Produto(1, "Bolo de Pote", 5, 10);

        // Verificação: O método deve retornar TRUE
        assertTrue(p.isEstoqueBaixo(), "O alerta deveria estar ativado!");
    }

    @Test
    public void naoDeveDetectarEstoqueBaixoQuandoOk() {
        // Cenário: Quantidade (15) é maior que o Mínimo (10)
        Produto p = new Produto(2, "Bolo de Pote", 15, 10);

        // Verificação: O método deve retornar FALSE
        assertFalse(p.isEstoqueBaixo(), "O alerta NÃO deveria estar ativado!");
    }
}