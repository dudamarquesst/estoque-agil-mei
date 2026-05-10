package com.estoque.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {
    public String[] buscarEndereco(String cep) throws Exception {
        // Limpa o CEP de hífens ou espaços
        String url = "https://viacep.com.br/ws/" + cep.replace("-", "").trim() + "/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.body());

            // Verifica se o CEP é inexistente (a API retorna erro no JSON)
            if (node.has("erro")) {
                throw new RuntimeException("CEP não encontrado.");
            }

            // Retorna (Logradouro, Bairro, Localidade/Cidade)
            return new String[]{
                    node.get("logradouro").asText(),
                    node.get("bairro").asText(),
                    node.get("localidade").asText()
            };
        }
        throw new RuntimeException("Erro ao consultar API.");
    }
}