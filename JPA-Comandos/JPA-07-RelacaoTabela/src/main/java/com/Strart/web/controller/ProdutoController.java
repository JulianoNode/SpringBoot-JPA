package com.Strart.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Strart.DTOs.ProdutoDTO;
import com.Strart.repository.CategoriaRepository;
import com.Strart.repository.FornecedorRepository;
import com.Strart.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute("produtoDTO", new ProdutoDTO());
        model.addAttribute("fornecedores", fornecedorRepository.findAll());
        model.addAttribute("categorias", categoriaRepository.findAll());

        return "produtos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute ProdutoDTO produtoDTO) {
        produtoService.salvar(produtoDTO);
        return "redirect:/produtos";
    }
    
    @GetMapping
    public String listar(Model model) {
    	model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/lista";
    }
}
