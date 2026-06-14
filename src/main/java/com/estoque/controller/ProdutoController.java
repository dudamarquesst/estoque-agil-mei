package com.estoque.controller;

import com.estoque.model.Produto;
import com.estoque.repository.ProdutoDAO;
import com.estoque.service.ViaCepService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProdutoController {

    private final ProdutoDAO dao = new ProdutoDAO();
    private final ViaCepService viaCepService = new ViaCepService();

    @GetMapping("/")
    public String index(Model model) {
        List<Produto> produtos = dao.listarTodos();
        model.addAttribute("produtos", produtos);
        return "index";
    }

    @PostMapping("/adicionar")
    public String adicionar(
            @RequestParam String nome,
            @RequestParam int quantidadeAtual,
            @RequestParam int quantidadeMinima) {
        Produto p = new Produto(0, nome, quantidadeAtual, quantidadeMinima);
        dao.salvar(p);
        return "redirect:/";
    }

    @GetMapping("/buscar-cep")
    public String buscarCep(@RequestParam String cep, Model model) {
        try {
            String[] endereco = viaCepService.buscarEndereco(cep);
            model.addAttribute("endereco", endereco);
        } catch (Exception e) {
            model.addAttribute("erro", "CEP não encontrado.");
        }
        List<Produto> produtos = dao.listarTodos();
        model.addAttribute("produtos", produtos);
        return "index";
    }
}
