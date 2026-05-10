package com.estoque.model;

import com.estoque.service.ViaCepService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ViaCepServiceTest {
    @Test
    void deveRetornarEnderecoValido() throws Exception {
        ViaCepService service = new ViaCepService();
        String[] resultado = service.buscarEndereco("01001000"); // CEP Praça da Sé

        assertEquals("Praça da Sé", resultado[0]);
        assertNotNull(resultado[2]); // Cidade
    }
}
